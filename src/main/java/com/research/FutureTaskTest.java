package com.research;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 有返回值的多线程测试(不需要创建线程池)
 * 方法1：使用 FutureTask 类来包装 Callable 对象，该 FutureTask 对象封装了该
 * Callable 对象的 call() 方法的返回值。使用 FutureTask 对象作为 Thread
 * 对象的 target 创建并启动新线程。调用 FutureTask 对象的 get() 方法来获
 * 得子线程执行结束后的返回值。
 */
public class FutureTaskTest {

    public static void main(String[] args) throws Exception {
        FutureTaskCall ctt = new FutureTaskCall(99);
        FutureTask<Integer> ft = new FutureTask<>(ctt);

        new Thread(ft).start();

        System.out.println("主线程进行业务操作----->");
        System.out.println("balabalabalabala........");
        Thread.sleep(5000);
        System.out.println("<-----主线程业务操作结束");

        System.out.println("子线程的返回值：" + ft.get());
    }
}


class FutureTaskCall implements Callable<Integer> {

    private Integer taskNum;

    FutureTaskCall(Integer taskNum) {
        this.taskNum = taskNum;
    }


    @Override
    public Integer call() throws Exception {
        // 线程需要完成的任务
        System.out.println(">>>" + taskNum + "任务启动");
        Date dateTmp1 = new Date();
        Thread.sleep(1000);
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        System.out.println(">>>" + taskNum + "任务终止");
        return taskNum*100;
    }
}