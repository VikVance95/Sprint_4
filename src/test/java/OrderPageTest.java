import org.example.pageobject.MainPage;
import org.example.pageobject.OrderPage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


@RunWith(Parameterized.class)

public class OrderPageTest {

    private WebDriver driver;
    private final int orderButtonPlace; //1-кнопка заказа вверху, 2-кнопка заказа внизу
    private final String userName;
    private final String userSurname;
    private final String address;
    private final String subwayStation;
    private final String userPhone;
    private final String rentalStartDate;
    private final String rentalPeriod;
    private final String scooterColor;
    private final String comment;

    public OrderPageTest(int orderButtonPlace, String userName, String userSurname, String address,
                            String subwayStation, String userPhone, String rentalStartDate, String rentalPeriod,
                            String scooterColor, String comment){
        this.orderButtonPlace = orderButtonPlace;
        this.userName = userName;
        this.userSurname = userSurname;
        this.address = address;
        this.subwayStation = subwayStation;
        this.userPhone = userPhone;
        this.rentalStartDate = rentalStartDate;
        this.rentalPeriod = rentalPeriod;
        this.scooterColor = scooterColor;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getInputData() {
        return new Object[][] {
                {1, "Джон", "Доу", "Москва, Красная площадь, 1", "Чистые пруды", "+123456789012", "06.10.2022", "четверо суток", "чёрный жемчуг", "комментарий"},
                {2, "Джейн", "Доу", "Москва, Большая басманная, 1", "Сокольники", "+123456789012", "31.12.2022", "сутки", "серая безысходность", "комментарий"}
        };
    }

    public void initChrome() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    @Before
    public void startUp() {
        initChrome();
    }

    @Test
    public void checkPlacingOrder() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage objMainPage = new MainPage(driver);
        OrderPage objOrderPage = new OrderPage(driver);

        //Клик по кнопке заказа
        switch (orderButtonPlace) {
            case 1: objMainPage.clickOrderUpperButton();
                break;
            case 2: objMainPage.clickOrderLowerButton();
                break;
        }
        objMainPage.clickCookieAcceptButton();

        //Для кого самокат
        new WebDriverWait(driver, 1)
                .until(ExpectedConditions.visibilityOfElementLocated(objOrderPage.getOrderAboutUserLabel()));
        objOrderPage.addUserInfoInOrder(userName, userSurname, address, subwayStation, userPhone);

        //Про аренду
        new WebDriverWait(driver, 1)
                .until(ExpectedConditions.visibilityOfElementLocated(objOrderPage.getOrderAboutRentingLabel()));
        objOrderPage.addRentingInfoInOrder(rentalStartDate, rentalPeriod, scooterColor, comment);

        //Подтверждение заказа
        new WebDriverWait(driver, 1)
                .until(ExpectedConditions.visibilityOfElementLocated(objOrderPage.getOrderConfirmationLabel()));
        objOrderPage.clickOrderConfirmationButton();

        //Заказ подтвержден
        new WebDriverWait(driver, 1)
                .until(ExpectedConditions.visibilityOfElementLocated(objOrderPage.getOrderConfirmedLabel()));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
