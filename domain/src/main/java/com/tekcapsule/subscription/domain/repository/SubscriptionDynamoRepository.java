package com.tekcapsule.subscription.domain.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.Select;
import com.tekcapsule.subscription.domain.model.Subscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class SubscriptionDynamoRepository implements SubscriptionRepository {

    private DynamoDBMapper dynamo;

    @Autowired
    public SubscriptionDynamoRepository(DynamoDBMapper dynamo) {
        this.dynamo = dynamo;
    }


    @Override
    public Subscription findBy(String subscriptionId) {
        return dynamo.load(Subscription.class, subscriptionId);
    }

    @Override
    public List<Subscription> findAll() {
        return dynamo.scan(Subscription.class,new DynamoDBScanExpression());
    }

    @Override
    public Subscription save(Subscription subscription) {
        dynamo.save(subscription);
        return subscription;
    }

    @Override
    public int getSubscriptionsCount() {
        return dynamo.count(Subscription.class,new DynamoDBScanExpression().withSelect(Select.COUNT));
    }

}
