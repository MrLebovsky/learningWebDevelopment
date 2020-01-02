package com.learning.web.development.services.integration;

import com.learning.web.development.dto.Animal;
import org.springframework.stereotype.Service;

@Service("bService")
public class B {
    public Animal process(Animal animal){
        System.out.println("B is processing animal");
        return animal;
    }
}