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
    BoardHandler boardList2 = new BoardHandler(memberList);
    BoardHandler boardList3 = new BoardHandler(memberList);

    loop:
      while (true) {
        String command = Prompt.inputString("=========================================================================================\n"
            + "득                                     < Cat Gym >                                     근\n"
            + "=========================================================================================\n"
            + "|---------------------------------------------------------------------------------------|\n"
            + "|[회원 등록(정보)] = /member/add (list)       [게시글 등록(목록)] = /board/add (list)   |\n"
            + "|[트레이너 등록(정보)] = /trainer/add (list)  [임시 게시판] = board2, board3            |\n"
            + "|[결제/예약 관리(정보)] = /pay/add (list)                                               |\n"
            + "|---------------------------------------------------------------------------------------|\n"
            + "|[게시글 상세보기(변경)(삭제)] = /board/detail (update)(delete) ...미완성               |\n"
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
          case "/board/detail":
            boardList.detail();
            break;
          case "/board/update":
            boardList.update();
            break;
          case "/board/delete":
            boardList.delete();
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
