package com.otus.components;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Inject;
import com.otus.annotations.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CoursePage;
import support.GuiceScoped;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Component(".lessons a")
public class OtusCourses extends AnyComponentAbs<OtusCourses> {

  @FindBy(css = ".lessons a")
  private List<WebElement> courses;

  @FindBy(xpath = "(//div[@class='lessons'])[1]/a")
  private List<WebElement> favoriteCourses;

  @FindBy(xpath = "(//div[@class='lessons'])[2]/a")
  private List<WebElement> regularCourses;

  @FindBy(css = ".lessons a .lessons__new-item-title")
  private List<WebElement> coursesTitles;

  private long coursesAmount;
  private List<WebElement> namedCourses;

  @Inject
  public OtusCourses(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public CoursePage clickFirstCourseItem() {
    scrollToElement(courses.get(0));
    courses.get(0).click();

    return new CoursePage((GuiceScoped) driver);
  }

  public OtusCourses getCoursesAmount() {
    this.coursesAmount = courses.size();
    return this;
  }

  public void checkThatLessonsAmountIsGreaterThan(int expectedCoursesAmount) {
    assertThat(coursesAmount)
        .withFailMessage("У OTUS'a большие проблемы с выбором курсов!")
        .isGreaterThan(expectedCoursesAmount);
  }

  //Метод фильтр по названию курса (ищет подстроку в названии курса без учета регистра)
  public OtusCourses filterCoursesNamedAs(String subStringInCourseName) {
    namedCourses = courses.stream()
        .filter(item -> (item.findElement(By.cssSelector(".lessons__new-item-title")).getText().toLowerCase())
            .contains(subStringInCourseName.toLowerCase()))
        .collect(Collectors.toList());
    return this;
  }

  public OtusCourses printFilteredCoursesTitleAndClick() {
    namedCourses
        .forEach(course -> {
          System.out.println(course.findElement(By.cssSelector(".lessons__new-item-title")).getText());
          scrollToElement(course);
          course.click();
        });
    return this;
  }

  public void checkThatNamedCoursesAmountIsGreaterThan(int expectedNamedCoursesAmount, String subStringInCourseName) {
    assertThat(namedCourses.size())
        .withFailMessage("Всего курсов, содержащих фразу " + subStringInCourseName + " равно "
            + namedCourses.size() + ", а это не больше, чем " + expectedNamedCoursesAmount)
        .isGreaterThan(expectedNamedCoursesAmount);
  }

  //Метод выбора курса, стартующего раньше всех/позже всех (при совпадении дат - выбрать любой) при помощи reduce
  public void chooseTheLatestPopularCourse() {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.forLanguageTag("ru"));
    LocalDate theLatestDate = favoriteCourses.stream()
        .map(course -> {
          String dateInString = (course.findElement(By.cssSelector(".lessons__new-item-start")))
              .getText().toLowerCase().split("с ")[1] + " " + Year.now();
          return LocalDate.parse(dateInString, formatter);
        })
        .reduce((a, b) -> a.isAfter(b) ? a : b).orElse(null);//find maximum

    for (WebElement course : favoriteCourses) {
      if (course.findElement(By.cssSelector(".lessons__new-item-start")).getText().toLowerCase(Locale.forLanguageTag("ru"))
          .contains(theLatestDate.format(DateTimeFormatter.ofPattern("dd MMMM", Locale.forLanguageTag("ru"))))) {
        scrollToElement(course);
        course.click();
        return;
      }
    }
  }


  //Метод выбора курса, стартующего раньше всех/позже всех (при совпадении дат - выбрать любой) при помощи reduce
  public void chooseTheEarliestRegularCourse() {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.forLanguageTag("ru"));
    LocalDate theEarliestDate = regularCourses.stream()
        .map(course -> {
          String[] splittedDate = (course.findElement(By.cssSelector(".lessons__new-item-time"))).getText()
              .toLowerCase().split(" ");
          String dateInString = splittedDate[0] + " " + splittedDate[1];
          dateInString += ((splittedDate[2].length() == 4) && splittedDate[2].matches("20[0-9]+"))
              ? " " + splittedDate[2] : " " + Year.now();
          try {
            return LocalDate.parse(dateInString, formatter);
          } catch (Exception exception) {//ignore
          }
          return null;
        })
        .filter(Objects::nonNull)//remove null objects
        .reduce((a, b) -> a.isAfter(b) ? b : a).orElse(null);//find minimum

    for (WebElement course : regularCourses) {
      if (course.findElement(By.cssSelector(".lessons__new-item-time")).getText().toLowerCase(Locale.forLanguageTag("ru"))
          .contains(theEarliestDate.format(DateTimeFormatter.ofPattern("dd MMMM", Locale.forLanguageTag("ru"))))) {
        scrollToElement(course);
        course.click();
        return;
      }
    }
  }

  //Метод поиска курсов, стартующих в указанную дату или позже указанной даты и вывод информации о них в консоль (название, дата старта)
  public void filterCourseFromDate(String startDate) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.forLanguageTag("ru"));
    LocalDate dateToCompare = LocalDate.parse(startDate, formatter);
    regularCourses.stream()
        .map(course -> {
          String[] splittedDate = (course.findElement(By.cssSelector(".lessons__new-item-time"))).getText()
              .toLowerCase().split(" ");
          String dateInString = splittedDate[0] + " " + splittedDate[1];
          dateInString += ((splittedDate[2].length() == 4) && splittedDate[2].matches("20[0-9]+"))
              ? " " + splittedDate[2] : " " + Year.now();
          try {
            LocalDate courseDate = LocalDate.parse(dateInString, formatter);
            if (courseDate.isAfter(dateToCompare) || courseDate.equals(dateToCompare))
              System.out.println("START COURSE DATE: " + dateInString
                  + " COURSE TITLE: " + course.findElement(By.cssSelector(".lessons__new-item-title")).getText()
              );
            return courseDate;
          } catch (Exception exception) {//ignore
          }
          return null;
        })
        .filter(Objects::nonNull)//remove null objects
        .reduce((a, b) -> a.isAfter(b) ? b : a).orElse(null);//find minimum
  }

}
