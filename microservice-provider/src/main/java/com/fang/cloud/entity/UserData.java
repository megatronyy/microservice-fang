package com.fang.cloud.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by quwb on 2017/12/5.
 */
public class UserData {
    private Integer userid;
    private String username;
    private String openid;
    private String password;
    private String mobile;
    private Date lastlogintime;
    private List<ShopInfo> shopInfo;
    private List<PublisherInfo> publisherInfo;

    public Integer getUserid() {
        return userid;
    }
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }
    public Date getLastlogintime() {
        return lastlogintime;
    }
    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }
    public List<ShopInfo> getShopInfo(){return this.shopInfo;}
    public void setShopInfo(List<ShopInfo> shopInfo){this.shopInfo=shopInfo;}
    public List<PublisherInfo> getPublisherInfo(){return this.publisherInfo;}
    public void setPublisherInfo(List<PublisherInfo> publisherInfo){this.publisherInfo=publisherInfo;}
}
