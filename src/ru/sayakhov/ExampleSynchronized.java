package ru.sayakhov;

public class ExampleSynchronized {
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        ExampleSynchronized test = new ExampleSynchronized();
        test.doWork();
    }

//    private synchronized void increment() {
//        counter++; // не атомарная операция - состоит из 3 частей (counter = counter + 1 ; считываение предыдущего значения counter, +1, и присвоение)
//    }

    private void increment() {
        synchronized(this){
            counter++; // не атомарная операция - состоит из 3 частей (counter = counter + 1 ; считываение предыдущего значения counter, +1, и присвоение)
        }
    }

    /*
     * модификатор valotile не подходит потому что counter не атомарная операция
     *
     * без Synchronized
     * 1:100 -> 101 -> 102           100 -> 101 -> 102
     * 2:              100 -> 101 -> 102
     *
     * с Synchronized
     * 1:100 -> 101 -> 102           104 -> 105 -> 106
     * 2:              102 -> 103 -> 104
     *
     * */

    public void doWork() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for ( int i = 0; i < 10000; i++ ) {
//                    counter = counter + 1;
                    increment();
                }
            }
        }); // анонимный класс

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for ( int i = 0; i < 10000; i++ ) {
//                    counter++; // не атомарная операция - состоит из 3 частей (counter = counter + 1 ; считываение предыдущего значения counter, +1, и присвоение)
                    increment();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join(); // поток маин останавливается здесь и будет ждать пока поток не отработается полностью только потом перейдет на след строку
        thread2.join();

        System.out.println(counter);
    }


}
