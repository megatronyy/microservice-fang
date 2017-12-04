package com.fang.cloud.controller;

import com.fang.cloud.entity.Customization;
import com.fang.cloud.mapper.CustomizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by quwb on 2017/12/4.
 */
@RestController
@RequestMapping("/user")
public class CustomizationController {
    @Autowired
    private CustomizationMapper customizationMapper;

    @RequestMapping("getcustom/{userId}")
    public List<Customization> GetCustomization(Integer userId){
        return customizationMapper.selectByUserId(userId);
    }

    @RequestMapping(value = "setcustom", method = { RequestMethod.POST })
    public int SetCustomization(@RequestBody Customization custom){
        return customizationMapper.insertSelective(custom);
    }
}
