package com.cat.gym.handler;

import java.sql.Date;
import com.cat.gym.domain.Trainer;
import com.cat.util.Prompt;

public class TrainerHandler {

  private TrainerList trainerList = new TrainerList();

  private MemberHandler memberHandler;

  public TrainerHandler(MemberHandler memberHandler) {
    this.memberHandler = memberHandler;
  }

  public void service() {
    loop:
      while (true) {
        String command = Prompt.inputString(""
            + "=========================================================================\n"
            + "|                            < 트레이너 메뉴 >        /trainer/...      |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|[등록] = /add     [목록] = /list   [정보] = /detail  [변경] = /update  |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|[삭제] = /delete  [홈 화면] = home                                     |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "\n명령어> ");
        System.out.println();

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
      }
  }

  public void add() {
    System.out.println("[트레이너 등록]");
    System.out.println();

    Trainer t = new Trainer();
    t.setNo(Prompt.inputInt("등록 번호: "));
    t.setBag(Prompt.inputString("전문분야: "));
    t.setPhoto(Prompt.inputString("사진: "));
    t.setName(Prompt.inputString("이름: "));
    t.setPhoneNumber(Prompt.inputString("전화번호: "));
    t.setContractS(Prompt.inputDate("계약 시작일(YYYY-MM-DD): "));
    t.setContractE(Prompt.inputDate("계약 종료일(YYYY-MM-DD): "));

    t.setMembers(memberHandler.inputMembers(
        String.format("PT회원 ID등록(완료: 빈 문자열): ", t.getMembers())));

    trainerList.add(t);

    System.out.println();
    System.out.println("신규 트레이너님 환영합니다..!");
    System.out.println();
  }

  public void list() {
    System.out.println("[트레이너 목록]");
    System.out.println();

    Trainer[] trainers = trainerList.toArray();
    for (Trainer t : trainers) {
      System.out.printf("%d %s %s\n", t.getNo(), t.getName(), t.getPhoneNumber());
      System.out.println();
    }
  }

  public void detail() {
    System.out.println("[트레이너 정보]");
    System.out.println();

    int no = Prompt.inputInt("등록 번호: ");

    Trainer trainer = trainerList.get(no);
    if (trainer == null) {
      System.out.println("해당 아이디의 회원이 없습니다.");
      System.out.println();
      return;
    }

    System.out.printf("전문 분야: %s\n", trainer.getBag());
    System.out.printf("사진: %s\n", trainer.getPhoto());
    System.out.printf("이름: %s\n", trainer.getName());
    System.out.printf("전화번호: %s\n", trainer.getPhoneNumber());
    System.out.printf("계약 시작일: %s\n", trainer.getContractS());
    System.out.printf("계약 종료일: %s\n", trainer.getContractE());
    System.out.printf("PT회원 ID목록: [%s]\n", trainer.getMembers());
    System.out.println();
  }

  public void update() {
    System.out.println("[트레이너 정보 변경]");
    System.out.println();

    int no = Prompt.inputInt("등록 번호: ");
    System.out.println();

    Trainer trainer = trainerList.get(no);
    if (trainer == null) {
      System.out.println("해당 번호의 트레이너가 없습니다.");
      System.out.println();
      return;
    }

    String bag = Prompt.inputString(String.format("전문 분야(%s): ", trainer.getBag()));
    String photo = Prompt.inputString(String.format("사진(%s): ", trainer.getPhoto()));
    String name = Prompt.inputString(String.format("이름(%s): ", trainer.getName()));
    String phoneNumber = Prompt.inputString(String.format("전화번호(%s): ", trainer.getPhoneNumber()));
    Date contractE = Prompt.inputDate(String.format("계약 종료일(%s): ", trainer.getContractE()));
    String members = Prompt.inputString(String.format("PT회원 ID목록[%s]: ", trainer.getMembers()));

    System.out.println();
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    System.out.println();

    if (input.equalsIgnoreCase("Y")) {
      trainer.setBag(bag);
      trainer.setPhoto(photo);
      trainer.setName(name);
      trainer.setPhoneNumber(phoneNumber);
      trainer.setContractE(contractE);
      trainer.setMembers(members);
      System.out.println("정보를 변경하였습니다.");
      System.out.println();

    } else {
      System.out.println("정보 변경을 취소하였습니다.");
      System.out.println();
    }
  }

  public void delete() {
    System.out.println("[트레이너 삭제]");
    System.out.println();

    int no = Prompt.inputInt("등록 번호: ");
    System.out.println();

    Trainer trainer = trainerList.get(no);
    if (trainer == null) {
      System.out.println("해당 번호의 트레이너가 없습니다.");
      System.out.println();
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    System.out.println();

    if (input.equalsIgnoreCase("Y")) {
      trainerList.delete(no);
      System.out.println("트레이너를 삭제하였습니다.");
      System.out.println();

    } else {
      System.out.println("트레이너 삭제를 취소하였습니다.");
      System.out.println();
    }
  }
}
