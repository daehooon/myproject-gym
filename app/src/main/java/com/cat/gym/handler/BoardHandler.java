package com.cat.gym.handler;

import java.sql.Date;
import com.cat.gym.domain.Board;
import com.cat.gym.domain.Trainer;
import com.cat.util.Prompt;

public class BoardHandler {

  static final int LENGTH = 100;

  MemberHandler memberList;

  Board[] boards = new Board[LENGTH];
  int size = 0;

  public BoardHandler(MemberHandler memberHandler) {
    this.memberList = memberHandler;
  }

  public void add() {
    System.out.println("[게시글 등록]");
    System.out.println();

    Board b = new Board();

    b.no = Prompt.inputInt("글 번호: ");
    b.title = Prompt.inputString("제목: ");
    while (true) {
      String id = Prompt.inputString("아이디(취소: 빈 문자열): ");
      if (id.length() == 0) {
        System.out.println("게시글 등록을 취소합니다.");
        System.out.println();
        return;
      } 
      if (this.memberList.exist(id)) {
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
    this.boards[this.size++] = b;
    System.out.println();
  }

  public void list() {
    System.out.println("[게시글 목록]");
    System.out.println();
    for (int i = 0; i < this.size; i++) {
      Board b = this.boards[i];
      System.out.printf("%d %s %s %s %d\n",
          b.no, b.title, b.id,
          b.viewCount, b.like);
      System.out.println();
    }
  }

  public void detail() {
    System.out.println("[게시글 정보]");
    System.out.println();
    
    int no = Prompt.inputInt("글 번호: ");
    
    Board board = findByNo(no);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    
    board.viewCount++;
    System.out.printf("제목: %s\n", board.title);
    System.out.printf("내용: %s\n", board.content);
    System.out.printf("작성자: %s\n", board.id);
    System.out.printf("작성일: %s\n", board.registeredDate);
    System.out.printf("조회수: %s\n", board.viewCount);
    System.out.printf("Like: %s\n", board.like);
    System.out.println();
  }

  public void update() {
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
    
    String bag = Prompt.inputString(String.format("전문 분야(%s): ", trainer.bag));
    String photo = Prompt.inputString(String.format("사진(%s): ", trainer.photo));
    String name = Prompt.inputString(String.format("이름(%s): ", trainer.name));
    String phoneNumber = Prompt.inputString(String.format("전화번호(%s): ", trainer.phoneNumber));
    Date contractE = Prompt.inputDate(String.format("계약 종료일(%s): ", trainer.contractE));
    String members = Prompt.inputString(String.format("PT회원 ID목록[%s]: ", trainer.members));
    
    System.out.println();
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    System.out.println();
    
    if (input.equalsIgnoreCase("Y")) {
      trainer.bag = bag;
      trainer.photo = photo;
      trainer.name = name;
      trainer.phoneNumber = phoneNumber;
      trainer.contractE = contractE;
      trainer.members = members;
      System.out.println("정보를 변경하였습니다.");
      System.out.println();
      
    } else {
      System.out.println("정보 변경을 취소하였습니다.");
      System.out.println();
    }
  }

  public void delete() {

  }
  
  int indexOf(int boardNo) {
    for (int i = 0; i < this.size; i++) {
      Board board = this.boards[i];
      if (board.no == boardNo) {
        return i;
      }
    }
    return -1;
  }
  
  Board findByNo(int boardNo) {
    int i = indexOf(boardNo);
    if (i == -1)
      return null;
    else
      return this.boards[i];
  }
  
}
