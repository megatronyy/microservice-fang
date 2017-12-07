package com.fang.cloud.dao;

/**
 * Created by quwb on 2017/12/6.
 */
public class ResponseEntity<T> {
    private boolean isSuccess;
    private String message;
    private int code;
    private String sign;
    private String appId;
    private T data;

    public boolean getIsSuccess(){ return this.isSuccess; }
    public void setIsSuccess(boolean isSuccess){ this.isSuccess = isSuccess; }

    public String getMessage(){ return this.message; }
    public void setMessage(String message){ this.message = message; }

    public int getCode(){ return this.code; }
    public void setCode(int code){ this.code = code; }

    public String getSign(){ return this.sign; }
    public void setSign(String sign){ this.sign = sign; }

    public String getAppId(){ return this.appId; }
    public void setAppId(String appId){ this.appId = appId; }

    public T getData(){ return this.data; }
    public void setData(T data){ this.data = data; }
}
