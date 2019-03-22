package com.research;

/**
 * ThreadLoad方法测试
 * 线程局部变量；对于多线程资源共享的问题，同步机制采用了
 * “以时间换空间”的方式，而ThreadLocal采用了“以空间换时间”的方式。
 * 前者仅提供一份变量，让不同的线程排队访问，而后者为每一个线程都提供了一份变量，
 * 因此可以同时访问而互不影响。
 */
public class ThreadLoadTest {
    static class ResourceClass {
        public final static ThreadLocal<String> RESOURCE_1 = new ThreadLocal<String>();
        public final static ThreadLocal<String> RESOURCE_2 = new ThreadLocal<String>();
    }

    static class A {
        public void setOne(String value) {
            ResourceClass.RESOURCE_1.set(value);
        }
        public void setTwo(String value) {
            ResourceClass.RESOURCE_2.set(value);
        }
    }

    static class B {
        public void display() {
            System.out.println(ResourceClass.RESOURCE_1.get() + ":" + ResourceClass.RESOURCE_2.get());
        }
    }

    public static void main(String[] args) {
        final A a = new A();
        final B b = new B();
        for (int i = 0; i < 15; i++) {
            final String resouce1 = "线程-" + i;
            final String resouce2 = " value = (" + i + ")";
            new Thread() {
                public void run() {
                    try {
                        a.setOne(resouce1);
                        a.setTwo(resouce2);
                        b.display();
                    } finally {
                        ResourceClass.RESOURCE_1.remove();
                        ResourceClass.RESOURCE_2.remove();
                    }
                }
            }.start();
        }
    }
}


