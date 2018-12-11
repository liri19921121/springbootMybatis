package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/index"})
public class IndexController {

    @GetMapping(value = {"/love"})
    public String setRedisData(HttpServletRequest request){
        return "love";
    }

}
