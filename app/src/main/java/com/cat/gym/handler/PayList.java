package com.cat.gym.handler;

import com.cat.gym.domain.Pay;

public class PayList {
  
  Node first;
  Node last;
  int size = 0;
  
  void add(Pay p) {
    Node node = new Node(p);

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
  
  Pay[] toArray() {
    Pay[] arr = new Pay[size];

    Node cursor = this.first;
    int i = 0;

    while (cursor != null) {
      arr[i++] = cursor.pay;
      cursor = cursor.next;
    }
    return arr;
  }
  
  Pay get(String payId) {
    Node cursor = first;
    while (cursor != null) {
      Pay p = cursor.pay;
      if (p.id == payId) {
        return p;
      }
      cursor = cursor.next;
    }
    return null;
  }
  
  void delete(String payId) {
    Node cursor = first;
    while (cursor != null) {
      if (cursor.pay.id.equals(payId)) {
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
    Pay pay;
    Node next;
    Node prev;

    Node(Pay p) {
      this.pay = p;
    }
  }
}
