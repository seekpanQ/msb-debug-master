package com.mashibing.debug.idempotent.moduler;

import com.mashibing.debug.idempotent.ApplicationTests;
import com.mashibing.debug.idempotent.moduler.entity.User;
import com.mashibing.debug.idempotent.moduler.mapper.UserMapper;
import com.mashibing.debug.idempotent.moduler.service.UserService;
import com.mashibing.debug.idempotent.moduler.service.UserServiceA;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;

public class UserServiceTest extends ApplicationTests {

    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserServiceA userServiceA;

    @Resource
    private RedissonClient redissonClient;

    @Test
    public void addTest() {
        User user = new User();
        user.setName("张三");
        user.setIdCard("1000");
        //反例
//        userService.addOk(user);
        //正例
        String key = "key";
        RLock lock = redissonClient.getLock(key);
        lock.lock();
        try {
            userService.addOk(user);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    @Test
    public void testTransactional() {
        userServiceA.a();
    }


}
