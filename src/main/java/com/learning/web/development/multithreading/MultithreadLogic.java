package com.learning.web.development.multithreading;

import com.learning.web.development.tasks.FindMinimunTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import static com.learning.web.development.utils.ConcurrentUtils.stop;

@Service
public class MultithreadLogic {

    private Logger logger = LoggerFactory.getLogger(MultithreadLogic.class);
    private static final int SIZE = 100000000;
    private ReentrantLock lock = new ReentrantLock();

    private int count = 0;

    public void simpleTask() {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });

        /*
            Исполнитель пытается завершить работу, ожидая завершения запущенных задач
            в течение определенного времени (5 секунд). По истечении этого времени он
            останавливается, прерывая все незавершенные задачи.
         */
        try {
            logger.debug("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.debug("tasks interrupted");
        } finally {
            if (!executor.isTerminated()) {
                logger.debug("cancel non-finished tasks");
            }
            executor.shutdownNow();
            logger.debug("shutdown finished");
        }
    }

    public void simpleTaskPool() throws InterruptedException {

        //создает ForkJoinPool с определенным параллелизмом (parallelism size), по умолчанию равным количеству ядер машины
        ExecutorService executor = Executors.newWorkStealingPool();

        //Callable-задачи, которые могут возвращать значения
        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3");

        //Запуск всех задач и запрос результата каждой задачи
        executor.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(logger::debug);
    }

    private synchronized void incrementSync() {
        count = count + 1;
    }

    private void incrementLock() {
        lock.lock();
        // важно оборачивать код в try{}finally{}, чтобы ресурсы освободились даже в случае выброса исключения
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public void simpleRaceConditionSolve() {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10000)
                .forEach(i -> executor.submit(this::incrementSync));

        IntStream.range(0, 10000)
                .forEach(i -> executor.submit(this::incrementLock));
        stop(executor);
        logger.debug("count = " + count);

    }

    public void simpleConcurrentExample() {

        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("r2", "d2");
        map.put("c3", "p0");

        //Итерации выполняются последовательно, в текущем потоке
        map.forEach(
                (key, value) -> logger.debug("key = " + key + " value = " + value)
        );

        //Метод putIfAbsent() помещает в таблицу значение, только если по данному ключу ещё нет другого значения
        String value = map.putIfAbsent("c3", "p1");
        logger.debug(value);    // p0

        value = map.putIfAbsent("c4", "p1");
        logger.debug(value); // p1

        map.forEach(
                (a, b) -> logger.debug("key = " + a + " value = " + b)
        );

        /*
            Метод replaceAll() принимает в качестве аргумента лямбда-выражение типа BiFunction.
            Этой лямбде по очереди передаются все комбинации ключ-значения из карты, а результат,
            который она возвращает, записывается соответствующему ключу в качестве значения
         */
        map.replaceAll((k, v) -> "r2".equals(k) ? "d3" : v);
        logger.debug(map.get("r2"));
    }

    public void findMinElement() throws InterruptedException {
        int[] array = new int[SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }

        //найдем минимальный элемент последовательно
        int min = array[0];
        long start = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            if (min > array[i]) min = array[i];
        }
        long executionTime = System.currentTimeMillis() - start;
        logger.debug("Sequential min: " + min + "; Execution time: " + executionTime);

        //найдем минимальный элемент параллельно
        int threadsCount = 2;
        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);
        start = System.currentTimeMillis();

        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < threadsCount; i++)
            callables.add(new FindMinimunTask(array, i + 1, threadsCount));

        min = executor.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .min(Integer::compareTo)
                .get();
        executionTime = System.currentTimeMillis() - start;
        logger.debug("Parallel min: " + min + "; Execution time: " + executionTime);
    }
}
