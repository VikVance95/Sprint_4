package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private final WebDriver driver;

    //Локатор для кнопки согласия с куками
    private final By cookieAcceptButton = By.xpath(".//button[@class='App_CookieButton__3cvqF']");

    //Локатор для верхней кнопки Заказать
    private final By moveToOrderUpperButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
    //Локатор для нижней кнопки Заказать
    private final By moveToOrderLowerButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");

    //паттерн для локаторов вопросов
    private final static String faqQuestionPattern = ".//div[text()='%s']";

    //раскрытый ответ
    private final By OpenedAnswer = By.xpath(".//div[contains(@class, 'accordion__panel') and not(@hidden)]/p");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    //Кликнуть по кнопке согласия с куками
    public void clickCookieAcceptButton() {
        driver.findElement(cookieAcceptButton).click();
    }

    public void clickFAQQuestionButton(String questionMessage){
        String questionLocator = String.format(faqQuestionPattern, questionMessage);
        driver.findElement(By.xpath(questionLocator)).click();
    }

    public void scrollToElement(String questionMessage){
        String questionLocator = String.format(faqQuestionPattern, questionMessage);
        WebElement element = driver.findElement(By.xpath(questionLocator));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public String getAnswersText(){ //String answerMessage){
        return driver.findElement(OpenedAnswer).getText();
    }

    public void isEnabledQuestionButton(String questionMessage){
        String questionLocator = String.format(faqQuestionPattern, questionMessage);
        driver.findElement(By.xpath(questionLocator)).isEnabled();
    }

    public void clickUpperOrderButton(){
        driver.findElement(moveToOrderUpperButton).isEnabled();
        driver.findElement(moveToOrderUpperButton).click();
    }

    public void clickLowerOrderButton(){
        driver.findElement(moveToOrderLowerButton).isEnabled();
        driver.findElement(moveToOrderLowerButton).click();
    }

    public void isAnswerVisible(){
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(OpenedAnswer));
    }
}
