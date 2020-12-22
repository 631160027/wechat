package com.zhangbo.service.user;

import com.zhangbo.po.user.User;

public interface UserService {
    User findUserById(Integer id);

    void updateUser(User user);
}
