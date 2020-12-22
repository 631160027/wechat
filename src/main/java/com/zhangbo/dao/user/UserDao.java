package com.zhangbo.dao.user;

import com.zhangbo.po.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    public User findUserById(@Param("id") Integer id);

    void updateUser(User user);
}
