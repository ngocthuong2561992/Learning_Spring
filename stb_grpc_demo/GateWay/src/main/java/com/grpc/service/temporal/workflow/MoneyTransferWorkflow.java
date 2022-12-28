package com.grpc.service.temporal.workflow;

import com.grpc.service.dto.request.TransferMoneyRequest;
import com.grpc.service.dto.response.TransferMoneyResponse;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface MoneyTransferWorkflow {
    public static final String QUEUE_NAME = "MoneyTransfer";
    @WorkflowMethod
    TransferMoneyResponse transferMoney(TransferMoneyRequest request);

}
