package com.learning.web.development.patterns.patternFactory.classesProducts;

public class BRStyleGrilkaPizza extends Pizza {

    public BRStyleGrilkaPizza() {
        name = "Barnaul pizza in Grilnitsa style";
    }

    //в эту пиццу добавим свои особенности
    public void cut() {
        logger.debug("Make special cut for Grilnitsa style");
    }
}
