package com.cat.gym.handler;

import java.sql.Date;
import com.cat.gym.domain.Trainer;
import com.cat.util.Prompt;

public class TrainerHandler {

  static final int PLENGTH = 100;

  MemberHandler memberList;

  Trainer[] trainers = new Trainer[PLENGTH];
  int size = 0;
  
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
  
  public TrainerHandler(MemberHandler memberHandler) {
    this.memberList = memberHandler;
  }

  public void add() {
    System.out.println("[트레이너 등록]");
    System.out.println();

    Trainer t = new Trainer();

    t.no = Prompt.inputInt("등록 번호: ");
    t.bag = Prompt.inputString("전문분야: ");
    t.photo = Prompt.inputString("사진: ");
    t.name = Prompt.inputString("이름: ");
    t.phoneNumber = Prompt.inputString("전화번호: ");
    t.contractS = Prompt.inputDate("계약 시작일(YYYY-MM-DD): ");
    t.contractE = Prompt.inputDate("계약 종료일(YYYY-MM-DD): ");

    t.members = "";
    while (true) {
      String name = Prompt.inputString("PT회원 ID등록(완료: 빈 문자열): ");
      if (name.length() == 0) {
        break;
      } else if (this.memberList.exist(name)) {
        if (!t.members.isEmpty()) {
          t.members += ",";
        }
        t.members += name;
      } else {
        System.out.println("등록된 회원이 아닙니다.");
        System.out.println();
      }
    }

    this.trainers[this.size++] = t;
    System.out.println();
  }

  public void list() {
    System.out.println("[트레이너 목록]");
    System.out.println();

    for (int i = 0; i < this.size; i++) {
      Trainer t = this.trainers[i];
      System.out.printf("%d %s %s\n", t.no, t.name, t.phoneNumber);
      System.out.println();
    }
  }

  public void detail() {
    System.out.println("[트레이너 정보]");
    System.out.println();
    
    int no = Prompt.inputInt("등록 번호: ");
    
    Trainer trainer = findByNo(no);
    if (trainer == null) {
      System.out.println("해당 아이디의 회원이 없습니다.");
      return;
    }

    System.out.printf("전문 분야: %s\n", trainer.bag);
    System.out.printf("사진: %s\n", trainer.photo);
    System.out.printf("이름: %s\n", trainer.name);
    System.out.printf("전화번호: %s\n", trainer.phoneNumber);
    System.out.printf("계약 시작일: %s\n", trainer.contractS);
    System.out.printf("계약 종료일: %s\n", trainer.contractE);
    System.out.printf("PT회원 ID목록: [%s]\n", trainer.members);
    System.out.println();
  }
  
  public void update() {
    System.out.println("[트레이너 정보 변경]");
    System.out.println();
    
    int no = Prompt.inputInt("등록 번호: ");
    System.out.println();
    
    Trainer trainer = findByNo(no);
    if (trainer == null) {
      System.out.println("해당 번호의 트레이너가 없습니다.");
      System.out.println();
      return;
    }
    
    String bag = Prompt.inputString(String.format("전문 분야(%s): ", trainer.bag));
    String photo = Prompt.inputString(String.format("사진(%s): ", trainer.photo));
    String name = Prompt.inputString(String.format("이름(%s): ", trainer.name));
    String phoneNumber = Prompt.inputString(String.format("전화번호(%s): ", trainer.phoneNumber));
    Date contractE = Prompt.inputDate(String.format("계약 종료일(%s): ", trainer.contractE));
    String members = Prompt.inputString(String.format("PT회원 ID목록[%s]: ", trainer.members));
    
    System.out.println();
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    System.out.println();
    
    if (input.equalsIgnoreCase("Y")) {
      trainer.bag = bag;
      trainer.photo = photo;
      trainer.name = name;
      trainer.phoneNumber = phoneNumber;
      trainer.contractE = contractE;
      trainer.members = members;
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
    
    int i = indexOf(no);
    if (i == -1) {
      System.out.println("해당 번호의 트레이너가 없습니다.");
      System.out.println();
      return;
    }
    
    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    System.out.println();
    
    if (input.equalsIgnoreCase("Y")) {
      for (int x = i + 1; x < this.size; x++) {
        this.trainers[x-1] = this.trainers[x];
      }
      trainers[--this.size] = null; 
      
      System.out.println("트레이너를 삭제하였습니다.");
      System.out.println();
      
    } else {
      System.out.println("트레이너 삭제를 취소하였습니다.");
      System.out.println();
    }
  }
  
  int indexOf(int trainerNo) {
    for (int i = 0; i < this.size; i++) {
      Trainer trainer = this.trainers[i];
      if (trainer.no == trainerNo) {
        return i;
      }
    }
    return -1;
  }
  
  Trainer findByNo(int trainerNo) {
    int i = indexOf(trainerNo);
    if (i == -1)
      return null;
    else
      return this.trainers[i];
  }
  
}
