package com.tekcapsule.subscription.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TransactionType {
    PAYMENT("Payment"),
    REFUND("Refund"),
    REDEEM("Redeem");

    @Getter
    private String value;
}