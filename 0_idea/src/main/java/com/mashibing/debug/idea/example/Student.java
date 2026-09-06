package com.mashibing.debug.idea.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Student {

    private Integer age;

    /*
    * 1-{age:20,num:100}
    * 2-{1:20,2:100}
    * */

    public static void main(String[] args) {
        int num = 10000;
        List<Student> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            list.add(new Student(i));
        }
        List<Student> collect = list.stream()
                .skip(1)
                .limit(100)
                .filter(item -> item.getAge() > 20)
                .filter(item -> item.getAge() < 50)
                .collect(Collectors.toList());

    }

}
