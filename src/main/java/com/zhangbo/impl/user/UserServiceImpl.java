package com.zhangbo.impl.user;

import com.zhangbo.dao.user.UserDao;
import com.zhangbo.po.user.User;
import com.zhangbo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Author zhangbo
 * @Date 2020/12/22 15:22
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
