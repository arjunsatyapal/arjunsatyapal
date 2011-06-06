// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.gwtxml.client.pojos;

import static com.arjunsatyapal.practice.gwtxml.client.gwtui.cnxml.presenter.HtmlUtils.getItalicString;

import com.arjunsatyapal.practice.gwtxml.client.gwtui.cnxml.presenter.HtmlUtils;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 */
public class Book {


  private String author;
  private String title;
  // This maps to Connexion's surname.
  private String publisher;
  private String year;
  private String volume;
  private String series;
  private String address;
  private String edition;
  private String month;
  private String note;

  private Book() {
  }

  public String getHtmlString() {
    // TODO(arjuns) : Add more bib things here.
    return author + ".(" + year + ")." + getItalicString(title) + "." + publisher;
  }

  public String getAuthor() {
    return author;
  }

  public String getTitle() {
    return title;
  }

  public String getPublisher() {
    return publisher;
  }

  public String getYear() {
    return year;
  }

  public String getVolume() {
    return volume;
  }

  public String getSeries() {
    return series;
  }

  public String getAddress() {
    return address;
  }

  public String getEdition() {
    return edition;
  }

  public String getMonth() {
    return month;
  }

  public String getNote() {
    return note;
  }

  public static class Builder {
    private String author;
    private String title;
    private String publisher;
    private String year;
    private String volume;
    private String series;
    private String address;
    private String edition;
    private String month;
    private String note;

    public Builder setAuthor(String author) {
      this.author = author;
      return this;
    }

    public Builder setTitle(String title) {
      this.title = title;
      return this;
    }

    public Builder setPublisher(String publisher) {
      this.publisher = publisher;
      return this;
    }


    public Builder setYear(String year) {
      this.year = year;
      return this;
    }


    public Builder setVolume(String volume) {
      this.volume = volume;
      return this;
    }

    public Builder setSeries(String series) {
      this.series = series;
      return this;
    }

    public Builder setAddress(String address) {
      this.address = address;
      return this;
    }

    public Builder setEdition(String edition) {
      this.edition = edition;
      return this;
    }

    public Builder setMonth(String month) {
      this.month = month;
      return this;
    }

    public Builder setNote(String note) {
      this.note = note;
      return this;
    }

    public Book build() {
      Book person = new Book();
      person.author = author;
      person.title = title;
      person.publisher = publisher;
      person.year = year;
      person.volume = volume;
      person.series = series;
      person.address = address;
      person.edition = edition;
      person.month = month;
      person.note = note;
      return person;
    }
  }
}
