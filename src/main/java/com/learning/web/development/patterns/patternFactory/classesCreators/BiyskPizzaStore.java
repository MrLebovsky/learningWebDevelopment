package com.learning.web.development.patterns.patternFactory.classesCreators;

import com.learning.web.development.patterns.patternFactory.classesProducts.BskStyleAmericanPizza;
import com.learning.web.development.patterns.patternFactory.classesProducts.BskStyleItalianPizza;
import com.learning.web.development.patterns.patternFactory.classesProducts.Pizza;

public class BiyskPizzaStore extends PizzaStore {

    @Override
    Pizza createPizza(String type) {
        if (type.equals("Italian")) {
            return new BskStyleItalianPizza();
        } else if (type.equals("American")) {
            return new BskStyleAmericanPizza();
        } else {
            return null;
        }
    }
}
