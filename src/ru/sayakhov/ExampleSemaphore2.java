package ru.sayakhov;

import java.util.concurrent.*;

public class ExampleSemaphore2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Connection connection = Connection.getConnection();



        for ( int i = 0; i < 200 ; i++ ) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        connection.work();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.shutdown();//закончили сабмитить запускаем выполнение
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}

//Singleton
class Connection{
    private static Connection connection = new Connection();
    private int connectionsCount;
    private Connection(){}
    private Semaphore semaphore = new Semaphore(10);

    public static Connection getConnection(){
        return connection;
    }
    public void work() throws InterruptedException {
        semaphore.acquire(); // даем разрещение
        try{
            doWork();
        }finally {
            semaphore.release(); // забираем разрещение
        }//мы должны обязательно вернуть наши разрешения
    }
    private void doWork() throws InterruptedException {
        synchronized (this) {
            connectionsCount++;
        }

        Thread.sleep(5000);

        synchronized (this){
            connectionsCount--;
            System.out.println(connectionsCount);
        }
    }
}// Приватный потому что мы не можем создавать сколько угодна обьектов класса Connection, конектион - 1. ТАкой паттерн называется Singleton