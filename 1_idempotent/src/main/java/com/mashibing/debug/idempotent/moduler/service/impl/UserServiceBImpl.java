package com.mashibing.debug.idempotent.moduler.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mashibing.debug.idempotent.moduler.entity.User;
import com.mashibing.debug.idempotent.moduler.mapper.UserMapper;
import com.mashibing.debug.idempotent.moduler.service.UserServiceB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserServiceBImpl implements UserServiceB {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void b() {
        User user = new User();

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(User::getIdCard, "1000");
        User user1 = userMapper.selectOne(wrapper);
        user1.setName("lisi");
        userMapper.updateById(user1);
        System.out.println(1 / 0);
    }
}
