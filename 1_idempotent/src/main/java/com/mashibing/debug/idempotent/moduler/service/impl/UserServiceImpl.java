package com.mashibing.debug.idempotent.moduler.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mashibing.debug.idempotent.moduler.entity.User;
import com.mashibing.debug.idempotent.moduler.mapper.UserMapper;
import com.mashibing.debug.idempotent.moduler.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedissonClient redissonClient;

    /**
     * 接口需要幂等，此处身份证号不允许重复
     *
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addError(User user) {
        log.info("add user params user:{}", JSON.toJSONString(user));
        Assert.isTrue(StringUtils.isNotBlank(user.getIdCard()), "身份证号不允许null");
        String key = "key";
        RLock lock = redissonClient.getLock(key);
        lock.lock();
        try {
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                    .eq(User::getIdCard, user.getIdCard());
            long count = userMapper.selectCount(wrapper);
            if (count == 0) {
                userMapper.insert(user);
            }
        } catch (Exception e) {
            log.error("add user error", e);
        } finally {
            lock.unlock();
        }
        System.out.println("并发执行，同时插入了两条");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addOk(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(User::getIdCard, user.getIdCard());
        long count = userMapper.selectCount(wrapper);
        if (count == 0) {
            userMapper.insert(user);
        }
    }
}
