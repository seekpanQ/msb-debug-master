package com.mashibing.debug.memory3;

import com.mashibing.debug.memory3.xml.Student;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * 类定义测试
 *
 * @author sunzhiqiang
 * @date 2021/12/05 17:14:10
 */
public class DefineClassTest {
    public static void main(String args[]) throws Throwable {
        Method defineClass = ClassLoader.class.getDeclaredMethod("defineClass",
                new Class[]{String.class, byte[].class, int.class, int.class});
        defineClass.setAccessible(true);

        ClassLoader classLoader = DefineClassTest.class.getClassLoader();

        String classPath = Student.class.getName().replace(".", "/") + ".class";
        InputStream in = classLoader.getResourceAsStream(classPath);
        byte[] image = IOUtils.toByteArray(in);
        while (true) {
            try {
                defineClass.invoke(classLoader, "com.mashibing.debug.memory3.xml.Student", image, 0, image.length);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

    }
}
