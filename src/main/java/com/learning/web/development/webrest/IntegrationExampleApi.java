package com.learning.web.development.webrest;

import com.learning.web.development.services.integration.AlgorithmBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegrationExampleApi {
    private AlgorithmBuilder algorithmBuilder;

    public IntegrationExampleApi(AlgorithmBuilder algorithmBuilder) {
        this.algorithmBuilder = algorithmBuilder;
    }

    @RequestMapping("/integration/1")
    public void showIntegrationExample() {
        algorithmBuilder.makeProcess();
    }
}
