package com.grpc.service;


import com.grpc.service.temporal.activity.AccountActivity;
import com.grpc.service.temporal.workflow.MoneyTransferWorkflow;
import com.grpc.service.temporal.workflow.MoneyTransferWorkflowImpl;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GateWayApplication {

    public static void main(String[] args) {
        // SpringApplication.run(GateWayApplication.class);

//        ConfigurableApplicationContext applicationContext = SpringApplication.run(GateWayApplication.class, args);
//
//        AccountActivity accountActivity = applicationContext.getBean(AccountActivityImpl.class);
//
//        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
//
//        WorkflowClient client = WorkflowClient.newInstance(service);
//
//        WorkerFactory factory = WorkerFactory.newInstance(client);
//
//        Worker worker = factory.newWorker(MoneyTransferWorkflow.QUEUE_NAME);
//
//        worker.registerWorkflowImplementationTypes(MoneyTransferWorkflowImpl.class);
//        worker.registerActivitiesImplementations(accountActivity);
//
//        factory.start();
        ConfigurableApplicationContext appContext = SpringApplication.run(GateWayApplication.class);
        WorkerFactory factory = appContext.getBean(WorkerFactory.class);
        AccountActivity signUpActivity = appContext.getBean(AccountActivity.class);
        Worker worker = factory.newWorker(MoneyTransferWorkflow.QUEUE_NAME);
        worker.registerWorkflowImplementationTypes(MoneyTransferWorkflowImpl.class);
        worker.registerActivitiesImplementations(signUpActivity);
        factory.start();
    }

}
