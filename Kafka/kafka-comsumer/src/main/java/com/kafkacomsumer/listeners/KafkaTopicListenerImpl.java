package com.kafkacomsumer.listeners;

import com.kafkacomsumer.config.ConsumerGroupId;
import com.kafkacomsumer.config.TopicName;
import com.kafkacomsumer.dto.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaTopicListenerImpl implements KafkaTopicListener {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    @KafkaListener(topics = TopicName.TOPIC_STRING, containerFactory = ConsumerGroupId.GROUP_STRING_1)
    public void stringTopic(String message) {
        System.out.println("Received Message 1: " + message);
    }

    @Override
    @KafkaListener(topics = TopicName.TOPIC_STRING, containerFactory = ConsumerGroupId.GROUP_STRING_1)
    public void stringTopicBar(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        System.out.println("Received Message 2: " + message + "; from partition: " + partition);
    }

//    @RetryableTopic(
//            attempts = "2",
//            backoff = @Backoff(delay = 300000, multiplier = 10.0),
//            autoCreateTopics = "false",
//            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE
//    )
    @Override
    @KafkaListener(topics = TopicName.TOPIC_GREETING, containerFactory = ConsumerGroupId.GROUP_GREETING)
    public void greetingTopic(Greeting greeting) {
        System.out.println("Received greeting message: " + greeting);
//        this.greetingLatch.countDown();
    }

    @Override
    @KafkaListener(topics = TopicName.TOPIC_MULTI_TYPE, containerFactory = ConsumerGroupId.GROUP_MULTITYPE)
    public void multiTypeTopic(Greeting object) {
        System.out.println("Received multiType message: " + object);
    }

}
