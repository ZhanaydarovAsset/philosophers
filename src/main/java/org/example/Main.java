package org.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Lock[] forks = new ReentrantLock[5]; // создал пустой массив
        for (int i = 0; i < forks.length; i++) { // заполнил массив вилками типа Lock
            forks[i] = new ReentrantLock();
        }

        Philosopher[] philosophers = new Philosopher[5]; // создал пустой массив для филосовов
        for (int i = 0; i < philosophers.length; i++) {
            Lock leftFork = forks[i]; // левая вилка совпадает с индексом философа
            Lock rightFork = forks[(i + 1) % forks.length]; // правая вилка. для последнего философа вилка с индексом 0
            philosophers[i] = new Philosopher("Философ " + (i + 1), leftFork, rightFork); //заполняем массив
            philosophers[i].start(); //запускаем паток
        }
    }
}