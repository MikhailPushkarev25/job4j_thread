package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {

    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        Base result = memory.computeIfPresent(model.getId(), (key, value) -> {
            Base sorted = memory.get(model.getId());
            if (sorted.getVersion() != model.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            String name = model.getName();
            value = new Base(model.getId(), model.getVersion() + 1);
            value.setName(name);
            return value;
        });
        return result != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }

    public Base getKey(int key) {
        return memory.get(key);
    }
}
