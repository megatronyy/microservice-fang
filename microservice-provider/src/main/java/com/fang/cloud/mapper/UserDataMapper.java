package com.fang.cloud.mapper;

import com.fang.cloud.entity.UserData;

import java.util.Map;

/**
 * Created by quwb on 2017/12/5.
 */
public interface UserDataMapper {
    UserData getUserData(Map<String, Object> param);
}
