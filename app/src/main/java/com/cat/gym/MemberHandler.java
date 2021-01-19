package com.cat.gym;

import java.sql.Date;

public class MemberHandler {

  static final int LENGTH = 100;
  static int[] id = new int[LENGTH];
  static String[] name = new String[LENGTH];
  static String[] phoneNumber = new String[LENGTH];
  static String[] residence = new String[LENGTH];
  static String[] password = new String[LENGTH];
  static Date[] apply = new Date[LENGTH];
  static int size = 0;

  static void add() {
    System.out.println("[회원등록]");
    System.out.println();

    id[size] = Prompt.inputInt("회원번호: ");
    name[size] = Prompt.inputString("이름: ");
    phoneNumber[size] = Prompt.inputString("전화번호: ");
    residence[size] = Prompt.inputString("주소: ");
    password[size] = Prompt.inputString("비밀번호: ");
    apply[size] = new java.sql.Date(System.currentTimeMillis());
    size++;
    System.out.println();
  }

  static void list() {
    System.out.println("[회원정보]");
    System.out.println();
    for (int i = 0; i < size; i++) {
      System.out.printf("회원번호: %d\n"
          + "이름: %s\n"
          + "전화번호: %s\n"
          + "주소: %s\n"
          + "비밀번호: %s\n"
          + "가입일: %s\n",
          id[i], name[i], phoneNumber[i], residence[i],
          password[i], apply[i]);
      System.out.println();
    }
  }
}
