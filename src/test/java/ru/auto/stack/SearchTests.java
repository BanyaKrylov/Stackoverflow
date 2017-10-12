package ru.auto.stack;

import org.testng.annotations.Test;
import ru.auto.stack.pages.StackPage;

import java.io.IOException;

public class SearchTests extends AbstractTest {
  String searchText = "webdriver";

  @Test
  public void Search() throws IOException, InterruptedException {
    MANAGER.search().openEbayHomePage().
            search(searchText)
            .verifyTitleQuestion()
            .getHeaderQuestion()
            .assertSummaruAndHeaders()
            .searchTags(searchText)
            .verifyTagsResult()
            .openFullMatchResult()
            .verifyTagsQuestionResult();
  }
}
