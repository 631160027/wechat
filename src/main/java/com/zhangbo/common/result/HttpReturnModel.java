package com.zhangbo.common.result;


public class HttpReturnModel {
    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof HttpReturnModel)) return false;
        HttpReturnModel other = (HttpReturnModel) o;
        if (!other.canEqual(this)) return false;
        if (getStatus() != other.getStatus()) return false;
        Object this$message = getMessage();
        Object other$message = other.getMessage();
        return this$message == null ? other$message == null : this$message.equals(other$message);
    }

    protected boolean canEqual(Object other) {
        return other instanceof HttpReturnModel;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + getStatus();
        Object $message = getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        return result;
    }

    public String toString() {
        return "HttpReturnModel(status=" + getStatus() + ", message=" + getMessage() + ")";
    }

    public HttpReturnModel(int status, String message) {
        this.status = status;
        this.message = message;
    }


    public int getStatus() {
        return this.status;
    }

    private int status = 200;

    public String getMessage() {
        return this.message;
    }

    private String message = "SUCCESS";

    public HttpReturnModel() {
    }
}