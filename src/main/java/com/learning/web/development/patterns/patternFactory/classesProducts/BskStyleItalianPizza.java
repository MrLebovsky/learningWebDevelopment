package com.learning.web.development.patterns.patternFactory.classesProducts;

public class BskStyleItalianPizza extends Pizza {

    public BskStyleItalianPizza() {
        name = "Biysk pizza in Italian style";
    }

    //в эту пиццу добавим свои особенности
    public void prepare() {
        logger.debug("Prepare pizza in the Biysk Italian style");
    }
}
