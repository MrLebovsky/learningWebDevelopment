package com.learning.web.development.services.integration;

import com.learning.web.development.dto.Animal;
import org.springframework.stereotype.Service;

@Service("cService")
public class C {
    public Animal process(Animal animal) {
        System.out.println("C is processing animal");
        return animal;
    }
}