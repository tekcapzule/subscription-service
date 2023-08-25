package com.tekcapsule.subscription.domain.service;

import com.tekcapsule.subscription.domain.command.SubscribeCommand;
import com.tekcapsule.subscription.domain.command.UnsubscribeCommand;
import com.tekcapsule.subscription.domain.model.Subscription;

import java.util.List;


public interface SubscriptionService {

    void subscribe(SubscribeCommand subscribeCommand);

    void unsubscribe(UnsubscribeCommand unsubscribeCommand);

    List<Subscription> getAllSubscriptions();

    Subscription getSubscription( String subscriptionId);
    int getSubscriptionCount();
}
