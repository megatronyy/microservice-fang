package com.fang.cloud.controller;

import com.fang.cloud.dao.response.ResponseEntity;
import com.fang.cloud.entity.BusinessType;
import com.fang.cloud.entity.UserData;
import com.fang.cloud.mapper.BusinessTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by quwb on 2017/12/5.
 */
@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private BusinessTypeMapper businessTypeMapper;

    @GetMapping("getbusiness/{businessId}")
    public ResponseEntity<BusinessType> getBusinessById(@PathVariable int businessId){
        BusinessType findOne = this.businessTypeMapper.selectByPrimaryKey(businessId);
        //返回结果
        if(findOne != null){
            return new ResponseEntity<BusinessType>(true, "获取用户信息成功", 0, "", "", findOne);
        }else{
            return new ResponseEntity<BusinessType>(false, "获取用户信息失败", -100, "", "", findOne);
        }
    }
}
