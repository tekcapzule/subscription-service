package com.tekcapsule.subscription.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SubscriptionType {
    FREE("Free"),
    STANDARD("Standard"),
    PROFESSIONAL("Professional");

    @Getter
    private String value;
}