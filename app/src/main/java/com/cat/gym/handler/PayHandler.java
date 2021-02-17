package com.cat.gym.handler;

import java.sql.Date;
import java.util.Iterator;
import java.util.LinkedList;
import com.cat.gym.domain.Pay;
import com.cat.util.Prompt;

public class PayHandler {

  private LinkedList<Pay> payList =  new LinkedList<>();

  private MemberHandler memberHandler;

  public PayHandler(MemberHandler memberHandler) {
    this.memberHandler = memberHandler;
  }

  public void service() throws CloneNotSupportedException {
    loop:
      while (true) {
        String command = Prompt.inputString(""
            + "=========================================================================\n"
            + "|                           < 결제/예약 메뉴 >          /pay/...        |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|[신청] = /add     [목록] = /list   [정보] = /detail  [변경] = /update  |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|[취소] = /delete  [홈 화면] = home                                     |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "\n명령어> ");
        System.out.println();

        try {
          switch (command.toLowerCase()) {
            case "/add":
              this.add();
              break;
            case "/list":
              this.list();
              break;
            case "/detail":
              this.detail();
              break;
            case "/update":
              this.update();
              break;
            case "/delete":
              this.delete();
              break;
            case "home":
              break loop;
            default:
              System.out.println("실행할 수 없는 명령어입니다.");
              System.out.println();
          }
        } catch (Exception e) {
          System.out.println("---------------------------------------------------------");
          System.out.printf("명령어 실행중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
          System.out.println("---------------------------------------------------------");
        }
      }
  }

  public void add() {
    System.out.println("[결제/예약 신청]");
    System.out.println();

    Pay p = new Pay();

    p.setId(memberHandler.inputMember("아이디(취소: 빈 문자열): "));
    if (p.getId() == null) {
      System.out.println("결제/예약을 취소합니다.");
      System.out.println();
      return;
    }

    p.setSelect(Prompt.inputInt("회원권 선택\n"
        + "0: 1개월(80,000원)\n"
        + "1: 3개월(90,000원)\n"
        + "2: 6개월(150,000원)\n"
        + "3: 1년(240,000원)\n"
        + "> "));
    p.setJoin(Prompt.inputString("신규 회원 - 가입비 33,000원(O/X): "));
    p.setRental(Prompt.inputString("운동복 대여 - 월 1만원(O/X): "));
    p.setLocker(Prompt.inputString("개인 락커 예약 - 월 1만원(O/X): "));
    //    p.setCard(Prompt.inputString("카드 정보: "));
    //    p.setHistory(Prompt.inputString("결재 내역: "));
    p.setStartDate(Prompt.inputDate("시작일(yyyy-MM-dd): "));
    payList.add(p);
    System.out.println();
    System.out.println("결제/예약이 완료되었습니다.");
    System.out.println();
  }

  public void list() throws CloneNotSupportedException {
    System.out.println("[결제/예약 목록]");
    System.out.println();

    Iterator<Pay> iterator = payList.iterator();

    while (iterator.hasNext()) {
      Pay p = iterator.next();
      System.out.printf("%s %s %s\n",
          p.getId(), getSelectLabel(p.getSelect()), p.getStartDate());
      System.out.println();
    }
  }

  public void detail() {
    System.out.println("[결제/예약 정보]");
    System.out.println();

    String id = Prompt.inputString("아이디: ");

    Pay pay = findById(id);
    if (pay == null) {
      System.out.println();
      System.out.println("해당 아이디의 회원이 없습니다.");
      System.out.println();
      return;
    }

    System.out.printf("회원권: %s\n", getSelectLabel(pay.getSelect()));
    System.out.printf("신규 회원: %s\n", pay.getJoin());
    System.out.printf("운동복 대여: %s\n", pay.getRental());
    System.out.printf("개인 락커 예약: %s\n", pay.getLocker());
    //    System.out.printf("카드 정보: %s\n", pay.getCard());
    //    System.out.printf("결제 내역: %s\n", pay.getHistory());
    System.out.printf("시작일: %s\n", pay.getStartDate());
    System.out.println();
  }

  public void update() {
    System.out.println("[결제/예약 변경]");
    System.out.println();

    String id = Prompt.inputString("아이디 확인: ");
    System.out.println();

    Pay pay = findById(id);
    if (pay == null) {
      System.out.println("해당 아이디의 회원이 없습니다.");
      System.out.println();
      return;
    }

    String rental = Prompt.inputString(String.format("운동복 대여(%s): ", pay.getRental()));
    String locker = Prompt.inputString(String.format("개인 락커 예약(%s): ", pay.getLocker()));
    Date startDate = Prompt.inputDate(String.format("시작일(%s): ", pay.getStartDate()));

    System.out.println();
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    System.out.println();

    if (input.equalsIgnoreCase("Y")) {
      pay.setRental(rental);
      pay.setLocker(locker);
      pay.setStartDate(startDate);
      System.out.println("결제/예약을 변경하였습니다.");
      System.out.println();

    } else {
      System.out.println("결제/예약 변경을 취소하였습니다.");
      System.out.println();
    }
  }

  public void delete() {
    System.out.println("[결제/예약 취소]");
    System.out.println();

    String id = Prompt.inputString("아이디 확인: ");
    System.out.println();

    Pay pay = findById(id);
    if (pay == null) {
      System.out.println("해당 아이디의 회원이 없습니다.");
      System.out.println();
      return;
    }

    String input = Prompt.inputString("정말 취소하시겠습니까?(y/N) ");
    System.out.println();

    if (input.equalsIgnoreCase("Y")) {
      payList.remove(pay);
      System.out.println("결제/예약을 취소하였습니다.");
      System.out.println();

    } else {
      System.out.println("결제/예약 취소가 진행되지 않았습니다.");
      System.out.println();
    }
  }

  String getSelectLabel(int select) {
    switch (select) {
      case 1:
        return "3개월(90,000원)";
      case 2:
        return "6개월(150,000원)";
      case 3:
        return "1년(240,000원)";
      default:
        return "1개월(80,000원)";
    }
  }

  private Pay findById(String memberId) {
    Pay[] arr = payList.toArray(new Pay[payList.size()]);
    for (Pay p : arr) {
      if (p.getId().equals(memberId)) {
        return p;
      }
    }
    return null;
  }
}
