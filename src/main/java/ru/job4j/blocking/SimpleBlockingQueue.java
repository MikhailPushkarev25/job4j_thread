package ru.job4j.blocking;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private int limit = 0;

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue() {
        this.limit = Integer.MAX_VALUE;
    }

    public synchronized void offer(T value) {
            queue.add(value);
            notifyAll();
    }

    public synchronized T poll() {
            while (queue.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                   e.printStackTrace();
                }
            }
        return queue.poll();
    }

}
