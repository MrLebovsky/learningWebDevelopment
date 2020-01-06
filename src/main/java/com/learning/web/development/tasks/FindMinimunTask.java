package com.learning.web.development.tasks;

import java.util.concurrent.Callable;


/**
 * Find the min element in the array for 4 threads
 */
public class FindMinimunTask implements Callable<Integer> {

    private int[] array;
    private int numThread;
    private int threadsCount;

    public FindMinimunTask(int[] array, int numThread, int threadsCount) {
        this.array = array;
        this.numThread = numThread;
        this.threadsCount = threadsCount;
    }

    public Integer call() {

        int w0 = ((numThread - 1) / threadsCount) * array.length;
        int w = ((numThread / threadsCount) * array.length);
        int min = array[w0];

        for (int i = w0; i < w; i++) {
            if (min > array[i]) min = array[i];
        }
        return min;
    }
}