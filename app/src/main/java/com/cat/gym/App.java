package com.cat.gym;

public class App {

  public static void main(String[] args) {

    loop:
      while (true) {
        String command = Prompt.inputString("======================================================================================\n"
            + "득                                   < Cat Gym >                                    근\n"
            + "======================================================================================\n"
            + "|------------------------------------------------------------------------------------|\n"
            + "|[회원등록] = /member/add  [트레이너등록] = /trainer/add  [결제/예약관리] = /pay/add |\n"
            + "|[회원정보] = /member/list [트레이너정보] = /trainer/list [결제/예약정보] = /pay/list|\n"
            + "|------------------------------------------------------------------------------------|\n"
            + "|[프로그램종료] = exit                                                               |\n"
            + "|------------------------------------------------------------------------------------|\n"
            + "\n명령> ");
        System.out.println();

        switch (command.toLowerCase()) {
          case "/member/add":
            MemberHandler.add();
            break;
          case "/member/list":
            MemberHandler.list();
            break;

          case "/trainer/add":
            TrainerHandler.add();
            break;
          case "/trainer/list":
            TrainerHandler.list();
            break;

          case "/pay/add":
            PayHandler.add();
            break;
          case "/pay/list":
            PayHandler.list();
            break;

          case "quit":
          case "exit":
            System.out.println("프로그램을 종료합니다.");
            break loop;

          default:
            System.out.println("실행할 수 없는 명령입니다.");
        }
      }

    Prompt.close();
  }
}
