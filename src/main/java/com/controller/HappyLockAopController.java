package com.controller;

import com.service.HappyLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = {"/happyLock"})
public class HappyLockAopController {

    @Autowired
    HappyLockService happyLockService;

    @GetMapping("test")
    @ResponseBody
    public void IsTryAgainMethod(String content) throws Exception {
        happyLockService.IsTryAgainMethod(content);
    }

}
