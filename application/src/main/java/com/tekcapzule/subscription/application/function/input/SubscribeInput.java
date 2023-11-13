package com.tekcapzule.subscription.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.subscription.domain.model.Channel;
import com.tekcapzule.subscription.domain.model.SubscriptionPlan;
import com.tekcapzule.subscription.domain.model.SubscriptionType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class SubscribeInput {
    private String subscriptionId;
    private SubscriptionPlan subscriptionPlan;
    private SubscriptionType subscriptionType;
    private Channel channel;
}
