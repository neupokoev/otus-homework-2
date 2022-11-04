package com.otus.driver.impl;

import com.otus.exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.Config;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public interface IDriver {
  String REMOTE_URL = System.getProperty("webdriver.remote.url");
  boolean HEADLESS = Boolean.parseBoolean(System.getProperty("webdriver.headless"));

  WebDriver newDriver();

  default URL getRemoteUrl() {
    try {
      return new URL(REMOTE_URL);
    } catch (MalformedURLException e) {
      return null;
    }
  }

  default void downloadLocalWebDriver(DriverManagerType driverType) throws DriverTypeNotSupported {
    String browserVersion = System.getProperty("browser.version", "CHROME");
    Config webDriverManagerConfig;

    if (!browserVersion.isEmpty()) {
      switch (driverType) {
        case CHROME:
          webDriverManagerConfig = WebDriverManager.chromedriver().config();
          webDriverManagerConfig.setChromeDriverVersion(browserVersion);
          break;
        case FIREFOX:
          webDriverManagerConfig = WebDriverManager.firefoxdriver().config();
          webDriverManagerConfig.setFirefoxVersion(browserVersion);
          break;
        case OPERA:
          webDriverManagerConfig = WebDriverManager.operadriver().config();
          webDriverManagerConfig.setOperaDriverVersion(browserVersion);
          break;
        default:
          throw new DriverTypeNotSupported(driverType);
      }
    }

    WebDriverManager.getInstance(driverType).setup();
  }
}
