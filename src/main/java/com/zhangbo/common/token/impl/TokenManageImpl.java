package com.zhangbo.common.token.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import com.zhangbo.common.token.TokenManage;
import com.zhangbo.common.token.TokenModel;
import com.zhangbo.po.user.User;
import com.zhangbo.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
/**
 * @Description:
 * @Author: zhangbo
 * @Date: 2020/12/22 15:05
 * @return: null
 **/
@AllArgsConstructor
@org.springframework.stereotype.Service
public class TokenManageImpl implements TokenManage {
    @Autowired
    private UserService userService;
    private static final String JWT_KEY = "...//....///....///.//////";
    private static final String JWT_SECRET = "f*H*(^l&{:L6adf*&93dSfrff8)%";

    public static void main(String[] args) {
        System.out.println(JWT.create().withIssuer(JWT_KEY).withJWTId(UUID.randomUUID().toString().toUpperCase()).withClaim("user", 1).sign(Algorithm.HMAC256(JWT_SECRET)));

    }
    @Override
    public TokenModel createUserToken(Integer userId) {
        String token = null;
        try {
            token = JWT.create().withIssuer(JWT_KEY).withJWTId(java.util.UUID.randomUUID().toString().toUpperCase()).withClaim("user", userId).sign(Algorithm.HMAC256(JWT_SECRET));
        } catch (Exception e) {
            token = "error";
        }
        TokenModel model = new TokenModel(userId.intValue(), token);
        User user = userService.findUserById(userId);
        user.setToken(token);
        userService.updateUser(user);
        return model;
    }

    @Override
    public boolean checkUserToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        try {
            TokenModel tokenModel = getUserToken(token);
            String dbtoken = null;
            User user = null;
            if (!StringUtils.isEmpty(tokenModel)) {
                user = userService.findUserById(Integer.valueOf(tokenModel.getUserId()));
            }
            if (user != null) {
                dbtoken = user.getToken();
            }
            return (dbtoken != null) && (token.equals(dbtoken));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public TokenModel getUserToken(String token) {
        com.auth0.jwt.JWTVerifier verifier = null ;
        try {
            verifier = JWT.require(Algorithm.HMAC256(JWT_SECRET)).withIssuer(JWT_KEY).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (verifier == null) {
            return null;
        }
        DecodedJWT jwt = verifier.verify(token);
        Integer adminId = jwt.getClaim("user").asInt();
        if (adminId == null) {
            return null;
        }
        return new TokenModel(adminId, jwt.getToken());
    }
}