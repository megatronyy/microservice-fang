package com.fang.cloud.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by quwb on 2017/11/28.
 */
@Component
@ConfigurationProperties(prefix="myProps")
public class Config {
    private String accesstoken;

    public String getAccessToken(){
        return accesstoken;
    }

    public  void setAccessToken(String accessToken){
        this.accesstoken = accessToken;
    }
}
