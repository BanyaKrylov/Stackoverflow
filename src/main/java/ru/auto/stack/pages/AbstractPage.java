package ru.auto.stack.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected List<WebElement> elementQuestions;
    protected List<WebElement> tagsQuestions;
    protected List<String> titleQuestions = new ArrayList<>();
    protected List<String> textTags = new ArrayList<>();
    protected List<String> textHeaders = new ArrayList<>();

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    //============================================ selenide methods ====================================================

    protected void click(By locator) {
        WebElement dynamicElement = getDriverWait(10).until(ExpectedConditions.presenceOfElementLocated(locator));
        click(dynamicElement);
    }

    protected void click(WebElement element) {
        element.click();
    }

    protected void type(By locator, String type) {
        click(locator);
        if (type != null) {
            String existingText = find(locator).getAttribute("value");
            if (!type.equals(existingText)) {
                find(locator).clear();
                find(locator).sendKeys(type);
            }
        }
    }

    protected void type(WebElement element, String type) {
        click(element);
        if (type != null) {
            String existingText = element.getAttribute("value");
            if (!type.equals(existingText)) {
                element.clear();
                element.sendKeys(type);
            }
        }
    }

    protected void verifySummaryQuestions(By locator) {
        elementQuestions = driver.findElements(locator);
        for (WebElement element : elementQuestions) {
            element.getAttribute("title");
            for (String title : titleQuestions) {
                Assert.assertTrue(title.contains("webdriver"));
            }
        }
    }

    protected void verifySummaryQuestions(List<WebElement> locators) {
        elementQuestions = locators;
        for (WebElement element : elementQuestions) {
            titleQuestions.add(element.getAttribute("title"));
        }
        for (String title : titleQuestions) {
            Assert.assertTrue(checkForWord(title, "webdriver"));
        }
    }

    protected void verifyTagsQuestions(By locator) {
        tagsQuestions = driver.findElements(locator);
        for (WebElement element : tagsQuestions) {
            textTags.add(element.getText());
            for (String text : textTags) {
                Assert.assertTrue(text.contains("webdriver"));
            }
        }
    }

    protected void verifyTagsQuestions(List<WebElement> locators) {
        tagsQuestions = locators;
        for (WebElement element : tagsQuestions) {
            textTags.add(element.getText());
            for (String text : textTags) {
                Assert.assertTrue(text.contains("webdriver"));
            }
        }
    }

    protected void getHeadersQuestions(WebElement headerLocator) {
        for (int i = 0; i < titleQuestions.size(); i++) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementQuestions.get(i));
            click(elementQuestions.get(i));
            textHeaders.add(headerLocator.getText());
            driver.navigate().back();
        }
    }

    protected void assertSummaryAndHeadersQuestions() {
        for (int i = 0; i < titleQuestions.size(); i++) {
            Assert.assertTrue(checkForWord(textHeaders.get(i), titleQuestions.get(i)));
        }
    }

    public void switchToTabOrWindowWithIndex(int tabIndex) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabIndex));
    }

    public void closeTabOrWindowWithIndex(int tabIndex) {
        int mainTab = 0;
        switchToTabOrWindowWithIndex(tabIndex);
        driver.close();
        if (tabIndex > 0) {
            switchToTabOrWindowWithIndex(mainTab);
        }
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    protected void open(String url) {
        driver.get(url);
    }

    protected FluentWait<WebDriver> getDriverWait(int timeOut) {
        return new WebDriverWait(driver, timeOut);
    }

    //========================================= other ====================================================================

    protected void waitFor(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    boolean checkForWord(String line, String word) {
        return line.toLowerCase().contains(word.toLowerCase());
    }
}
