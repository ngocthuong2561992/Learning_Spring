package com.springactivemq.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.ObjectMessage;
import java.util.concurrent.TimeUnit;

@Component
public class JMSConsumer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @JmsListener(destination = QueueName.PRINT_NAME)
    public void handleMessage(String message) {
        logger.info("received: " + message);
    }

    @JmsListener(destination = QueueName.HANDLE_BATCH)
    public void handleObj(ObjectMessage objectMessage) {
        try {
            MessageInfo message = (MessageInfo) objectMessage.getObject();
            logger.info("received: " + new ObjectMapper().writeValueAsString(message));
            TimeUnit.SECONDS.sleep(3);
            objectMessage.acknowledge();
        } catch (Exception e) {
            logger.error("\nhandleObj:\n", e);
        }
    }
}
