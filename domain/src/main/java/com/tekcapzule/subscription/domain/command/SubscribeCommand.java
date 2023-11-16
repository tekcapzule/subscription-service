package com.tekcapzule.subscription.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.core.domain.Command;
import com.tekcapzule.subscription.domain.model.SubscriptionChannel;
import com.tekcapzule.subscription.domain.model.SubscriptionPlan;
import com.tekcapzule.subscription.domain.model.SubscriptionType;
import lombok.Builder;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class SubscribeCommand extends Command {
    private String subscriptionId;
    private SubscriptionPlan subscriptionPlan;
    private SubscriptionType subscriptionType;
    private SubscriptionChannel subscriptionChannel;
}


