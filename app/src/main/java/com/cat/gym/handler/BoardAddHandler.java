package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Board;
import com.cat.util.Prompt;

public class BoardAddHandler extends AbstractBoardHandler {

  private MemberValidatorHandler memberValidatorHandler;

  public BoardAddHandler(List<Board> boardList, MemberValidatorHandler memberValidatorHandler) {
    super(boardList);
    this.memberValidatorHandler = memberValidatorHandler;
  }

  @Override
  public void service() {
    System.out.println("[게시글 등록]");
    System.out.println();

    Board b = new Board();

    b.setId(memberValidatorHandler.inputMember("아이디(취소: 빈 문자열): "));
    if (b.getId() == null) {
      System.out.println();
      System.out.println("게시글 등록을 취소합니다.");
      System.out.println();
      return;
    }

    while(true) {
      b.setNo(Prompt.inputInt("글 번호: "));
      b.setTitle(Prompt.inputString("제목: "));
      b.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
      b.setContent(Prompt.inputString("내용: "));

      if (b.getTitle().equals("") ||
          b.getContent().equals("")) {
        System.out.println();
        System.out.println("모든 항목에 정보를 입력해 주세요.");
        System.out.println();
      } else {
        boardList.add(b);
        break;      
      }
    }
    System.out.println();
    System.out.println("게시글을 등록하였습니다.");
    System.out.println();
  }

}
