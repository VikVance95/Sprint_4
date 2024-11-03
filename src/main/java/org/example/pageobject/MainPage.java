package org.example.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private final WebDriver driver;

    //Локатор для кнопки согласия с куками
    private final By cookieAcceptButton = By.xpath(".//button[@class='App_CookieButton__3cvqF']");

    //Локаторы для блока с вопросами
    private By faqAccordionItemHeading;
    private By faqAccordionItemPanel;
    private By faqAccordionItemPanelText;

    //Локатор для верхней кнопки Заказать
    private final By moveToOrderUpperButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
    //Локатор для нижней кнопки Заказать
    private final By moveToOrderLowerButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");

    public MainPage(WebDriver driver, String accordionItemId){
        this.driver = driver;
        this.faqAccordionItemHeading = By.xpath(".//div[@id='"+ accordionItemId +"']");
        this.faqAccordionItemPanel = By.xpath(".//div[@aria-labelledby='"+ accordionItemId +"']");
        this.faqAccordionItemPanelText = By.xpath(".//div[@aria-labelledby='"+ accordionItemId +"']/p");
    }

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    //Кликнуть по кнопке согласия с куками
    public void clickCookieAcceptButton() {
        driver.findElement(cookieAcceptButton).click();
    }

    //Скролл до блока с вопросами + ожидание + клик
    public void clickFaqAccordionItemHeading() {
        WebElement element = driver.findElement(faqAccordionItemHeading);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(faqAccordionItemHeading));
        new Actions(driver).moveToElement(element).click().perform();
    }

    public WebElement getFaqAccordionItemPanel() {
        return driver.findElement(faqAccordionItemPanel);
    }

    public WebElement getFaqAccordionItemPanelText() {
        return driver.findElement(faqAccordionItemPanelText);
    }

    //Ожидание + клик по верхней кнопке "Заказать"
    public void clickOrderUpperButton() {
        new WebDriverWait(driver, 1)
                .until(ExpectedConditions.visibilityOfElementLocated(moveToOrderUpperButton));
        driver.findElement(moveToOrderUpperButton).click();
    }

    //Скролл до элемента + ожидание + клик по нижней кнопке "Заказать"
    public void clickOrderLowerButton() {
        WebElement element = driver.findElement(moveToOrderLowerButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(moveToOrderLowerButton));
        driver.findElement(moveToOrderLowerButton).click();
    }
}