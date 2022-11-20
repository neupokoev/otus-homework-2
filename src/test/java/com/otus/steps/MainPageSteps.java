package com.otus.steps;

import com.google.inject.Inject;
import support.GuiceScoped;
import com.otus.driver.DriverFactory;
import pages.CoursePage;
import pages.MainPage;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;

public class MainPageSteps {

  @Inject
  private DriverFactory driverFactory;
  @Inject
  private GuiceScoped guiceScoped;
  @Inject
  private MainPage mainPage;
  @Inject
  private CoursePage coursePage;

  @Пусть("Открыта главная страница в браузере {string}")
  public void openMainPage(String browserName) {
    guiceScoped.browserName = browserName;
    guiceScoped.driver = driverFactory.getDriver();
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
