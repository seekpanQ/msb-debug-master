package com.mashibing.debug.doubletest;

import org.junit.jupiter.api.Test;

public class DoubleTest {

    @Test
    public void test1() {
        double d = 65.383;
        System.out.println(Double.doubleToRawLongBits(d));
        System.out.println(Long.toHexString(Double.doubleToRawLongBits(d)));
    }

    @Test
    public void test2() {
        double d1 = 123456.123456789;
        double d2 = 123456.1234567890;
        double d3 = 123456.12345678901;
        double d4 = 123456.12345678906;
        double d5 = 123456.12345678909;
        double d6 = 123456.12345678910;
        double d7 = 123456.1234567891;
        double d8 = 123456.1234567890;

        System.out.println(d1 == d2);
        System.out.println(d2 == d3);
        System.out.println(d2 < d3);
        System.out.println(d2 > d3);
        System.out.println(d6 > d5);
        System.out.println(d7 > d8);
    }


}
