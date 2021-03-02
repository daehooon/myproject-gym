package com.cat.util;

public interface ObjectFactory<T> {
  T create(String csvStr);
}
