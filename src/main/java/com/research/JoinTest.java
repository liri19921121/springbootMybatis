package com.research;

/**
 * join方法
 * 原理：
 * 从源码中可以看到：join方法的原理就是调用相应线程的wait方法进行等待操作的，
 * 例如A线程中调用了B线程的join方法，则相当于在A线程中调用了B线程的wait方法，
 * 当B线程执行完（或者到达等待时间），B线程会自动调用自身的notifyAll方法唤醒A线程，
 * 从而达到同步的目的
 */
public class JoinTest {

    public static void main(String[] args) throws Exception {

        ThreadJoinTest2 t = new ThreadJoinTest2("测试");
        t.start();

        ThreadJoinTest t1 = new ThreadJoinTest("小明");
        ThreadJoinTest t2 = new ThreadJoinTest("小东");
        t1.start();
        /**join的意思是使得放弃当前线程的执行，并返回对应的线程，例如下面代码的意思就是：
         程序在main线程中调用t1线程的join方法，则main线程放弃cpu控制权，并返回t1线程继续执行直到线程t1执行完毕
         所以结果是t1线程执行完后，才到主线程执行，相当于在main线程中同步t1线程，t1执行完了，main线程才有执行的机会
         */
        t1.join();

        /**join方法可以传递参数，join(10)表示main线程会等待t1线程10毫秒，10毫秒过去后，
         * main线程和t1线程之间执行顺序由串行执行变为普通的并行执行
         *
         * 程序执行前面10毫秒内打印的都是小明线程，10毫秒后，小明和小东程序交替打印。
         */
         //t1.join(10);


        t2.start();

    }
}

class ThreadJoinTest extends Thread {
    public ThreadJoinTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(this.getName() + ":" + i);
        }
    }
}


class ThreadJoinTest2 extends Thread {
    public ThreadJoinTest2(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            synchronized (this) {
                //测试notifyAll不会唤醒其他线程
                wait(10000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(this.getName()+"-------------------------------------------");
    }
}

