package ru.auto.stack.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class StackPage extends AbstractPage{
    private final static String HOME_URL = "https://stackoverflow.com/";

    @FindBy(xpath = "//form[@id='search']/input")
    private WebElement fieldSearch;

    @FindBy(xpath = "//form[@id='search']/button")
    private WebElement buttonSearch;

    @FindBy(xpath = "//div[@id='question-header']/h1/a")
    private WebElement questionsHeader;

    @FindBy(xpath = "//div[@class='result-link']/span")
    private WebElement questionsSummary;

    @FindBy(xpath = "//div[@class='result-link']/span/a")
    private List<WebElement> questionsSummarys;

    @FindBy(xpath = "//input[@id='tagfilter']")
    private WebElement tagsSearch;

    @FindBy(xpath = "//table[@id='tags-browser']//a[contains(text(), 'webdriver')]")
    private WebElement tagsResult;

    @FindBy(xpath = "//table[@id='tags-browser']//a[.='webdriver']")
    private WebElement fullMatchTagsResult;

    @FindBy(xpath = "//table[@id='tags-browser']//a[.='webdriver']")
    private List<WebElement> fullMatchTagsResults;

    @FindBy(xpath = "//a[.='webdriver']")
    private WebElement verifyTags;

    @FindBy(xpath = "//a[.='webdriver']")
    private List<WebElement> verifyTagss;

    @FindBy(xpath = "//a[@id='nav-tags']")
    private WebElement tagsButtonSearch;
    //==================================================================================================================

    public StackPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public StackPage openHomePage() {
        open(HOME_URL);
        return this;
    }

    public StackPage search(String searchText) {
        type(fieldSearch, searchText);
        click(buttonSearch);
        return this;
    }

    public StackPage verifyTitleQuestion() {
        verifySummaryQuestions(questionsSummarys);
        return this;
    }

    public StackPage getHeaderQuestion() {
        getHeadersQuestions(questionsHeader);
        return this;
    }

    public StackPage assertSummaruAndHeaders() {
        assertSummaryAndHeadersQuestions();
        return this;
    }

    public StackPage searchTags(String searchText) {
        click(tagsButtonSearch);
        type(tagsSearch, searchText);
        return this;
    }

    public StackPage verifyTagsResult() {
        verifyTagsQuestions(fullMatchTagsResults);
        return this;
    }

    public StackPage openFullMatchResult() {
        click(fullMatchTagsResult);
        return this;
    }

    public StackPage verifyTagsQuestionResult() {
        verifyTagsQuestions(verifyTagss);
        return this;
    }
}
