package com.mashibing.debug.idempotent.moduler.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mashibing.debug.idempotent.moduler.entity.User;
import com.mashibing.debug.idempotent.moduler.mapper.UserMapper;
import com.mashibing.debug.idempotent.moduler.service.UserServiceA;
import com.mashibing.debug.idempotent.moduler.service.UserServiceB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserServiceAImpl implements UserServiceA {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserServiceB userServiceB;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void a() {
        try {
            userServiceB.b();
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = new User();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(User::getIdCard, "1000");
        User user1 = userMapper.selectOne(wrapper);
        user1.setName("zhangsan");
        userMapper.updateById(user1);
        //调用其他系统接口
        //B.service.b()
//        new Thread(() -> userServiceB.b()).start();

    }

    private void b() {
        User user = new User();

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(User::getIdCard, "1001");
        User user1 = userMapper.selectOne(wrapper);
        user1.setName("zhangsan");
        userMapper.updateById(user1);
    }
}
