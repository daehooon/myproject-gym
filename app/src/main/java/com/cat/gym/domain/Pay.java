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
