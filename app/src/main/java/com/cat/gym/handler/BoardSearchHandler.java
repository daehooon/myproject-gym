package com.cat.gym.handler;

import java.util.ArrayList;
import java.util.List;
import com.cat.gym.domain.Board;
import com.cat.util.Prompt;

public class BoardSearchHandler extends AbstractBoardHandler {

  public BoardSearchHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service() {
    System.out.println("[게시글 검색]");
    System.out.println();

    String keyword = Prompt.inputString("검색어: ");

    if (keyword.length() == 0) {
      System.out.println();
      System.out.println("다시 입력하세요.");
      System.out.println();
      return;
    }

    ArrayList<Board> list = new ArrayList<>();

    Board[] boards = boardList.toArray(new Board[boardList.size()]);
    for (Board b : boards) {
      if (b.getTitle().contains(keyword) ||
          b.getId().contains(keyword) ||
          b.getContent().contains(keyword)) {
        list.add(b);
        System.out.println();
      }
    }

    if (list.isEmpty()) {
      System.out.println();
      System.out.println("해당 검색어의 게시글이 없습니다.");
      System.out.println();
      return;
    }

    for (Board b : list) {
      b.setViewCount(b.getViewCount() + 1);
      System.out.printf("글 번호: %d\n", b.getNo());
      System.out.printf("제목: %s\n", b.getTitle());
      System.out.printf("내용: %s\n", b.getContent());
      System.out.printf("작성자: %s\n", b.getId());
      System.out.printf("작성일: %s\n", b.getRegisteredDate());
      System.out.printf("조회수: %s\n", b.getViewCount());
      System.out.printf("Like: %s\n", b.getLike());
      System.out.println();
    }
  }

}
