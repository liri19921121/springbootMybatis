package com.service.impl;

import com.alibaba.druid.util.Base64;
import com.alibaba.druid.util.StringUtils;
import com.common.utils.ImgUtil;
import com.mapper.ImgPathMapper;
import com.mapper.ImgTitleMapper;
import com.pojo.ImgPath;
import com.pojo.ImgTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

@Service
public class ImageUploadService {

    @Value("${upload.root.path}")
    public String root_fold;

    @Value("${upload.root.img}")
    public String img_path;

    @Autowired
    private ImgTitleMapper imgTitleMapper;

    @Autowired
    private ImgPathMapper imgPathMapper;

    public String uploadImg(String base64Img) {
        String userPath = null;
        try {
            base64Img = ImgUtil.replaceBase64Before(base64Img);
            byte[] bytes = Base64.base64ToByteArray(base64Img);
            InputStream in = new ByteArrayInputStream(bytes);
            //生成新的名称，防止名字重复
            String newName = this.random(8) + ".png";
            String filePath = img_path + newName;
            userPath = ImgUtil.uploadImg(root_fold, filePath, in);
        } catch (IOException e) {
            userPath = "上传失败";
        }
        return root_fold + userPath;
    }


    /**
     * 返回随机数
     *
     * @param n 个数
     * @return
     */
    public static String random(int n) {
        if (n < 1 || n > 10) {
            throw new IllegalArgumentException("cannot random " + n
                    + " bit number");
        }
        Random ran = new Random();
        if (n == 1) {
            return String.valueOf(ran.nextInt(10));
        }
        int bitField = 0;
        char[] chs = new char[n];
        for (int i = 0; i < n; i++) {
            while (true) {
                int k = ran.nextInt(10);
                if ((bitField & (1 << k)) == 0) {
                    bitField |= 1 << k;
                    chs[i] = (char) (k + '0');
                    break;
                }
            }
        }
        return new String(chs);
    }

    public void imgsDown() {
        Example example = new Example(ImgPath.class);
        example.createCriteria()
                .andEqualTo("isDown", 0);
        List<ImgPath> list = imgPathMapper.selectByExample(example);
        int i = 0;
        for (ImgPath imgPath : list) {
            i++;
            try {
                update(imgPath);
                System.out.println("第"+i+"张");
            } catch (Exception e) {
                //出现异常就跳过此条
                continue;
            }
        }
        list = imgPathMapper.selectByExample(example);
        if (list.isEmpty()) {
            System.out.println("全部下载完成");
        } else {
            imgsDown();
        }
    }


    @Transactional(propagation= Propagation.REQUIRES_NEW)
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
