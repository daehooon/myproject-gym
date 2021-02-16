package com.cat.util;

public class Stack<E> extends List<E> implements Cloneable {

  public E push(E item) {
    this.add(item);
    return item;
  }

  public E pop() {
    return this.delete(this.size - 1);
  }

  @Override
  public Stack<E> clone() throws CloneNotSupportedException {
    Stack<E> stack = new Stack<>();

    for (int i = 0; i < this.size; i++) {
      stack.push(this.get(i));
    }
    return stack;
  }

  @Override
  public Iterator<E> iterator() throws CloneNotSupportedException {
    Stack<E> stack = this.clone();

    return new Iterator<E>() {
      @Override
      public boolean hasNext() {
        return stack.size() > 0;
      }

      @Override
      public E next() {
        return stack.pop();
      }
    };
  }
}
