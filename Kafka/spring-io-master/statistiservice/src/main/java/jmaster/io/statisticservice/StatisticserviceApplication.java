package jmaster.io.statisticservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.JsonMessageConverter;

@SpringBootApplication
public class StatisticserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatisticserviceApplication.class, args);
	}

	@Bean
	JsonMessageConverter converter() {
		return new JsonMessageConverter();
	}
	
//	@Bean
//	DefaultErrorHandler errorHandler(KafkaOperations<String, Object> template) {
//		return new DefaultErrorHandler(new DeadLetterPublishingRecoverer(template), new FixedBackOff(1000,2));
//	}
	
//	@Bean
//	NewTopic dlt() {
//		return new NewTopic("statistic.DLT", 1, (short) 1);
//	}
}
