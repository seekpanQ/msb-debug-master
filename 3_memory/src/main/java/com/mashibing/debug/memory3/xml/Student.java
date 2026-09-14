package com.mashibing.debug.memory3.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@Data
@XmlRootElement(name = "pivotFlow")
@XmlType(propOrder = {"name", "age"})
public class Student {

    private String name;

    private Integer age;

}
