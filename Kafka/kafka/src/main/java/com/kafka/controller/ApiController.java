package com.kafka.controller;


import com.kafka.config.TopicName;
import com.kafka.dto.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ApiController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Greeting> greetingKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Object> multiTypeKafkaTemplate;

    @GetMapping(value = "/sendString")
    public <T> T sendString(@RequestParam("title") String title) throws Exception {
        kafkaTemplate.send(TopicName.TOPIC_STRING, title);
        return null;
    }

    @GetMapping(value = "/sendObject")
    public <T> T sendObject() throws Exception {
        greetingKafkaTemplate.send(TopicName.TOPIC_GREETING, new Greeting("Greetings", "World!"));
        return null;
    }

    @GetMapping(value = "/sendMultiType")
    public <T> T sendMultiType() throws Exception {
        multiTypeKafkaTemplate.send(TopicName.TOPIC_MULTI_TYPE, new Greeting("Greetings", "World!"));
        return null;
    }


}
