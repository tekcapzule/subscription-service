package com.tekcapsule.subscription.domain.service;

import com.tekcapsule.subscription.domain.command.UnsubscribeCommand;
import com.tekcapsule.subscription.domain.model.Subscription;
import com.tekcapsule.subscription.domain.repository.SubscriptionRepository;
import com.tekcapsule.subscription.domain.command.SubscribeCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public void subscribe(SubscribeCommand subscribeCommand) {

        log.info(String.format("Entering subscribe service - Email Id :%s", subscribeCommand.getEmailId()));

        Subscription subscription = subscriptionRepository.findBy(subscribeCommand.getEmailId());
        if (subscription != null) {
            subscription.setActive(true);
        }else{
             subscription = Subscription.builder()
                    .emailId(subscribeCommand.getEmailId())
                     .activeSince(subscribeCommand.getExecOn())
                     .channel(subscribeCommand.getChannel().toString())
                    .active(true)
                    .build();
        }
        subscription.setAddedOn(subscribeCommand.getExecOn());
        subscription.setUpdatedOn(subscribeCommand.getExecOn());
        subscription.setAddedBy(subscribeCommand.getExecBy().getUserId());
        subscription.setUpdatedBy(subscribeCommand.getExecBy().getUserId());

        subscriptionRepository.save(subscription);
    }

    @Override
    public void unsubscribe(UnsubscribeCommand unsubscribeCommand) {

        log.info(String.format("Entering unsubscribe service - Email Id:%s", unsubscribeCommand.getEmailId()));

        Subscription subscription = subscriptionRepository.findBy(unsubscribeCommand.getEmailId());
        if (subscription != null) {
            subscription.setActive(false);
            subscription.setUpdatedOn(unsubscribeCommand.getExecOn());
            subscription.setUpdatedBy(unsubscribeCommand.getExecBy().getUserId());
            subscriptionRepository.save(subscription);
        }
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        log.info("Entering findAll subscription service");

        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription getSubscription(String subscriptionId) {
        log.info(String.format("Entering findBy subscription service - Subscription Id:%s",subscriptionId));
        return subscriptionRepository.findBy(emailId);
    }

    @Override
    public int getSubscriptionCount() {
        log.info("Entering getall Subscriptions count service");
        return subscriptionRepository.getAllSubscriptionsCount();
    }

}
