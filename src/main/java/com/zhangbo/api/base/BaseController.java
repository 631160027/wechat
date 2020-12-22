package com.zhangbo.api.base;

import com.zhangbo.common.annotation.Authorization;
import com.zhangbo.common.result.JsonResult;
import com.zhangbo.common.result.ResultCode;
import com.zhangbo.po.base.WechatConfig;
import com.zhangbo.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BaseController
 * @Author zhangbo
 * @Date 2020/12/22 13:38
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/base")
public class BaseController {
    @Autowired
    private BaseService baseService;
    /**
     * @Description:
     * @Author: zhangbo
     * @Date: 2020/12/22 13:40
     * @return: com.zhangbo.common.result.JsonResult
     **/
    @Authorization
    @RequestMapping("/getWeChatInfo")
    public JsonResult getWeChatInfo(Integer id){
        WechatConfig weChatInfo = baseService.getWeChatInfo(id);
        if (weChatInfo==null){
            return new JsonResult(ResultCode.PARAMS_ERROR,ResultCode.PARAMS_ERROR.msg());
        }
        return new JsonResult(ResultCode.SUCCESS,ResultCode.SUCCESS.msg(),weChatInfo);
    }
}
