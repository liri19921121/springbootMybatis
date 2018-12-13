package com.service;

import com.pojo.Message;
import java.util.List;

public interface MessageService {

    public List<Message> getByConditionList()throws Exception;

    public String addMessage(String message,String name) throws Exception;

}
