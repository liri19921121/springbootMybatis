package com.controller;

import com.mapper.GirlMapper;
import com.mapper.UserInfoMapper;
import com.pojo.Girl;
import com.pojo.Message;
import com.pojo.UserInfo;
import com.service.impl.GirlServiceImpl;
import com.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = {"/index"})
public class IndexController {


    @Autowired
    private MessageServiceImpl messageService;

    @Autowired
    private GirlMapper girlMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private GirlServiceImpl girlService;

    @RequestMapping(value = {"/love"})
    public String setRedisData(HttpServletRequest request){
        return "love";
    }

    @PostMapping(value = {"/BaseServiceAddTest"})
    @ResponseBody
    public String Message(HttpServletRequest request){
        messageService.add();
        return "success";
    }

    @PostMapping(value = {"/BaseServicePlusAddTest"})
    @ResponseBody
    public String BaseServicePlusAddTest(HttpServletRequest request){
        Girl girl = new Girl();
        girl.setAge(100);
        girl.setCupSize("36");
        girlService.add(girl);
        return "success";
    }


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
