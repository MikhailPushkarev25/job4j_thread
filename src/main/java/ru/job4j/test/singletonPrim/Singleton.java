package ru.job4j.test.singletonPrim;

public class Singleton {

    private Singleton() {
        System.out.println("Singleton created");
    }

    private static class SingletonHolder {
        public static final Singleton HOLDER_INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }
}
