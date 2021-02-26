package com.cat.gym.domain;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String phoneNumber;
  private String residence;
  private String id;
  private String password;
  private Date apply;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((apply == null) ? 0 : apply.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
    result = prime * result + ((residence == null) ? 0 : residence.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Member other = (Member) obj;
    if (apply == null) {
      if (other.apply != null)
        return false;
    } else if (!apply.equals(other.apply))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    if (phoneNumber == null) {
      if (other.phoneNumber != null)
        return false;
    } else if (!phoneNumber.equals(other.phoneNumber))
      return false;
    if (residence == null) {
      if (other.residence != null)
        return false;
    } else if (!residence.equals(other.residence))
      return false;
    return true;
  }

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
