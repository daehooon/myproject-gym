package com.cat.gym.listener;

import com.cat.context.ApplicationContextListener;

public class AppListener implements ApplicationContextListener {

  @Override
  public void contextInitialized() {
    System.out.println("Cat Gym 관리 시스템 실행!");
  }

  @Override
  public void contextDestroyed() {
    System.out.println("Cat Gym 관리 시스템 종료!");
  }
}
