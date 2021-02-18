package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Trainer;

public abstract class AbstractTrainerHandler implements Command {

  protected List<Trainer> trainerList;

  public AbstractTrainerHandler(List<Trainer> trainerList) {
    this.trainerList = trainerList;
  }

  protected Trainer findByNo(int trainerNo) {
    Trainer[] arr = trainerList.toArray(new Trainer[trainerList.size()]);
    for (Trainer t : arr) {
      if (t.getNo() == trainerNo) {
        return t;
      }
    }
    return null;
  }

  //  public void service() throws CloneNotSupportedException {
  //    loop:
  //      while (true) {
  //        String command = Prompt.inputString(""
  //            + "=========================================================================\n"
  //            + "|                            < 트레이너 메뉴 >        /trainer/...      |\n"
  //            + "|-----------------------------------------------------------------------|\n"
  //            + "|[등록] = /add     [목록] = /list   [정보] = /detail  [변경] = /update  |\n"
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
