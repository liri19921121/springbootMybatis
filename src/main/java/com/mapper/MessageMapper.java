package com.mapper;

import com.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import CommonMapper.CommonMapper;

@Mapper
public interface MessageMapper extends CommonMapper<Message> {
}
