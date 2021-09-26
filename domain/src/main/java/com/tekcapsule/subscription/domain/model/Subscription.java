package com.tekcapsule.subscription.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.AggregateRoot;
import com.tekcapsule.core.domain.BaseDomainEntity;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "Subscription")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subscription extends BaseDomainEntity<String> implements AggregateRoot {

    @DynamoDBAttribute(attributeName="emailId")
    private String emailId;
    @DynamoDBAttribute(attributeName="active")
    private boolean active;
}