package ru.sayakhov;

import java.util.concurrent.Semaphore;

public class ExampleSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);// пропускная способность максимум 3 потока, 3 разрешения

        try {
            semaphore.acquire(); // начинаем взаимодействие с ресурсом семафора (забираем 1 пропуск), и ждет если пока не появится свободный пропуск
            semaphore.acquire();
            semaphore.acquire();

            System.out.println("All permits have been acquired");

            semaphore.acquire();
            System.out.println("Can`t reach here...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        semaphore.release();// отдаем разрешение

        System.out.println(semaphore.availablePermits());// возвращет колличество разрещений которое у нас свободно

    }
}
