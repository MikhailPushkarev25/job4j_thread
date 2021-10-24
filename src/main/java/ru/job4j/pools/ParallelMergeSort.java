package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ParallelMergeSort extends RecursiveTask<int[]> {

    private final int[] array;
    private final int form;
    private final int to;

    public ParallelMergeSort(int[] array, int form, int to) {
        this.array = array;
        this.form = form;
        this.to = to;
    }

    @Override
    protected int[] compute() {
        if (form == to) {
            return new int[] {array[form]};
        }

        int mid = (form + to) / 2;
        // создаем задачи для отсортировки частей
        ParallelMergeSort leftSort = new ParallelMergeSort(array, form, mid);
        ParallelMergeSort rightSort = new ParallelMergeSort(array, mid + 1, to);
        // Произведем деление
        // оно будет происходить, пока в частях не останется по одному элементу
        leftSort.fork();
        rightSort.fork();
        // объединяем полученные результаты
        int[] left = leftSort.join();
        int[] right = rightSort.join();
        return MergeSort.merge(left, right);
    }

    public static int[] sort(int[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelMergeSort(array, 0, array.length - 1));
    }
}
