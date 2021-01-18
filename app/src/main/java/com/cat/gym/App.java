package com.cat.gym;

import java.sql.Date;
import java.util.Scanner;

public class App {

  static Scanner keyScan = new Scanner(System.in);

  // 회원 데이터
  static final int LENGTH = 100;
  static int[] id = new int[LENGTH];
  static String[] name = new String[LENGTH];
  static String[] phoneNumber = new String[LENGTH];
  static String[] residence = new String[LENGTH];
  static String[] password = new String[LENGTH];
  static Date[] apply = new Date[LENGTH];
  static int size = 0;

  // 트레이너 데이터
  static final int PLENGTH = 100;
  static String[] bag = new String[PLENGTH];
  static String[] photo = new String[PLENGTH];
  static String[] tName = new String[PLENGTH];
  static String[] tPhone = new String[PLENGTH];
  static Date[] contractS = new Date[PLENGTH];
  static Date[] contractE = new Date[PLENGTH];
  static String[] members = new String[PLENGTH];
  static int psize = 0;

  // 결제/예약 데이터
  static final int TLENGTH = 100;
  static int[] select = new int[TLENGTH];
  static String[] rental = new String[TLENGTH];
  static String[] locker = new String[TLENGTH];
  static String[] card = new String[TLENGTH];
  static String[] history = new String[TLENGTH];
  static Date[] startDate = new Date[TLENGTH];
  static int tsize = 0;

  public static void main(String[] args) {

    loop:
      while (true) {
        String command = promptString("==============================================================\n"
            + "득                       < Cat Gym >                        근\n"
            + "==============================================================\n"
            + "|------------------------------------------------------------|\n"
            + "|[회원등록] = /member/add [회원정보] = /member/list          |\n"
            + "|[트레이너등록] = /trainer/add [트레이너정보] = /trainer/list|\n"
            + "|[결제/예약관리] = /pay/add [결제/예약정보] = /pay/list      |\n"
            + "|------------------------------------------------------------|\n"
            + "\n명령> ");

        switch (command.toLowerCase()) {
          case "/member/add":
            addMember();
            break;
          case "/member/list":
            listMember();
            break;

          case "/trainer/add":
            addTrainer();
            break;
          case "/trainer/list":
            listTrainer();
            break;

          case "/pay/add":
            addPay();
            break;
          case "/pay/list":
            listPay();
            break;

          case "quit":
          case "exit":
            System.out.println("프로그램을 종료합니다.");
            break loop;

          default:
            System.out.println("실행할 수 없는 명령입니다.");
        }
        System.out.println();
      }

    keyScan.close();
  }

  // 회원 등록
  static void addMember() {
    System.out.println("[회원등록]");

    id[size] = promptInt("회원번호: ");
    name[size] = promptString("이름: ");
    phoneNumber[size] = promptString("전화번호: ");
    residence[size] = promptString("주소: ");
    password[size] = promptString("비밀번호: ");
    apply[size] = new java.sql.Date(System.currentTimeMillis());
    size++;
  }

  // 회원 정보
  static void listMember() {
    System.out.println("[회원정보]");
    for (int i = 0; i < size; i++) {
      // 회원번호, 이름, 전화번호, 주소, 비밀번호, 가입일
      System.out.printf("%d, %s, %s, %s, %s, %s\n",
          id[i], name[i], phoneNumber[i], residence[i], password[i], apply[i]);
    }
  }

  // 트레이너 등록
  static void addTrainer() {
    System.out.println("[트레이너등록]");

    bag[psize] = promptString("전문분야: ");
    photo[psize] = promptString("사진: ");
    tName[psize] = promptString("이름: ");
    tPhone[psize] = promptString("전화번호: ");
    contractS[psize] = promptDate("계약시작일: ");
    contractE[psize] = promptDate("계약종료일: ");
    members[psize] = promptString("팀원: ");
    psize++;
  }

  // 트레이너 정보
  static void listTrainer() {
    System.out.println("[트레이너정보]");

    for (int i = 0; i < psize; i++) {
      // 전문분야, 사진, 이름, 전화번호, 계약시작일, 계약종료일, 팀원
      System.out.printf("%s, %s, %s, %s, %s, %s, %s\n",
          bag[i], photo[i], tName[i], tPhone[i], contractS[i], contractE[i], members[i]);
    }
  }

  // 결제/예약 관리
  static void addPay() {
    System.out.println("[결제/예약관리]");

    select[tsize] = promptInt("회원권선택\n"
        + "0: 3개월(90,000원)\n"
        + "1: 6개월(150,000원)\n"
        + "2: 1년(240,000원)\n"
        + "> ");
    rental[tsize] = promptString("운동복대여: ");
    locker[tsize] = promptString("락커예약: ");
    card[tsize] = promptString("카드정보: ");
    history[tsize] = promptString("결재내역: ");
    startDate[tsize] = promptDate("시작일: ");
    tsize++;
  }

  // 결제/예약 정보
  static void listPay() {
    System.out.println("[결제/예약정보]");

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
      // 회원권선택, 운동복대여, 락커예약, 카드정보, 결재내역, 시작일
      System.out.printf("%s, %s, %s, %s, %s, %s\n",
          selectLabel, rental[i], locker[i], card[i], history[i], startDate[i]);
    }
  }

  static String promptString(String title) {
    System.out.print(title);
    return keyScan.nextLine();
  }

  static int promptInt(String title) {
    return Integer.valueOf(promptString(title));
  }

  static Date promptDate(String title) {
    return Date.valueOf(promptString(title));
  }

}
