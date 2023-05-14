package com.zzqedu.dousheng.dao.mapper;

import com.zzqedu.dousheng.dao.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    User selectByName(@Param("name") String name);

    List<User> selectAll();
}