package com.cat.gym.handler;

import com.cat.gym.domain.Member;
import com.cat.util.Prompt;

public class MemberHandler {

  private MemberList memberList = new MemberList();

  public MemberList getMemberList() {
    return this.memberList;
  }

  public void service() {
    loop:
      while (true) {
        String command = Prompt.inputString(""
            + "=========================================================================\n"
            + "|                             < 회원 메뉴 >        /member/...          |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|[등록] = /add     [목록] = /list   [정보] = /detail  [변경] = /update  |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|[탈퇴] = /delete  [홈 화면] = home                                     |\n"
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
    System.out.println("[회원 등록]");
    System.out.println();

    Member m = new Member();

    m.setName(Prompt.inputString("이름: "));
    m.setPhoneNumber(Prompt.inputString("전화번호: "));
    m.setResidence(Prompt.inputString("주소: "));
    m.setId(Prompt.inputString("아이디: "));
    m.setPassword(Prompt.inputString("비밀번호: "));
    m.setApply(new java.sql.Date(System.currentTimeMillis()));

    memberList.add(m);

    System.out.println();
    System.out.println("Cat Gym 회원이 되신걸 환영합니다!");
    System.out.println();
  }

  public void list() {
    System.out.println("[회원 목록]");
    System.out.println();

    Member[] members = memberList.toArray();
    for (Member m : members) {
      System.out.printf("%s %s %s\n", m.getName(), m.getId(), m.getPhoneNumber());
      System.out.println();
    }
  }

  public void detail() {
    System.out.println("[회원 정보]");
    System.out.println();

    String id = Prompt.inputString("아이디: ");

    Member member = memberList.get(id);
    if (member == null) {
      System.out.println();
      System.out.println("해당 아이디의 회원이 없습니다.");
      System.out.println();
      return;
    }

    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("전화번호: %s\n", member.getPhoneNumber());
    System.out.printf("주소: %s\n", member.getResidence());
    System.out.printf("비밀번호: %s\n", member.getPassword());
    System.out.printf("가입일: %s\n", member.getApply());
    System.out.println();
  }

  public void update() {
    System.out.println("[회원 정보 변경]");
    System.out.println();

    String id2 = Prompt.inputString("아이디 확인: ");
    System.out.println();

    Member member = memberList.get(id2);
    if (member == null) {
      System.out.println("해당 아이디의 회원이 없습니다.");
      System.out.println();
      return;
    }

    String name = Prompt.inputString(String.format("이름(%s): ", member.getName()));
    String phoneNumber = Prompt.inputString(String.format("전화번호(%s): ", member.getPhoneNumber()));
    String residence = Prompt.inputString(String.format("주소(%s): ", member.getResidence()));
    String id = Prompt.inputString(String.format("아이디(%s): ", member.getId()));
    String password = Prompt.inputString(String.format("비밀번호(%s): ", member.getPassword()));

    System.out.println();
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    System.out.println();

    if (input.equalsIgnoreCase("Y")) {
      member.setName(name);
      member.setPhoneNumber(phoneNumber);
      member.setResidence(residence);
      member.setId(id);
      member.setPassword(password);
      System.out.println("정보를 변경하였습니다.");
      System.out.println();

    } else {
      System.out.println("정보 변경을 취소하였습니다.");
      System.out.println();
    }
  }

  public void delete() {
    System.out.println("[회원 탈퇴]");
    System.out.println();

    String id = Prompt.inputString("아이디 확인: ");
    System.out.println();

    Member member = memberList.get(id);
    if (member == null) {
      System.out.println("해당 아이디의 회원이 없습니다.");
      System.out.println();
      return;
    }

    String input = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) ");
    System.out.println();

    if (input.equalsIgnoreCase("Y")) {
      memberList.delete(id);
      System.out.println("회원을 탈퇴하였습니다.");
      System.out.println();

    } else {
      System.out.println("회원 탈퇴를 취소하였습니다.");
      System.out.println();
    }
  }

  public String inputMember(String promptTitle) {
    while (true) {
      String id = Prompt.inputString(promptTitle);
      if (id.length() == 0) {
        return null;
      } else if (this.memberList.exist(id)) {
        return id;
      } else {
        System.out.println("등록된 회원이 아닙니다.");
        System.out.println();
      }
    }
  }

  public String inputMembers(String promptTitle) {
    String members = "";
    while (true) {
      String name = inputMember(promptTitle);
      if (name == null) {
        return members;
      } else {
        if (!members.isEmpty()) {
          members += ",";
        }
        members += name;
      }
    }
  }
}
