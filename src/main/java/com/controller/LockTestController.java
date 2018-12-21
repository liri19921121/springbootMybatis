package com.controller;

import com.mapper.TestForMapper;
import com.pojo.TestFor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping(value = {"/lockTest"})
public class LockTestController {
    @Autowired
    private TestForMapper testForMapper;


    @PostMapping("forUpdate")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public String testLock() throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        map.put("title",2);
        /*select <include refid="Base_Column_List" />
          from test_for where id = #{id} for update*/
        TestFor testFor = testForMapper.testLockForUpdate(map);
        System.out.println("1睡着了");
        Thread.sleep(20000);
        System.out.println("1睡醒了");
        System.out.println( testFor.getIsDown() );
        return "success";
    }

    @PostMapping("select")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public String select() throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        map.put("title",3);
        System.out.println("2开始");
        TestFor testFor = testForMapper.testLockForUpdate2(map);
        System.out.println("2结束");
        System.out.println( testFor.getIsDown() );
        return "success";
    }
}
