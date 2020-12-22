package com.zhangbo.common.token;
/**
 * @Description:JwtToken管理器
 * @Author: zhangbo
 * @Date: 2020/12/22 14:59
 **/
public class TokenModel {
    private int userId;
    private String token;

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof TokenModel)) return false;
        TokenModel other = (TokenModel) o;
        if (!other.canEqual(this)) return false;
        if (getUserId() != other.getUserId()) return false;
        Object this$token = getToken();
        Object other$token = other.getToken();
        return this$token == null ? other$token == null : this$token.equals(other$token);
    }

    protected boolean canEqual(Object other) {
        return other instanceof TokenModel;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + getUserId();
        Object $token = getToken();
        result = result * 59 + ($token == null ? 43 : $token.hashCode());
        return result;
    }

    public String toString() {
        return "TokenModel(userId=" + getUserId() + ", token=" + getToken() + ")";
    }
    public TokenModel(int userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}