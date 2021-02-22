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

}
