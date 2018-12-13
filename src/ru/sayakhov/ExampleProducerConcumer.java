package ru.sayakhov;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ExampleProducerConcumer {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10); // ArrayBlockingQueue - очередь не может иметь больше 10 элементов

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join(); // join - не дает потоку main продожать выполнять свой поток, до конца вызываемых потоков
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void produce() throws InterruptedException {//производитель
        Random random = new Random();
        while (true) {
            queue.put(random.nextInt(100));// метод add не потоко безопастный поэтому используем put
        }
    }

    private static void consumer() throws InterruptedException {//потребитель
        Random random = new Random();
        while(true){
            Thread.sleep(100);

            if (random.nextInt(10)==5) {
                System.out.println(queue.take());
                System.out.println(queue.take());
                System.out.println(queue.size() + " - Queue size");
            }
        }
    }
}
