package com.fang.cloud.mapper;

import com.fang.cloud.entity.Announce;

import java.util.List;

public interface AnnounceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Announce record);

    int insertSelective(Announce record);

    Announce selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Announce record);

    int updateByPrimaryKey(Announce record);

    List<Announce> getAnnounceListByUserId(Integer userId);
}