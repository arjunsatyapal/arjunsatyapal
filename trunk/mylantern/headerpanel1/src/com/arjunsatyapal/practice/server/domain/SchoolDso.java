package com.arjunsatyapal.practice.server.domain;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.arjunsatyapal.practice.shared.dtos.SchoolDto;
import com.arjunsatyapal.practice.shared.exceptions.InvalidClientInputException;

@PersistenceCapable(identityType = IdentityType.APPLICATION,
  detachable = "true")
public class SchoolDso implements AbstractDso {
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Long id;
  @Persistent
  private String schoolName;
  @Persistent
  private String address1;
  @Persistent
  private String address2;
  @Persistent
  private String city;
  @Persistent
  private String state;
  @Persistent
  private String zip;
  @Persistent
  private String adminEmail;
  
  @Override
  public StringBuilder getStringBuilder() {
    StringBuilder builder = new StringBuilder("SchoolDso[");
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
  
  private SchoolDso() {
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
  
  // TODO(arjuns) : This should be probably temporary. See if it can be removed.
  public void setId(long id) {
    this.id = id;
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
  
  public static SchoolDso fromDto(SchoolDto dto, boolean isCreate) {
    if (!dto.validate().isEmpty()) {
      throw new InvalidClientInputException("Invalid SchoolDso with Error : ["
        + dto.validate() + "].");
    }
    if (isCreate && dto.getId() != 0) {
      throw new InvalidClientInputException("Invalid Id[" + dto.getId()
        + "]. Only server is allowed to set the id. Clients should not set it.");
    }
    
    SchoolDso schoolDso = new SchoolDso();
    schoolDso.schoolName = dto.getSchoolName();
    schoolDso.address1 = dto.getAddress1();
    schoolDso.address2 = dto.getAddress2();
    schoolDso.city = dto.getCity();
    schoolDso.state = dto.getState();
    schoolDso.zip = dto.getZip();
    schoolDso.adminEmail = dto.getAdminEmail();
    
    return schoolDso;
  }
  
  public SchoolDto toDto() {
    return new SchoolDto.Builder().setId(id).setSchoolName(schoolName)
      .setAddress1(address1).setAddress2(address2).setCity(city)
      .setState(state).setZip(zip).setAdminEmail(adminEmail).build();
  }
}
