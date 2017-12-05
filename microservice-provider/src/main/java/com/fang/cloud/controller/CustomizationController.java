package com.fang.cloud.controller;

import com.fang.cloud.entity.Customization;
import com.fang.cloud.entity.UserData;
import com.fang.cloud.mapper.CustomizationMapper;
import com.fang.cloud.mapper.UserDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by quwb on 2017/12/4.
 */
@RestController
@RequestMapping("/user")
public class CustomizationController {
    @Autowired
    private CustomizationMapper customizationMapper;

    @Autowired
    private UserDataMapper userDataMapper;

    @RequestMapping("getcustom/{userId}")
    public List<Customization> GetCustomization(@PathVariable Integer userId){
        return customizationMapper.selectByUserId(userId);
    }

    @RequestMapping(value = "setcustom", method = { RequestMethod.POST })
    public int SetCustomization(@RequestBody Customization custom){
        return customizationMapper.insertSelective(custom);
    }

    @RequestMapping("getshoplist/{userId}")
    public UserData getUserShopList(@PathVariable Integer userId){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("UserId", userId);
        UserData userData = userDataMapper.getUserData(userId);
        return userData;
    }
}
