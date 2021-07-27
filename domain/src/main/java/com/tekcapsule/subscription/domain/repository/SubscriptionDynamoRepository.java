package com.tekcapsule.subscription.domain.repository;

import com.tekcapsule.subscription.domain.query.SearchItem;
import in.devstream.core.domain.CrudRepository;
import com.tekcapsule.subscription.domain.model.Subscription;

import java.util.List;

public interface SubscriptionDynamoRepository extends CrudRepository<Subscription, String> {

    void disableById(String tenantId, String id);
    List<SearchItem> search(String tenantId);
}
