package com.tekcapsule.subscription.application.function;

import com.tekcapsule.subscription.application.config.AppConfig;
import com.tekcapsule.core.domain.EmptyFunctionInput;
import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.Stage;
import com.tekcapsule.subscription.domain.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class GetCountFunction implements Function<Message<EmptyFunctionInput>, Message<Integer>> {

    private final SubscriptionService subscriptionService;

    private final AppConfig appConfig;

    public GetCountFunction(final SubscriptionService subscriptionService, final AppConfig appConfig) {
        this.subscriptionService = subscriptionService;
        this.appConfig = appConfig;
    }


    @Override
    public Message<Integer> apply(Message<EmptyFunctionInput> getAllCountMessage) {

        Map<String, Object> responseHeaders = new HashMap<>();
        int subscriptionsCount = 0;
        String stage = appConfig.getStage().toUpperCase();
        try {
            log.info("Entering get all subscriptions count Function");
            subscriptionsCount = subscriptionService.getSubscriptionCount();
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage<>(subscriptionsCount, responseHeaders);
    }
}