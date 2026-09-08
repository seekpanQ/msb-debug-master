package com.mashibing.debug.idempotent.moduler.service;

import com.mashibing.debug.idempotent.moduler.entity.User;

public interface UserService {

    void addError(User user);

    void addOk(User user);
}
