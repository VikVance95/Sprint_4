package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderIsProcessed {
    private WebDriver driver;
    //Локатор поля Заказ оформлен
    private By orderIsProcessed = By.xpath("//div[text()='Заказ оформлен']");

    public OrderIsProcessed(WebDriver driver) {

        this.driver = driver;
    }

    //метод возвращает истину, если поле Заказ оформлен отображено
    public boolean orderIsProcessedTextIsDisplayed() {

        return driver.findElement(orderIsProcessed).isDisplayed();
    }
}
