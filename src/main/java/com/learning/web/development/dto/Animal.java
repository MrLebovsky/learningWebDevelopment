package com.learning.web.development.dto;

public class Animal {

    private String name;

    public Animal(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}