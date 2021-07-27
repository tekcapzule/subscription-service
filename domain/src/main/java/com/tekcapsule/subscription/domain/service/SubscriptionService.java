package com.tekcapsule.subscription.domain.service;

import com.tekcapsule.subscription.domain.command.SubscribeCommand;
import com.tekcapsule.subscription.domain.command.UnsubscribeCommand;
import com.tekcapsule.subscription.domain.model.Subscription;
import com.tekcapsule.subscription.domain.query.SearchItem;
import com.tekcapsule.subscription.domain.query.SearchQuery;

import java.util.List;

public interface SubscriptionService {

    Subscription subscribe(SubscribeCommand subscribeCommand);

    void unsubscribe(UnsubscribeCommand unsubscribeCommand);

    List<SearchItem> search(SearchQuery searchQuery);

    Integer getSubscriptionCount();
}
