package com.otus.components;

import com.google.inject.Inject;
import com.otus.annotations.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.GuiceScoped;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component(".lessons a")
public class OnlineOtusCourses extends AnyComponentAbs<OnlineOtusCourses> {

  @FindBy(css = ".lessons a")
  private List<WebElement> courses;

  @FindBy(css = ".lessons a .lessons__new-item-title")
  private List<WebElement> coursesTitles;

  private long coursesAmount;
  private List<WebElement> namedCourses;
  private Integer theCheapestCourse = 0;
  private Integer theMostExpensiveCourse = 0;

  @Inject
  public OnlineOtusCourses(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  //Метод фильтр по названию курса (ищет подстроку в названии курса без учета регистра)
  public OnlineOtusCourses filterCoursesNamedAs(String subStringInCourseName) {
    namedCourses = courses.stream()
        .filter(item -> (item.findElement(By.cssSelector(".lessons__new-item-title")).getText().toLowerCase())
            .contains(subStringInCourseName.toLowerCase()))
        .collect(Collectors.toList());
    return this;
  }

  public OnlineOtusCourses printFilteredCoursesTitleAndClick() {
    namedCourses
        .forEach(course -> {
          System.out.println(course.findElement(By.cssSelector(".lessons__new-item-title")).getText());
          scrollToElement(course);
          course.click();
        });
    return this;
  }

  public void printFilteredCoursesInformation(Integer coursePrice) {
    if (coursePrice == null || coursePrice == 0) {
      throw new RuntimeException("Not valid parameter!");
    }
    courses
        .forEach(course -> {
          String price = (course.findElement(By.cssSelector(".lessons__new-item-price")))
              .getText()
              .trim()
              .toLowerCase()
              .split(" ")[0];
          if (Integer.parseInt(price) == coursePrice) {
            System.out.println("Course type: "
                + course.findElement(By.cssSelector(".lessons__new-item-what")).getText());
            System.out.println("Course description: "
                + course.findElement(By.cssSelector(".lessons__new-item-title")).getText());
            System.out.println(String.format("Course price: %s\n",
                course.findElement(By.cssSelector(".lessons__new-item-price")).getText()));
            scrollToElement(course);
          }
        });
  }

  //Метод выбора курса, который дороже всех
  public void filterTheMostExpensiveCourse() {
    theMostExpensiveCourse = courses.stream()
        .map(course -> {
          String priceCourse = (course.findElement(By.cssSelector(".lessons__new-item-price")))
              .getText()
              .trim()
              .toLowerCase()
              .split(" ")[0];
          return Integer.parseInt(priceCourse);
        })
        .filter(Objects::nonNull)//remove null objects
        .reduce((a, b) -> a > b ? b : a).orElse(null);//find maximum
    System.out.println("THE MOST EXPENSIVE COURSE IS/ARE:");
    printFilteredCoursesInformation(theMostExpensiveCourse);
  }

  //Метод выбора курса, который дешевле всех
  public void filterTheCheapestCourse() {
    theCheapestCourse = courses.stream()
        .map(course -> {
          String priceCourse = (course.findElement(By.cssSelector(".lessons__new-item-price")))
              .getText()
              .trim()
              .toLowerCase()
              .split(" ")[0];
          return Integer.parseInt(priceCourse);
        })
        .filter(Objects::nonNull)//remove null objects
        .reduce((a, b) -> a < b ? b : a).orElse(null);//find minimum
    System.out.println("THE CHEAPEST COURSE IS/ARE:");
    printFilteredCoursesInformation(theCheapestCourse);
  }

}
