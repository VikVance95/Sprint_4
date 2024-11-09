import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.MainPage;
import pageobject.OrderIsProcessed;
import pageobject.OrderPage;


import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class OrderPageTest extends BaseUITest {

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

    @Test
    public void checkPlacingOrderTest() {

        MainPage objMainPage = new MainPage(driver);
        OrderPage objOrderPage = new OrderPage(driver);

        //Клик по кнопке заказа
        switch (orderButtonPlace) {
            case 1: objMainPage.clickUpperOrderButton();
                break;
            case 2: objMainPage.clickLowerOrderButton();
                break;
        }
        objMainPage.clickCookieAcceptButton();

        //Для кого самокат
        objOrderPage.addUserInfoInOrder(userName, userSurname, address, subwayStation, userPhone);

        //Про аренду
        objOrderPage.addRentingInfoInOrder(rentalStartDate, rentalPeriod, scooterColor, comment);

        //Подтверждение заказа
        objOrderPage.clickOrderConfirmationButton();

        //Заказ подтвержден
        new WebDriverWait(driver, 1)
                .until(ExpectedConditions.visibilityOfElementLocated(objOrderPage.getOrderConfirmedLabel()));
        OrderIsProcessed objOrderIsProcessed = new OrderIsProcessed(driver);
        //Проверка, что поле "Заказ оформлен". В приложении есть баг, который не даёт оформить заказ. Он воспроизводится только в Chrome
        assertTrue(objOrderIsProcessed.orderIsProcessedTextIsDisplayed());
    }
}
