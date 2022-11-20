package com.otus.steps;

import com.google.inject.Inject;
import com.otus.components.OnlineOtusCourses;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import pages.MainPage;
import pages.OnlineCoursesPage;

public class OnlineCoursesPageSteps {

  @Inject
  private MainPage mainPage;
  @Inject
  private OnlineOtusCourses onlineOtusCourses;
  @Inject
  private OnlineCoursesPage onlineCoursesPage;

  @Пусть("Я открываю страницу Подготовительные курсы")
  public void openMainPage() {
    onlineCoursesPage.open();
  }

  @Тогда("Выбрать самый дешевый курс")
  public void findTheCheapestCourse() {
    onlineOtusCourses
        .filterTheCheapestCourse();
  }

  @Тогда("Выбрать самый дорогой курс")
  public void findTheMostExpensiveCourse() {
    onlineOtusCourses
        .filterTheMostExpensiveCourse();
  }

}
