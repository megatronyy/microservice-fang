package com.fang.cloud.controller;

import com.fang.cloud.entity.BusinessType;
import com.fang.cloud.repository.BusinessTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by quwb on 2017/11/28.
 */
@RestController
public class BusinessTypeController {
    @Autowired
    private BusinessTypeRepository businessTypeRepository;

    @GetMapping("/business/{businessId}")
    public BusinessType findById(@PathVariable int businessId){
        BusinessType findOne = this.businessTypeRepository.findOne(businessId);
        return findOne;
    }
}
