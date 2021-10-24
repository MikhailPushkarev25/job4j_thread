package ru.job4j.pools.poolIndex;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class IndexSearchTest {

    @Test
    public void whenTestIndexSearch6() {
        Integer[] integers = {
                4, 10, 3, 2, 7, 9, 1
        };
        assertThat(IndexSearch.search(integers, 3), is(2));
    }

    @Test
    public void whenTestIndexSearch20() {
        Integer[] integers = {
                2, 5, 1, 4, 9, 12, 15, 8, 2, 0,
                1, 3, 2, 8, 0, 11, 9, 5, 4, 3
        };
        assertThat(IndexSearch.search(integers, 0), is(9));
    }

    @Test
    public void whenError() {
        Integer[] integers = {
        };
        assertThat(IndexSearch.search(integers, 2), is(-1));
    }
}