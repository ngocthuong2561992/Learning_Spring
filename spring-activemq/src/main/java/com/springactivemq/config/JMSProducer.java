package com.springactivemq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.jms.ObjectMessage;

@Component
public class JMSProducer {
    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String message) {
        jmsTemplate.send(QueueName.PRINT_NAME, session -> session.createTextMessage(message));
    }

    public void handleObj(MessageInfo message) {
        jmsTemplate.send(QueueName.HANDLE_BATCH, session -> {
            ObjectMessage objectMessage = session.createObjectMessage(message);
            objectMessage.setObject(message);
            return objectMessage;
        });
    }

}
