package pages;

import annotations.UrlPrefix;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@UrlPrefix("/")
public class MainPage extends AnyPageAbs<MainPage> {

  //ELEMENTS
  @FindBy(css = ".cookies__button")
  private WebElement cookiesButton;

  public MainPage(WebDriver driver) {
    super(driver);
  }

  //METHODS
  public MainPage closeAnnoyedCookiesBanner() {
    standardWaiter.waitForElementVisible(cookiesButton);
    cookiesButton.click();
    return this;
  }

}
