package com.cat.gym.domain;

import java.sql.Date;

public class Member {
  private String name;
  private String phoneNumber;
  private String residence;
  private String id;
  private String password;
  private Date apply;

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPhoneNumber() {
    return phoneNumber;
  }
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  public String getResidence() {
    return residence;
  }
  public void setResidence(String residence) {
    this.residence = residence;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public Date getApply() {
    return apply;
  }
  public void setApply(Date apply) {
    this.apply = apply;
  }
}
