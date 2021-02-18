package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Pay;

public abstract class AbstractPayHandler implements Command {

  protected List<Pay> payList;

  public AbstractPayHandler(List<Pay> payList) {
    this.payList = payList;
  }

  protected String getSelectLabel(int select) {
    switch (select) {
      case 1:
        return "3개월(90,000원)";
      case 2:
        return "6개월(150,000원)";
      case 3:
        return "1년(240,000원)";
      default:
        return "1개월(80,000원)";
    }
  }

  protected Pay findById(String memberId) {
    Pay[] arr = payList.toArray(new Pay[payList.size()]);
    for (Pay p : arr) {
      if (p.getId().equals(memberId)) {
        return p;
      }
    }
    return null;
  }

  //  public void service() throws CloneNotSupportedException {
  //    loop:
  //      while (true) {
  //        String command = Prompt.inputString(""
  //            + "=========================================================================\n"
  //            + "|                           < 결제/예약 메뉴 >          /pay/...        |\n"
  //            + "|-----------------------------------------------------------------------|\n"
  //            + "|[신청] = /add     [목록] = /list   [정보] = /detail  [변경] = /update  |\n"
  //            + "|-----------------------------------------------------------------------|\n"
  //            + "|[취소] = /delete  [홈 화면] = home                                     |\n"
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
