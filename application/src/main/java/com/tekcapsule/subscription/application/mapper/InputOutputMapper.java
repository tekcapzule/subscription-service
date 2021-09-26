package com.tekcapsule.subscription.application.mapper;

import com.tekcapsule.subscription.application.function.input.SubscribeInput;
import com.tekcapsule.subscription.application.function.input.UnSubscribeInput;
import in.devstream.core.domain.Command;
import in.devstream.core.domain.ExecBy;
import in.devstream.core.domain.Origin;
import in.devstream.mentor.domain.command.CreateCommand;
import in.devstream.mentor.domain.command.DisableCommand;
import in.devstream.mentor.domain.command.UpdateCommand;
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

    public static final BiFunction<SubscribeInput, Origin, CreateCommand> buildCreateCommandFromCreateInput = (subscribeInput, origin) -> {
        CreateCommand createCommand =  CreateCommand.builder().build();
        BeanUtils.copyProperties(subscribeInput, createCommand);
        addOrigin.apply(createCommand, origin);
        return createCommand;
    };

    public static final BiFunction<UpdateInput, Origin, UpdateCommand> buildUpdateCommandFromUpdateInput = (updateInput, origin) -> {
        UpdateCommand updateCommand = UpdateCommand.builder().build();
        BeanUtils.copyProperties(updateInput, updateCommand);
        addOrigin.apply(updateCommand, origin);
        return updateCommand;
    };

    public static final BiFunction<UnSubscribeInput, Origin, DisableCommand> buildDisableCommandFromDisableInput = (unSubscribeInput, origin) -> {
        DisableCommand disableCommand =  DisableCommand.builder().build();
        BeanUtils.copyProperties(unSubscribeInput, disableCommand);
        addOrigin.apply(disableCommand, origin);
        return disableCommand;
    };

}
