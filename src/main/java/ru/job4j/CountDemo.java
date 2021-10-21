package ru.job4j;

public class CountDemo {
    public static void main(String[] args) throws InterruptedException {
        CountBarrier barrier = new CountBarrier(1);
        Thread one = new Thread(
                () -> {
                        System.out.println(Thread.currentThread().getName() + " started");
                        barrier.count();
                },
                "Master"
        );
        Thread two = new Thread(
                () -> {
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Slave"
        );
        one.start();
        two.start();
    }
}
