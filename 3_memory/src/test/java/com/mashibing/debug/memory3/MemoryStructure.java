package com.mashibing.debug.memory3;

public class MemoryStructure {

    /**
     * -XX:+PrintCommandLineFlags -XX:NewSize=10m -XX:MaxNewSize=10m -Xms20m -Xmx20m  -XX:PretenureSizeThreshold=3m -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps
     * <p>
     * 2342.1
     * -verbose:class
     * -XX:NewSize=10m
     * -XX:MaxNewSize=10m
     * -Xms20m
     * -Xmx20m
     * -XX:PretenureSizeThreshold=3m
     * -XX:+UseConcMarkSweepGC
     * -XX:+PrintCommandLineFlags
     * -Xloggc:gc.log
     * -XX:+PrintGCDetails
     * -XX:+PrintGCDateStamps
     */
    public static void main(String[] args) {
        byte[] array1 = new byte[4 * 1024 * 1024];
        array1 = null;

        byte[] array2 = new byte[2 * 1024 * 1024];
        byte[] array3 = new byte[2 * 1024 * 1024];
        byte[] array4 = new byte[2 * 1024 * 1024];
        System.out.println("-------------------");
    }
}
