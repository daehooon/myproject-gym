package com.cat.gym.listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.cat.context.ApplicationContextListener;
import com.cat.gym.domain.Board;
import com.cat.gym.domain.Member;
import com.cat.gym.domain.Pay;
import com.cat.gym.domain.Trainer;
import com.cat.util.CsvObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FileListener implements ApplicationContextListener {

  File memberFile = new File("members.json");
  File payFile = new File("pays.json");
  File boardFile = new File("boards.json");
  File trainerFile = new File("trainers.json");

  List<Member> memberList;
  List<Pay> payList;
  List<Board> boardList;
  List<Trainer> trainerList;

  @Override
  public void contextInitialized(Map<String,Object> context) {

    memberList = loadObjects(memberFile, Member.class);
    payList = loadObjects(payFile, Pay.class);
    boardList = loadObjects(boardFile, Board.class);
    trainerList = loadObjects(trainerFile, Trainer.class);

    context.put("memberList", memberList);
    context.put("payList", payList);
    context.put("boardList", boardList);
    context.put("trainerList", trainerList);

  }

  @Override
  public void contextDestroyed(Map<String,Object> context) {

    saveObjects(memberFile, memberList);
    saveObjects(payFile, payList);
    saveObjects(boardFile, boardList);
    saveObjects(trainerFile, trainerList);

  }

  private <T> List<T> loadObjects(File file, Class<T> elementType) {

    try (BufferedReader in = new BufferedReader(new FileReader(file))) {

      StringBuilder strBuilder = new StringBuilder();
      String str = null;
      while((str = in.readLine()) != null) {
        strBuilder.append(str);
      }

      Type listType = TypeToken.getParameterized(ArrayList.class, elementType).getType();
      List<T> list = new Gson().fromJson(strBuilder.toString(), listType);

      System.out.printf("%s 로딩 완료\n", file.getName());

      return list;

    } catch (Exception e) {
      System.out.printf("%s 로딩중 오류 발생\n", file.getName());
      return new LinkedList<T>();
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
