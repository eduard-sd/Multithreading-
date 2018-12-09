package ru.sayakhov;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * Threadpool - удобен когда нужно выполнить много поторяющейся работы
 *
 * */
public class ExampleThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2); // ExecutorService - перевод сервис по выполнению или пул потоков
        long before = System.currentTimeMillis();

        for ( int i = 0; i < 10; i++ ) {
            executorService.submit(new Work(i));// передали 5 задания 2м потокам
        }
        executorService.shutdown();// метод сообщает что мы перестаем передавать новые задание и приступаем к выплнению имеющихся
                                    // executorService.shutdown() - похоже на "поток.start()";

        System.out.println("All tasks submitted");
        executorService.awaitTermination(1, TimeUnit.DAYS);// сколько мы готовы ждать пока потоки выполнять наше задание

        long after = System.currentTimeMillis();
        System.out.println("Program took " + (after - before) + " second to run");

    }
}

class Work implements Runnable {
    private int id;

    public Work(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000); // представим что поток здесь что-то вычисляет и тратит на это 3 секунды
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work " + id + " was completed");
    }

}