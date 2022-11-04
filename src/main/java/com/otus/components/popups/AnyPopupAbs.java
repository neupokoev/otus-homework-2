package com.otus.components.popups;

import com.google.inject.Inject;
import com.otus.actions.CommonActions;
import com.otus.diconfig.GuiceScoped;
import org.openqa.selenium.WebDriver;

public abstract class AnyPopupAbs<T> extends CommonActions<T> implements IPopup<T> {

  @Inject
  public AnyPopupAbs(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

}
