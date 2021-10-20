package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Count implements Runnable{

    @GuardedBy("this")
    private int value;

    public synchronized void increment() {
            value++;
    }

    public synchronized int get() {
            return this.value;
    }

    @Override
    public void run() {
        increment();
    }
}
