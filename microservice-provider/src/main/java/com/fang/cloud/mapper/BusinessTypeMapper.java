package com.fang.cloud.mapper;

import com.fang.cloud.entity.BusinessType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by quwb on 2017/12/1.
 */
public interface BusinessTypeMapper {

    @Select("SELECT * FROM BusinessType (NOLOCK)")
    List<BusinessType> getAll();

    @Select("SELECT * FROM BusinessType (NOLOCK) WHERE BusinessID = #{businessId}")
    BusinessType getOne(Integer businessId);
}
