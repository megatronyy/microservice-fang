package com.fang.cloud.entity;

import java.io.Serializable;

/**
 * Created by quwb on 2017/11/28.
 */
public class BusinessType implements Serializable {
    private Integer BusinessID;
    private String BusinessDes;
    private Short SortNum;
    private Short IsShow;
    private String Icon;
    private String LargeIcon;

    public BusinessType(){
        super();
    }

    public BusinessType(Integer businessId, String businessDes, Short sortNum, Short isShow, String icon, String largeIcon){
        super();
        this.BusinessID = businessId;
        this.BusinessDes = businessDes;
        this.SortNum = sortNum;
        this.IsShow = isShow;
        this.Icon = icon;
        this.LargeIcon = largeIcon;
    }
    public Integer getBusinessID(){
        return this.BusinessID;
    }
    public  void setBusinessID(Integer businessId){
        this.BusinessID = businessId;
    }

    public String getBusinessDes(){
        return this.BusinessDes;
    }
    public  void setBusinessDes(String businessDes){
        this.BusinessDes = businessDes;
    }

    public Short getSortNum(){
        return this.SortNum;
    }
    public  void setSortNum(Short sortNum){
        this.SortNum = sortNum;
    }

    public Short getIsShow(){
        return this.IsShow;
    }
    public  void setIsShow(Short isShow){
        this.IsShow = isShow;
    }

    public String getIcon(){
        return this.Icon;
    }
    public  void setIcon(String icon){
        this.Icon = icon;
    }

    public String getLargeIcon(){
        return this.LargeIcon;
    }
    public  void setLargeIcon(String largeIcon){
        this.LargeIcon = largeIcon;
    }
}
