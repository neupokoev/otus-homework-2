package com.otus.driver;

import com.google.inject.Inject;
import com.otus.driver.impl.ChromeWebDriver;
import com.otus.driver.impl.FirefoxWebDriver;
import com.otus.driver.impl.OperaWebDriver;
import com.otus.exceptions.DriverTypeNotSupported;
import org.openqa.selenium.WebDriver;
import support.GuiceScoped;

import java.util.Locale;

public class DriverFactory implements IDriverFactory {

  public GuiceScoped guiceScoped;

  @Inject
  public DriverFactory(GuiceScoped guiceScoped) {
    this.guiceScoped = guiceScoped;
  }

  @Override
  public WebDriver getDriver() {
    switch (guiceScoped.browserName.toLowerCase(Locale.ROOT)) {

      case "chrome":
        return new ChromeWebDriver().newDriver();

      case "firefox":
        return new FirefoxWebDriver().newDriver();

      case "opera":
        return new OperaWebDriver().newDriver();

      default:
        try {
          throw new DriverTypeNotSupported(guiceScoped.browserName);
        } catch (DriverTypeNotSupported ex) {
          ex.printStackTrace();
          return null;
        }
    }
  }

}
