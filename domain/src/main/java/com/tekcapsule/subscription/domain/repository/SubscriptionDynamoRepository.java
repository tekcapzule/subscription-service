package com.tekcapsule.subscription.domain.repository;

import com.tekcapsule.core.domain.CrudRepository;
import com.tekcapsule.subscription.domain.model.Subscription;


public interface SubscriptionDynamoRepository extends CrudRepository<Subscription, String> {
    int getAllSubscriptionsCount();
}
