package com.service.impl;

import com.common.constant.MovieResourceType;
import com.mapper.DomesticResourceMapper;
import com.mapper.TestForMapper;
import com.pojo.DomesticResource;
import com.pojo.ResourceMovieTitle;
import com.pojo.TestFor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {

    @Autowired
    private TestForMapper testForMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void update(TestFor t){
        t.setIsDown("2");
        testForMapper.updateByPrimaryKeySelective(t);
    }
}
