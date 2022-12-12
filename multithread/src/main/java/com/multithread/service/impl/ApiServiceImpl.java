package com.multithread.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.multithread.service.ApiService;
import com.multithread.service.RestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.concurrent.*;

@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    private RestService restService;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("taskExecutor")
    private Executor threadPoolExecutor;

    @Override
    public ModelMap getDataAsyncWithThreadPool() throws Exception {
        ModelMap result = new ModelMap();
        CompletableFuture<Void> getUser = CompletableFuture
                .runAsync(() -> {
                    result.put("user", restService.getUser());
                    System.out.println("AAA: " + Thread.currentThread().getName());
                }, threadPoolExecutor)
                .exceptionally(e -> {
                    LOGGER.error(e.getMessage(), e);
                    result.put("user", e.getMessage());
                    return null;
                });
        CompletableFuture<Void> getClient = CompletableFuture
                .runAsync(() -> result.put("client", restService.getClient()), threadPoolExecutor)
                .handle((res, ex) -> {
                    if (null != ex) {
                        LOGGER.error(ex.getMessage(), ex);
                        result.put("error", ex.getMessage());
                    }
                    return res;
                });
        getUser.get(6, TimeUnit.SECONDS);
        getClient.get(6, TimeUnit.SECONDS);
        return result;
    }

    @Override
    public ModelMap getDataAsyncNoThreadPool() throws Exception {
        ModelMap result = new ModelMap();
        CompletableFuture<Void> getUser = CompletableFuture
                .runAsync(() -> {
                    result.put("user", restService.getUser());
                    System.out.println("AAA: " + Thread.currentThread().getName());
                })
                .exceptionally(e -> {
                    LOGGER.error(e.getMessage(), e);
                    result.put("user", e.getMessage());
                    return null;
                });
        CompletableFuture<Void> getClient = CompletableFuture
                .runAsync(() -> result.put("client", restService.getClient()))
                .handle((res, ex) -> {
                    if (null != ex) {
                        LOGGER.error(ex.getMessage(), ex);
                        result.put("error", ex.getMessage());
                    }
                    return res;
                });
        getUser.get(6, TimeUnit.SECONDS);
        getClient.get(6, TimeUnit.SECONDS);
        return result;
    }

    @Override
    public ModelMap getDataAsyncAllOf() throws Exception {
        ModelMap result = new ModelMap();
        CompletableFuture<Void> futureGetUser = CompletableFuture
                .runAsync(() -> result.put("user", restService.getUser()), threadPoolExecutor);
        CompletableFuture<Void> futureGetClient = CompletableFuture
                .runAsync(() -> result.put("client", restService.getClient()), threadPoolExecutor);
        CompletableFuture
            .allOf(futureGetUser, futureGetClient)
            .exceptionally(e -> {
                LOGGER.error(e.getMessage(), e);
                result.put("error", e.getMessage());
                return null;
            })
            .get(6, TimeUnit.SECONDS);
        return result;
    }

    @Override
    public ModelMap getDataAsyncAnnotation() throws Exception {
        ModelMap result = new ModelMap();
        CompletableFuture<Object> user = restService.getUserAsync();
        CompletableFuture<JsonNode> client = restService.getClientAsync();
//        CompletableFuture<Void> saveUser = restService.saveUserAsync();
        CompletableFuture
                .allOf(user, client/*, saveUser*/)
                .exceptionally(e -> {
                    LOGGER.error(e.getMessage(), e);
                    result.put("error", e.getMessage());
                    return null;
                })
                .join();
        result.put("user", user.get(6, TimeUnit.SECONDS));
        result.put("client", client.get(6, TimeUnit.SECONDS));
//        saveUser.get(6, TimeUnit.SECONDS);
        return result;
    }
}
