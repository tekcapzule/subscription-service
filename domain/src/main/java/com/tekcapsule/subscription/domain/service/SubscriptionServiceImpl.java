package com.tekcapsule.subscription.domain.service;

import com.tekcapsule.subscription.domain.command.UnsubscribeCommand;
import com.tekcapsule.subscription.domain.model.Subscription;
import com.tekcapsule.subscription.domain.repository.SubscriptionDynamoRepository;
import com.tekcapsule.subscription.domain.command.SubscribeCommand;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionDynamoRepository subscriptionDynamoRepository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionDynamoRepository subscriptionDynamoRepository) {
        this.subscriptionDynamoRepository = subscriptionDynamoRepository;
    }

    @Override
    public Subscription subscribe(SubscribeCommand subscribeCommand) {

        log.info(String.format("Entering subscribe service - Email Id :{1}", subscribeCommand.getEmailId()));

        Subscription subscription = Subscription.builder()
                .emailId(subscribeCommand.getEmailId())
                .active(true)
                .build();

        subscription.setAddedOn(subscribeCommand.getExecOn());
        subscription.setUpdatedOn(subscribeCommand.getExecOn());
        subscription.setAddedBy(subscribeCommand.getExecBy().getUserId());
        subscription.setUpdatedBy(subscribeCommand.getExecBy().getUserId());

        return subscriptionDynamoRepository.save(subscription);
    }

    @Override
    public void unsubscribe(UnsubscribeCommand unsubscribeCommand) {

        log.info(String.format("Entering unsubscribe service mentor service - Tenant Id:{0}, User Id:{1}", disableCommand.getTenantId(), disableCommand.getUserId()));

        subscriptionDynamoRepository.disableById(unsubscribeCommand.getTenantId(), unsubscribeCommand.getUserId());
    }

    @Override
    public List<Subscription> findAllSubscriptions() {
        return null;
    }

}
