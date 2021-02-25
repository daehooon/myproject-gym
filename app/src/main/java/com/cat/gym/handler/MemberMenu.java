package com.cat.gym.handler;

import java.util.HashMap;
import java.util.List;
import com.cat.gym.domain.Member;
import com.cat.util.Prompt;

public class MemberMenu {

  public static void main(String[] args, List<Member> memberList) throws CloneNotSupportedException {

    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("/add", new MemberAddHandler(memberList));
    commandMap.put("/list", new MemberListHandler(memberList));
    commandMap.put("/detail", new MemberDetailHandler(memberList));
    commandMap.put("/update", new MemberUpdateHandler(memberList));
    commandMap.put("/delete", new MemberDeleteHandler(memberList));

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

        try {
          switch (command.toLowerCase()) {
            case "home":
              break loop;
            default:
              Command commandHandler = commandMap.get(command);

              if (commandHandler == null) {
                System.out.println("실행할 수 없는 명령어입니다.");
                System.out.println();
              } else {
                commandHandler.service();
              }
          }
        } catch (Exception e) {
          System.out.println("---------------------------------------------------------");
          System.out.printf("명령어 실행중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
          System.out.println("---------------------------------------------------------");
        }
      }

  }
}

