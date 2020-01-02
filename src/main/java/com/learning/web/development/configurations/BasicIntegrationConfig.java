package com.learning.web.development.configurations;

import com.learning.web.development.dto.Animal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;

@Configuration
@EnableIntegration
public class BasicIntegrationConfig {

    @Bean
    DirectChannel outputChannel() {
        return new DirectChannel();
    }

    @MessagingGateway
    public interface LaunchFunctions {
        @Gateway(requestChannel = "animalFlow.input")
        void process(Animal animal);
    }

    // канал DirectChannel с именем animalFlow.input создается автоматически
    @Bean
    public IntegrationFlow animalFlow() {
        return flow -> flow
                .handle("bService", "process")
                .handle("cService", "process")
                .handle("aService", "process")
                .channel("outputChannel");
    }
}
