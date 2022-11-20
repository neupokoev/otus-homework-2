package pages;

import com.google.inject.Inject;
import com.otus.annotations.Component;
import support.GuiceScoped;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Component("title")
public class CoursePage extends AnyPageAbs<CoursePage> {

  @FindBy(css = "title")
  WebElement pageTitle;

  @Inject
  public CoursePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

}
