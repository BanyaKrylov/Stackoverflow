package ru.auto.stack;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.auto.stack.ApplicationManagers.StackManager;

/**
 * Created by Иван on 04.10.2017.
 */
public abstract class AbstractTest {
  protected static final StackManager MANAGER = new StackManager();

  @BeforeSuite
  public void setUp() throws Exception {
    MANAGER.init();
  }

  @AfterSuite
  public void tearDown() {
    MANAGER.stop();
  }
}
