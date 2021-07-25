package com.tekcapsule.subscription.domain.service;

import com.tekcapsule.subscription.domain.command.CreateCommand;
import com.tekcapsule.subscription.domain.command.DisableCommand;
import com.tekcapsule.subscription.domain.command.UpdateCommand;
import com.tekcapsule.subscription.domain.model.Mentor;
import com.tekcapsule.subscription.domain.query.SearchItem;
import com.tekcapsule.subscription.domain.query.SearchQuery;

import java.util.List;

public interface SubscriptionService {

    Mentor create(CreateCommand createCommand);

    Mentor update(UpdateCommand updateCommand);

    void disable(DisableCommand disableCommand);

    List<SearchItem> search(SearchQuery searchQuery);

    Mentor get(String tenantId, String userId);
}
