package com.mashibing.debug.memory3.oom;

import java.nio.ByteBuffer;

/**
 * 〈一句话功能简述〉<br>
 * 配置参数：
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * <p>
 * 故障现象
 * Exception in thread "main" java.lang.OutOfMemoryError:Direct buffer memory
 * <p>
 * 导致原因：
 * 写NIO程序经典使用ByteBuffer来读取或者写入数据，这是一种基于通道（Channel）与缓冲区（Buffer）的 I/O方式，
 * 可以使用native函数库直接分配堆外内存，然后通过一个存储在Java堆里面的DirectByteBuffer对象作为这块内存的引用进行操作。
 * 这样能在一些场景种显著提高性能，因为避免了在Java堆和Native堆种来回复制数据。
 * <p>
 * ByteBuffer.allocate(capability) 第一种方式是分配JVM堆内存，
 * ByteBuffer.allocateDirect(capability)第一种方式时分配OS本地内存，由于不需要内存拷贝所以速度相对较快。
 * <p>
 * 但如果不断分配本地内存，对内存很少使用，那么JVM就不需要执行GC，DirectByteBuffer对象们就不会被回收。
 * 这时候对内存重组，但本地内存可能已经使用光了，再次尝试分配本地内存就会出OutOfMemoryError，那程序就直接崩溃了。
 **/

public class _5_DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory:" + (sun.misc.VM.maxDirectMemory() / (double) 1024 / 1024) + "MB");
        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }

}
