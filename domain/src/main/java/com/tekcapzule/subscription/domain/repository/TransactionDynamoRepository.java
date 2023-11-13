package com.tekcapzule.subscription.domain.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tekcapzule.subscription.domain.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransactionDynamoRepository implements TransactionRepository {

    private DynamoDBMapper dynamo;

    @Autowired
    public TransactionDynamoRepository(DynamoDBMapper dynamo) {
        this.dynamo = dynamo;
    }


    @Override
    public Transaction findBy(String transactionId) {
        return dynamo.load(Transaction.class, transactionId);
    }

    @Override
    public List<Transaction> findAll() {
        return dynamo.scan(Transaction.class,new DynamoDBScanExpression());
    }

    @Override
    public Transaction save(Transaction transaction) {
        dynamo.save(transaction);
        return transaction;
    }

}
