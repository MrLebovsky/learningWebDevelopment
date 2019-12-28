package com.learning.web.development.patterns.patternFactory.classesCreators;

import com.learning.web.development.patterns.patternFactory.classesProducts.BRStyleDubaiPizza;
import com.learning.web.development.patterns.patternFactory.classesProducts.BRStyleGrilkaPizza;
import com.learning.web.development.patterns.patternFactory.classesProducts.Pizza;

public class BarnaulPizzaStore extends PizzaStore {

    @Override
    Pizza createPizza(String type) {
        if (type.equals("grilka")) {
            return new BRStyleGrilkaPizza();
        } else if (type.equals("dubai")) {
            return new BRStyleDubaiPizza();
        } else {
            return null;
        }
    }
}
