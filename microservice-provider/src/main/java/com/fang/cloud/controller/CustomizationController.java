package com.fang.cloud.controller;

import com.fang.cloud.entity.Customization;
import com.fang.cloud.mapper.CustomizationMapper;
import com.fang.cloud.mapper.UserShopRelaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by quwb on 2017/12/4.
 */
@RestController
@RequestMapping("/user")
public class CustomizationController {
    @Autowired
    private CustomizationMapper customizationMapper;

    @Autowired
    private UserShopRelaMapper userShopRelaMapper;

    @RequestMapping("getcustom/{userId}")
    public List<Customization> GetCustomization(@PathVariable Integer userId){
        return customizationMapper.selectByUserId(userId);
    }

    @RequestMapping(value = "setcustom", method = { RequestMethod.POST })
    public int SetCustomization(@RequestBody Customization custom){
        return customizationMapper.insertSelective(custom);
    }

    @RequestMapping("getshoplist/{userId}")
    public ResultSet getUserShopList(@PathVariable Integer userId){
        return userShopRelaMapper.getUserShopRela(userId);
    }
}
