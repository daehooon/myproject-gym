package com.cat.gym;

import java.sql.Date;

public class TrainerHandler {

  static final int PLENGTH = 100;
  static String[] bag = new String[PLENGTH];
  static String[] photo = new String[PLENGTH];
  static String[] tName = new String[PLENGTH];
  static String[] tPhone = new String[PLENGTH];
  static Date[] contractS = new Date[PLENGTH];
  static Date[] contractE = new Date[PLENGTH];
  static String[] members = new String[PLENGTH];
  static int psize = 0;

  static void add() {
    System.out.println("[트레이너등록]");
    System.out.println();

    bag[psize] = Prompt.inputString("전문분야: ");
    photo[psize] = Prompt.inputString("사진: ");
    tName[psize] = Prompt.inputString("이름: ");
    tPhone[psize] = Prompt.inputString("전화번호: ");
    contractS[psize] = Prompt.inputDate("계약시작일: ");
    contractE[psize] = Prompt.inputDate("계약종료일: ");
    members[psize] = Prompt.inputString("팀원: ");
    psize++;
    System.out.println();
  }

  static void list() {
    System.out.println("[트레이너정보]");
    System.out.println();

    for (int i = 0; i < psize; i++) {
      System.out.printf("전문분야: %s\n"
          + "사진: %s\n"
          + "이름: %s\n"
          + "전화번호: %s\n"
          + "계약시작일: %s\n"
          + "계약종료일: %s\n"
          + "팀원: %s\n",
          bag[i], photo[i], tName[i], tPhone[i],
          contractS[i], contractE[i], members[i]);
      System.out.println();
    }
  }
}
