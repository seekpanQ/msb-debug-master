package com.mashibing.debug.memory3;

import com.mashibing.debug.memory3.xml.CDataXMLStreamWriter;
import com.mashibing.debug.memory3.xml.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

/**
 * -verbose:class -XX:MetaspaceSize=20m -XX:MaxMetaspaceSize=20m -XX:NewSize=10m -XX:MaxNewSize=10m -Xms100m -Xmx100m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:PretenureSizeThreshold=3m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintCommandLineFlags
 * -verbose:class
 * -XX:MetaspaceSize=20m
 * -XX:MaxMetaspaceSize=20m
 * -XX:NewSize=10m
 * -XX:MaxNewSize=10m
 * -Xms100m
 * -Xmx100m
 * -XX:+PrintGCDetails
 * -XX:+PrintCommandLineFlags
 * -XX:PretenureSizeThreshold=3m
 * -XX:+PrintGCDetails
 * -XX:+PrintGCTimeStamps
 * -XX:+PrintCommandLineFlags
 */
public class JaxbTest {
    public static void main(String[] args) throws Exception {
        JaxbTest jaxbTest = new JaxbTest();
        while (true) {
            Student student = new Student();
            student.setName("asd");
            student.setAge(1);
            jaxbTest.ojbectToXmlWithCDATA(Student.class, student);
            student = null;
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }

    public String ojbectToXmlWithCDATA(Class clazz, Object object) throws Exception {
        JAXBContext context = JAXBContext.newInstance(clazz);
        ByteArrayOutputStream op = new ByteArrayOutputStream();


        XMLOutputFactory xof = XMLOutputFactory.newInstance();
        XMLStreamWriter streamWriter = xof.createXMLStreamWriter(op);
        CDataXMLStreamWriter cDataXMLStreamWriter = new CDataXMLStreamWriter(streamWriter);

        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(object, cDataXMLStreamWriter);

        cDataXMLStreamWriter.flush();
        cDataXMLStreamWriter.close();
        return op.toString("UTF-8");
    }
}
