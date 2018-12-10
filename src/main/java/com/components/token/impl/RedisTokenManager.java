package com.components.token.impl;

import com.common.utils.Constants;
import com.components.redis.service.RedisService;
import com.components.token.TokenManager;
import com.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RedisTokenManager implements TokenManager {

    @Autowired
    private RedisService redisService;

    /**
     * 创建token
     * @param userInfo
     * @return
     */
    public String getToken(User userInfo){
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        String token_format=String.format(Constants.TOKEN_FORMAT,token);
        redisService.stringSetObject(token_format,userInfo);
        //redisService.stringSetValueAndExpireTime(token_format,userInfo,Constants.USER_TOKEN_EXPIRES);
        return token;
    }

    /**
     * 刷新用户
     * @param token
     */
    public void refreshUserToken(String token){
        token=String.format(Constants.TOKEN_FORMAT,token);
        /*if(redisService.hasKey(token)){
        	redisService.setExpireTime(token, Constants.USER_TOKEN_EXPIRES);
        }*/
    }
    
    @Override
    public void refreshToken(String token) {
    	if(redisService.hasKey(token)){
        	redisService.setExpireTime(token, Constants.TOKEN_EXPIRES);
        }
    }

    /**
     * 用户退出登陆
     * @param token
     */
    public void loginOff(String token){
         token=String.format(Constants.TOKEN_FORMAT,token);
         redisService.deleteFromRedis(token);
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    public User getUserInfoByToken(String token){
        token=String.format(Constants.TOKEN_FORMAT,token);
        return (User)redisService.stringGetByKey(token);

    }
    
    /**
     * 设置验证码
     */
    @Override
    public void setCodeToken(String code,String mail) {
         String token_format=String.format("TOKEN-CODE-%s",mail);
         //3分钟内有效
         redisService.stringSetValueAndExpireTime(token_format, code,Constants.TOKEN_EXPIRES);
    }
    
    /**
     * 获取验证码，通过token 
     */
    @Override
    public String getCodeByToken(String mail) {
    	String token_format=String.format("TOKEN-CODE-%s",mail);
    	String code = (String) redisService.stringGetByKey(token_format);
    	return code;
    }
    
    @Override
    public void setToken(String key, Object value, Long... times) {
    	if(times != null){
    	    //设置时间
    		 redisService.stringSetValueAndExpireTime(key, value,times[0]);
    	}else{
    		redisService.stringSetObject(key, value);
    	}
    }
    
    @Override
    public Object getToken(String key) {
    	return redisService.stringGetByKey(key);
    }
}
