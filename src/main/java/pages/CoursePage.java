package pages;

import annotations.Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Component("title")
public class CoursePage extends AnyPageAbs<CoursePage> {

  @FindBy(css = "title")
  WebElement pageTitle;

  public CoursePage(WebDriver driver) {
    super(driver);
  }

}
