package com.controller;

import com.components.token.TokenManager;
import com.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = {"/user"})
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private TokenManager tokenManager;

    @ApiOperation(value = "查询所有用户",notes = "查询用户所有信息")
    @RequestMapping(value = {"/findAll"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public List getAllUsers(){
       List list =  userService.findAllUser();
       return list;
    }

    @RequestMapping(value = {"/redis"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public String setRedisData(){
        tokenManager.setToken("tokenKey","tokenValue",null);
        return "success";
    }
}
