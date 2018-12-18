package com.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.common.utils.ImgUtil;
import com.mapper.ImgPathMapper;
import com.mapper.ImgTitleMapper;
import com.pojo.ImgPath;
import com.pojo.ImgTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImagerUpdateService {

    @Autowired
    private ImgTitleMapper imgTitleMapper;

    @Autowired
    private ImgPathMapper imgPathMapper;

    @Transactional(propagation= Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void update(ImgPath imgPath) {
        if (imgPath != null) {
            ImgTitle imgTitle = imgTitleMapper.selectByPrimaryKey(imgPath.getImgId());
            if (imgTitle != null) {
                //写入本地
                String result = ImgUtil.uploadQianURL(imgTitle.getTitle(), imgPath.getImgPath());
                if (StringUtils.isEmpty(result)) {
                    System.out.println("下载失败");
                } else {
                    //更新状态
                    imgPath.setIsDown(1);
                    imgPathMapper.updateByPrimaryKeySelective(imgPath);
                    System.out.println(imgPath.getImgPath() + "下载成功");
                }
            }
        }
    }

}
