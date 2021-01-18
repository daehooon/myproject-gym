package com.cat.gym;

import java.sql.Date;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Scanner keyScan = new Scanner(System.in);

    // 회원 데이터
    final int LENGTH = 100;
    int[] id = new int[LENGTH];
    String[] name = new String[LENGTH];
    String[] phoneNumber = new String[LENGTH];
    String[] residence = new String[LENGTH];
    String[] password = new String[LENGTH];
    Date[] apply = new Date[LENGTH];

    int size = 0;

    // 트레이너 데이터
    final int PLENGTH = 100;
    String[] bag = new String[PLENGTH];
    String[] photo = new String[PLENGTH];
    String[] tName = new String[PLENGTH];
    String[] tPhone = new String[PLENGTH];
    Date[] contractS = new Date[PLENGTH];
    Date[] contractE = new Date[PLENGTH];
    String[] members = new String[PLENGTH];

    int psize = 0;

    // 결제/예약 데이터
    final int TLENGTH = 100;

    int[] select = new int[TLENGTH];
    String[] rental = new String[TLENGTH];
    String[] locker = new String[TLENGTH];
    String[] card = new String[TLENGTH];
    String[] history = new String[TLENGTH];
    Date[] startDate = new Date[TLENGTH];

    int tsize = 0;

    loop:
      while (true) {
        System.out.println("<Cat Gym>");
        System.out.println("명령어");
        System.out.println("[회원등록] = /member/add [회원정보] = /member/list");
        System.out.println("[트레이너등록] = /trainer/add [트레이너정보] = /trainer/list");
        System.out.println("[결제/예약관리] = /pay/add [결제/예약정보] = /pay/list");
        System.out.print("명령> ");
        String command = keyScan.nextLine();

        switch (command) {
          case "/member/add":
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
            break;
          case "/member/list":
            System.out.println("[회원정보]");
            for (int i = 0; i < size; i++) {
              // 회원번호, 이름, 전화번호, 주소, 비밀번호, 가입일
              System.out.printf("%d, %s, %s, %s, %s, %s\n",
                  id[i], name[i], phoneNumber[i], residence[i], password[i], apply[i]);
            }
            break;
          case "/trainer/add":
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
            break;
          case "/trainer/list":
            System.out.println("[트레이너정보]");

            for (int i = 0; i < psize; i++) {
              // 전문분야, 사진, 이름, 전화번호, 계약시작일, 계약종료일, 팀원
              System.out.printf("%s, %s, %s, %s, %s, %s, %s\n",
                  bag[i], photo[i], tName[i], tPhone[i], contractS[i], contractE[i], members[i]);
            }
            break;
          case "/pay/add":
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
            break;
          case "/pay/list":
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
}
