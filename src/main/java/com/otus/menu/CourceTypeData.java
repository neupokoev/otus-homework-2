package com.otus.menu;

public enum CourceTypeData {
  Programmer("Программирование");

  private final String name;

  CourceTypeData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
