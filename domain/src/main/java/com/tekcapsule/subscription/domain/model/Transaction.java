package com.tekcapsule.subscription.domain.model;

import software.amazon.ion.Decimal;

import java.util.List;

public class Transaction {
    private String transactedOn;
    private String referenceId;
    private String invoiceId;
    private PaymentMethod paymentMethod;
    private TransactionType transactionType;
    private Currency currency;
    private Decimal amount;
    private BillingPeriod billingPeriod;
    private List<Comment> comments;
    private Status status;

}
