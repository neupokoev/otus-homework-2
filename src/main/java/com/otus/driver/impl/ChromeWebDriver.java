package driver.impl;

import exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeWebDriver implements IDriver {

  @Override
  public WebDriver newDriver() {

    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--disable-dev-shm-usage");
    chromeOptions.addArguments("--no-sandbox");
    chromeOptions.addArguments("--no-first-run");

    if (getRemoteUrl() == null) {
      try {
        downloadLocalWebDriver(DriverManagerType.CHROME);
      } catch (DriverTypeNotSupported ex) {
        ex.printStackTrace();
      }
      return new ChromeDriver(chromeOptions);
    } else
      return new RemoteWebDriver(getRemoteUrl(), chromeOptions);
  }

}
