package com.tekcapsule.subscription.domain.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.tekcapsule.subscription.domain.query.SearchItem;
import com.tekcapsule.subscription.domain.model.Subscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class SubscriptionRepositoryImpl implements SubscriptionDynamoRepository {

    private DynamoDBMapper dynamo;

    @Autowired
    public SubscriptionRepositoryImpl(DynamoDBMapper dynamo) {
        this.dynamo = dynamo;
    }

    @Override
    public List<Subscription> findAll(String emailId) {

        Subscription hashKey = Subscription.builder().emailId(emailId).build();
        DynamoDBQueryExpression<Subscription> queryExpression = new DynamoDBQueryExpression<Subscription>()
                .withHashKeyValues(hashKey);

        return dynamo.query(Subscription.class, queryExpression);
    }

    @Override
    public Subscription findBy(String emailId) {
        return dynamo.load(Subscription.class, emailId);
    }

    @Override
    public Subscription save(Subscription subscription) {
        dynamo.save(subscription);
        return subscription;
    }

    @Override
    public void delete(String id) {
        Subscription subscription = findBy(id);
        if (subscription != null) {
            dynamo.delete(subscription);
        }
    }

    @Override
    public void disableById( String id) {
        Subscription subscription = findBy(id);
        if (subscription != null) {
            subscription.setActive(false);
            dynamo.save(subscription);
        }
    }

    @Override
    public List<SearchItem> search() {
        Subscription hashKey = Subscription.builder().tenantId(tenantId).build();
        DynamoDBQueryExpression<Subscription> queryExpression = new DynamoDBQueryExpression<Subscription>()
                .withHashKeyValues(hashKey);
        List<Subscription> subscriptions = dynamo.query(Subscription.class, queryExpression);
        List<SearchItem> searchItems = new ArrayList<SearchItem>();
        if (subscriptions != null) {
            searchItems = subscriptions.stream().map(subscription -> {
                return SearchItem.builder()
                        .activeSince(subscription.getActiveSince())
                        .headLine(subscription.getHeadLine())
                        .name(subscription.getName())
                        .photoUrl(subscription.getPhotoUrl())
                        .rating(subscription.getRating())
                        .social(subscription.getSocial())
                        .build();
            }).collect(Collectors.toList());
        }
        return searchItems;
    }
}
