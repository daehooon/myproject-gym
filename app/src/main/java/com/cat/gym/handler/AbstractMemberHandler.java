package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Member;

public abstract class AbstractMemberHandler implements Command {

  protected List<Member> memberList;

  public AbstractMemberHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  protected Member findById(String memberId) {
    Member[] arr = memberList.toArray(new Member[memberList.size()]);
    for (Member m : arr) {
      if (m.getId().equals(memberId)) {
        return m;
      }
    }
    return null;
  }

  //  @Override
  //  public void service() throws CloneNotSupportedException {
  //    loop:
  //      while (true) {
  //        String command = Prompt.inputString(""
  //            + "=========================================================================\n"
  //            + "|                             < 회원 메뉴 >        /member/...          |\n"
  //            + "|-----------------------------------------------------------------------|\n"
  //            + "|[등록] = /add     [목록] = /list   [정보] = /detail  [변경] = /update  |\n"
  //            + "|-----------------------------------------------------------------------|\n"
  //            + "|[탈퇴] = /delete  [홈 화면] = home                                     |\n"
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
