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

    System.out.print("회원번호: ");
    id[size] = Integer.parseInt(keyScan.nextLine());

    System.out.print("이름: ");
    name[size] = keyScan.nextLine();

    System.out.print("전화번호: ");
    phoneNumber[size] = keyScan.nextLine();

    System.out.print("주소: ");
    residence[size] = keyScan.nextLine();

    System.out.print("비밀번호: ");
    password[size] = keyScan.nextLine();

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

    System.out.print("전문분야: ");
    bag[psize] = keyScan.nextLine();

    System.out.print("사진: ");
    photo[psize] = keyScan.nextLine();

    System.out.print("이름: ");
    tName[psize] = keyScan.nextLine();

    System.out.print("전화번호: ");
    tPhone[psize] = keyScan.nextLine();

    System.out.print("계약시작일: ");
    contractS[psize] = Date.valueOf(keyScan.nextLine());

    System.out.print("계약종료일: ");
    contractE[psize] = Date.valueOf(keyScan.nextLine());

    System.out.print("팀원: ");
    members[psize] = keyScan.nextLine();

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

    System.out.println("회원권선택");
    System.out.println("0: 3개월(90,000원)");
    System.out.println("1: 6개월(150,000원)");
    System.out.println("2: 1년(240,000원)");
    System.out.print("> ");
    select[tsize] = Integer.valueOf(keyScan.nextLine());

    System.out.print("운동복대여: ");
    rental[tsize] = keyScan.nextLine();

    System.out.print("락커예약: ");
    locker[tsize] = keyScan.nextLine();

    System.out.print("카드정보: ");
    card[tsize] = keyScan.nextLine();

    System.out.print("결재내역: ");
    history[tsize] = keyScan.nextLine();

    System.out.print("시작일: ");
    startDate[tsize] = Date.valueOf(keyScan.nextLine());

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
  
}
