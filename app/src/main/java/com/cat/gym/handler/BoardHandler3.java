package com.cat.gym.handler;

import com.cat.gym.domain.Board;
import com.cat.util.Prompt;

public class BoardHandler3 {

  static final int LENGTH = 100;
  static Board[] boards = new Board[LENGTH];
  static int size = 0;

  public static void add() {
    System.out.println("[게시글 등록]");
    System.out.println();

    Board b = new Board();

    b.number = Prompt.inputInt("글 번호: ");
    b.title = Prompt.inputString("제목: ");
    while (true) {
      String id = Prompt.inputString("아이디(취소: 빈 문자열): ");
      if (id.length() == 0) {
        System.out.println("결제/예약을 취소합니다.");
        System.out.println();
        return;
      } 
      if (MemberHandler.exist(id)) {
        b.id = id;
        break;
      }
      System.out.println("등록된 회원이 아닙니다.");
      System.out.println();
    }
    b.registeredDate = new java.sql.Date(System.currentTimeMillis());
    b.content = Prompt.inputString("내용: ");
    System.out.println();
    System.out.println("게시글을 등록하였습니다.");
    boards[size++] = b;
    System.out.println();
  }

  public static void list() {
    System.out.println("[게시글 목록]");
    System.out.println();
    for (int i = 0; i < size; i++) {
      Board b = boards[i];
      System.out.printf("글 번호: %s\n"
          + "제목: %s\n"
          + "작성자: %s\n"
          + "작성일: %s\n"
          + "조회수: %s\n"
          + "내용: %s\n"
          + "Like: %d\n",
          b.number, b.title, b.id, b.registeredDate,
          b.viewCount, b.content, b.like);
      System.out.println();
    }
  }
}
