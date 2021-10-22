package com.tekcapsule.subscription.application.function;
import com.tekcapsule.subscription.application.config.AppConstants;
import com.tekcapsule.subscription.application.function.input.GetInput;
import com.tekcapsule.subscription.domain.model.Subscription;
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
public class GetFunction implements Function<Message<GetInput>, Message<Subscription>> {

    private final SubscriptionService subscriptionService;

    public GetFunction(final SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }


    @Override
    public Message<Subscription> apply(Message<GetInput>  findByInputMessage) {

        GetInput getInput = findByInputMessage.getPayload();

        log.info("Entering get subscription Function Email Id:%S",getInput.getEmailId());

        Subscription subscription = subscriptionService.findBy(getInput.getEmailId());
        Map<String, Object> responseHeader = new HashMap<>();
        responseHeader.put(AppConstants.HTTP_STATUS_CODE_HEADER, HttpStatus.OK.value());

        return new GenericMessage<>(subscription, responseHeader);
    }
}