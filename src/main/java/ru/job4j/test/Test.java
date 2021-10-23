package ru.job4j.test;

public class Test {
    public static void main(String[] args) {
        MySingleton firstSingleton = MySingleton.getInstance();
        System.out.println(firstSingleton.getClass());

        MySingleton secondSingleton = MySingleton.getInstance();
        System.out.println(firstSingleton == secondSingleton);
    }
}
