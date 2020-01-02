package com.learning.web.development.services.integration;

import com.learning.web.development.dto.Animal;
import org.springframework.stereotype.Service;

@Service("aService")
public class A {
    public Animal process(Animal animal) {
        System.out.println("A is processing animal");
        return animal;
    }
}
