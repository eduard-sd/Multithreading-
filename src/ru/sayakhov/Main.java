package ru.sayakhov;
/*
* многопоточность
* */
public class Main {

    public static void main(String[] args) throws InterruptedException {
	// write your code here
//        Thread myThread2 = new MyThread2();
//        myThread2.start();

        System.out.println("Hello world");

        Thread myThread = new MyThread();
        myThread.start();

//        Thread myThread3 = new MyThread2();
//        myThread3.start();

        Thread thred = new Thread(new Runner());
        thred.start();

        System.out.println("Im going to sleep");
        Thread.sleep(3000);
        System.out.println("Im awake!");
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for ( int i = 0; i < 10; i++ ) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);

        }
        System.out.println("End MyThread");

    }
}


//class MyThread2 extends Thread {
//    @Override
//    public void run() {
//        for ( int i = 0; i > -10; i-- ) {
//            System.out.println(i);
//
//        }
//        System.out.println("2222222222222222222222");
//
//    }
//}

class Runner implements Runnable{
    @Override
    public void run() {
        for ( int i = 0; i > -10; i-- ) {
            System.out.println(i);
        }

    }
}