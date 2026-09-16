package com.mashibing.debug.snowflake;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdWorker {

    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long twepoch = 1288834974657L;
    private long workerIdBits = 5L;
    private long datacenterIdBits = 5L;
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private long sequenceBits = 12L;
    private long workerIdShift = sequenceBits;
    // 17= 12+5
    private long datacenterIdShift = sequenceBits + workerIdBits;
    //22 =12+ 5+ 5
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private long sequenceMask = -1L ^ (-1L << sequenceBits);
    private long lastTimestamp = -1L;

    public IdWorker(long workerId, long datacenterId, long sequence) {
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        System.out.printf("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
                timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);

        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    public static String toBinaryStr(long num) {
        String binaryString = Long.toBinaryString(num);
        while (binaryString.length() < 64) {
            binaryString = "0" + binaryString;
        }
        return binaryString;
    }

    //---------------测试---------------
    public static void main(String[] args) {
        IdWorker idWorker = new IdWorker(1, 1, 1);
        System.out.println(idWorker.nextId());
        System.out.println(toBinaryStr(System.currentTimeMillis()));
        Date date = new Date(1288834974657L);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

    }

    public long getWorkerId() {
        return workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public synchronized long nextId() {
        long timestamp = timeGen();

        /*当前时间<上次时间，说明时钟回拨*/
        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }
        /*上次生成时间==当前时间，说明在同一个毫秒内*/
        if (lastTimestamp == timestamp) {
            //
            //
            //sequenceMask=0000000000000000000000000000000000000000000000000000111111111111
            //如果sequence=0，说明：
            //sequence + 1=0000000000000000000000000000000000000000000000000000111111111111
            //sequence =   0000000000000000000000000000000000000000000000000000111111111110

            //初始sequence= 0000000000000000000000000000000000000000000000000000000000000001
            /*保证sequence不会超过序列号所能容纳的最大值*/
            sequence = (sequence + 1) & sequenceMask;
            /*如果sequence==0，说明已经到了最大数量+1*/
            if (sequence == 0) {

                /*时间等待1毫秒*/
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            /*每一个毫秒从0开始生成*/
            sequence = 0;
        }
        /*记录上次生成序列的时间*/
        lastTimestamp = timestamp;
        /*构造最终的数据：时间戳+数据中心位+机器号位+序列*/
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        /*如果当前时间小于等于上次生成时间，目的是等待下一个毫秒*/
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

}
