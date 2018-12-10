package com.service;

import com.pojo.UserInfo;

import java.util.List;

public interface IUserService {
    List findAllUser();
    UserInfo getUserByid(Long id);
}
