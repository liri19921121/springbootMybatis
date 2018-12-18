package com.service.impl;

import com.mapper.TestForMapper;
import com.pojo.TestFor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class TestForService {

    @Autowired
    TestForMapper testForMapper;


    @Autowired
    private TestService testService;

    @Transactional
    public Object forTest(HttpServletRequest request) {
        Example example = new Example(TestFor.class);
        example.createCriteria().andEqualTo("isDown",1);
        List<TestFor> list = testForMapper.selectByExample(example);
        for (TestFor t : list){
            testService.update(t);
        }
        return "success";
    }


}
