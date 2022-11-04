package com.otus.components;

import com.google.inject.Inject;
import com.otus.annotations.Component;
import com.otus.diconfig.GuiceScoped;
import com.otus.menu.CourcesData;
import com.otus.menu.MenuItemData;
import com.otus.pages.CoursePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Component("//*[contains(@class, 'header2-menu_main')]")
public class HeaderMenuComponent extends AnyComponentAbs<HeaderMenuComponent> {

  private final String menuItemLocator = baseLocator
      + "//*[contains(@class, 'header2-menu__item_dropdown')][.//*[contains(@class, 'header2-menu__item')][text()='%s']]";
  private final String menuItemDropdownListLocator = menuItemLocator + "//*[@class='header2-menu__dropdown']";

  @Inject
  public HeaderMenuComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public HeaderMenuComponent moveToMenuItem(MenuItemData menuItemData) {
    WebElement menuItemElement = driver.findElement(By.xpath(String.format(menuItemLocator, menuItemData.getName())));
    actions.moveToElement(menuItemElement).build().perform();

    return this;
  }

  public HeaderMenuComponent checkSubMenuListVisible(MenuItemData menuItemData) {
    assert standardWaiter.waitForElementVisible(
        driver.findElement(By.xpath(String.format(menuItemDropdownListLocator, menuItemData.getName())))
    ) : "Sub menu not visible";

    return this;
  }

  public HeaderMenuComponent checkSubMenuListNotVisible(MenuItemData menuItemData) {
    assert standardWaiter.waitForElementNotVisible(
        driver.findElement(By.xpath(String.format(menuItemDropdownListLocator, menuItemData.getName())))
    ) : "Sub menu visible";

    return this;
  }

  public CoursePage clickCourseItem(CourcesData courcesData) {
    String baseCourseTypeLocator = menuItemDropdownListLocator
        + "/*[contains(@class, 'header2-menu__subdropdown-wrapper')][.//a[@title='%s']]";
    WebElement baseCourseElement = driver.findElement(By.xpath(String.format(baseCourseTypeLocator,
        MenuItemData.Courses.getName(), courcesData.getCourseTypeData().getName())));

    actions.moveToElement(baseCourseElement).build().perform();
    assert standardWaiter.waitForCondition(ExpectedConditions
        .attributeContains(baseCourseElement, "class", "header2-menu__dropdown-wrapper_open")) : "";

    baseCourseElement.findElement(By.xpath(String.format(".//a[@title='%s']", courcesData.getName()))).click();

    return new CoursePage((GuiceScoped) driver);
  }

}
