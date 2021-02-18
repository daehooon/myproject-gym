package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Member;
import com.cat.util.Prompt;

public class MemberValidatorHandler extends AbstractMemberHandler {

  public MemberValidatorHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service() {}

  public String inputMember(String promptTitle) {
    while (true) {
      String id = Prompt.inputString(promptTitle);
      if (id.length() == 0) {
        return null;
      }
      if (findById(id) != null) {
        return id;
      }
      System.out.println("등록된 회원이 아닙니다.");
      System.out.println();
    }
  }

  public String inputMembers(String promptTitle) {
    String members = "";
    while (true) {
      String name = inputMember(promptTitle);
      if (name == null) {
        return members;
      } else {
        if (!members.isEmpty()) {
          members += ",";
        }
        members += name;
      }
    }
  }

}
