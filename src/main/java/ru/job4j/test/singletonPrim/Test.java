package ru.job4j.test.singletonPrim;

public class Test {
    public static void main(String[] args) {
        Singleton first = Singleton.getInstance();
        System.out.println(first.getClass());

        Singleton second = Singleton.getInstance();
    }
}
