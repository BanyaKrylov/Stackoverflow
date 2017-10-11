package ru.auto.stack.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 05.10.2017.
 */
public abstract class AbstractPage {
    protected WebDriver driver;
    protected List<WebElement> elementQuestions;
    protected List<WebElement> tagsQuestions;
    protected List<String> titleQuestions;
    protected List<String> textTags;

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

    protected void verifySummaryQuestions(WebElement locator) {
        elementQuestions = driver.findElements(By.xpath(String.valueOf(locator)));
        for (WebElement element : elementQuestions) {
            titleQuestions.add(element.getAttribute("title"));
            for (int i = 0; i < titleQuestions.size(); i++) {
                Assert.assertTrue(titleQuestions.get(i).contains("webdriver"));
            }
        }
    }

    protected void verifyTagsQuestions(WebElement locator) {
        tagsQuestions = driver.findElements(By.xpath(String.valueOf(locator)));
        for (WebElement element : tagsQuestions) {
            textTags.add(element.getText());
            for (int i = 0; i < textTags.size(); i++) {
                Assert.assertTrue(textTags.get(i).contains("webdriver"));
            }
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
}
