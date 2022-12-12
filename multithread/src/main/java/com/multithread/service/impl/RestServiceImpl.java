package com.multithread.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.multithread.service.RestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class RestServiceImpl implements RestService {
    @Autowired
    @Qualifier("customRestTemplate")
    private RestTemplate restTemplate;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public JsonNode getUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        String url = "http://localhost:9191/swagger2/api/getUser";
        return restTemplate.postForObject(url, request, JsonNode.class);
    }

    @Override
    public JsonNode getClient() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        String url = "http://localhost:9191/swagger2/api/getClient";
        return restTemplate.postForObject(url, request, JsonNode.class);
    }

    @Async
    @Override
    public <T> CompletableFuture<T> getUserAsync() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        String url = "http://localhost:9191/swagger2/api/getUser";
        try {
            JsonNode response = restTemplate.postForObject(url, request, JsonNode.class);
            return (CompletableFuture<T>) CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return (CompletableFuture<T>) CompletableFuture.completedFuture(e.getMessage());
        }
    }

    @Async
    @Override
    public CompletableFuture<JsonNode> getClientAsync() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        String url = "http://localhost:9191/swagger2/api/getClient";
        JsonNode response = restTemplate.postForObject(url, request, JsonNode.class);
        return CompletableFuture.completedFuture(response);
    }

    @Async
    @Override
    public CompletableFuture<Void> saveUserAsync() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        String url = "http://localhost:9191/swagger2/api/saveUser";
        restTemplate.postForObject(url, request, JsonNode.class);
        return null;
    }
}
