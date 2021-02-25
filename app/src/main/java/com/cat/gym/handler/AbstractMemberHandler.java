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

}
