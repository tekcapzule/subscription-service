package com.tekcapsule.subscription.domain.service;

import com.tekcapsule.subscription.domain.command.InitiateTransactionCommand;
import com.tekcapsule.subscription.domain.command.UpdateTransactionCommand;
import com.tekcapsule.subscription.domain.model.Subscription;
import com.tekcapsule.subscription.domain.model.Transaction;
import com.tekcapsule.subscription.domain.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    @Override
    public void initiateTransaction(InitiateTransactionCommand initiateTransactionCommand) {
        log.info(String.format("Entering subscribe service - Subscription Id :%s", initiateTransactionCommand.getSubscriptionId()));

        Transaction transaction = transactionRepository.findBy(initiateTransactionCommand.getSubscriptionId());
        if (transaction != null) {
            transaction = Transaction.builder()
                    .transactedOn(initiateTransactionCommand.getTransactedOn())
                    .externalReferenceId(initiateTransactionCommand.getExternalReferenceId())
                    .billingPeriod(initiateTransactionCommand.getBillingPeriod())
                    .invoiceId(initiateTransactionCommand.getInvoiceId())
                    .transactionType(initiateTransactionCommand.getTransactionType())
                    .invoiceId(initiateTransactionCommand.getInvoiceId())
                    .amount(initiateTransactionCommand.getAmount())
                    .comments(initiateTransactionCommand.getComments())
                    .transactionId(initiateTransactionCommand.getSubscriptionId())
                    .paymentMethod(initiateTransactionCommand.getPaymentMethod())
                    .currency(initiateTransactionCommand.getCurrency()).build();
        }
        transaction.setAddedOn(initiateTransactionCommand.getExecOn());
        transaction.setUpdatedOn(initiateTransactionCommand.getExecOn());
        transaction.setAddedBy(initiateTransactionCommand.getExecBy().getUserId());
        transaction.setUpdatedBy(initiateTransactionCommand.getExecBy().getUserId());

        transactionRepository.save(transaction);
    }

    @Override
    public void updateTransaction(UpdateTransactionCommand updateTransactionCommand) {
        log.info(String.format("Entering unsubscribe service - Subscription Id:%s", updateTransactionCommand.getSubscriptionId()));

        Transaction transaction = transactionRepository.findBy(updateTransactionCommand.getSubscriptionId());
        if (transaction != null) {
            transaction.setUpdatedOn(updateTransactionCommand.getExecOn());
            transaction.setUpdatedBy(updateTransactionCommand.getExecBy().getUserId());
            transactionRepository.save(transaction);
        }
    }

    @Override
    public Transaction getTransaction(String transactionId) {
        log.info(String.format("Entering getTransaction service - Transaction Id:%s",transactionId));
        return transactionRepository.findBy(transactionId);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        log.info("Entering getAll transactions service");
        return transactionRepository.findAll();
    }
}
