package com.components.token;


import com.pojo.User;

public interface TokenManager {

    /**
     * 创建token
     * @param userInfo
     * @return
     */
    String getToken(User userInfo);

    /**
     * 刷新用户
     * @param token
     */
    void refreshUserToken(String token);
    void refreshToken(String token);

    /**
     * 用户退出登陆
     * @param token
     */
    void loginOff(String token);

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    User getUserInfoByToken(String token);
    
    void setToken(String key, Object value, Long... times);
    Object getToken(String key);
    void setCodeToken(String code, String mail);
    String getCodeByToken(String mail);

}