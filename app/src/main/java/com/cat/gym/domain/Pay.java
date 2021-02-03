package com.cat.gym.domain;

import java.sql.Date;

public class Pay {
  private String id;
  private int select;
  private String join;
  private String rental;
  private String locker;
  private String card;
  private String history;
  private Date startDate;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((card == null) ? 0 : card.hashCode());
    result = prime * result + ((history == null) ? 0 : history.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((join == null) ? 0 : join.hashCode());
    result = prime * result + ((locker == null) ? 0 : locker.hashCode());
    result = prime * result + ((rental == null) ? 0 : rental.hashCode());
    result = prime * result + select;
    result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
    Pay other = (Pay) obj;
    if (card == null) {
      if (other.card != null)
        return false;
    } else if (!card.equals(other.card))
      return false;
    if (history == null) {
      if (other.history != null)
        return false;
    } else if (!history.equals(other.history))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (join == null) {
      if (other.join != null)
        return false;
    } else if (!join.equals(other.join))
      return false;
    if (locker == null) {
      if (other.locker != null)
        return false;
    } else if (!locker.equals(other.locker))
      return false;
    if (rental == null) {
      if (other.rental != null)
        return false;
    } else if (!rental.equals(other.rental))
      return false;
    if (select != other.select)
      return false;
    if (startDate == null) {
      if (other.startDate != null)
        return false;
    } else if (!startDate.equals(other.startDate))
      return false;
    return true;
  }
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public int getSelect() {
    return select;
  }
  public void setSelect(int select) {
    this.select = select;
  }
  public String getJoin() {
    return join;
  }
  public void setJoin(String join) {
    this.join = join;
  }
  public String getRental() {
    return rental;
  }
  public void setRental(String rental) {
    this.rental = rental;
  }
  public String getLocker() {
    return locker;
  }
  public void setLocker(String locker) {
    this.locker = locker;
  }
  public String getCard() {
    return card;
  }
  public void setCard(String card) {
    this.card = card;
  }
  public String getHistory() {
    return history;
  }
  public void setHistory(String history) {
    this.history = history;
  }
  public Date getStartDate() {
    return startDate;
  }
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
}
