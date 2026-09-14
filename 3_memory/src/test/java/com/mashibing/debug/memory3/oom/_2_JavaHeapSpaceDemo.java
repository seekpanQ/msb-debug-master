package com.mashibing.debug.memory3.oom;

import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * 堆空间溢出模拟
 * JVM参数:-Xms20m -Xmx20m
 *
 * @author sunzhiqiang
 * @date 2021/12/01 21:35:56
 */
public class _2_JavaHeapSpaceDemo {
    public static void main(String[] args) {
        new Thread(() -> {
            String str = "asdasd";
            while (true) {
                str += str + new Random().toString();
            }
        }).start();
        new Thread(() -> {
            int num = 0;
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(num);
                num++;
            }
        }).start();
    }
}
