package com.tekcapzule.subscription.application.mapper;

import com.tekcapzule.core.domain.Command;
import com.tekcapzule.core.domain.ExecBy;
import com.tekcapzule.core.domain.Origin;
import com.tekcapzule.subscription.application.function.input.SubscribeInput;
import com.tekcapzule.subscription.application.function.input.UnSubscribeInput;
import com.tekcapzule.subscription.domain.command.SubscribeCommand;
import com.tekcapzule.subscription.domain.command.UnsubscribeCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.function.BiFunction;

@Slf4j
public final class InputOutputMapper {
    private InputOutputMapper() {

    }

    public static final BiFunction<Command, Origin, Command> addOrigin = (command, origin) -> {
        OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);
        command.setChannel(origin.getChannel());
        command.setExecBy(ExecBy.builder().tenantId(origin.getTenantId()).userId(origin.getUserId()).build());
        command.setExecOn(utc.toString());
        return command;
    };

    public static final BiFunction<SubscribeInput, Origin, SubscribeCommand> buildSubscribeCommandFromSubscribeInput = (subscribeInput, origin) -> {
        SubscribeCommand subscribeCommand =  SubscribeCommand.builder().build();
        BeanUtils.copyProperties(subscribeInput, subscribeCommand);
        addOrigin.apply(subscribeCommand, origin);
        return subscribeCommand;
    };

    public static final BiFunction<UnSubscribeInput, Origin, UnsubscribeCommand> buildUnSubscribeCommandFromUnSubscribeInput = (unSubscribeInput, origin) -> {
        UnsubscribeCommand unsubscribeCommand =  UnsubscribeCommand.builder().build();
        BeanUtils.copyProperties(unSubscribeInput, unsubscribeCommand);
        addOrigin.apply(unsubscribeCommand, origin);
        return unsubscribeCommand;
    };

}
