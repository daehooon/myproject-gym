package com.cat.gym.handler;

import java.util.HashMap;
import java.util.LinkedList;
import com.cat.gym.domain.Board;
import com.cat.gym.domain.Member;
import com.cat.util.Prompt;

public class BoardMenu {

  public static void main(String[] args, LinkedList<Member> memberList) {

    LinkedList<Board> boardList = new LinkedList<>();
    HashMap<String,Command> commandMap = new HashMap<>();
    MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberList);

    commandMap.put("/add", new BoardAddHandler(boardList, memberValidatorHandler));
    commandMap.put("/list", new BoardListHandler(boardList));
    commandMap.put("/detail", new BoardDetailHandler(boardList));
    commandMap.put("/update", new BoardUpdateHandler(boardList));
    commandMap.put("/delete", new BoardDeleteHandler(boardList));
    commandMap.put("/search", new BoardSearchHandler(boardList));

    loop:
      while (true) {
        String command = Prompt.inputString(""
            + "=========================================================================\n"
            + "|                              < 게시판 >              /board/...       |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|[등록] = /add     [목록] = /list   [보기] = /detail  [수정] = /update  |\n"
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




