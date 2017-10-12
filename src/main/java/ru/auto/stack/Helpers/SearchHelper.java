package ru.auto.stack.Helpers;

import org.openqa.selenium.WebDriver;
import ru.auto.stack.pages.StackPage;

public class SearchHelper {
  private StackPage stackPage;

  public SearchHelper(WebDriver wd) {
    stackPage = new StackPage(wd);
  }

  public StackPage openEbayHomePage() {
    return stackPage.openHomePage();
  }
}
