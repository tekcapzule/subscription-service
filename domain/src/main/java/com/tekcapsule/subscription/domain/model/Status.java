package com.tekcapsule.subscription.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Status {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    EXPIRED("Expired");

    @Getter
    private String value;
}