package com.tekcapzule.subscription.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Currency {
    INR("INR"),
    USD("USD"),
    EUR("EUR"),
    GBP("GBP");

    @Getter
    private String value;
}