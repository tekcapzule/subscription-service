package com.tekcapsule.subscription.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
public enum Channel {
    MOBILE("Mobile"),
    WEB("Web");

    @Getter
    private String value;
}