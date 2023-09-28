package com.tekcapsule.subscription.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.Command;
import com.tekcapsule.subscription.domain.model.*;
import lombok.Builder;
import lombok.Data;
import software.amazon.ion.Decimal;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class InitiateTransactionCommand extends Command {
    private String subscriptionId;
    private String transactedOn;
    private String externalReferenceId;
    private String invoiceId;
    private PaymentMethod paymentMethod;
    private TransactionType transactionType;
    private Currency currency;
    private Decimal amount;
    private BillingPeriod billingPeriod;
    private List<Comment> comments;

}
