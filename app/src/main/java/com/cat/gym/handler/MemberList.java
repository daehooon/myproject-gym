package com.cat.gym.handler;

import com.cat.gym.domain.Member;

public class MemberList {
  
  Node first;
  Node last;
  int size = 0;
  
  void add(Member m) {
    Node node = new Node(m);

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
  
  Member[] toArray() {
    Member[] arr = new Member[size];

    Node cursor = this.first;
    int i = 0;

    while (cursor != null) {
      arr[i++] = cursor.member;
      cursor = cursor.next;
    }
    return arr;
  }
  
  Member get(String memberId) {
    Node cursor = first;
    while (cursor != null) {
      Member m = cursor.member;
      if (m.id.equals(memberId)) {
        return m;
      }
      cursor = cursor.next;
    }
    return null;
  }
  
  void delete(String memberId) {
    Node cursor = first;
    while (cursor != null) {
      if (cursor.member.id.equals(memberId)) {
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
  
  public boolean exist(String id) {
    Node cursor = first;
    while (cursor != null) {
      Member m = cursor.member;
      if (m.id.equals(id)) {
        return true;
      }
      cursor = cursor.next;
    }
    return false;
  }
  
  static class Node {
    Member member;
    Node next;
    Node prev;

    Node(Member m) {
      this.member = m;
    }
  }
}
