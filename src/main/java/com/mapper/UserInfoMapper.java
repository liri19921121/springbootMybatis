package com.mapper;

import com.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import CommonMapper.CommonMapper;

@Mapper
public interface UserInfoMapper extends CommonMapper<UserInfo> {

}