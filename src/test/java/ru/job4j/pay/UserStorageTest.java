package ru.job4j.pay;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenAdd() {
        User user = new User(1, 100);
        UserStorage userStorage = new UserStorage();
        boolean res = userStorage.add(user);
        assertThat(res, is(true));
    }

    @Test
    public void whenUpdate() {
        User user = new User(1, 100);
        UserStorage userStorage = new UserStorage();
        userStorage.add(user);
        boolean res = userStorage.update(new User(1, 200));
        assertThat(res, is(true));
    }

    @Test
    public void whenDelete() {
        User user = new User(1, 200);
        UserStorage userStorage = new UserStorage();
        userStorage.add(user);
        boolean delete = userStorage.delete(user);
        assertThat(delete, is(true));
    }

    @Test
    public void whenTransfer() throws InterruptedException {
        User user = new User(1, 100);
        User user1 = new User(2, 200);
        UserStorage userStorage = new UserStorage();
        Thread one = new Thread(() -> userStorage.transfer(user.getId(), user.getAmount(), 1));
        Thread two = new Thread(() -> userStorage.transfer(user1.getId(), user1.getAmount(), 2));
        one.start();
        one.join();
        two.start();
        two.join();
        assertThat(user.getAmount(), is(100));
        assertThat(user1.getAmount(), is(200));
    }
}