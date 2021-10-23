package ru.job4j;

public class Test {

    private Test() {
        System.out.println("Singleton created");
    }

    public static final Test Instance = new Test();

    public void printName() {
        System.out.println("I am singleton");
    }
}
