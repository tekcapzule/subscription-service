package com.tekcapsule.subscription.application.function;

import com.tekcapsule.core.domain.EmptyFunctionInput;
import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.Stage;
import com.tekcapsule.subscription.application.config.AppConfig;
import com.tekcapsule.subscription.domain.model.Subscription;
import com.tekcapsule.subscription.domain.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


@Component
@Slf4j
public class GetAllFunction implements Function<Message<EmptyFunctionInput>, Message<List<Subscription>>> {

    private final SubscriptionService subscriptionService;

    private final AppConfig appConfig;

    public GetAllFunction(final SubscriptionService subscriptionService, final AppConfig appConfig) {
        this.subscriptionService = subscriptionService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<List<Subscription>> apply(Message<EmptyFunctionInput> findAllInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        List<Subscription> subscriptions = new ArrayList<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            log.info("Entering get all subscription Function");
            subscriptions = subscriptionService.getAllSubscriptions();
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.NOT_FOUND);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage(subscriptions, responseHeaders);
    }
}