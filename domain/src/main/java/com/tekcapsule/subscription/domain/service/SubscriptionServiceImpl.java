package com.tekcapsule.subscription.domain.service;

import com.tekcapsule.subscription.domain.command.UnsubscribeCommand;
import com.tekcapsule.subscription.domain.model.Subscription;
import com.tekcapsule.subscription.domain.query.SearchItem;
import com.tekcapsule.subscription.domain.query.SearchQuery;
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

        log.info(String.format("Entering create mentor service - Tenant Id:{0}, Name:{1}", subscribeCommand.getTenantId(), subscribeCommand.getName().toString()));

        if (subscribeCommand.get != null) {
            dateOfBirth.setDateOfBirth(String.format("{0}/{1}/{2}", dateOfBirth.getDay(), dateOfBirth.getMonth(), dateOfBirth.getYear()));
        }
        Subscription subscription = Subscription.builder()
                .active(true)
                .activeSince(DateTime.now(DateTimeZone.UTC).toString())
                .build();

        subscription.setAddedOn(subscribeCommand.getExecOn());
        subscription.setUpdatedOn(subscribeCommand.getExecOn());
        subscription.setAddedBy(subscribeCommand.getExecBy().getUserId());

        return mentorRepository.save(subscription);
    }

    @Override
    public void unsubscribe(UnsubscribeCommand unsubscribeCommand) {

        log.info(String.format("Entering disable mentor service - Tenant Id:{0}, User Id:{1}", disableCommand.getTenantId(), disableCommand.getUserId()));

        subscriptionDynamoRepository.disableById(unsubscribeCommand.getTenantId(), unsubscribeCommand.getUserId());
    }

    @Override
    public Subscription getSubscriptionCount(String tenantId, String userId) {

        log.info(String.format("Entering get mentor service - Tenant Id:{0}, User Id:{1}", tenantId, userId));

        return mentorRepository.findBy(tenantId, userId);
    }
}
