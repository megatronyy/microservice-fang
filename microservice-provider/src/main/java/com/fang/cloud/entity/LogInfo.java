package com.fang.cloud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by quwb on 2017/11/28.
 */
@Entity
@Table(name = "loginfo")
public class LogInfo {
    @Id
    @Column(name = "LogID")
    private Integer id;
    private Integer systemid;
    private Integer moduleid;
    private String infoid;
    private String ip;
    private Date accesstime;
    private Short isactive;

    public Integer getId(){
        return this.id;
    }
    public  void setId(Integer businessId){
        this.id = businessId;
    }

    public Integer getSystemId(){
        return this.systemid;
    }
    public  void setSystemId(Integer systemId){
        this.systemid = systemId;
    }

    public Integer getModuleId(){
        return this.moduleid;
    }
    public  void setModuleId(Integer moduleId){
        this.moduleid = moduleId;
    }

    public String getInfoId(){
        return this.infoid;
    }
    public  void setInfoId(String infoId){
        this.infoid = infoId;
    }

    public String getIp(){
        return this.ip;
    }
    public  void setIp(String ip){
        this.ip = ip;
    }

    public Date getAccessTime(){
        return this.accesstime;
    }

    public void setAccessTime(Date accessTime){
        this.accesstime = accessTime;
    }

    public Short getIsActive(){
        return this.isactive;
    }
    public  void setIsActive(Short isActive){
        this.isactive = isActive;
    }
}
