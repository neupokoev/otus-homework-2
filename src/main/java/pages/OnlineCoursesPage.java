package pages;

import com.google.inject.Inject;
import com.otus.annotations.UrlPrefix;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.GuiceScoped;

@UrlPrefix("/online/")
public class OnlineCoursesPage extends AnyPageAbs<OnlineCoursesPage> {

  //ELEMENTS
  @FindBy(css = ".cookies__button")
  private WebElement cookiesButton;

  @Inject
  public OnlineCoursesPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  //METHODS
  public OnlineCoursesPage closeAnnoyedCookiesBanner() {
    standardWaiter.waitForElementVisible(cookiesButton);
    cookiesButton.click();
    return this;
  }

}
