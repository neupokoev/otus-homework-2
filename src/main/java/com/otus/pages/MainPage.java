package com.otus.pages;

import com.google.inject.Inject;
import com.otus.annotations.UrlPrefix;
import com.otus.diconfig.GuiceScoped;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@UrlPrefix("/")
public class MainPage extends AnyPageAbs<MainPage> {

  //ELEMENTS
  @FindBy(css = ".cookies__button")
  private WebElement cookiesButton;

  @Inject
  public MainPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  //METHODS
  public MainPage closeAnnoyedCookiesBanner() {
    standardWaiter.waitForElementVisible(cookiesButton);
    cookiesButton.click();
    return this;
  }

}
