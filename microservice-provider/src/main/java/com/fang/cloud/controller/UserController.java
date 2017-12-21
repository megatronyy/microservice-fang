package com.fang.cloud.controller;

import com.fang.cloud.common.Security;
import com.fang.cloud.dao.response.ResponseEntity;
import com.fang.cloud.dao.request.UserRequestEntity;
import com.fang.cloud.entity.*;
import com.fang.cloud.mapper.*;
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

    @Autowired
    private MsgInfoMapper msgInfoMapper;

    @Autowired
    private MobileCodeInfoMapper mobileCodeInfoMapper;

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
            return new ResponseEntity<Integer>(false, "手机号已经存在", -100, "", "", 0);
        }else{
            //对密码进行md5加密
            userAccount.setPassword(Security.getMD5(userAccount.getPassword()));
            Integer ret = userAccountMapper.insert(userAccount);
            return new ResponseEntity<Integer>(true, "添加用户成功", 0, "", "", ret);
        }
    }

    /**
     * 更新帐号信息
     * @param userAccount
     * @return
     */
    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public ResponseEntity<Integer> updateAccount(@RequestBody UserAccount userAccount){
        //对密码进行md5加密
        userAccount.setMobile(Security.getMD5(userAccount.getMobile()));
        Integer ret =  userAccountMapper.updateByPrimaryKey(userAccount);
        return new ResponseEntity<Integer>(true, "更新用户信息成功", 0, "", "", ret);
    }

    /**
     * 用户登录
     * @param mobile
     * @return
     */
    @RequestMapping(value = "login")
    public ResponseEntity<UserAccount> Login(@RequestParam String mobile, @RequestParam String pwd){
        if(mobile.isEmpty() || pwd.isEmpty()){
            return new ResponseEntity<UserAccount>(false, "传入的参数有误", -99, "", "", null);
        }

        String md5Pwd = Security.getMD5(pwd);

        Map<String, String> param = new HashMap<String, String>();
        param.put("mobile", mobile);
        param.put("password", md5Pwd);

        UserAccount userAccount = userAccountMapper.selectForLogin(param);
        if(userAccount != null)
            return new ResponseEntity<UserAccount>(true, "用户登录成功", 0, "", "", userAccount);
        else
            return new ResponseEntity<UserAccount>(false, "用户名或密码错误", -100, "", "", null);
    }

    /**
     * 发送短信
     * @param msgInfo
     * @return
     */
    @RequestMapping(value = "send")
    public ResponseEntity<Integer> sendMsg(@RequestBody MsgInfo msgInfo){
        if(msgInfo == null || msgInfo.getMobile()==""){
            return new ResponseEntity<Integer>(false, "传入的参数有误", -99, "", "", 0);
        }

        Integer ret = msgInfoMapper.insertSelective(msgInfo);
        if(ret == 0)
            return new ResponseEntity<Integer>(false, "发送短信失败", -99, "", "", ret);
        else
            return new ResponseEntity<Integer>(true, "发送短信成功", 0, "", "", ret);
    }

    @RequestMapping(value = "code")
    public ResponseEntity<Integer> getCode(@RequestBody MobileCodeInfo mobileCodeInfo){
        if(mobileCodeInfo == null || mobileCodeInfo.getMobile()==""){
            return new ResponseEntity<Integer>(false, "传入的参数有误", -99, "", "", 0);
        }

        Integer ret = mobileCodeInfoMapper.insertSelective(mobileCodeInfo);
        if(ret == 0)
            return new ResponseEntity<Integer>(false, "添加验证码失败", -99, "", "", ret);
        else
            return new ResponseEntity<Integer>(true, "添加验证码成功", 0, "", "", ret);
    }
}
