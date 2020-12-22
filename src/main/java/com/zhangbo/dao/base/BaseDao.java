package com.zhangbo.dao.base;

import com.zhangbo.po.base.WechatConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface BaseDao {
    public WechatConfig getWeChatInfo(@Param("id") Integer id);
}
