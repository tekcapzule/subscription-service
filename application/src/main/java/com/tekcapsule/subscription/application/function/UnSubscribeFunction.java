package com.tekcapsule.subscription.application.function;

import com.tekcapsule.subscription.application.config.AppConstants;
import com.tekcapsule.subscription.application.function.input.UnSubscribeInput;
import com.tekcapsule.subscription.application.mapper.InputOutputMapper;
import com.tekcapsule.subscription.domain.service.SubscriptionService;
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
public class UnSubscribeFunction implements Function<Message<UnSubscribeInput>, Message<Void>> {
    private final SubscriptionService subscriptionService;

    public UnSubscribeFunction(final SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }


    @Override
    public Message<Void> apply(Message<UnSubscribeInput> disableInputMessage) {

        UnSubscribeInput unSubscribeInput = disableInputMessage.getPayload();

        log.info(String.format("Entering disable mentor Function - Tenant Id:{0}, User Id:{1}", unSubscribeInput.getTenantId(), unSubscribeInput.getUserId()));

        Origin origin = HeaderUtil.buildOriginFromHeaders(disableInputMessage.getHeaders());

        DisableCommand disableCommand = InputOutputMapper.buildDisableCommandFromDisableInput.apply(unSubscribeInput, origin);
        mentorService.disable(disableCommand);
        Map<String, Object> responseHeader = new HashMap();
        responseHeader.put(AppConstants.HTTP_STATUS_CODE_HEADER, HttpStatus.OK.value());

        return new GenericMessage( responseHeader);
    }
}
