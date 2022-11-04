package com.otus.steps;

import com.google.inject.Inject;
import com.otus.pages.CoursePage;
import com.otus.pages.MainPage;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;

public class MainPageSteps {

  @Inject
  private MainPage mainPage;

  @Inject
  private CoursePage coursePage;

  @Пусть("Открываем главную страницу")
  public void openMainPage() {
    mainPage.open();
  }

  @Если("Закрыт баннер с куки")
  public void closeAnnoyedCookiesBanner() {
    mainPage.closeAnnoyedCookiesBanner();
  }

  @Тогда("Посчитать количество курсов на странице")
  public void countOnMainPage() {
    //mainPage.
  }
}
