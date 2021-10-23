package ru.job4j.pool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ThreadPoolTest {

    @Test
    public void whenWork() throws InterruptedException {
        ThreadPool pool = new ThreadPool();
        List<Integer> list = new ArrayList<>();
        pool.run();
        for (int i = 0; i < 20; i ++) {
            int rsl = i;
            Thread thread = new Thread(() -> list.add(rsl));
            thread.start();
            thread.join();
        }
        pool.shutdown();
        assertThat(list.size(), is(20));
    }
}