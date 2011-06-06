// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.gwtxml.client.pojos;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 *
 */
public class Person {
  private String id;
  private String firstName;
  // This maps to Connexion's surname.
  private String lastName;
  private String otherName;
  private String fullName;
  private String email;

  private Person() {
  }

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getOtherName() {
    return otherName;
  }

  public String getFullName() {
    return fullName;
  }

  public String getEmail() {
    return email;
  }

  public static class Builder {
    private String id;
    private String firstName;
    private String lastName;
    private String otherName;
    private String fullName;
    private String email;

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setFirstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public Builder setLastName(String lastName) {
      this.lastName = lastName;
      return this;
    }


    public Builder setOtherName(String otherName) {
      this.otherName = otherName;
      return this;
    }


    public Builder setFullName(String fullName) {
      this.fullName = fullName;
      return this;
    }


    public Builder setEmail(String email) {
      this.email = email;
      return this;
    }

    public Person build() {
      Person person = new Person();
      person.id = id;
      person.firstName = firstName;
      person.lastName = lastName;
      person.otherName = otherName;
      person.fullName = fullName;
      person.email = email;
      return person;
    }
  }
}
