package ru.job4j;


import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
public class CountTest {

    /**
     * класс описывает нить со с счетчиком
     */

    private static class ThreadCount extends Thread {

        private final Count count;

        private ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        /*Создаем счетчик */
        final Count count = new Count();
        /**
         * Создаем нити
         */
        Thread first = new Thread(count);
        Thread second = new Thread(count);
        /**
         * Запускаем нити
         */
        first.start();
        second.start();
        /**
         * Заставляем главную нить дождаться наших нитей
         */
        first.join();
        second.join();
        /**
         * Проверяем результат
         */
        assertThat(count.get(), is(2));
    }
}