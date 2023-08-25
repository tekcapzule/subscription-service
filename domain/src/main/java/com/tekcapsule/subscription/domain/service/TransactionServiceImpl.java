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
        log.info(String.format("Entering subscribe service - Email Id :%s", initiateTransactionCommand.getEmailId()));

        Subscription subscription = transactionRepository.findBy(subscribeCommand.getEmailId());
        if (subscription != null) {
            subscription.setActive(true);
        }else{
            subscription = Subscription.builder()
                    .emailId(subscribeCommand.getEmailId())ß
                    .activeSince(subscribeCommand.getExecOn())
                    .channel(subscribeCommand.getChannel().toString())
                    .active(true)
                    .build();
        }
        subscription.setAddedOn(subscribeCommand.getExecOn());
        subscription.setUpdatedOn(subscribeCommand.getExecOn());
        subscription.setAddedBy(subscribeCommand.getExecBy().getUserId());
        subscription.setUpdatedBy(subscribeCommand.getExecBy().getUserId());

        transactionRepository.save(subscription);
    }

    @Override
    public void updateTransaction(UpdateTransactionCommand updateTransactionCommand) {
        log.info(String.format("Entering unsubscribe service - Email Id:%s", unsubscribeCommand.getEmailId()));

        Subscription subscription = transactionRepository.findBy(unsubscribeCommand.getEmailId());
        if (subscription != null) {
            subscription.setActive(false);
            subscription.setUpdatedOn(unsubscribeCommand.getExecOn());
            subscription.setUpdatedBy(unsubscribeCommand.getExecBy().getUserId());
            transactionRepository.save(subscription);
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
