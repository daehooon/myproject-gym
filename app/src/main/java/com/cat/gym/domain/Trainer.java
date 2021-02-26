package com.cat.gym.domain;

import java.io.Serializable;
import java.sql.Date;

public class Trainer implements Serializable {
  private static final long serialVersionUID = 1L;
  private int no;
  private String bag;
  private String photo;
  private String name;
  private String phoneNumber;
  private Date contractS;
  private Date contractE;
  private String members;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((bag == null) ? 0 : bag.hashCode());
    result = prime * result + ((contractE == null) ? 0 : contractE.hashCode());
    result = prime * result + ((contractS == null) ? 0 : contractS.hashCode());
    result = prime * result + ((members == null) ? 0 : members.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + no;
    result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
    result = prime * result + ((photo == null) ? 0 : photo.hashCode());
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
    Trainer other = (Trainer) obj;
    if (bag == null) {
      if (other.bag != null)
        return false;
    } else if (!bag.equals(other.bag))
      return false;
    if (contractE == null) {
      if (other.contractE != null)
        return false;
    } else if (!contractE.equals(other.contractE))
      return false;
    if (contractS == null) {
      if (other.contractS != null)
        return false;
    } else if (!contractS.equals(other.contractS))
      return false;
    if (members == null) {
      if (other.members != null)
        return false;
    } else if (!members.equals(other.members))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (no != other.no)
      return false;
    if (phoneNumber == null) {
      if (other.phoneNumber != null)
        return false;
    } else if (!phoneNumber.equals(other.phoneNumber))
      return false;
    if (photo == null) {
      if (other.photo != null)
        return false;
    } else if (!photo.equals(other.photo))
      return false;
    return true;
  }

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getBag() {
    return bag;
  }
  public void setBag(String bag) {
    this.bag = bag;
  }
  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
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
  public Date getContractS() {
    return contractS;
  }
  public void setContractS(Date contractS) {
    this.contractS = contractS;
  }
  public Date getContractE() {
    return contractE;
  }
  public void setContractE(Date contractE) {
    this.contractE = contractE;
  }
  public String getMembers() {
    return members;
  }
  public void setMembers(String members) {
    this.members = members;
  }
}
