package com.grpc.service.config;

import com.grpc.service.service.CreditService;
import com.grpc.service.service.DebitService;
import com.grpc.service.temporal.activity.AccountActivityImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.WorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class Temporal {

    private String temporalNamespace = "default";

    @Bean
    public WorkflowServiceStubs workflowServiceStubs() {
        return WorkflowServiceStubs.newLocalServiceStubs();
                //.newInstance(WorkflowServiceStubsOptions.newBuilder().setTarget(temporalServiceAddress).build());
    }

    @Bean
    public WorkflowClient workflowClient(WorkflowServiceStubs workflowServiceStubs) {
        return WorkflowClient.newInstance(workflowServiceStubs,
                WorkflowClientOptions.newBuilder().setNamespace(temporalNamespace).build());
    }

    @Bean
    public WorkerFactory workerFactory(WorkflowClient workflowClient) {
        return WorkerFactory.newInstance(workflowClient);
    }

    @Bean
    public AccountActivityImpl SignUpActivity(DebitService debitService, CreditService creditService) {
        return new AccountActivityImpl(debitService, creditService);
    }
}
