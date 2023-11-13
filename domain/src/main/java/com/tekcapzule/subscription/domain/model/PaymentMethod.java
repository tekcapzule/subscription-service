package com.tekcapzule.subscription.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PaymentMethod {
    CC("Credit Card"),
    PAYPAL("Paypal");

    @Getter
    private String value;
}