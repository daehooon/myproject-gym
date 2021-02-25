package com.cat.gym.handler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import com.cat.gym.domain.Member;
import com.cat.gym.domain.Pay;
import com.cat.util.Prompt;

public class PayMenu {

  public static void main(String[] args, LinkedList<Member> memberList, List<Pay> payList) {

    HashMap<String,Command> commandMap = new HashMap<>();
    MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberList);

    commandMap.put("/add", new PayAddHandler(payList, memberValidatorHandler));
    commandMap.put("/list", new PayListHandler(payList));
    commandMap.put("/detail", new PayDetailHandler(payList));
    commandMap.put("/update", new PayUpdateHandler(payList));
    commandMap.put("/delete", new PayDeleteHandler(payList));


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
