package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.function.Predicate;

public class ReadFile {

    private final File file;

    public ReadFile(File file) {
        this.file = file;
    }

    public String content(Predicate<Character> filter) {
        StringBuilder text = new StringBuilder();
        try (InputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) > 0) {
                if (filter.test((char) data)) {
                    text.append((char) data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text.toString();
    }
}
