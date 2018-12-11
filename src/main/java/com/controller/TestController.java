package com.controller;

import com.mapper.GirlMapper;
import com.mapper.UserInfoMapper;
import com.pojo.Girl;
import com.pojo.Message;
import com.pojo.UserInfo;
import com.service.impl.GirlServiceImpl;
import com.service.impl.MessageServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private MessageServiceImpl messageService;

    @Autowired
    private GirlMapper girlMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private GirlServiceImpl girlService;


    @ApiOperation("baseService反射测试")
    @PostMapping(value = {"/BaseServiceAddTest"})
    @ResponseBody
    public String Message(HttpServletRequest request){
        Message message = new Message();
        message.setMessage("ceshi");
        messageService.add(message);
        return "success";
    }


    @ApiOperation("baseService加强版反射测试")
    @PostMapping(value = {"/BaseServicePlusAddTest"})
    @ResponseBody
    public String BaseServicePlusAddTest(HttpServletRequest request){
        Girl girl = new Girl();
        girl.setAge(100);
        girl.setCupSize("36");
        girlService.add(girl);
        return "success";
    }

    @ApiOperation("通用mapper测试")
    @PostMapping(value = {"/commonMapperTest"})
    @ResponseBody
    public Object commonMapperTest(HttpServletRequest request){
        List<Girl> girlList = girlMapper.selectAll();
        for (Girl p: girlList) {
            System.out.println(p.getAge());
        }

        List<UserInfo> userList = userInfoMapper.selectAll();
        for (UserInfo userInfo : userList){
            System.out.println(userInfo.getUsername());
        }
        return "success";
    }
}
