package com.service.impl;

import com.alibaba.druid.util.Base64;
import com.common.utils.ImgUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

@Service
public class ImageUploadService {

    @Value("${upload.root.path}")
    public String root_fold;

    @Value("${upload.root.img}")
    public String img_path;

    public String uploadImg(String base64Img){
        String userPath = null;
        try {
            base64Img = ImgUtil.replaceBase64Before(base64Img);
            byte[] bytes = Base64.base64ToByteArray(base64Img);
            InputStream in = new ByteArrayInputStream(bytes);
            //生成新的名称，防止名字重复
            String newName = this.random(8)+".png";
            String filePath = img_path+newName;
            userPath = ImgUtil.uploadImg(root_fold,filePath, in);
        }catch (IOException e){
            userPath = "上传失败";
        }
        return root_fold+userPath;
    }



    /**
     * 返回随机数
     *
     * @param n
     *            个数
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


}
