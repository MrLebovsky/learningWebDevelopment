package com.learning.web.development.patterns.patternFactory.classesCreators;

import com.learning.web.development.patterns.patternFactory.classesProducts.Pizza;

/*
    Реализация паттерна "Фабричный метод"
 */
public abstract class PizzaStore {

    Pizza pizza;

    public Pizza orderPizza(String type) {
        pizza = createPizza(type);

        pizza.prepare();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    abstract Pizza createPizza(String type);
}
