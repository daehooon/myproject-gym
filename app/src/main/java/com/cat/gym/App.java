package com.cat.gym;

import com.cat.gym.handler.BoardHandler;
import com.cat.gym.handler.MemberHandler;
import com.cat.gym.handler.PayHandler;
import com.cat.gym.handler.TrainerHandler;
import com.cat.util.Prompt;

public class App {

  public static void main(String[] args) {

    MemberHandler memberHandler = new MemberHandler();

    TrainerHandler trainerHandler = new TrainerHandler(memberHandler);

    PayHandler payHandler = new PayHandler(memberHandler);

    BoardHandler boardHandler = new BoardHandler(memberHandler);

    loop:
      while (true) {
        String command = Prompt.inputString(""
            + "=========================================================================\n"
            + "|                              ▶ Cat Gym ◀                              |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|[회원 메뉴] = /member [결제/예약 메뉴] = /pay [게시글 메뉴] = /board   |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|[트레이너 메뉴] = /trainer                                             |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|[프로그램 종료] = exit                                                 |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "\n명령어> ");
        System.out.println();

        switch (command.toLowerCase()) {
          case "/member":
            memberHandler.service();
            break;
          case "/pay":
            //payHandler.service();
            break;
          case "/board":
            boardHandler.service();
            break;
          case "/trainer":
            trainerHandler.service();
            break;
          case "exit":
            System.out.println("득근하세요!! Ten Reps!!!");
            break loop;
          default:
            System.out.println("실행할 수 없는 명령어입니다.");
            System.out.println();
        }
      }

    Prompt.close();
  }
}
