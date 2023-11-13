package com.tekcapzule.subscription.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SubscriptionPlan {
    MONTHLY("Monthly"),
    YEARLY("Yearly");

    @Getter
    private String value;
}