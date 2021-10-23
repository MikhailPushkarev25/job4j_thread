package ru.job4j;

public class Singleton {

    private Singleton() {

    }

    public static final Singleton INSTANCE = new Singleton();
}
