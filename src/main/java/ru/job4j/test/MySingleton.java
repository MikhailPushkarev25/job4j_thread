package ru.job4j.test;

public class MySingleton {
    private static MySingleton instance;

    public MySingleton() {
        System.out.println("Singleton created");
    }

    public static synchronized MySingleton getInstance() {
        if (instance == null) {
            instance = new MySingleton();
        }
        return instance;
    }
}
