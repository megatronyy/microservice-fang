package com.fang.cloud.controller;

import com.fang.cloud.common.CommonLib;
import com.fang.cloud.common.Security;
import com.fang.cloud.dao.response.ResponseEntity;
import com.fang.cloud.dao.request.UserRequestEntity;
import com.fang.cloud.entity.*;
import com.fang.cloud.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @Autowired
    private AnnounceMapper announceMapper;

    @RequestMapping(value = "info", method = {RequestMethod.POST})
    public String getUserInfo(@RequestBody UserRequestEntity user) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userid", user.getUserId());
        UserData userData = userDataMapper.getUserData(param);

        //返回结果
        if (userData != null) {
            return new ResponseEntity<UserData>(true, "获取用户信息成功", 0, "", "", userData).toString();
        } else {
            return new ResponseEntity<UserData>(false, "获取用户信息失败", -100, "", "", userData).toString();
        }
    }

    @RequestMapping("getcustom/{userId}")
    public String getCustomization(@PathVariable Integer userId) {
        List<Customization> list = customizationMapper.selectByUserId(userId);
        return new ResponseEntity<List<Customization>>(true, "获取用户定制信息成功", 0, "", "", list).toString();
    }

    /**
     * @param custom
     * @return
     */
    @RequestMapping(value = "setcustom", method = {RequestMethod.POST})
    public String setCustomization(@RequestBody Customization custom) {
        Integer ret = customizationMapper.insertSelective(custom);
        return new ResponseEntity<Integer>(true, "用户添加定制成功", 0, "", "", ret).toString();
    }

    /**
     * 添加帐号
     *
     * @param userAccount
     * @return
     */
    @RequestMapping(value = "add", method = {RequestMethod.POST})
    public String addAccount(@RequestBody UserAccount userAccount) {
        int isExists = userAccountMapper.isExistsForUser(userAccount.getMobile());
        if (isExists > 0) {
            return new ResponseEntity<Integer>(true, "手机号已经存在", -100, "", "", 0).toString();
        } else {
            //对密码进行md5加密
            userAccount.setPassword(Security.getMD5(userAccount.getPassword()));
            Integer ret = userAccountMapper.insert(userAccount);
            return new ResponseEntity<Integer>(true, "添加用户成功", 0, "", "", ret).toString();
        }
    }

    /**
     * 更新帐号信息
     *
     * @param userAccount
     * @return
     */
    @RequestMapping(value = "update", method = {RequestMethod.POST})
    public String updateAccount(@RequestBody UserAccount userAccount) {
        //对密码进行md5加密
        userAccount.setMobile(Security.getMD5(userAccount.getMobile()));
        Integer ret = userAccountMapper.updateByPrimaryKey(userAccount);
        return new ResponseEntity<Integer>(true, "更新用户信息成功", 0, "", "", ret).toString();
    }

    /**
     * 用户登录
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "login", method = {RequestMethod.GET})
    public String Login(@RequestParam String mobile, @RequestParam String pwd) {
        if (mobile.isEmpty() || pwd.isEmpty()) {
            return new ResponseEntity<UserAccount>(false, "传入的参数有误", -99, "", "", null).toString();
        }

        String md5Pwd = Security.getMD5(pwd);

        Map<String, String> param = new HashMap<String, String>();
        param.put("mobile", mobile);
        param.put("password", md5Pwd);

        UserAccount userAccount = userAccountMapper.selectForLogin(param);
        if (userAccount != null) {
            userAccount.setPassword("");
            return new ResponseEntity<UserAccount>(true, "用户登录成功", 0, "", "", userAccount).toString();
        } else {
            return new ResponseEntity<UserAccount>(false, "用户名或密码错误", -100, "", "", null).toString();
        }
    }

    /**
     * 发送短信
     *
     * @param msgInfo
     * @return
     */
    @RequestMapping(value = "send", method = {RequestMethod.POST})
    public String sendMsg(@RequestBody MsgInfo msgInfo) {
        if (msgInfo == null || msgInfo.getMobile() == "") {
            return new ResponseEntity<Integer>(true, "传入的参数有误", -99, "", "", 0).toString();
        }

        Integer ret = msgInfoMapper.insertSelective(msgInfo);
        if (ret == 0)
            return new ResponseEntity<Integer>(true, "发送短信失败", -99, "", "", ret).toString();
        else
            return new ResponseEntity<Integer>(true, "发送短信成功", 0, "", "", ret).toString();
    }

    /**
     * 添加验证码
     *
     * @param
     * @return
     */
    @RequestMapping(value = "setcode", method = {RequestMethod.POST})
    public String setCode(@RequestBody MobileCodeInfo mobileCodeInfo) {
        if (mobileCodeInfo == null || mobileCodeInfo.getMobile() == "") {
            return new ResponseEntity<Integer>(false, "传入的参数有误", -99, "", "", 0).toString();
        }

        //判断已发送的验证码是否过期
        MobileCodeInfo preInfo = mobileCodeInfoMapper.selectByParas(mobileCodeInfo.getMobile());
        if (preInfo != null) {
            Date preDate = preInfo.getCreatetime();
            Date newDate = mobileCodeInfo.getCreatetime();

            long min = CommonLib.diffDate(preDate, newDate);
            if (min < 30) {
                return new ResponseEntity<Integer>(true, "已发送验证码30分钟内有效", 1, "", "", 0).toString();
            }
        }
        //插入验证码
        Integer ret = mobileCodeInfoMapper.insertSelective(mobileCodeInfo);
        if (ret == 0)
            return new ResponseEntity<Integer>(true, "添加验证码失败", -99, "", "", ret).toString();
        else
            return new ResponseEntity<Integer>(true, "添加验证码成功", 0, "", "", ret).toString();
    }

    /**
     * 验证验证码
     *
     * @param
     * @return
     */
    @RequestMapping(value = "vercode", method = {RequestMethod.POST})
    public String verifyCode(@RequestBody MobileCodeInfo mobileCodeInfo) {
        if (mobileCodeInfo == null || mobileCodeInfo.getMobile() == "") {
            return new ResponseEntity<Integer>(true, "传入的参数有误", -99, "", "", 0).toString();
        }

        MobileCodeInfo preInfo = mobileCodeInfoMapper.selectByParas(mobileCodeInfo.getMobile());
        if (preInfo == null)
            return new ResponseEntity<Integer>(true, "未找到验证码", -99, "", "", 0).toString();
        if (mobileCodeInfo.getMobile().equals(preInfo.getMobile()) &&
                mobileCodeInfo.getCode().equals(preInfo.getCode()))
            return new ResponseEntity<Integer>(true, "验证通过", 0, "", "", 0).toString();

        return new ResponseEntity<Integer>(true, "验证失败", -99, "", "", 0).toString();

    }

    @RequestMapping(value = "/announces/{userId}", method = {RequestMethod.GET})
    public String getAnnounceListByUserId(@PathVariable Integer userId) {
        List<Announce> list = this.announceMapper.getAnnounceListByUserId(userId);
        return new ResponseEntity<List<Announce>>(true, "", 0, "", "", list).toString();
    }
}
