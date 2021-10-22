package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {

    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
     Integer result;
     do {
         result = count.get();
         if (result == null) {
             result = 0;
             count.set(result);
         }
     } while (!count.compareAndSet(result, result + 1));
    }

    public int get() {
        return count.get();
    }
}
