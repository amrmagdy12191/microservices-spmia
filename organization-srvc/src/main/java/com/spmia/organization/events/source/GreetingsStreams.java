package com.spmia.organization.events.source;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface GreetingsStreams {

    String OUTPUT = "greetings-out";

    
    @Output(OUTPUT)
    MessageChannel outboundGreetings();
}
