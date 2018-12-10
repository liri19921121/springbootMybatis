package com.service.impl;

import com.dao.UserInfoMapper;
import com.pojo.UserInfo;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserInfoMapper userDao;//这里可能会报错，但是并不会影响

    public List findAllUser(){
       return  userDao.findAllUser();
    }

    public UserInfo getUserByid(Long id){
       return userDao.getById(id);
    }
}
