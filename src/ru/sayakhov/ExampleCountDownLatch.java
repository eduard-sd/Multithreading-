package ru.sayakhov;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExampleCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);// указываем колличество сколько раз мы можем вызвать метод countDown из любого колличества потоков

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i = 0; i < 3; i++){
            executorService.submit(new Processor(i,countDownLatch)); // нашим трем потокам назначили задание
        }
        executorService.shutdown();//обязательный метод чтоб прекратить принимать задания

        for ( int i = 0; i < 3 ; i++ ) {
            Thread.sleep(1000);
            countDownLatch.countDown();
        }

    }
}

class Processor implements Runnable {
    private CountDownLatch countDownLatch;
    private int id;

    public Processor(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            countDownLatch.await();//поток ожидает, пока не будет выполнено некоторое условие и пока другой поток не вызовет методы
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread with id "+id+" proceeded");
    }
}