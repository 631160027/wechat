package com.zhangbo.api.user;

import com.zhangbo.common.result.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Test
 * @Author zhangbo
 * @Date 2020/12/22 12:00
 * @Version 1.0
 **/
@RequestMapping("/wechat")
@RestController("/wechat")
public class Test {
    @RequestMapping("/test")
    public JsonResult test(){
        return new JsonResult();
    }
}
