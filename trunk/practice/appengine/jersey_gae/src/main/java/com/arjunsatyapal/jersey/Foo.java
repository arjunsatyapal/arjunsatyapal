package com.arjunsatyapal.jersey;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Foo {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @XmlAttribute
  int x = 2;

  public Foo() {
  }
}
