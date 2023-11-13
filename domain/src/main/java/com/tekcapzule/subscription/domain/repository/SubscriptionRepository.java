package com.tekcapzule.subscription.domain.repository;

import com.tekcapzule.core.domain.CrudRepository;
import com.tekcapzule.subscription.domain.model.Subscription;


public interface SubscriptionRepository extends CrudRepository<Subscription, String> {
    int getSubscriptionsCount();
}
