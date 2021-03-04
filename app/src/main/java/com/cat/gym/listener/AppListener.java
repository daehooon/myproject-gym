package com.cat.gym.listener;

import java.util.Map;
import com.cat.context.ApplicationContextListener;

public class AppListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String,Object> context) {
    System.out.println("Cat Gym 관리 시스템 실행!");
    System.out.println();
  }

  @Override
  public void contextDestroyed(Map<String,Object> context) {
    System.out.println("Cat Gym 관리 시스템 종료!");
    System.out.println();
  }
}
