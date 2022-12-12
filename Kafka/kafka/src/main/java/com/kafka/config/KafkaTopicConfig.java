package com.kafka.config;

import java.util.HashMap;
import java.util.Map;

import com.kafka.common.FunctionCommonUtils;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@EnableKafka
@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topicString() {
        return new NewTopic(TopicName.TOPIC_STRING, 2, (short) 2);
    }

    @Bean
    public NewTopic topicGreeting() {
        return new NewTopic(TopicName.TOPIC_GREETING, 1, (short) 1);
    }

    @Bean
    public NewTopic topicMultiType() {
        return new NewTopic(TopicName.TOPIC_MULTI_TYPE, 1, (short) 1);
    }

//    @Bean
//    public NewTopic topic5() {
//        NewTopic newTopic = new NewTopic(longMsgTopicName, 1, (short) 1);
//        Map<String, String> configs = new HashMap<>();
//        configs.put("max.message.bytes", "20971520");
//        newTopic.configs(configs);
//        return newTopic;
//    }
//
}
