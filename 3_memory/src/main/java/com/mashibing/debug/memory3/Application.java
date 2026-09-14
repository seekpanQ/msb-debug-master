package com.mashibing.debug.memory3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * java -Xms4g -Xmx4g -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heapdump.txt -jar 3_memory-0.0.1-SNAPSHOT.jar
 * <p>
 * crul http://192.168.254.201:9090/oom
 *
 * @author sunzhiqiang
 * @date 2021/12/05 15:51:02
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
