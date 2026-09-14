package com.mashibing.debug.memory3;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 内存泄漏模拟
 * jmap -dump:format=b,file=D:/workspace/mashibing/msb-debug/dump.hprof pid
 *
 * @author sunzhiqiang
 * @date 2021/11/26 21:56:42
 */
@Data
public class MemoryLeakMock {
    private List<Object> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        MemoryLeakMock memoryLeakMock = new MemoryLeakMock();
        long num = 1 * 1024 * 1024;
        while (num > 0) {
            memoryLeakMock.getList().add(new byte[1024]);
            num--;
        }
        new CountDownLatch(1).await();
    }
}
