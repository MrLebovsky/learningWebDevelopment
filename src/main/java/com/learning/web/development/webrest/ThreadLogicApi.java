package com.learning.web.development.webrest;

import com.learning.web.development.multithreading.MultithreadLogic;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreadLogicApi {
    private MultithreadLogic multithreadLogic;

    public ThreadLogicApi(MultithreadLogic multithreadLogic) {
        this.multithreadLogic = multithreadLogic;
    }

    @RequestMapping("/threads/first")
    public void simpleTask() {
        multithreadLogic.simpleTask();
    }

    @RequestMapping("/threads/second")
    public void simpleTaskPool() throws InterruptedException {
        multithreadLogic.simpleTaskPool();
    }

    @RequestMapping("/threads/third")
    public void simpleRaceConditionSolve() {
        multithreadLogic.simpleRaceConditionSolve();
    }

    @RequestMapping("/threads/concurrentExample")
    public void simpleConcurrentExample() {
        multithreadLogic.simpleConcurrentExample();
    }

    @RequestMapping("/threads/min")
    public void trainParallelAlg() throws InterruptedException {
        multithreadLogic.findMinElement();
    }
}
