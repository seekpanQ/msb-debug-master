package com.mashibing.debug.doubletest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StringTest {

    @Test
    public void test() {
        String str1 = "a";
        String str2 = "b";
        if (true) {
            str2 = null;
        }
        /*错误*/
        System.out.println(str1.equals(str2));
        /*主要是为了避免空指针*/
        System.out.println(Objects.equals(str1,str2));
        Lists.newArrayListWithExpectedSize(12);
        Maps.newHashMapWithExpectedSize(10);
        /*问题：如果期望建一个初始容量为N的Map，应该怎么建*/
        Map<Object, Object> map = new HashMap<>();
    }
}
