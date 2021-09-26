package com.tekcapsule.subscription.application.function;

import com.tekcapsule.subscription.application.config.AppConstants;
import com.tekcapsule.subscription.application.function.input.SubscribeInput;
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
public class SubscribeFunction implements Function<Message<SubscribeInput>, Message<Mentor>> {

    private final SubscriptionService subscriptionService;

    public SubscribeFunction(final SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }


    @Override
    public Message<Mentor> apply(Message<SubscribeInput> createInputMessage) {

        SubscribeInput subscribeInput = createInputMessage.getPayload();

        log.info(String.format("Entering create mentor Function - Tenant Id:{0}, Name:{1}", subscribeInput.getTenantId(), subscribeInput.getName().toString()));

        Origin origin = HeaderUtil.buildOriginFromHeaders(createInputMessage.getHeaders());

        CreateCommand createCommand = InputOutputMapper.buildCreateCommandFromCreateInput.apply(subscribeInput, origin);
        Mentor mentor = mentorService.create(createCommand);
        Map<String, Object> responseHeader = new HashMap();
        responseHeader.put(AppConstants.HTTP_STATUS_CODE_HEADER, HttpStatus.OK.value());

        return new GenericMessage(mentor, responseHeader);
    }
}