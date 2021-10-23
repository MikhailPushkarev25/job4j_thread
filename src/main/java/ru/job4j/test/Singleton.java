package ru.job4j.test;

public class Singleton {
    private static Singleton instance;

    private Singleton() {

    }

    public static synchronized Singleton getINSTANCE() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
