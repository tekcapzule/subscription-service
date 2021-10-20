package com.tekcapsule.subscription.domain.service;

import com.tekcapsule.subscription.domain.command.SubscribeCommand;
import com.tekcapsule.subscription.domain.command.UnsubscribeCommand;
import com.tekcapsule.subscription.domain.model.Subscription;

import java.util.List;


public interface SubscriptionService {

    Subscription subscribe(SubscribeCommand subscribeCommand);

    void unsubscribe(UnsubscribeCommand unsubscribeCommand);

    List<Subscription> findAllSubscriptions();

    Subscription findBy( String emailId);
}
