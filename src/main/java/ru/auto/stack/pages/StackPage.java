package ru.auto.stack.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class StackPage extends AbstractPage{
    private final static String HOME_URL = "https://stackoverflow.com/";

    @FindBy(xpath = "//form[@id='search']/input")
    private WebElement fieldSearch;

    @FindBy(xpath = "//form[@id='search']/button")
    private WebElement buttonSearch;

    @FindBy(xpath = "//div[@id='question-header']/h1/a")
    private WebElement questionsHeader;

    @FindBy(xpath = "//div[@class='question-summary search-result']//a[contains(@title, 'webdriver')]")
    private WebElement questionsSummary;

    @FindBy(xpath = "//input[@id='tagfilter']")
    private WebElement tagsSearch;

    @FindBy(xpath = "//table[@id='tags-browser']//a[contains(text(), 'webdriver')]")
    private WebElement tagsResult;

    @FindBy(xpath = "//table[@id='tags-browser']//a[.='webdriver']")
    private WebElement fullMatchTagsResult;

    @FindBy(xpath = "//a[.='webdriver']")
    private WebElement verifyTags;

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
        verifySummaryQuestions(questionsSummary);
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
        verifyTagsQuestions(fullMatchTagsResult);
        return this;
    }

    public StackPage openFullMatchResult() {
        click(fullMatchTagsResult);
        return this;
    }

    public StackPage verifyTagsQuestionResult() {
        verifyTagsQuestions(verifyTags);
        return this;
    }
}
