package com.cat.gym.handler;

import com.cat.gym.domain.Trainer;

public class TrainerList {

  private Node first;
  private Node last;
  private int size = 0;

  public void add(Trainer t) {
    Node node = new Node(t);

    if (last == null) {
      last = node;
      first = node;
    } else { 
      last.next = node;
      node.prev = last; 
      last = node;
    }

    size++;
  }

  public Trainer[] toArray() {
    Trainer[] arr = new Trainer[size];

    Node cursor = this.first;
    int i = 0;

    while (cursor != null) {
      arr[i++] = cursor.trainer;
      cursor = cursor.next;
    }
    return arr;
  }

  public Trainer get(int trainerNo) {
    Node cursor = first;
    while (cursor != null) {
      Trainer t = cursor.trainer;
      if (t.getNo() == trainerNo) {
        return t;
      }
      cursor = cursor.next;
    }
    return null;
  }

  public void delete(int trainerNo) {
    Node cursor = first;
    while (cursor != null) {
      if (cursor.trainer.getNo() == trainerNo) {
        this.size--;
        if (first == last) {
          first = last = null;
          break;
        }
        if (cursor == first) {
          first = cursor.next;
          cursor.prev = null;
        } else {
          cursor.prev.next = cursor.next;
          if (cursor.next != null) {
            cursor.next.prev = cursor.prev;
          }
        }
        if (cursor == last) {
          last = cursor.prev;
        }

        break;
      }
      cursor = cursor.next;
    }
  }

  static class Node {
    Trainer trainer;
    Node next;
    Node prev;

    Node(Trainer t) {
      this.trainer = t;
    }
  }
}
