package ru.sayakhov;

/*
* volatile - запрешает процессу кешировать обьект, с которым работает несколько потоков.
*  т.к. обьект может измениться в процессе работы в одом из потоков. в другом обьект или переменная будет устаревшая.
* */
import java.util.Scanner;

public class ExampleVolatile {
    public static void main(String[] args) {
        MyThread4 myThread = new MyThread4();
        myThread.start();

        Scanner scanner = new Scanner(System.in);
        scanner.hasNextLine();

        myThread.shutdown();

    }
}

class MyThread4 extends Thread {
    private volatile boolean running = true;

    public void run() {
        while(running){
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown(){
        this.running = false;
    }
}