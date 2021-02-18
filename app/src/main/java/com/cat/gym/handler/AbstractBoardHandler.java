package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Board;

public abstract class AbstractBoardHandler implements Command {

  protected List<Board> boardList;

  public AbstractBoardHandler(List<Board> boardList) {
    this.boardList = boardList;
  }

  protected Board findByNo(int boardNo) {
    Board[] arr = boardList.toArray(new Board[boardList.size()]);
    for (Board b : arr) {
      if (b.getNo() == boardNo) {
        return b;
      }
    }
    return null;
  }

  //  public void service() throws CloneNotSupportedException {
  //    loop:
  //      while (true) {
  //        String command = Prompt.inputString(""
  //            + "=========================================================================\n"
  //            + "|                              < 게시판 >              /board/...       |\n"
  //            + "|-----------------------------------------------------------------------|\n"
  //            + "|[등록] = /add     [목록] = /list   [보기] = /detail  [수정] = /update  |\n"
  //            + "|-----------------------------------------------------------------------|\n"
  //            + "|[삭제] = /delete  [홈 화면] = home                                     |\n"
  //            + "|-----------------------------------------------------------------------|\n"
  //            + "\n명령어> ");
  //        System.out.println();
  //
  //        try {
  //          switch (command.toLowerCase()) {
  //            case "/add":
  //              this.add();
  //              break;
  //            case "/list":
  //              this.list();
  //              break;
  //            case "/detail":
  //              this.detail();
  //              break;
  //            case "/update":
  //              this.update();
  //              break;
  //            case "/delete":
  //              this.delete();
  //              break;
  //            case "home":
  //              break loop;
  //            default:
  //              System.out.println("실행할 수 없는 명령어입니다.");
  //              System.out.println();
  //          }
  //        } catch (Exception e) {
  //          System.out.println("---------------------------------------------------------");
  //          System.out.printf("명령어 실행중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
  //          System.out.println("---------------------------------------------------------");
  //        }
  //      }
  //  }

}
