package ru.job4j.pools;

public class MergeSort {

    public static int[] sort(int[] array) {
        return sort(array, 0, array.length - 1);
    }

    private static int[] sort(int[] array, int form, int to) {
        // При следующем условии, массив из одного элемента
        // делить не чего, возвращая элемент
        if (form == to) {
            return new int[] {array[form]};
        }
        // Попали сюда, значит в массиве более одного элемента
        // находим середину
        int mid = (form + to) / 2;
        // объединяем отсортированные части
        return merge(
                // объединяем левую часть
                sort(array, form, mid),
                // сортируем правую
                sort(array, mid + 1, to)
        );
    }

    public static int[] merge(int[] left, int[] right) {
        int li = 0;
        int ri = 0;
        int resI = 0;
        int[] result = new int[left.length + right.length];
        while (resI != result.length) {
            if (li == left.length) {
                result[resI++] = right[ri++];
            } else if (ri == right.length) {
                result[resI++] = left[li++];
            } else if (left[li] <= right[ri]) {
                result[resI++] = left[li++];
            } else {
                result[resI++] = right[ri++];
            }
        }
        return result;
    }
}
