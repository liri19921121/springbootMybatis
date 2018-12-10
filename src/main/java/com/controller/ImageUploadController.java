package com.controller;

import com.service.ImageUploadService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/*@RestController*/
@Controller
@RequestMapping(value = {"/img"})
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;

    @GetMapping(path="/index")
    public String index() {
        return "uploadImg";
    }


    @ApiOperation(value = "图片上传",notes = "base64图片上传")
    @RequestMapping(value="/upload",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> uploadImg(String base64Img) {
        String parth = imageUploadService.uploadImg(base64Img);
        Map<String,String> map = new HashMap<>();
        map.put("msg","上传成功,保存地址为："+parth);
        return map;
    }
}
