package ru.job4j.cache;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenAdd() {
        Base base = new Base(1, 0);
        Cache cache = new Cache();
        boolean res = cache.add(base);
        assertEquals(res, true);
    }

    @Test
    public void whenUpdate() {
        Base base = new Base(1, 0);
        Base base1 = new Base(2, 0);
        Cache cache = new Cache();
        cache.add(base);
        cache.add(base1);
        base.setName("Users");
        boolean res = cache.update(new Base(2, 0));
        assertEquals(res, true);
    }

    @Test
    public void whenDelete() {
        Base base = new Base(1, 0);
        Cache cache = new Cache();
        cache.add(base);
        assertThat(base.getId(), is(1));
        cache.delete(base);
        assertNull(cache.getKey(base.getId()));
    }

    @Test (expected = OptimisticException.class)
    public void whenExpected() {
        Base base = new Base(1, 0);
        Base base1 = new Base(1, 3);
        Cache cache = new Cache();
        cache.add(base);
        cache.add(base1);
        cache.update(base1);
    }
}