package com.arjunsatyapal.practice.shared.dtos;


public class SchoolDto implements AbstractDto {
  private long id;
  private String schoolName;
  private String address1;
  private String address2;
  private String city;
  private String state;
  private String zip;
  private String adminEmail;

  @Override
  public StringBuilder getStringBuilder() {
    StringBuilder builder = new StringBuilder("SchoolDto[");
    builder.append("id:").append(id);
    builder.append(", schoolName:").append(schoolName);
    builder.append(", address1:").append(address1);
    builder.append(", address2:").append(address2);
    builder.append(", city:").append(city);
    builder.append(", state:").append(state);
    builder.append(", zip:").append(zip);
    builder.append(", adminEmail:").append(adminEmail);
    builder.append("].");

    return builder;
  }

  @Override
  public String toString() {
    return getStringBuilder().toString();
  }

  private SchoolDto() {
  }

  @Override
  public String validate() {
    StringBuilder builder = new StringBuilder("");

    // id will be set only by server. So not checking it.
    if (schoolName.isEmpty()) {
      builder.append("schoolName is empty.\n");
    }
    if (address1.isEmpty()) {
      builder.append("address1 is empty.\n");
    }
    if (address2.isEmpty()) {
      builder.append("address2 is empty.\n");
    }
    if (city.isEmpty()) {
      builder.append("city is empty.\n");
    }
    if (state.isEmpty()) {
      builder.append("state is empty.\n");
    }
    if (zip.isEmpty()) {
      builder.append("zip is empty.\n");
    }
    if (adminEmail.isEmpty()) {
      builder.append("adminEmail is empty.\n");
    }

    return builder.toString();
  }

  public long getId() {
    return id;
  }

  public String getSchoolName() {
    return schoolName;
  }

  public String getAddress1() {
    return address1;
  }

  public String getAddress2() {
    return address2;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getZip() {
    return zip;
  }

  public String getAdminEmail() {
    return adminEmail;
  }

  public static class Builder {
    private long id;
    private String schoolName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String adminEmail;

    public Builder setId(long id) {
      this.id = id;
      return this;
    }

    public Builder setSchoolName(String schoolName) {
      this.schoolName = schoolName;
      return this;
    }

    public Builder setAddress1(String address1) {
      this.address1 = address1;
      return this;
    }

    public Builder setAddress2(String address2) {
      this.address2 = address2;
      return this;
    }

    public Builder setCity(String city) {
      this.city = city;
      return this;
    }

    public Builder setState(String state) {
      this.state = state;
      return this;
    }

    public Builder setZip(String zip) {
      this.zip = zip;
      return this;
    }

    public Builder setAdminEmail(String adminEmail) {
      this.adminEmail = adminEmail;
      return this;
    }

    public SchoolDto build() {
      SchoolDto dto = new SchoolDto();
      dto.id = this.id;
      dto.schoolName = this.schoolName;
      dto.address1 = this.address1;
      dto.address2 = this.address2;
      dto.city = this.city;
      dto.state = this.state;
      dto.zip = this.zip;
      dto.adminEmail = this.adminEmail;
      dto.validate();
      return dto;
    }
  }
}
