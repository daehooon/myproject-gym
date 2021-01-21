package com.cat.gym.handler;

import java.sql.Date;
import com.cat.util.Prompt;

public class MemberHandler {

  static class Member {
    String name;
    String phoneNumber;
    String residence;
    String id;
    String password;
    Date apply;
  }

  static final int LENGTH = 100;
  static Member[] members = new Member[LENGTH];
  static int size = 0;

  public static void add() {
    System.out.println("[회원 등록]");
    System.out.println();

    Member m = new Member();

    m.name = Prompt.inputString("이름: ");
    m.phoneNumber = Prompt.inputString("전화번호: ");
    m.residence = Prompt.inputString("주소: ");
    m.id = Prompt.inputString("아이디: ");
    m.password = Prompt.inputString("비밀번호: ");
    m.apply = new java.sql.Date(System.currentTimeMillis());
    members[size++] = m;
    System.out.println();
  }

  public static void list() {
    System.out.println("[회원 정보]");
    System.out.println();
    for (int i = 0; i < size; i++) {
      Member m = members[i];
      System.out.printf("이름: %s\n"
          + "전화번호: %s\n"
          + "주소: %s\n"
          + "아이디: %s\n"
          + "비밀번호: %s\n"
          + "가입일: %s\n",
          m.name, m.phoneNumber, m.residence, m.id,
          m.password, m.apply);
      System.out.println();
    }
  }

  public static boolean exist(String id) {
    for(int i = 0; i < size; i++) {
      if (id.equals(members[i].id)) {
        return true;
      }
    }
    return false;
  }
}
