package com.zhangbo.po.base;

import lombok.Data;

/**
 * @ClassName WechatConfig
 * @Author zhangbo
 * @Date 2020/12/22 13:44
 * @Version 1.0
 **/
@Data
public class WechatConfig {
    private Integer id;
    private String appId;
    private String name;
    private String appSecret;
}
