package ru.job4j.pools.poolIndex;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class IndexSearch extends RecursiveTask<Integer> {

    private final Integer[] array;
    private final Integer form;
    private final Integer to;
    private final Integer element;

    public IndexSearch(Integer[] array, Integer form, Integer to, Integer element) {
        this.array = array;
        this.form = form;
        this.to = to;
        this.element = element;
    }

    @Override
    protected Integer compute() {
        int rsl = -1;
        if ((form - to) < 10) {
            for (int i = form; i < to; i++) {
                if (array[i].equals(element)) {
                    return i;
                }
            }
            return rsl;
        }
        int mid = (form + to) / 2;
        IndexSearch leftSort = new IndexSearch(array, form, mid, element);
        IndexSearch rightSort = new IndexSearch(array, mid + 1, to, element);
        leftSort.fork();
        rightSort.fork();
        int left = leftSort.join();
        int right = rightSort.join();
        return left != -1 ? left : right;
    }

    public static Integer search(Integer[] array, Integer element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new IndexSearch(array, 0, array.length - 1, element));
    }
}
