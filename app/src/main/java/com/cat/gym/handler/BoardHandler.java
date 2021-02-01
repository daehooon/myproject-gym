package com.cat.gym.handler;

import com.cat.gym.domain.Board;
import com.cat.util.Prompt;

public class BoardHandler {
  
  BoardList boardList = new BoardList();

  MemberList memberList;
  
  public BoardHandler(MemberList memberList) {
    this.memberList = memberList;
  }

  public void service() {
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

        switch (command.toLowerCase()) {
          case "/add":
            this.add();
            break;
          case "/list":
            this.list();
            break;
          case "/detail":
            this.detail();
            break;
          case "/update":
            this.update();
            break;
          case "/delete":
            this.delete();
            break;
          case "home":
            break loop;
          default:
            System.out.println("실행할 수 없는 명령어입니다.");
            System.out.println();
        }
      }
  }

  public void add() {
    System.out.println("[게시글 등록]");
    System.out.println();

    Board b = new Board();
    
    b.no = Prompt.inputInt("글 번호: ");
    b.title = Prompt.inputString("제목: ");
    
    b.id = inputMember("아이디(취소: 빈 문자열): ");
    if (b.id == null) {
      System.out.println("게시글 등록을 취소합니다.");
      System.out.println();
      return;
    }
    
    b.registeredDate = new java.sql.Date(System.currentTimeMillis());
    b.content = Prompt.inputString("내용: ");
    
    boardList.add(b);
    
    System.out.println();
    System.out.println("게시글을 등록하였습니다.");
    System.out.println();
  }

  public void list() {
    System.out.println("[게시글 목록]");
    System.out.println();
    
    Board[] boards = boardList.toArray();
    for (Board b : boards) {
      System.out.printf("%d %s %s %s %d\n",
          b.no, b.title, b.id,
          b.viewCount, b.like);
      System.out.println();
    }
  }

  public void detail() {
    System.out.println("[게시글 보기]");
    System.out.println();

    int no = Prompt.inputInt("글 번호: ");

    Board board = boardList.get(no);
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

    Board board = boardList.get(no);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      System.out.println();
      return;
    }

    String title = Prompt.inputString(String.format("제목(%s): ", board.title));
    String content = Prompt.inputString(String.format("내용(%s): ", board.content));


    System.out.println();
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    System.out.println();

    if (input.equalsIgnoreCase("Y")) {
      board.title = title;
      board.content = content;
      System.out.println("게시글을 수정하였습니다.");
      System.out.println();

    } else {
      System.out.println("게시글 수정을 취소하였습니다.");
      System.out.println();
    }
  }

  public void delete() {
    System.out.println("[게시글 삭제]");
    System.out.println();

    int no = Prompt.inputInt("글 번호: ");
    System.out.println();

    Board board = boardList.get(no);
    if (board == null) {
      System.out.println("해당 번호의 글이 없습니다.");
      System.out.println();
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    System.out.println();

    if (input.equalsIgnoreCase("Y")) {
      boardList.delete(no);
      System.out.println("게시글을 삭제하였습니다.");
      System.out.println();

    } else {
      System.out.println("게시글 삭제를 취소하였습니다.");
      System.out.println();
    }
  }
  
  String inputMember(String promptTitle) {
    while (true) {
      String id = Prompt.inputString(promptTitle);
      if (id.length() == 0) {
        return null;
      } else if (this.memberList.exist(id)) {
        return id;
      } else {
        System.out.println("등록된 회원이 아닙니다.");
        System.out.println();
      }
    }
  }
}
