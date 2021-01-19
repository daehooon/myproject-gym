package com.cat.gym;

import java.sql.Date;

public class PayHandler {

  static final int TLENGTH = 100;
  static int[] select = new int[TLENGTH];
  static String[] rental = new String[TLENGTH];
  static String[] locker = new String[TLENGTH];
  static String[] card = new String[TLENGTH];
  static String[] history = new String[TLENGTH];
  static Date[] startDate = new Date[TLENGTH];
  static int tsize = 0;

  static void add() {
    System.out.println("[결제/예약관리]");
    System.out.println();

    select[tsize] = Prompt.inputInt("회원권선택\n"
        + "0: 3개월(90,000원)\n"
        + "1: 6개월(150,000원)\n"
        + "2: 1년(240,000원)\n"
        + "> ");
    rental[tsize] = Prompt.inputString("운동복대여: ");
    locker[tsize] = Prompt.inputString("락커예약: ");
    card[tsize] = Prompt.inputString("카드정보: ");
    history[tsize] = Prompt.inputString("결재내역: ");
    startDate[tsize] = Prompt.inputDate("시작일: ");
    tsize++;
    System.out.println();
  }

  static void list() {
    System.out.println("[결제/예약정보]");
    System.out.println();

    for (int i = 0; i < tsize; i++) {
      String selectLabel = null;
      switch (select[i]) {
        case 1:
          selectLabel = "3개월(90,000원)";
          break;
        case 2:
          selectLabel = "6개월(150,000원)";
          break;
        default:
          selectLabel = "1년(240,000원)";
      }
      System.out.printf("회원권선택: %s\n"
          + "운동복대여: %s\n"
          + "락커예약: %s\n"
          + "카드정보: %s\n"
          + "결제내역: %s\n"
          + "시작일: %s\n",
          selectLabel, rental[i], locker[i], card[i],
          history[i], startDate[i]);
      System.out.println();
    }
  }
}
