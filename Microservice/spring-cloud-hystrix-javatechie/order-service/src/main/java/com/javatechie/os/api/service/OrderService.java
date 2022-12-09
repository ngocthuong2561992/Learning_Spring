package com.javatechie.os.api.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.os.api.common.InventoryResponse;
import com.javatechie.os.api.common.OrderLineItemsDto;
import com.javatechie.os.api.common.OrderRequest;
import com.javatechie.os.api.common.Payment;
import com.javatechie.os.api.common.TransactionRequest;
import com.javatechie.os.api.common.TransactionResponse;
import com.javatechie.os.api.entity.Order;
import com.javatechie.os.api.entity.OrderLineItems;
import com.javatechie.os.api.event.OrderPlacedEvent;
import com.javatechie.os.api.repository.OrderRepository;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.extern.slf4j.Slf4j;


import brave.Span;
import brave.Tracer;

@Service
@RefreshScope
@Slf4j
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	@Lazy
	private RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder = null;
    private final Tracer tracer = null;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate = null ;
    
//    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
	private String ENDPOINT_URL = "http://localhost:9191/payment/doPayment";

	public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {
		String response = "";
		Order order = request.getOrder();
		Payment payment = request.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
		// rest call
		log.info("Order-Service Request : " + new ObjectMapper().writeValueAsString(request));
		Payment paymentResponse = restTemplate.postForObject(ENDPOINT_URL, payment, Payment.class);

		response = paymentResponse.getPaymentStatus().equals("success")
				? "payment processing successful and order placed"
				: "there is a failure in payment api , order added to cart";
		log.info("Order Service getting Response from Payment-Service : "
				+ new ObjectMapper().writeValueAsString(response));
		orderRepository.save(order);
		return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(),
				response);
	}

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public Order findById(Integer id) {
		return orderRepository.findById(id).get();
	}

	public void save(Order req) {
		try {
			boolean exist = orderRepository.existsById(req.getId());
			if (exist) {
				throw new DuplicateKeyException("exist data");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Order req) {
		try {
			Boolean exist = orderRepository.existsById(req.getId());
			if (exist) {
				orderRepository.save(req);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Integer id) {
		try {
			orderRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Boolean existOrder(Integer id) {
		return orderRepository.existsById(id);
	}

	public String placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto)
				.toList();

		order.setOrderLineItemsList(orderLineItems);

		List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();
		Span inventoryServiceLookup = tracer.nextSpan().name("InventoryServiceLookup");

        try (Tracer.SpanInScope isLookup = tracer.withSpanInScope(inventoryServiceLookup.start())) {

			// inventoryServiceLookup.tag("call", "inventory-service");
			// Call Inventory Service, and place order if product is in
			// stock
			InventoryResponse[] inventoryResponsArray = webClientBuilder.build().get()
					.uri("http://inventory-service/inventory/isInStock",
							uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
					.retrieve().bodyToMono(InventoryResponse[].class).block();

			boolean allProductsInStock = Arrays.stream(inventoryResponsArray).allMatch(InventoryResponse::isInStock);

			if (allProductsInStock) {
				orderRepository.save(order);
                kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
                return "Order Placed Successfully";
			} else {
				throw new IllegalArgumentException("Product is not in stock, please try again later");
			}
		} finally {
			inventoryServiceLookup.flush();
		}
	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}
}
