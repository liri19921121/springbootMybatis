package com.mapper;

public interface messageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(message record);

    int insertSelective(message record);

    message selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(message record);

    int updateByPrimaryKey(message record);
}