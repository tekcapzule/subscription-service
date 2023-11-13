package com.tekcapzule.subscription.domain.service;

import com.tekcapzule.subscription.domain.command.InitiateTransactionCommand;
import com.tekcapzule.subscription.domain.command.UpdateTransactionCommand;
import com.tekcapzule.subscription.domain.model.Transaction;

import java.util.List;

public interface TransactionService {

    void initiateTransaction(InitiateTransactionCommand initiateTransactionCommand);
    void updateTransaction(UpdateTransactionCommand updateTransactionCommand);
    Transaction getTransaction( String transactionId);
    List<Transaction> getAllTransactions();
}
