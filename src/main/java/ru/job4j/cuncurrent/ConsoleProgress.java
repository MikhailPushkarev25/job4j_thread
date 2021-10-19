package ru.job4j.cuncurrent;

public class ConsoleProgress implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
    }

    @Override
    public void run() {
        int i = 0;
        String[] process = new String[] {"\\", "\\|", "\\|/"};
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\r Load: " + process[i++ % process.length]);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
