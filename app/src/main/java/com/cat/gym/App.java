package com.cat.gym;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import com.cat.gym.domain.Board;
import com.cat.gym.domain.Member;
import com.cat.gym.domain.Pay;
import com.cat.gym.domain.Trainer;
import com.cat.gym.handler.BoardMenu;
import com.cat.gym.handler.MemberMenu;
import com.cat.gym.handler.PayMenu;
import com.cat.gym.handler.TrainerMenu;
import com.cat.util.Prompt;

public class App {

  static ArrayDeque<String> commandStack = new ArrayDeque<>();
  static LinkedList<String> commandQueue = new LinkedList<>();

  public static void main(String[] args) throws CloneNotSupportedException {

    LinkedList<Member> memberList = new LinkedList<>();
    LinkedList<Pay> payList = new LinkedList<>();
    LinkedList<Board> boardList = new LinkedList<>();
    LinkedList<Trainer> trainerList = new LinkedList<>();

    loop:
      while (true) {
        String command = Prompt.inputString(""
            + "=========================================================================\n"
            + "|                              ▶ Cat Gym ◀                              |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|[회원 메뉴] = /member   [결제/예약 메뉴] = /pay   [게시판] = /board    |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|[트레이너 메뉴] = /trainer [최근 입력 기록] = history 또는 history2    |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|[프로그램 종료] = exit                                                 |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "\n명령어> ");
        System.out.println();

        if (command.length() == 0)
          continue;

        commandStack.push(command);
        commandQueue.offer(command);

        try {
          switch (command.toLowerCase()) {
            case "/member":
              MemberMenu.main(args, memberList);
              break;
            case "/pay":
              PayMenu.main(args, memberList, payList);
              break;
            case "/board":
              BoardMenu.main(args, memberList, boardList);
              break;
            case "/trainer":
              TrainerMenu.main(args, memberList, trainerList);
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
        } catch (Exception e) {
          System.out.println("---------------------------------------------------------");
          System.out.printf("명령어 실행중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
          System.out.println("---------------------------------------------------------");
        }
      }
    Prompt.close();
  }

  static void printCommandHistory(Iterator<String> iterator) {
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
