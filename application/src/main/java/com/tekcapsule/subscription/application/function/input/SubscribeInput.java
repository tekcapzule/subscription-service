package com.tekcapsule.subscription.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.subscription.domain.model.Channel;
import com.tekcapsule.subscription.domain.model.SubscriptionPlan;
import com.tekcapsule.subscription.domain.model.SubscriptionType;
import com.tekcapsule.subscription.domain.model.Transaction;
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
