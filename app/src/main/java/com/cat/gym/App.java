package com.cat.gym;

import com.cat.gym.handler.BoardHandler;
import com.cat.gym.handler.BoardHandler2;
import com.cat.gym.handler.BoardHandler3;
import com.cat.gym.handler.MemberHandler;
import com.cat.gym.handler.PayHandler;
import com.cat.gym.handler.TrainerHandler;
import com.cat.util.Prompt;

public class App {

  public static void main(String[] args) {

    loop:
      while (true) {
        String command = Prompt.inputString("=========================================================================================\n"
            + "득                                     < Cat Gym >                                     근\n"
            + "=========================================================================================\n"
            + "|---------------------------------------------------------------------------------------|\n"
            + "|[회원 등록] = /member/add  [트레이너 등록] = /trainer/add  [결제/예약 관리] = /pay/add |\n"
            + "|[회원 정보] = /member/list [트레이너 정보] = /trainer/list [결제/예약 정보] = /pay/list|\n"
            + "|---------------------------------------------------------------------------------------|\n"
            + "|[게시글 등록] = /board/add [게시글 목록] = /board/list [임시 게시판] = board2, board3  |\n"
            + "|---------------------------------------------------------------------------------------|\n"
            + "|[프로그램종료] = exit                                                                  |\n"
            + "|---------------------------------------------------------------------------------------|\n"
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
          case "/board/add":
            BoardHandler.add();
            break;
          case "/board/list":
            BoardHandler.list();
            break;
          case "/board2/add":
            BoardHandler2.add();
            break;
          case "/board2/list":
            BoardHandler2.list();
            break;
          case "/board3/add":
            BoardHandler3.add();
            break;
          case "/board3/list":
            BoardHandler3.list();
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
