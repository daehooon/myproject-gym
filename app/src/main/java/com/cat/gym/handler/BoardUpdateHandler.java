package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Board;
import com.cat.util.Prompt;

public class BoardUpdateHandler extends AbstractBoardHandler {

  public BoardUpdateHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service() {
    System.out.println("[게시글 수정]");
    System.out.println();

    int no = Prompt.inputInt("글 번호: ");
    System.out.println();

    Board board = findByNo(no);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      System.out.println();
      return;
    }

    String title = Prompt.inputString(String.format("제목(%s): ", board.getTitle()));
    String content = Prompt.inputString(String.format("내용(%s): ", board.getContent()));

    System.out.println();
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    System.out.println();

    if (input.equalsIgnoreCase("Y")) {
      board.setTitle(title);
      board.setContent(content);
      System.out.println("게시글을 수정하였습니다.");
      System.out.println();

    } else {
      System.out.println("게시글 수정을 취소하였습니다.");
      System.out.println();
    }
  }

}
