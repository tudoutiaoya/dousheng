package com.zzqedu.dousheng.mapper;

import com.zzqedu.dousheng.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    void add(@Param("name") String name,@Param("age") String age);

    User getOne();

}
