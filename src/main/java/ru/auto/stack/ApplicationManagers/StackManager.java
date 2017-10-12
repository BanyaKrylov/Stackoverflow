package ru.auto.stack.ApplicationManagers;

import ru.auto.stack.Helpers.SearchHelper;
import static ru.auto.stack.driver.DriverManager.*;

public class StackManager {
  private SearchHelper searchHelper;

  public void init() {
    searchHelper = new SearchHelper(driver());
  }

  public void stop() {
    quit();
  }

  public SearchHelper search() {
    return searchHelper;
  }
}
