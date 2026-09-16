package com.mashibing.debug.snowflake;

import com.alibaba.fastjson.JSON;
import com.mashibing.debug.snowflake.model.Student;
import org.junit.jupiter.api.Test;

public class FastJsonTest {

    @Test
    public void test1() {
        Student student = new Student();
        student.setName("xingming");

//        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
//        byteBuffer.putLong(123);
//        student.setByteBuffer(byteBuffer);

        System.out.println(JSON.toJSONString(student));
    }

}
