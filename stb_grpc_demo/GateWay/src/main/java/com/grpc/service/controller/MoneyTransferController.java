package com.grpc.service.controller;

import com.grpc.service.dto.request.TransferMoneyRequest;
import com.grpc.service.temporal.workflow.MoneyTransferWorkflow;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@Slf4j
public class MoneyTransferController {

    private final WorkflowClient workflowClient;

    @PostMapping("/transfer-money/start")
    public ResponseEntity<?> startTransferMoneyWorkflow(@RequestBody TransferMoneyRequest request) {
        log.info("/transfer-money/start - request : {}", request);
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(MoneyTransferWorkflow.QUEUE_NAME)
                .setWorkflowId("money-transfer-workflow")
                .build();

//        var service = WorkflowServiceStubs.newLocalServiceStubs();
//        var client = WorkflowClient.newInstance(service);
        MoneyTransferWorkflow workflow = workflowClient.newWorkflowStub(MoneyTransferWorkflow.class, options);

        // Asynchronous execution. This process will exit after making this call.
        // WorkflowExecution executionWF = WorkflowClient.start(workflow::transferMoney, request);

        // synchronous execution
        var response = workflow.transferMoney(request);

        return ResponseEntity.ok(response);
    }
}
