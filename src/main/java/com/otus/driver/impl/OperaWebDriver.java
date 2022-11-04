package driver.impl;

import exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class OperaWebDriver implements IDriver {

  @Override
  public WebDriver newDriver() {

    ChromeOptions operaOptions = new ChromeOptions();
    operaOptions.addArguments("allow-elevated-browser");
    operaOptions.addArguments("--no-sandbox");
    operaOptions.addArguments("--no-first-run");
    operaOptions.addArguments("--enable-extensions");
    operaOptions.addArguments("--homepage=about:blank");
    operaOptions.addArguments("--ignore-certificate-errors");
    operaOptions.addArguments("--disable-dev-shm-usage");

    if (getRemoteUrl() == null) {
      try {
        downloadLocalWebDriver(DriverManagerType.OPERA);
      } catch (DriverTypeNotSupported ex) {
        ex.printStackTrace();
      }
      return new ChromeDriver(operaOptions);
    } else
      return new RemoteWebDriver(getRemoteUrl(), operaOptions);
  }

}
