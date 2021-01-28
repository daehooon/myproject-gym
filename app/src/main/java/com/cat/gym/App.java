package com.cat.gym;

import com.cat.gym.handler.BoardHandler;
import com.cat.gym.handler.MemberHandler;
import com.cat.gym.handler.PayHandler;
import com.cat.gym.handler.TrainerHandler;
import com.cat.util.Prompt;

public class App {

  public static void main(String[] args) {

    MemberHandler memberList = new MemberHandler();

    TrainerHandler trainerList = new TrainerHandler(memberList);

    PayHandler payList = new PayHandler(memberList);

    BoardHandler boardList = new BoardHandler(memberList);

    loop:
      while (true) {
        String command = Prompt.inputString(""
            + "=========================================================================================\n"
            + "|                                     < Cat Gym >                                       |\n"
            + "|---------------------------------------------------------------------------------------|\n"
            + "|[회원 등록] = m1 [회원 정보] = m2            [트레이너 등록] = t1 [트레이너 정보] = t2 |\n"
            + "|---------------------------------------------------------------------------------------|\n"
            + "|[결제/예약 관리] = p1 [결제/예약 정보] = p2  [게시글 등록] = b1 [게시글 목록] = b2     |\n"
            + "|---------------------------------------------------------------------------------------|\n"
            + "|[프로그램종료] = exit                                                                  |\n"
            + "|---------------------------------------------------------------------------------------|\n"
            + "\n명령> ");
        System.out.println();

        switch (command.toLowerCase()) {
          case "m1":
            memberList.add();
            break;
          case "m2":
            memberList.list();
            break;
          case "t1":
            trainerList.add();
            break;
          case "t2":
            trainerList.list();
            break;
          case "p1":
            payList.add();
            break;
          case "p2":
            payList.list();
            break;
          case "b1":
            boardList.add();
            break;
          case "b2":
            boardList.list();
            break;
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
