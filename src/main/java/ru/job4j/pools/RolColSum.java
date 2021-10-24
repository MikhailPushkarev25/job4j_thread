package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static class Sums {

        private int rowSum;
        private int ColSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return ColSum;
        }

        public void setColSum(int colSum) {
            ColSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] array = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int rowColumn = 0;
            int colSum = 0;
            array[i] = new Sums();
            for (int j = 0; j < matrix.length; j++) {
                rowColumn += matrix[i][j];
                colSum += matrix[j][i];
            }
            array[i].setRowSum(rowColumn);
            array[i].setColSum(colSum);
        }
        return array;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = getTasks(matrix, i).get();
        }
        return sums;
    }

    public static CompletableFuture<Sums> getTasks(int[][] matrix, int element) {
        return CompletableFuture.supplyAsync(() -> {
            Sums sums = new Sums();
            int row = 0;
            int cell = 0;
            for (int i = 0; i < matrix.length; i++) {
                row += matrix[element][i];
                cell += matrix[i][element];
            }
            sums.setColSum(cell);
            sums.setRowSum(row);
            return sums;
        });
    }
}
