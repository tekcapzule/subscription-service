package com.tekcapzule.subscription.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.core.domain.AggregateRoot;
import com.tekcapzule.core.domain.BaseDomainEntity;
import lombok.*;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "Subscription")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subscription extends BaseDomainEntity implements AggregateRoot {
    @DynamoDBHashKey(attributeName="subscriptionId")
    private String subscriptionId;
    @DynamoDBAttribute(attributeName="transactionId")
    @DynamoDBAutoGeneratedKey
    private String transactionId;
    @DynamoDBAttribute(attributeName="subscriptionPlan")
    @DynamoDBTypeConvertedEnum
    private SubscriptionPlan subscriptionPlan;
    @DynamoDBAttribute(attributeName="subscriptionType")
    @DynamoDBTypeConvertedEnum
    private SubscriptionType subscriptionType;
    private List<Transaction> transactions;
    @DynamoDBAttribute(attributeName="subscriptionChannel")
    @DynamoDBTypeConvertedEnum
    private SubscriptionChannel subscriptionChannel;
    @DynamoDBAttribute(attributeName="activeSince")
    private String activeSince;
    @DynamoDBAttribute(attributeName="status")
    @DynamoDBTypeConvertedEnum
    private Status status;
}
