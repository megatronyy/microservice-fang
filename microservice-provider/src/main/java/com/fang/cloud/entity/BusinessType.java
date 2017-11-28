package com.fang.cloud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by quwb on 2017/11/28.
 */
@Entity
@Table(name = "businesstype")
public class BusinessType {
    @Id
    @Column(name = "BusinessID")
    private Integer id;
    private String businessdes;
    private Short sortnum;
    private Short isshow;
    private String icon;
    private String largeicon;

    public Integer getId(){
        return this.id;
    }
    public  void setId(Integer businessId){
        this.id = businessId;
    }

    public String getBusinessDes(){
        return this.businessdes;
    }
    public  void setBusinessDes(String businessDes){
        this.businessdes = businessDes;
    }

    public Short getSortNum(){
        return this.sortnum;
    }
    public  void setSortNum(Short sortNum){
        this.sortnum = sortNum;
    }

    public Short getIsShow(){
        return this.isshow;
    }
    public  void setIsShow(Short isShow){
        this.isshow = isShow;
    }

    public String getIcon(){
        return this.icon;
    }
    public  void setIcon(String icon){
        this.icon = icon;
    }

    public String getLargeIcon(){
        return this.largeicon;
    }
    public  void setLargeIcon(String largeIcon){
        this.largeicon = largeIcon;
    }
}
