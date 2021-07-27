package com.tekcapsule.subscription.domain.repository;

import com.tekcapsule.core.domain.CrudRepository;
import com.tekcapsule.subscription.domain.query.SearchItem;
import com.tekcapsule.subscription.domain.model.Subscription;

import java.util.List;

public interface SubscriptionDynamoRepository extends CrudRepository<Subscription, String> {

    void disableById( String id);
    List<SearchItem> search();
}
