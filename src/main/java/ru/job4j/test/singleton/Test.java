package ru.job4j.test.singleton;

public class Test {
    public static void main(String[] args) {
        Singleton first = Singleton.getInstance();
        System.out.println(first.getClass());

        Singleton second = Singleton.getInstance();
        System.out.println(first == second);
    }
}
