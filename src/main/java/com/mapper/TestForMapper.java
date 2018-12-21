package com.mapper;

import CommonMapper.CommonMapper;
import com.pojo.TestFor;

import java.util.HashMap;

public interface TestForMapper extends CommonMapper<TestFor> {

    public TestFor testLockForUpdate(HashMap<String, Object> argmap);

    public TestFor testLockForUpdate2(HashMap<String, Object> argmap);

}