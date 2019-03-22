package com.research;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 有返回值的多线程测试
 * 方法2：使用ExecutoreService提供了submit()方法，传递一个Callable，返回Future。
 */
public class CallbleTest {
    public static void main(String[] args) throws Exception {
        System.out.println("----程序开始运行----");
        Date date1 = new Date();
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        try {
            Callable c = new MyCallable("【异步线程日任务】");
            /*执行任务并获取Future对象,ExecutoreService提供了submit()方法，传递一个Callable，或Runnable，
            返回Future。如果Executor后台线程池还没有完成Callable的计算，
            这调用返回Future对象的get()方法，会阻塞直到计算完成。*/
            Future f = pool.submit(c);

            System.out.println("主线程进行业务操作----->");
            System.out.println("balabalabalabala........");
            Thread.sleep(5000);
            System.out.println("<-----主线程业务操作结束");

            // 从Future对象上获取任务的返回值
            System.out.println("获得异步线程结果--->" + f.get().toString());
            Date date2 = new Date();
            System.out.println("----程序结束运行----，程序运行时间【"
                    + (date2.getTime() - date1.getTime()) + "毫秒】");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            pool.shutdown();
        }

    }
}

class MyCallable implements Callable<Object> {
    private String taskNum;
    MyCallable(String taskNum) {
        this.taskNum = taskNum;
    }
    public Object call() throws Exception {
        System.out.println(">>>" + taskNum + "任务启动");
        Date dateTmp1 = new Date();
        Thread.sleep(1000);
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        System.out.println(">>>" + taskNum + "任务终止");
        return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
    }
}
