package com.zhangbo.common.interceptor;

import com.zhangbo.common.annotation.Authorization;
import com.zhangbo.common.result.HttpErrorReturn;
import com.zhangbo.common.token.TokenManage;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * @Description:登录验证拦截器
 * @Author: zhangbo
 * @Date: 2020/12/22 14:54
 **/
@Component
@Slf4j
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TokenManage manager;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Content-Type,Accept,token");
        response.setHeader("Access-Control-Expose-Headers", "token");
        response.setHeader("Access-Control-Allow-Method", "GET,HEAD,POST,PUT,DELETE,OPTIONS,PATCH");
        response.setHeader("Access-Control-Expose-Headers", "*");

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Authorization annotation = (Authorization) method.getAnnotation(Authorization.class);
        if (annotation == null) {
            return true;
        }
        String token = request.getHeader("token");

        if (StringUtils.isEmpty(token)) {
            HttpErrorReturn.writeNoLogin(response.getOutputStream());
            return false;
        }
        try {
            if (this.manager.checkUserToken(token)) {
                request.setAttribute("userId", Integer.valueOf(this.manager.getUserToken(token).getUserId()));
                return true;
            }
        } catch (Exception e) {
            HttpErrorReturn.writeNoLogin(response.getOutputStream());
            return false;
        }
        HttpErrorReturn.writeNoLogin(response.getOutputStream());
        return false;
    }
}