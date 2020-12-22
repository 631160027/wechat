package com.zhangbo.impl.base;

import com.zhangbo.dao.base.BaseDao;
import com.zhangbo.po.base.WechatConfig;
import com.zhangbo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName BaseServiceImpl
 * @Author zhangbo
 * @Date 2020/12/22 13:47
 * @Version 1.0
 **/
@Service
public class BaseServiceImpl implements BaseService {
    @Autowired
    private BaseDao baseDao;
    @Override
    public WechatConfig getWeChatInfo(Integer id) {
        WechatConfig weChatInfo = baseDao.getWeChatInfo(id);
        return weChatInfo;
    }
}
