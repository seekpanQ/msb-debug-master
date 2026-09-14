package com.mashibing.debug.memory3.oom;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * 堆栈溢出错误模拟
 *
 * @author sunzhiqiang
 * @date 2021/12/01 19:19:36
 */
public class _1_StackOverflowErrorMock {
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                stackOverflowError();
            } catch (Exception e) {
                e.printStackTrace();
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
        new CountDownLatch(1).await();
    }

    private static void stackOverflowError() {
        try {
            TimeUnit.NANOSECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stackOverflowError();
    }
}
