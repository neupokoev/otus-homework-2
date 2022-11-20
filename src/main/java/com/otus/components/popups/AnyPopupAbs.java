package com.otus.components.popups;

import com.google.inject.Inject;
import com.otus.actions.CommonActions;
import support.GuiceScoped;

public abstract class AnyPopupAbs<T> extends CommonActions<T> implements IPopup<T> {

  @Inject
  public AnyPopupAbs(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

}
