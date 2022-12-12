package com.multithread.service;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.concurrent.CompletableFuture;

public interface RestService {
    public JsonNode getUser();
    public JsonNode getClient();

    public <T> CompletableFuture<T> getUserAsync();
    public CompletableFuture<JsonNode> getClientAsync();
    public CompletableFuture<Void> saveUserAsync();
}
