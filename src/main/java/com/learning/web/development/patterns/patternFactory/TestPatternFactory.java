package com.learning.web.development.patterns.patternFactory;

import com.learning.web.development.annotations.ExceptionNotify;
import com.learning.web.development.patterns.TestPattern;
import com.learning.web.development.patterns.patternFactory.classesCreators.BarnaulPizzaStore;
import com.learning.web.development.patterns.patternFactory.classesCreators.BiyskPizzaStore;
import com.learning.web.development.patterns.patternFactory.classesCreators.PizzaStore;
import com.learning.web.development.patterns.patternFactory.classesProducts.Pizza;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestPatternFactory implements TestPattern {

    Logger logger = LoggerFactory.getLogger(TestPatternFactory.class);

    @Override
    @ExceptionNotify
    public void showPatternWork() {
        int a = Integer.parseInt(null);
        int b = a + 1;
        
        PizzaStore barnaulStore = new BarnaulPizzaStore();
        PizzaStore biyskStore = new BiyskPizzaStore();

        Pizza pizza = barnaulStore.orderPizza("grilka");
        logger.debug("Peter ordered a " + pizza.getName() + " pizza");

        pizza = biyskStore.orderPizza("Italian");
        logger.debug("John ordered a " + pizza.getName() + " pizza");
    }
}
