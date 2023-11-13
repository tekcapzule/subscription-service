package com.tekcapzule.subscription.domain.service;

import com.tekcapzule.subscription.domain.command.SubscribeCommand;
import com.tekcapzule.subscription.domain.command.UnsubscribeCommand;
import com.tekcapzule.subscription.domain.model.Subscription;

import java.util.List;


public interface SubscriptionService {

    void subscribe(SubscribeCommand subscribeCommand);

    void unsubscribe(UnsubscribeCommand unsubscribeCommand);

    List<Subscription> getAllSubscriptions();

    Subscription getSubscription( String subscriptionId);
    int getSubscriptionCount();
}
