package com.cat.gym;

import com.cat.gym.handler.BoardHandler;
import com.cat.gym.handler.MemberHandler;
import com.cat.gym.handler.PayHandler;
import com.cat.gym.handler.TrainerHandler;
import com.cat.util.Iterator;
import com.cat.util.Prompt;
import com.cat.util.Queue;
import com.cat.util.Stack;

public class App {

  static Stack commandStack = new Stack();
  static Queue commandQueue = new Queue();

  public static void main(String[] args) throws CloneNotSupportedException {

    MemberHandler memberHandler = new MemberHandler();
    TrainerHandler trainerHandler = new TrainerHandler(memberHandler);
    PayHandler payHandler = new PayHandler(memberHandler);
    BoardHandler boardHandler = new BoardHandler(memberHandler);

    loop:
      while (true) {
        String command = Prompt.inputString(""
            + "=========================================================================\n"
            + "|                              ▶ Cat Gym ◀                              |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|[회원 메뉴] = /member   [결제/예약 메뉴] = /pay   [게시판] = /board    |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|[트레이너 메뉴] = /trainer [최근 입력 기록] = /history 또는 /history2  |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|[프로그램 종료] = exit                                                 |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "\n명령어> ");
        System.out.println();

        if (command.length() == 0)
          continue;

        commandStack.push(command);
        commandQueue.offer(command);

        switch (command.toLowerCase()) {
          case "/member":
            memberHandler.service();
            break;
          case "/pay":
            payHandler.service();
            break;
          case "/board":
            boardHandler.service();
            break;
          case "/trainer":
            trainerHandler.service();
            break;
          case "history":
            printCommandHistory(commandStack.iterator());
            break;
          case "history2":
            printCommandHistory(commandQueue.iterator());
            break;
          case "exit":
            System.out.println("득근하세요!! Ten Reps!!!");
            break loop;
          default:
            System.out.println("실행할 수 없는 명령어입니다.");
            System.out.println();
        }
      }

    Prompt.close();
  }

  static void printCommandHistory(Iterator iterator) {
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      System.out.println();
      if ((++count % 5) == 0) {
        String input = Prompt.inputString("이어보기(enter) / 나가기(q): ");
        System.out.println();
        if (input.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }
}
