package driver.impl;

import exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirefoxWebDriver implements IDriver {

  @Override
  public WebDriver newDriver() {

    FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.addArguments("--no-sandbox");
    firefoxOptions.addArguments("--no-first-run");
    firefoxOptions.addArguments("--enable-extensions");
    firefoxOptions.addArguments("--homepage=about:blank");
    firefoxOptions.addArguments("--ignore-certificate-errors");
    firefoxOptions.getProfile().setPreference("webdriver.log.driver", "OFF");
    System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

    if (getRemoteUrl() == null) {
      try {
        downloadLocalWebDriver(DriverManagerType.FIREFOX);
      } catch (DriverTypeNotSupported ex) {
        ex.printStackTrace();
      }
      return new FirefoxDriver(firefoxOptions);
    } else
      return new RemoteWebDriver(getRemoteUrl(), firefoxOptions);
  }

}
