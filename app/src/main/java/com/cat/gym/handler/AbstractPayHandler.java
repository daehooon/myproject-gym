package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Pay;

public abstract class AbstractPayHandler implements Command {

  protected List<Pay> payList;

  public AbstractPayHandler(List<Pay> payList) {
    this.payList = payList;
  }

  protected String getSelectLabel(int select) {
    while (true) {
      if (select == 0) {
        return "1개월(80,000원)";
      } else if (select == 1) {
        return "3개월(90,000원)";
      } else if (select == 2) {
        return "6개월(150,000원)";
      } else if (select == 3) {
        return "1년(240,000원)";
      } else {
        System.out.println("올바른 값이 아닙니다. 다시 입력해 주세요.");
        continue;
      }
    }
  }

  //  protected String getSelectLabel(int select) {
  //    switch (select) {
  //      case 1:
  //        return "3개월(90,000원)";
  //      case 2:
  //        return "6개월(150,000원)";
  //      case 3:
  //        return "1년(240,000원)";
  //      default:
  //        return "1개월(80,000원)";
  //    }
  //  }

  protected Pay findById(String memberId) {
    Pay[] arr = payList.toArray(new Pay[payList.size()]);
    for (Pay p : arr) {
      if (p.getId().equals(memberId)) {
        return p;
      }
    }
    return null;
  }

}
