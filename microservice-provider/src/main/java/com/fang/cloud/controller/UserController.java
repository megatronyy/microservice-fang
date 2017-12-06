package com.fang.cloud.controller;

import com.fang.cloud.entity.Customization;
import com.fang.cloud.entity.UserAccount;
import com.fang.cloud.entity.UserData;
import com.fang.cloud.mapper.CustomizationMapper;
import com.fang.cloud.mapper.UserAccountMapper;
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

    @Autowired
    private UserAccountMapper userAccountMapper;

    @RequestMapping(value = "info", method = { RequestMethod.POST })
    public UserData getUserInfo(@RequestBody Integer userId){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userid", userId);
        UserData userData = userDataMapper.getUserData(param);
        return userData;
    }

    @RequestMapping("getcustom/{userId}")
    public List<Customization> getCustomization(@PathVariable Integer userId){
        return customizationMapper.selectByUserId(userId);
    }

    /**
     *
     * @param custom
     * @return
     */
    @RequestMapping(value = "setcustom", method = { RequestMethod.POST })
    public int setCustomization(@RequestBody Customization custom){
        return customizationMapper.insertSelective(custom);
    }

    /**
     * 添加帐号
     * @param userAccount
     * @return
     */
    @RequestMapping(value = "add", method = { RequestMethod.POST })
    public int addAccount(@RequestBody UserAccount userAccount){
        return userAccountMapper.insert(userAccount);
    }

    /**
     * 更新帐号信息
     * @param userAccount
     * @return
     */
    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public int updateAccount(@RequestBody UserAccount userAccount){
        return userAccountMapper.updateByPrimaryKey(userAccount);
    }
}
