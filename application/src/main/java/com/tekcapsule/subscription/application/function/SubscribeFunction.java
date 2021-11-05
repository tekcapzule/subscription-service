package com.tekcapsule.subscription.application.function;

import com.tekcapsule.core.domain.Origin;
import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.PayloadUtil;
import com.tekcapsule.core.utils.Stage;
import com.tekcapsule.subscription.application.config.AppConfig;
import com.tekcapsule.subscription.application.function.input.SubscribeInput;
import com.tekcapsule.subscription.domain.command.SubscribeCommand;
import com.tekcapsule.subscription.domain.service.SubscriptionService;
import com.tekcapsule.subscription.application.mapper.InputOutputMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class SubscribeFunction implements Function<Message<SubscribeInput>, Message<Void>> {

    private final SubscriptionService subscriptionService;

    private final AppConfig appConfig;

    public SubscribeFunction(final SubscriptionService subscriptionService, final AppConfig appConfig) {
        this.subscriptionService = subscriptionService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<Void> apply(Message<SubscribeInput> subscribeInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            log.info(String.format("Entering subscribe Function payload:%s", subscribeInputMessage.getPayload()));
            SubscribeInput subscribeInput = subscribeInputMessage.getPayload();
            Origin origin = HeaderUtil.buildOriginFromHeaders(subscribeInputMessage.getHeaders());
            SubscribeCommand subscribeCommand = InputOutputMapper.buildSubscribeCommandFromSubscribeInput.apply(subscribeInput, origin);
            subscriptionService.subscribe(subscribeCommand);
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
            payload = PayloadUtil.composePayload(Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
            payload = PayloadUtil.composePayload(Outcome.ERROR);
        }
        return new GenericMessage(payload, responseHeaders);
    }
}