package com.cat.gym.handler;

import java.util.HashMap;
import java.util.LinkedList;
import com.cat.gym.domain.Member;
import com.cat.gym.domain.Trainer;
import com.cat.util.Prompt;

public class TrainerMenu {

  public static void main(String[] args, LinkedList<Member> memberList) {

    LinkedList<Trainer> trainerList = new LinkedList<>();
    HashMap<String,Command> commandMap = new HashMap<>();
    MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberList);

    commandMap.put("/add", new TrainerAddHandler(trainerList, memberValidatorHandler));
    commandMap.put("/list", new TrainerListHandler(trainerList));
    commandMap.put("/detail", new TrainerDetailHandler(trainerList));
    commandMap.put("/update", new TrainerUpdateHandler(trainerList, memberValidatorHandler));
    commandMap.put("/delete", new TrainerDeleteHandler(trainerList));

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
