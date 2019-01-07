package com.service.impl;

import com.aop.IsTryAgain;
import com.aop.TryAgainException;
import com.mapper.HappyLockTestMapper;
import com.pojo.HappyLockTest;
import com.service.HappyLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HappyLockServiceImpl implements HappyLockService {

    @Autowired
    HappyLockTestMapper happyLockTestMapper;

    /**
     * update有排它锁,当当前事务update没提交之前，另外集群上面的事务不能更新需要等待
     * @param content
     * @throws Exception
     */
    @IsTryAgain
    @Transactional(rollbackFor = Exception.class,isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void IsTryAgainMethod(String content) throws Exception{
        HappyLockTest happyLockTest = happyLockTestMapper.selectByPrimaryKey(1L);
        happyLockTest.setContent(content);
        System.out.println(content+"--->"+happyLockTest.getVersion());
        Thread.sleep(8000);
        System.out.println("开始--->");
        happyLockTest.setVersion(happyLockTest.getVersion());
        int temp =  happyLockTestMapper.updateByPrimaryKeySelective(happyLockTest);
        if (temp == 0){
            throw new TryAgainException("进入切面重试");
        }
        System.out.println("结束了去查询吧------------》");
        Thread.sleep(8000);
        System.out.println("---------------结束------------");
    }
}
