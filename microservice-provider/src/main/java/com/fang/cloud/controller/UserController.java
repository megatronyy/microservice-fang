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
 * Created by quwb on 2017/12/5.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDataMapper userDataMapper;

    @Autowired
    private CustomizationMapper customizationMapper;

    @RequestMapping("info/{userId}")
    public UserData getUserInfo(@PathVariable Integer userId){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userid", userId);
        UserData userData = userDataMapper.getUserData(param);
        return userData;
    }

    @RequestMapping("getcustom/{userId}")
    public List<Customization> GetCustomization(@PathVariable Integer userId){
        return customizationMapper.selectByUserId(userId);
    }

    @RequestMapping(value = "setcustom", method = { RequestMethod.POST })
    public int SetCustomization(@RequestBody Customization custom){
        return customizationMapper.insertSelective(custom);
    }
}
