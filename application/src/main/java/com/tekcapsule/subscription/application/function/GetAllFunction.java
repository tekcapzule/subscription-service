package com.tekcapsule.subscription.application.function;

import com.tekcapsule.subscription.application.config.AppConstants;
import com.tekcapsule.subscription.domain.model.Subscription;
import com.tekcapsule.subscription.domain.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


@Component
@Slf4j
public class GetAllFunction implements Function<Message<Void>, Message<List<Subscription>>> {

    private final SubscriptionService subscriptionService;

    public GetAllFunction(final SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public Message<List<Subscription>> apply(Message<Void>  findAllInputMessage) {

        log.info("Entering find all subscription Function");

        List<Subscription> subscriptions = subscriptionService.findAllSubscriptions();
        Map<String, Object> responseHeader = new HashMap();
        responseHeader.put(AppConstants.HTTP_STATUS_CODE_HEADER, HttpStatus.OK.value());

        return new GenericMessage(subscriptions, responseHeader);
    }
}