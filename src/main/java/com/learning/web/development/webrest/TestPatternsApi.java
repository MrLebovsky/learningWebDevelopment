package com.learning.web.development.webrest;

import com.learning.web.development.patterns.patternFactory.TestPatternFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestPatternsApi {
    private TestPatternFactory testPatternFactory;

    public TestPatternsApi(TestPatternFactory testPatternFactory) {
        this.testPatternFactory = testPatternFactory;
    }

    @RequestMapping("/patterns/factory")
    public void showPatternFactory() {
        testPatternFactory.showPatternWork();
    }
}
