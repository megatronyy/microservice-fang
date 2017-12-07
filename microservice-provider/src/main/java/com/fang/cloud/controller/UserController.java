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
        ResponseEntity<UserData> responseEntity = new ResponseEntity<UserData>();
        if(userData != null){
            responseEntity.setIsSuccess(true);
            responseEntity.setMessage("获取用户信息成功");
            responseEntity.setCode(0);
            responseEntity.setSign("");
            responseEntity.setAppId("");
            responseEntity.setData(userData);
            return responseEntity;
        }else{
            responseEntity.setIsSuccess(false);
            responseEntity.setMessage("获取用户信息失败");
            responseEntity.setCode(100);
            responseEntity.setSign("");
            responseEntity.setAppId("");
            responseEntity.setData(userData);
            return responseEntity;
        }
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
