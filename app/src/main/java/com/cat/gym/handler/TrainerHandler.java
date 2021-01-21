package com.cat.gym.handler;

import java.sql.Date;
import com.cat.util.Prompt;

public class TrainerHandler {

  static class Trainer {
    String bag;
    String photo;
    String name;
    String phoneNumber;
    Date contractS;
    Date contractE;
    String members;
  }

  static final int PLENGTH = 100;
  static Trainer[] trainers = new Trainer[PLENGTH];
  static int psize = 0;

  public static void add() {
    System.out.println("[트레이너 등록]");
    System.out.println();

    Trainer t = new Trainer();

    t.bag = Prompt.inputString("전문분야: ");
    t.photo = Prompt.inputString("사진: ");
    t.name = Prompt.inputString("이름: ");
    t.phoneNumber = Prompt.inputString("전화번호: ");
    t.contractS = Prompt.inputDate("계약 시작일: ");
    t.contractE = Prompt.inputDate("계약 종료일: ");

    t.members = "";
    while (true) {
      String name = Prompt.inputString("PT회원 ID등록(완료: 빈 문자열): ");
      if (name.length() == 0) {
        break;
      } else if (MemberHandler.exist(name)) {
        if (!t.members.isEmpty()) {
          t.members += ",";
        }
        t.members += name;
      } else {
        System.out.println("등록된 회원이 아닙니다.");
        System.out.println();
      }
    }

    trainers[psize++] = t;
    System.out.println();
  }

  public static void list() {
    System.out.println("[트레이너 정보]");
    System.out.println();

    for (int i = 0; i < psize; i++) {
      Trainer t = trainers[i];
      System.out.printf("전문분야: %s\n"
          + "사진: %s\n"
          + "이름: %s\n"
          + "전화번호: %s\n"
          + "계약 시작일: %s\n"
          + "계약 종료일: %s\n"
          + "PT회원 ID목록: [%s]\n",
          t.bag, t.photo, t.name, t.phoneNumber,
          t.contractS, t.contractE, t.members);
      System.out.println();
    }
  }
}