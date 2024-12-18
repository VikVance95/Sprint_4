package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private final WebDriver driver;

    //Локаторы полей данных пользователя в форме "Для кого самокат"
    //Локатор заголовка формы "Для кого самокат"
    private final By orderAboutUserLabel = By.xpath(".//div[text() = 'Для кого самокат']");
    //Локатор для поля "Имя"
    private final By orderUserNameField = By.xpath(".//input[@placeholder='* Имя']");
    //Локатор для поля "Фамилия"
    private final By orderUserSurnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    //Локатор для поля "Адрес"
    private final By orderAddressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Локатор для поля "Станция метро"
    private final By orderSubwayStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    //Локатор для поля "Телефон"
    private final By orderUserPhoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Локатор для кнопки "Далее"
    private final By orderMoveToRentingInfoButton = By.xpath(".//button[text() = 'Далее']");

    //Локаторы полей в форме "Про аренду"
    //Локатор заголовка формы "Про аренду"
    private final By orderAboutRentingLabel = By.xpath(".//div[text() = 'Про аренду']");
    //Локатор для поля "Когда привезти самокат"
    private final By orderRentalStartDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Локатор для поля "Срок аренды"
    private final By orderRentalPeriodField = By.className("Dropdown-placeholder");
    //Локатор для чекбокса "Цвет самоката"
    private final By orderScooterColorField = By.className("Checkbox_Input__14A2w");
    //Локатор для поля "Комментарий"
    private final By orderCommentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Локатор для кнопки "Заказать"
    private final By orderMoveToConfirmOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text() = 'Заказать']");

    //Локаторы окна "Хотите оформить заказ?"
    private final By orderConfirmationLabel = By.xpath(".//div[text() = 'Хотите оформить заказ?']");
    //Локатор для кнопки "Да" в окне "Хотите оформить заказ?"
    private final By orderConfirmationButton = By.xpath(".//button[text() = 'Да']");

    //Локатор окна "Заказ оформлен"
    private final By orderOrderConfirmedLabel = By.className("Order_ModalHeader__3FDaJ");


    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    //Методы заполнения формы "Для кого самокат"
    public By getOrderAboutUserLabel() {
        return orderAboutUserLabel;
    }
    public By getOrderRentalPeriodValue(String value) {
        return By.xpath(".//div[text() = '"+ value +"']");
    }
    public void setOrderUserName(String userName) {
        driver.findElement(orderUserNameField).sendKeys(userName);
    }
    public void setOrderUserSurname(String userSurname) {
        driver.findElement(orderUserSurnameField).sendKeys(userSurname);
    }
    public void setOrderAddress(String address) {
        driver.findElement(orderAddressField).sendKeys(address);
    }
    public void setOrderSubwayStation(String subwayStation) {
        driver.findElement(orderSubwayStationField).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(orderSubwayStationField).sendKeys(subwayStation);
        driver.findElement(orderSubwayStationField).sendKeys(Keys.ENTER);
    }
    public void setOrderPhone(String phone) {
        driver.findElement(orderUserPhoneField).sendKeys(phone);
    }
    public void clickMoveToRentingInfoInOrderButton() {
        driver.findElement(orderMoveToRentingInfoButton).click();
    }

    //Методы заполнения формы "Про аренду"
    public By getOrderAboutRentingLabel() {
        return orderAboutRentingLabel;
    }
    public void setOrderStartDate(String startDate) {
        driver.findElement(orderRentalStartDateField).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(orderRentalStartDateField).sendKeys(startDate);
        driver.findElement(orderRentalStartDateField).sendKeys(Keys.ENTER);
    }
    public void setOrderRentalPeriod(String value) {
        driver.findElement(orderRentalPeriodField).click();
        driver.findElement(getOrderRentalPeriodValue(value)).click();
    }
    public void setOrderScooterColor(String scooterColor) {
        driver.findElement(orderScooterColorField).sendKeys(scooterColor);
    }
    public void setOrderComment(String comment) {
        driver.findElement(orderCommentField).sendKeys(comment);
    }
    public void clickMoveToConfirmationOrderButton() {
        driver.findElement(orderMoveToConfirmOrderButton).click();
    }

    //Методы подтверждения заказа
    public By getOrderConfirmationLabel() {
        return orderConfirmationLabel;
    }
    public void clickOrderConfirmationButton() {
        driver.findElement(orderConfirmationButton).click();
    }

    //Метод проверки подтверждения заказа
    public By getOrderConfirmedLabel() {
        new WebDriverWait(driver, 1)
                .until(ExpectedConditions.visibilityOfElementLocated(getOrderConfirmationLabel()));
        return orderOrderConfirmedLabel;
    }

    //Заполнение формы "Для кого самокат"
    public void addUserInfoInOrder(String userName, String userSurname, String address, String subwayStation, String phone) {
        new WebDriverWait(driver, 1)
                .until(ExpectedConditions.visibilityOfElementLocated(getOrderAboutUserLabel()));
        setOrderUserName(userName);
        setOrderUserSurname(userSurname);
        setOrderAddress(address);
        setOrderSubwayStation(subwayStation);
        setOrderPhone(phone);
        clickMoveToRentingInfoInOrderButton();
    }

    //Заполнение формы "Про аренду"
    public void addRentingInfoInOrder(String startDate, String rentalPeriod, String scooterColor, String comment) {
        new WebDriverWait(driver, 1)
                .until(ExpectedConditions.visibilityOfElementLocated(getOrderAboutRentingLabel()));
        setOrderStartDate(startDate);
        setOrderRentalPeriod(rentalPeriod);
        setOrderScooterColor(scooterColor);
        setOrderComment(comment);
        clickMoveToConfirmationOrderButton();
    }

}
