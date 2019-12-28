package com.learning.web.development.patterns.patternFactory.classesProducts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Pizza {

    Logger logger = LoggerFactory.getLogger(Pizza.class);
    String name;

    public void prepare() {
        logger.debug("Preparing " + name + " pizza!");
    }

    public void cut() {
        logger.debug("Cutting " + name + " pizza!");
    }

    public void box() {
        logger.debug("Place pizza  " + name + " in the Official PizzaStore box!");
    }

    public String getName() {
        return name;
    }
}
