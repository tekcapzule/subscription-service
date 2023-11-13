package com.tekcapzule.subscription.application.function;

import com.tekcapzule.core.domain.Origin;
import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.PayloadUtil;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.subscription.application.config.AppConfig;
import com.tekcapzule.subscription.application.function.input.UnSubscribeInput;
import com.tekcapzule.subscription.application.mapper.InputOutputMapper;
import com.tekcapzule.subscription.domain.command.UnsubscribeCommand;
import com.tekcapzule.subscription.domain.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class UnSubscribeFunction implements Function<Message<UnSubscribeInput>, Message<Void>> {
    private final SubscriptionService subscriptionService;

    private final AppConfig appConfig;

    public UnSubscribeFunction(final SubscriptionService subscriptionService, final AppConfig appConfig) {
        this.subscriptionService = subscriptionService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<Void> apply(Message<UnSubscribeInput> unSubscribeInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            UnSubscribeInput unSubscribeInput = unSubscribeInputMessage.getPayload();
            log.info(String.format("Entering unsubscribe Function - Email Id:%s", unSubscribeInput.getSubscriptionId()));
            Origin origin = HeaderUtil.buildOriginFromHeaders(unSubscribeInputMessage.getHeaders());
            UnsubscribeCommand unsubscribeCommand = InputOutputMapper.buildUnSubscribeCommandFromUnSubscribeInput.apply(unSubscribeInput, origin);
            subscriptionService.unsubscribe(unsubscribeCommand);
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
