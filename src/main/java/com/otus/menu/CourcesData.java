package com.otus.menu;

public enum CourcesData {
  Highload_Architect("Highload Architect", CourceTypeData.Programmer);

  private final String name;
  private final CourceTypeData courceTypeData;

  CourcesData(String name, CourceTypeData courceTypeData) {
    this.name = name;
    this.courceTypeData = courceTypeData;
  }

  public String getName() {
    return name;
  }

  public CourceTypeData getCourseTypeData() {
    return courceTypeData;
  }
}
