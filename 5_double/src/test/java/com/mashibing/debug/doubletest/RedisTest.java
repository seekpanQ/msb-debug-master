package com.mashibing.debug.doubletest;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RedisTest {

    @Test
    public void redisLock() {
        RedissonClient redissonClient = getSingleServerClient();
        String key = "key";
        RLock lock = redissonClient.getLock(key);
        try {
            lock.lock();
            lock.lock();
            lock.lock();
            System.out.println("...");
        } finally {
            lock.unlock();
            lock.unlock();
            lock.unlock();
        }
    }

    public RedissonClient getSingleServerClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
        return Redisson.create(config);
    }

    public void saveUser() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        try {
            lock.lock();
            //业务逻辑
            //判断是否存在
            //存在：update
            //不存在，save
        } finally {
            lock.unlock();
        }

    }
}
