package com.tekcapsule.subscription.application.function;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.tekcapsule.core.domain.Origin;
import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.subscription.application.config.AppConstants;
import com.tekcapsule.subscription.application.function.input.SubscribeInput;
import com.tekcapsule.subscription.domain.command.SubscribeCommand;
import com.tekcapsule.subscription.domain.model.Subscription;
import com.tekcapsule.subscription.domain.service.SubscriptionService;
import com.tekcapsule.subscription.application.mapper.InputOutputMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class SubscribeFunction implements Function<Message<SubscribeInput>, Message<Subscription>> {

    private final SubscriptionService subscriptionService;

    public SubscribeFunction(final SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }


    @Override
    public Message<Subscription> apply(Message<SubscribeInput> subscribeInputMessage) {

        log.info(String.format("Entering subscribe Function payload:%s", subscribeInputMessage.getPayload()));
        SubscribeInput subscribeInput = subscribeInputMessage.getPayload();

        Origin origin = HeaderUtil.buildOriginFromHeaders(subscribeInputMessage.getHeaders());
        SubscribeCommand subscribeCommand = InputOutputMapper.buildSubscribeCommandFromSubscribeInput.apply(subscribeInput, origin);
        Subscription subscription = subscriptionService.subscribe(subscribeCommand);

        Map<String, Object> responseHeader = new HashMap<>();
        responseHeader.put(AppConstants.HTTP_STATUS_CODE_HEADER, HttpStatus.OK.value());

        return new GenericMessage(subscription, responseHeader);
    }
}