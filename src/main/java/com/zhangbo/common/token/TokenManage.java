package com.zhangbo.common.token;


/**
 * @Description:JwtToken
 * @Author: zhangbo
 * @Date: 2020/12/22 14:55
 **/
public  interface TokenManage {
    /**
     * @Description:创建JwtToken
     * @Author: zhangbo
     * @Date: 2020/12/22 15:00
     * @return: com.zhangbo.common.token.TokenModel
     **/
    TokenModel createUserToken(Integer paramInteger);
    /**
     * @Description:检测JwtToken是否有效
     * @Author: zhangbo
     * @Date: 2020/12/22 15:01
     * @return: boolean
     **/
    boolean checkUserToken(String paramString);
    /**
     * @Description:获取用户token
     * @Author: zhangbo
     * @Date: 2020/12/22 15:01
     * @return: com.zhangbo.common.token.TokenModel
     **/
    TokenModel getUserToken(String paramString) ;
}