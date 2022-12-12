package com.kafkacomsumer.listeners;


import com.kafkacomsumer.dto.Greeting;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

public interface KafkaTopicListener {
    void stringTopic(String message);
    void stringTopicBar(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition);
    void greetingTopic(Greeting greeting);
    void multiTypeTopic(Greeting object);
}
