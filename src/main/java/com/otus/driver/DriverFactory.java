package com.otus.driver;

import com.otus.driver.impl.FirefoxWebDriver;
import com.otus.driver.impl.OperaWebDriver;
import com.otus.exceptions.DriverTypeNotSupported;
import com.otus.driver.impl.ChromeWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Locale;

public class DriverFactory implements IDriverFactory {

  private final String browserType = System.getProperty("browser", "chrome").toLowerCase(Locale.ROOT);

  @Override
  public EventFiringWebDriver getDriver() {
    switch (this.browserType) {

      case "chrome":
        return new EventFiringWebDriver(new ChromeWebDriver().newDriver());

      case "firefox":
        return new EventFiringWebDriver(new FirefoxWebDriver().newDriver());

      case "opera":
        return new EventFiringWebDriver(new OperaWebDriver().newDriver());

      default:
        try {
          throw new DriverTypeNotSupported(this.browserType);
        } catch (DriverTypeNotSupported ex) {
          ex.printStackTrace();
          return null;
        }
    }
  }

}
