package com.fang.cloud.controller;

import com.fang.cloud.dao.response.ResponseEntity;
import com.fang.cloud.dao.request.UserRequestEntity;
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
    public ResponseEntity<UserData> getUserInfo(@RequestBody UserRequestEntity user){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userid", user.getUserId());
        UserData userData = userDataMapper.getUserData(param);

        //返回结果
        if(userData != null){
            return new ResponseEntity<UserData>(true, "获取用户信息成功", 0, "", "", userData);
        }else{
            return new ResponseEntity<UserData>(false, "获取用户信息失败", -100, "", "", userData);
        }
    }

    @RequestMapping("getcustom/{userId}")
    public ResponseEntity<List<Customization>> getCustomization(@PathVariable Integer userId){
        List<Customization> list = customizationMapper.selectByUserId(userId);
        return new ResponseEntity<List<Customization>>(true, "获取用户定制信息成功", 0, "", "", list);
    }

    /**
     *
     * @param custom
     * @return
     */
    @RequestMapping(value = "setcustom", method = { RequestMethod.POST })
    public ResponseEntity<Integer> setCustomization(@RequestBody Customization custom){
        Integer ret = customizationMapper.insertSelective(custom);
        return new ResponseEntity<Integer>(true, "用户添加定制成功", 0, "", "", ret);
    }

    /**
     * 添加帐号
     * @param userAccount
     * @return
     */
    @RequestMapping(value = "add", method = { RequestMethod.POST })
    public ResponseEntity<Integer> addAccount(@RequestBody UserAccount userAccount){
        int isExists = userAccountMapper.isExistsForUser(userAccount.getMobile());
        if(isExists>0){
            return new ResponseEntity<Integer>(false, "添加用户信息失败", -100, "", "", 0);
        }else{
            Integer ret = userAccountMapper.insert(userAccount);
            return new ResponseEntity<Integer>(true, "手机号已经存在", 0, "", "", ret);
        }
    }

    /**
     * 更新帐号信息
     * @param userAccount
     * @return
     */
    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public ResponseEntity<Integer> updateAccount(@RequestBody UserAccount userAccount){
        Integer ret =  userAccountMapper.updateByPrimaryKey(userAccount);
        return new ResponseEntity<Integer>(true, "更新用户信息成功", 0, "", "", ret);
    }
}
