package com.otus.steps;

import com.google.inject.Inject;
import com.otus.components.OtusCourses;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import pages.MainPage;

public class MainPageSteps {

  @Inject
  private MainPage mainPage;
  @Inject
  private OtusCourses otusCourses;

  @Пусть("Я открываю страницу с курсами")
  public void openMainPage() {
    mainPage.open();
  }

  @Если("Закрыт баннер с куки")
  public void closeAnnoyedCookiesBanner() {
    mainPage.closeAnnoyedCookiesBanner();
  }

  @Тогда("Выбрать курс с названием {string}")
  public void chooseCourseNamedAs(String courseName) {
    otusCourses
        .filterCoursesNamedAs(courseName)
        .printFilteredCoursesTitleAndClick();
  }

  @Тогда("Выбрать курсы с датой начала позже или равной {string} года")
  public void filterCoursesFromDate(String startDate) {
    otusCourses
        .filterCourseFromDate(startDate);
  }

}
