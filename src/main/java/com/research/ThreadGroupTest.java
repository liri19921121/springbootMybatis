package com.research;

/**
 * 线程组
 * ①定义线程组
 * ThreadGroup类中有 2个构造方法，它们用来定义线程组。这 2个构造方法的使用格 式如下：
 *     public ThreadGroup（String name）;
 *     public ThreadGroup（ThreadGroup parent，String name）；
 *     比如，创建一个名为fruit的线程组k，可用如下语句：
 *     ThreadGroup k＝new ThreadGroup（“fruit”）；
 *     如果一个线程组有父线程组，则可以在定义时，用第二个构造方法指出父线程组的名 字。比如：
 *     ThreadGroup k=new ThreadGroup（products，“fruit”）；
 * ②获得线程组中的信息
 * ThreadGroup类中有几个方法可用来获得线程组中有关线程和子线程的信息，这些信 息包括线程组名字、线程组中可运行线程的数目、线程组中线程的最大优先级、线程组中各 线程的名字等。这些方法有：
 *        public int activeCount(); // 获得当前线程组中线程数目， 包括可运行和不可运行的
 *        public int activeGroupCount()； //获得当前线程组中活动的子线程组的数目
 *        public int enumerate（Thread list[]）; //列举当前线程组中的线程
 *        public int enumerate（ThreadGroup list[]）； //列举当前线程组中的子线程组
 *        public final int getMaxPriority（）; //获得当前线程组中最大优先级
 *        public final String getName（）； //获得当前线程组的名字
 *        public final ThreadGroup getParent（）; //获得当前线程组的父线程组
 *        public boolean parentOf（ThreadGroup g）； //判断当前线程组是否为指定线程的父线程
 *        public boolean isDaemon（）; //判断当前线程组中是否有监护线程
 *        public void list（）; //列出当前线程组中所有线程和子线程名
 * ③对线程组操作
 *     ThreadGroup类中的方法都是以线程组为操作目标的。其中，包括设置线程组中线程 的最大优先级方法、将线程组中所有线程挂起或恢复到可运行状态的方法、终止线程组中所 有线程的方法等。
 *     对线程组进行操作的方法如下所示：
 *        public final void resume（）； //使被挂起的当前组内的线程恢复到可运行状态
 *        public final void setDaemon (boolean daemon); //指定一个线程为当前线程组的监护线程
 *        public final void setMaxPriority（int pri）； //设置当前线程组允许的最大优先级
 *        public final void stop（）；//终止当前线程组中所有线程
 *        public final void suspend（）; //挂起当前线程组中所有线程
 *        public String toStrinng（）; //将当前线程组转换为String类的对象
 */
public class ThreadGroupTest {

    public static void main(String[] args) throws Exception {
        ThreadGroup threadGroup = new ThreadGroup("线程组");

        Thread thread = new Thread(threadGroup,new GroupTest(),"线程1");
        Thread thread2 = new Thread(threadGroup,new GroupTest(),"线程2");
        Thread thread3 = new Thread(threadGroup,new GroupTest2(),"线程3");
        thread.start();
        thread2.start();
        thread3.start();

        //获取thread所在线程组名字
        System.out.println(thread.getThreadGroup().getName());
        //获得当前线程组中线程数目， 包括可运行和不可运行的
        System.out.println(threadGroup.activeCount());


    }
}

class GroupTest implements Runnable {
    @Override
    public void run() {
        try{
            Thread.sleep(10000);
            System.out.println("run1");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class GroupTest2 implements Runnable {
    @Override
    public void run() {
        try{
            Thread.sleep(10000);
            System.out.println("run2");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}