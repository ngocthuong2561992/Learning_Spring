package com.springactivemq.controller;

import com.springactivemq.config.JMSProducer;
import com.springactivemq.config.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ApiController {
    @Autowired
    private JMSProducer jmsProducer;

    @PostMapping(value = "/sendMessage")
    public void sendMessage() {
        jmsProducer.sendMessage("jms message!");
    }

    @PostMapping(value = "/handleObj")
    public void handleObj() {
        MessageInfo message = MessageInfo.builder()
                .batchCode(2)
                .classCode("1121245")
                .clientCode("1001001")
                .clientMemCode(23)
                .build();
        jmsProducer.handleObj(message);
    }
}
