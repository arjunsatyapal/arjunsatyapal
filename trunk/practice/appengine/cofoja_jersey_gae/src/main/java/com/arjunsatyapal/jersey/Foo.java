package com.arjunsatyapal.jersey;

import com.arjunsatyapal.jersey.temp.SomeStaticClass;

import com.google.java.contract.Ensures;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Foo {
  @XmlAttribute
  int x = 2;

  public Foo() {
  }
  
  
  
  public int get1() {
    return x;
  }
  
  public static final String name = SomeStaticClass.class.getName();
  public static final String condition = "com.arjunsatyapal.jersey.temp.SomeStaticClass.getValue() == true"; 
  @Ensures(Foo.condition)
  public void set(int y) {
    SomeStaticClass foo = new SomeStaticClass();
    x = y;
  }
}
