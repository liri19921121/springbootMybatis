package com.mapper;

import com.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import CommonMapper.CommonMapper;

@Mapper
public interface MessageMapper2 extends CommonMapper<Message> {
}
