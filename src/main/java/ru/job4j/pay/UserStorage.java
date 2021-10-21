package ru.job4j.pay;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
   private final Map<Integer, User> map = new HashMap<>();

    public synchronized boolean add(User user) {
        return map.putIfAbsent(user.getId(), user.of(user)) == null;
    }

    public synchronized boolean update(User user) {
        return map.replace(user.getId(), user.of(user)) != null;
    }

    public synchronized boolean delete(User user) {
        return map.remove(user.getId(), user.of(user));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User user1 = map.get(fromId);
        User user2 = map.get(toId);

        if (user1 == null || user2 == null || user1.getAmount() < amount) {
           return false;
        }
        user1.setAmount(user1.getAmount() - amount);
        user2.setAmount(user2.getAmount() + amount);

        return true;
    }
}
