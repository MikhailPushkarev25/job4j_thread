package ru.job4j.blocking;

import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {


    @Test
    public void WhenOffer() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    simpleBlockingQueue.offer(1);
                    simpleBlockingQueue.offer(2);
                    simpleBlockingQueue.offer(3);
                }
        );
        Thread consumer = new Thread(
                () -> {
                    simpleBlockingQueue.poll();
                    simpleBlockingQueue.poll();
                }
        );

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertEquals(simpleBlockingQueue.poll().intValue(), 3);
    }
}