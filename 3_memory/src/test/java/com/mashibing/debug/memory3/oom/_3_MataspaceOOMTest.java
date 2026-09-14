package com.mashibing.debug.memory3.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * JVM参数
 * -XX:MetaspaceSize=12m -XX:MaxMetaspaceSize=12m
 * <p>
 * java 8及之后的版本使用Metaspace来替代永久代,存放了以下信息：
 * <p>
 * 虚拟机加载的类信息
 * 即时编译后的代码
 * <p>
 * 模拟Metaspace空间溢出
 **/
public class _3_MataspaceOOMTest {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                while (true) {
                    Enhancer enhancer = new Enhancer();
                    enhancer.setSuperclass(OOMTest.class);
                    enhancer.setUseCache(false);
                    enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invoke(o, args));
                    enhancer.create();
                }

            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            int num = 0;
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(num);
                num++;
            }
        }).start();


    }

    static class OOMTest {
    }

}
