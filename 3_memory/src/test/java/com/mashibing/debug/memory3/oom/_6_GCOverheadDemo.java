package com.mashibing.debug.memory3.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * <p>
 * JVM参数配置演示
 * -Xms20m -Xmx20m -XX:+PrintGCDetails
 * <p>
 * GC 回收实践过程时会抛出OutOfMemoryError。过长的定义是，超过98%的时间用来做GC并且回收了不到2%的堆内存
 * 连续多次GC都只会收到了不到2%的极端情况下才会抛出。假如不抛出GC overhead limit错误会发生什么情况呢？
 * <p>
 * 那就是GC清理的这么点内存很快会再次填满，迫使GC再次执行，这样就形成了恶性循环，
 * CPU使用率一直100%，而GC却没有任何成果
 **/

public class _6_GCOverheadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) {
            System.out.println("*******i:" + i);
            e.printStackTrace();
            throw e;
        }
    }
}
