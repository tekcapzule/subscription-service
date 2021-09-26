package com.tekcapsule.subscription.domain.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.tekcapsule.subscription.domain.query.SearchItem;
import com.tekcapsule.subscription.domain.model.Subscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Slf4j
@Repository
public class SubscriptionRepositoryImpl implements SubscriptionDynamoRepository {

    private DynamoDBMapper dynamo;

    @Autowired
    public SubscriptionRepositoryImpl(DynamoDBMapper dynamo) {
        this.dynamo = dynamo;
    }


    @Override
    public Subscription findBy(String emailId) {
        throw new NotImplementedException();
    }

    @Override
    public List<Subscription> findAll() {
        throw new NotImplementedException();
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
}
