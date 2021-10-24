package ru.job4j.pools;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static ru.job4j.pools.RolColSum.asyncSum;

public class RolColSumTest {

    @Test
    public void whenAsyncSum() throws ExecutionException, InterruptedException {
        int[][] arrays = {
                {1, 2, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        RolColSum.Sums[] sums = asyncSum(arrays);
        assertThat(sums[0].getColSum(), is(3));
    }

    @Test
    public void whenAsyncSumRow() throws ExecutionException, InterruptedException {
        int[][] arrays = {
                {1, 2, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        RolColSum.Sums[] sums = asyncSum(arrays);
        assertThat(sums[0].getRowSum(), is(4));
    }
}