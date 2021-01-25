package com.cat.gym;

import com.cat.gym.handler.BoardHandler;
import com.cat.gym.handler.MemberHandler;
import com.cat.gym.handler.PayHandler;
import com.cat.gym.handler.TrainerHandler;
import com.cat.util.Prompt;

public class App {

  public static void main(String[] args) {

    MemberHandler memberList = new MemberHandler();

    TrainerHandler trainerList = new TrainerHandler();

    trainerList.memberList = memberList;

    PayHandler payList = new PayHandler();

    payList.memberList = memberList;

    BoardHandler boardList = new BoardHandler();
    BoardHandler boardList2 = new BoardHandler();
    BoardHandler boardList3 = new BoardHandler();

    boardList.memberList = memberList;
    boardList2.memberList = memberList;
    boardList3.memberList = memberList;

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
            memberList.add();
            break;
          case "/member/list":
            memberList.list();
            break;
          case "/trainer/add":
            trainerList.add();
            break;
          case "/trainer/list":
            trainerList.list();
            break;
          case "/pay/add":
            payList.add();
            break;
          case "/pay/list":
            payList.list();
            break;
          case "/board/add":
            boardList.add();
            break;
          case "/board/list":
            boardList.list();
            break;
          case "/board2/add":
            boardList2.add();
            break;
          case "/board2/list":
            boardList2.list();
            break;
          case "/board3/add":
            boardList3.add();
            break;
          case "/board3/list":
            boardList3.list();
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
