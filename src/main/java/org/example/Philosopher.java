package org.example;

import java.util.concurrent.locks.Lock;

public class Philosopher extends Thread {
    private Lock leftFork;
    private Lock rightFork;
    private String name;
    private int mealsEaten;

    public Philosopher(String name, Lock leftFork, Lock rightFork) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.mealsEaten = 0;
    }

    @Override
    public void run() {
        while (mealsEaten < 3) {
            // Размышляет
            think();
            // Пытается взять вилки
            if (leftFork.tryLock()) {
                try {
                    if (rightFork.tryLock()) {
                        try {
                            // Ест
                            eat();
                        } finally {
                            rightFork.unlock();
                        }
                    }
                } finally {
                    leftFork.unlock();
                }
            }
        }
    }

    private void think() {
        System.out.println(name + " размышляет");
        try {
            Thread.sleep(((int) (Math.random() * 100)));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void eat() {
        System.out.println(name + " начинает есть");
        try {
            Thread.sleep(((int) (Math.random() * 100)));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(name + " закончил есть");
        mealsEaten++;
    }
}
