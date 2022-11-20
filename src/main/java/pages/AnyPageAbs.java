package pages;

import com.google.inject.Inject;
import com.otus.actions.CommonActions;
import com.otus.annotations.UrlPrefix;
import support.GuiceScoped;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class AnyPageAbs<T> extends CommonActions<T> {

  @Inject
  private GuiceScoped guiceScoped;

  @Inject
  public AnyPageAbs(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  private static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
    try {
      return clazz.cast(o);
    } catch (ClassCastException e) {
      return null;
    }
  }

  /*private String getBaseUrl() {
    return StringUtils.stripEnd(System.getProperty("webdriver.base.url"), "/");
  }

  private String getUrlPrefix() {
    UrlPrefix urlAnnotation = getClass().getAnnotation(UrlPrefix.class);
    if (urlAnnotation != null) {
      return urlAnnotation.value();
    }

    return "";
  }*/

  public T open() {
    //guiceScoped.driver.get(getBaseUrl() + getUrlPrefix());
    guiceScoped.driver.get(System.getProperty("webdriver.base.url","http://otus.ru"));

    return (T) this;
  }

  public <T> T page(Class<T> clazz) {
    try {
      Constructor constructor = clazz.getConstructor(WebDriver.class);

      return convertInstanceOfObject(constructor.newInstance(driver), clazz);

    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
      e.printStackTrace();
    }

    return null;
  }

  public String getPageTitle() {
    return driver.getTitle();
  }

  public T printPageTitle() {
    System.out.println(driver.getTitle());
    return (T) this;
  }
}
