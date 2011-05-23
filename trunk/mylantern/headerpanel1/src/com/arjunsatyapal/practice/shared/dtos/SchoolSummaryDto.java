package com.arjunsatyapal.practice.shared.dtos;


public class SchoolSummaryDto implements AbstractDto {
  private long id;
  private String schoolName;

  @Override
  public StringBuilder getStringBuilder() {
    StringBuilder builder = new StringBuilder("SchoolDto[");
    builder.append("id:").append(id);
    builder.append(", schoolName:").append(schoolName);
    builder.append("].");

    return builder;
  }

  @Override
  public String toString() {
    return getStringBuilder().toString();
  }

  private SchoolSummaryDto() {
  }

  @Override
  public String validate() {
    StringBuilder builder = new StringBuilder("");

    // id will be set only by server. So not checking it.
    if (schoolName.isEmpty()) {
      builder.append("schoolName is empty.\n");
    }
    return builder.toString();
  }

  public long getId() {
    return id;
  }

  public String getSchoolName() {
    return schoolName;
  }

  public static class Builder {
    private long id;
    private String schoolName;

    public Builder setId(long id) {
      this.id = id;
      return this;
    }

    public Builder setSchoolName(String schoolName) {
      this.schoolName = schoolName;
      return this;
    }

    public SchoolSummaryDto build() {
      SchoolSummaryDto dto = new SchoolSummaryDto();
      dto.id = this.id;
      dto.schoolName = this.schoolName;
      dto.validate();
      return dto;
    }
  }
}
