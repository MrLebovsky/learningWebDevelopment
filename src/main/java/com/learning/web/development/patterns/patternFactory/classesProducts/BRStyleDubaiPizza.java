package com.learning.web.development.patterns.patternFactory.classesProducts;

public class BRStyleDubaiPizza extends Pizza {

    public BRStyleDubaiPizza() {
        name = "Barnaul pizza in Dubai style";
    }

    //в эту пиццу добавим свои особенности
    public void box() {
        logger.debug("Choose the green colored box for Barnaul Dubai Pizza");
    }
}
