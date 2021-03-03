package com.cat.gym;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.cat.context.ApplicationContextListener;
import com.cat.gym.domain.Board;
import com.cat.gym.domain.Member;
import com.cat.gym.domain.Pay;
import com.cat.gym.domain.Trainer;
import com.cat.gym.handler.BoardAddHandler;
import com.cat.gym.handler.BoardDeleteHandler;
import com.cat.gym.handler.BoardDetailHandler;
import com.cat.gym.handler.BoardListHandler;
import com.cat.gym.handler.BoardSearchHandler;
import com.cat.gym.handler.BoardUpdateHandler;
import com.cat.gym.handler.Command;
import com.cat.gym.handler.MemberAddHandler;
import com.cat.gym.handler.MemberDeleteHandler;
import com.cat.gym.handler.MemberDetailHandler;
import com.cat.gym.handler.MemberListHandler;
import com.cat.gym.handler.MemberUpdateHandler;
import com.cat.gym.handler.MemberValidatorHandler;
import com.cat.gym.handler.PayAddHandler;
import com.cat.gym.handler.PayDeleteHandler;
import com.cat.gym.handler.PayDetailHandler;
import com.cat.gym.handler.PayListHandler;
import com.cat.gym.handler.PayUpdateHandler;
import com.cat.gym.handler.TrainerAddHandler;
import com.cat.gym.handler.TrainerDeleteHandler;
import com.cat.gym.handler.TrainerDetailHandler;
import com.cat.gym.handler.TrainerListHandler;
import com.cat.gym.handler.TrainerUpdateHandler;
import com.cat.gym.listener.AppListener;
import com.cat.util.CsvObject;
import com.cat.util.Prompt;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class App {

  List<ApplicationContextListener> listeners = new ArrayList<>();

  ArrayDeque<String> commandStack = new ArrayDeque<>();
  LinkedList<String> commandQueue = new LinkedList<>();

  LinkedList<Member> memberList = new LinkedList<>();
  LinkedList<Pay> payList = new LinkedList<>();
  LinkedList<Board> boardList = new LinkedList<>();
  LinkedList<Trainer> trainerList = new LinkedList<>();

  File memberFile = new File("members.json");
  File payFile = new File("pays.json");
  File boardFile = new File("boards.json");
  File trainerFile = new File("trainers.json");

  public static void main(String[] args) {
    App app = new App();

    app.addApplicationContextListener(new AppListener());

    app.service();
  }

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  public void service() {

    notifyOnServiceStarted();

    loadObjects(memberFile, memberList, Member.class);
    loadObjects(payFile, payList, Pay.class);
    loadObjects(boardFile, boardList, Board.class);
    loadObjects(trainerFile, trainerList, Trainer.class);

    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("/member/add", new MemberAddHandler(memberList));
    commandMap.put("/member/list", new MemberListHandler(memberList));
    commandMap.put("/member/detail", new MemberDetailHandler(memberList));
    commandMap.put("/member/update", new MemberUpdateHandler(memberList));
    commandMap.put("/member/delete", new MemberDeleteHandler(memberList));
    MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberList);

    commandMap.put("/pay/add", new PayAddHandler(payList, memberValidatorHandler));
    commandMap.put("/pay/list", new PayListHandler(payList));
    commandMap.put("/pay/detail", new PayDetailHandler(payList));
    commandMap.put("/pay/update", new PayUpdateHandler(payList));
    commandMap.put("/pay/delete", new PayDeleteHandler(payList));

    commandMap.put("/board/add", new BoardAddHandler(boardList, memberValidatorHandler));
    commandMap.put("/board/list", new BoardListHandler(boardList));
    commandMap.put("/board/detail", new BoardDetailHandler(boardList));
    commandMap.put("/board/update", new BoardUpdateHandler(boardList));
    commandMap.put("/board/delete", new BoardDeleteHandler(boardList));
    commandMap.put("/board/search", new BoardSearchHandler(boardList));

    commandMap.put("/trainer/add", new TrainerAddHandler(trainerList, memberValidatorHandler));
    commandMap.put("/trainer/list", new TrainerListHandler(trainerList));
    commandMap.put("/trainer/detail", new TrainerDetailHandler(trainerList));
    commandMap.put("/trainer/update", new TrainerUpdateHandler(trainerList, memberValidatorHandler));
    commandMap.put("/trainer/delete", new TrainerDeleteHandler(trainerList));

    loop:
      while (true) {
        String command = Prompt.inputString(""
            + "=========================================================================\n"
            + "|                              ▶ Cat Gym ◀                              |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|  [회원] = /member       [결제/예약] = /pay     [게시판] = /board      |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|  [트레이너] = /trainer  [최근 입력 기록] = history(or 2)              |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|  [세부 메뉴] .../add list detail update delete  EX) /member/add       |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|  [프로그램 종료] = EXIT                                               |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "\n명령어> ");
        System.out.println();

        if (command.length() == 0)
          continue;

        commandStack.push(command);
        commandQueue.offer(command);

        try {
          switch (command.toLowerCase()) {
            case "history":
              printCommandHistory(commandStack.iterator());
              break;
            case "history2":
              printCommandHistory(commandQueue.iterator());
              break;
            case "exit":
              System.out.println("득근하세요!! Ten Reps!!!");
              System.out.println();
              break loop;
            default:
              Command commandHandler = commandMap.get(command);

              if (commandHandler == null) {
                System.out.println("실행할 수 없는 명령어입니다.");
                System.out.println();
              } else {
                commandHandler.service();
              }
          }
        } catch (Exception e) {
          System.out.println("---------------------------------------------------------");
          System.out.printf("명령어 실행중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
          System.out.println("---------------------------------------------------------");
        }
      }
    saveObjects(memberFile, memberList);
    saveObjects(payFile, payList);
    saveObjects(boardFile, boardList);
    saveObjects(trainerFile, trainerList);

    Prompt.close();

    notifyOnServiceStopped();
  }

  private void printCommandHistory(Iterator<String> iterator) {
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

  private <T> void loadObjects(File file, List<T> list, Class<T> elementType) {
    try (BufferedReader in = new BufferedReader(new FileReader(file))) {
      StringBuilder strBuilder = new StringBuilder();
      String str = null;
      while((str = in.readLine()) != null) {
        strBuilder.append(str);
      }

      Gson gson = new Gson();

      Type collectionType = TypeToken.getParameterized(Collection.class, elementType).getType();
      Collection<T> collection = gson.fromJson(strBuilder.toString(), collectionType);

      list.addAll(collection);

      System.out.printf("%s 로딩 완료\n", file.getName());

    } catch (Exception e) {
      System.out.printf("%s 로딩중 오류 발생\n", file.getName());
    }
  }

  private <T extends CsvObject> void saveObjects(File file, List<T> list) {
    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
      out.write(new Gson().toJson(list));
      System.out.printf("%s 저장 완료\n", file.getName());

    } catch (Exception e) {
      System.out.printf("%s 저장중 오류 발생\n", file.getName());
    }
  }
}
