package com.tekcapsule.subscription.domain.service;

import com.tekcapsule.subscription.domain.command.InitiateTransactionCommand;
import com.tekcapsule.subscription.domain.command.UpdateTransactionCommand;
import com.tekcapsule.subscription.domain.model.Subscription;
import com.tekcapsule.subscription.domain.model.Transaction;

import java.util.List;

public interface TransactionService {

    void initiateTransaction(InitiateTransactionCommand initiateTransactionCommand);
    void updateTransaction(UpdateTransactionCommand updateTransactionCommand);
    Transaction getTransaction( String transactionId);
    List<Transaction> getAllTransactions();
}
