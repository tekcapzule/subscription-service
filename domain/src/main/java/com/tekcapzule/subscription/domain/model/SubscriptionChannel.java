package com.tekcapzule.subscription.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
public enum SubscriptionChannel {
    MOBILE("Mobile"),
    WEB("Web");

    @Getter
    private String value;
}