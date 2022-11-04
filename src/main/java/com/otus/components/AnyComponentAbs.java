package components;

import actions.CommonActions;
import annotations.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class AnyComponentAbs<T> extends CommonActions<T> {

  protected String baseLocator;
  protected Actions actions;

  {
    this.standardWaiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(getComponentLocator()));
  }

  public AnyComponentAbs(WebDriver driver) {
    super(driver);
    actions = new Actions(driver);
  }

  private By getComponentLocator() {
    Component component = getClass().getAnnotation(Component.class);

    if (component != null) {
      String value = component.value();

      baseLocator = value;

      if (value.startsWith("/")) {
        return By.xpath(value);
      }
      return By.cssSelector(value);
    }

    return null;
  }

}
