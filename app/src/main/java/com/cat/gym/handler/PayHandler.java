package com.cat.gym.handler;

import com.cat.gym.domain.Pay;
import com.cat.util.Prompt;

public class PayHandler {

  static final int TLENGTH = 100;

  public MemberHandler memberList;

  Pay[] pays = new Pay[TLENGTH];
  int tsize = 0;

  public void add() {
    System.out.println("[결제/예약 관리]");
    System.out.println();

    Pay p = new Pay();

    while (true) {
      String id = Prompt.inputString("아이디(취소: 빈 문자열): ");
      if (id.length() == 0) {
        System.out.println("결제/예약을 취소합니다.");
        System.out.println();
        return;
      } 
      if (this.memberList.exist(id)) {
        p.id = id;
        break;
      }
      System.out.println("등록된 회원이 아닙니다.");
      System.out.println();
    }

    p.select = Prompt.inputInt("회원권선택\n"
        + "0: 1개월(80,000원)\n"
        + "1: 3개월(90,000원)\n"
        + "2: 6개월(150,000원)\n"
        + "3: 1년(240,000원)\n"
        + "> ");
    p.rental = Prompt.inputString("운동복대여: ");
    p.locker = Prompt.inputString("락커예약: ");
    p.card = Prompt.inputString("카드정보: ");
    p.history = Prompt.inputString("결재내역: ");
    p.startDate = Prompt.inputDate("시작일(YYYY-MM-DD): ");
    this.pays[this.tsize++] = p;
    System.out.println();
  }

  public void list() {
    System.out.println("[결제/예약 정보]");
    System.out.println();

    for (int i = 0; i < this.tsize; i++) {
      Pay p = this.pays[i];
      String selectLabel = null;
      switch (p.select) {
        case 1:
          selectLabel = "3개월(90,000원)";
          break;
        case 2:
          selectLabel = "6개월(150,000원)";
          break;
        case 3:
          selectLabel = "1년(240,000원)";
          break;
        default:
          selectLabel = "1개월(80,000원)";
          break;
      }

      System.out.printf("회원권선택: %s\n"
          + "운동복대여: %s\n"
          + "락커예약: %s\n"
          + "카드정보: %s\n"
          + "결제내역: %s\n"
          + "시작일: %s\n",
          selectLabel, p.rental, p.locker, p.card,
          p.history, p.startDate);
      System.out.println();
    }

  }

}
