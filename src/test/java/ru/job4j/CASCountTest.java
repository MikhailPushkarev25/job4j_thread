package ru.job4j;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void whenIncrement() throws InterruptedException {
        CASCount count = new CASCount();
        Thread first = new Thread(() -> IntStream.range(1, 7).forEach(c -> count.increment()));
        Thread second = new Thread(() -> IntStream.range(1, 7).forEach(c -> count.increment()));
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get(), is(12));
    }
}