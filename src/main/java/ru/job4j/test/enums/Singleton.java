package ru.job4j.test.enums;

public enum Singleton {
    INSTANCE;

    private Singleton() {
        System.out.println("Singleton created! By the way, in Enums the constructor is private by default");
    }
}
