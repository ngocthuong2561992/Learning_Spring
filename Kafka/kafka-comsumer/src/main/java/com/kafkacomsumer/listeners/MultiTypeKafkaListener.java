package com.kafkacomsumer.listeners;

import org.springframework.stereotype.Component;

@Component
//@KafkaListener(id = "consumer_group_multiType", topics = "${topic.multiType}")
public class MultiTypeKafkaListener {

//    @KafkaHandler
//    public void handleGreeting(Greeting greeting) {
//        System.out.println("Greeting received: " + greeting);
//    }
//
//    @KafkaHandler
//    public void handleF(Farewell farewell) {
//        System.out.println("Farewell received: " + farewell);
//    }

//    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        System.out.println("Unkown type received: " + object);
    }

}
