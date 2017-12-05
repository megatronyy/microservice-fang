package com.fang.cloud.controller;

import com.fang.cloud.entity.UserData;
import com.fang.cloud.mapper.UserDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by quwb on 2017/12/5.
 */
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDataMapper userDataMapper;

    @RequestMapping("info/{userId}")
    public UserData getUserInfo(@PathVariable Integer userId){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userid", userId);
        UserData userData = userDataMapper.getUserData(param);
        return userData;
    }
}
