package com.tekcapsule.subscription.application.function;

import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.Stage;
import com.tekcapsule.subscription.application.config.AppConfig;
import com.tekcapsule.subscription.application.function.input.GetInput;
import com.tekcapsule.subscription.domain.model.Subscription;
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
public class GetFunction implements Function<Message<GetInput>, Message<Subscription>> {

    private final SubscriptionService subscriptionService;

    private final AppConfig appConfig;

    public GetFunction(final SubscriptionService subscriptionService, final AppConfig appConfig) {
        this.subscriptionService = subscriptionService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<Subscription> apply(Message<GetInput> findByInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        Subscription subscription = new Subscription();
        String stage = appConfig.getStage().toUpperCase();
        try {
            GetInput getInput = findByInputMessage.getPayload();

            log.info("Entering get subscription Function Email Id:%s", getInput.getEmailId());
            subscription = subscriptionService.findBy(getInput.getEmailId());
            Map<String, Object> responseHeader = new HashMap<>();
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.NOT_FOUND);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage(subscription, responseHeaders);
    }
}