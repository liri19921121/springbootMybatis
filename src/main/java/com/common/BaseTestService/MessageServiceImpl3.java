package com.common.BaseTestService;

import com.common.BaseService.BaseService;
import com.pojo.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageServiceImpl3 extends BaseService<Message> {

}
