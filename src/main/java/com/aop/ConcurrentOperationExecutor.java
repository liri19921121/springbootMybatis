package com.aop;

import com.aop.exception.ApiException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 定义重试切面方法，是为了发生乐观锁异常时在一个全新的事务里提交上一次的操作，
 * 直到达到重试上限；因此切面实现 org.springframework.core.Ordered 接口，
 * 这样我们就可以把切面的优先级设定为高于事务通知 。
 */
@Aspect
@Component
public class ConcurrentOperationExecutor implements Ordered {
    private int maxRetries = 3;
    private int order = 1;

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getOrder() {
        return this.order;
    }

    /**
     * 切点可访问性修饰符与类可访问性修饰符的功能是相同的，它可以决定定义的切点可以在哪些类中可使用。
     * 因为命名切点仅利用方法名及访问修饰符的信息，所以我们一般定义方法的返回类型为 void ，并且方法体为空 。
     *
     * 所有加了自定义注解的地方切入
     *
     */
    @Pointcut("@annotation(IsTryAgain)")
    public void businessService() {

    }


    @Around("businessService()")
    @Transactional(rollbackFor = Exception.class)
    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
        int numAttempts = 0;
        do {
            numAttempts++;
            try {
                return pjp.proceed();
            } catch (TryAgainException ex) {
                if (numAttempts > maxRetries) {
                    throw new ApiException(1, "重试达到最大次数");
                } else {
                    System.out.println("=====try recovery=====");
                }
            }
        } while (numAttempts <= this.maxRetries);

        return null;
    }
}
