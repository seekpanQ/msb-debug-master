package com.mashibing.debug.idea.example;

public class Printer {

    private boolean ready;

    Printer() {
        System.out.println();
    }

    public static void main(String[] args) {
        Printer printer = new Printer();
        printer.print1();
        System.out.println("123");
        System.out.println(1 / 0);
    }

    void print1() {
        System.out.println(ready);
        System.out.println(ready = false);
        print2();
        System.out.println("print1 Printing to console");
    }

    void print2() {
        System.out.println("print2 Printing to console");
    }
}
