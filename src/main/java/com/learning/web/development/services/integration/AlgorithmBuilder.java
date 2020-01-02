package com.learning.web.development.services.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.stereotype.Service;


@Service
public class AlgorithmBuilder {
    private DirectChannel outputChannel;

    @Autowired
    private ApplicationContext context;

    public AlgorithmBuilder(@Qualifier("outputChannel") DirectChannel outputChannel) {
        this.outputChannel = outputChannel;
    }

    @Autowired
    public void context(ApplicationContext context) { this.context = context; }

    /*@Qualifier("animalFlow.input")
    public void setInputChannel(MessageChannel inputChannel) {
        this.inputChannel = inputChannel;
    }*/

    public void makeProcess() {
        // обработчик внутри subscribe выполнится как только закончится выполнение flow
        outputChannel.subscribe(x -> System.out.println(x));
    }
}
