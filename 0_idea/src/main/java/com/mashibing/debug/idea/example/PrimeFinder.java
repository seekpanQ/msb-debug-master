package com.mashibing.debug.idea.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PrimeFinder {

    public static void main(String[] args) {

        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(new Student(i));
        }

        list.stream()
                .skip(1)
                .limit(10)
                .filter(item -> item.getAge() > 5)
                .filter(item -> item.getAge() < 1000)
                .collect(Collectors.toList());

    }
}
