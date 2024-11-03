import org.example.pageobject.MainPage;

import org.hamcrest.MatcherAssert;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


@RunWith(Parameterized.class)
public class MainPageFaqTest {

    private WebDriver driver;

    private final String faqAccordionItemId;
    private final boolean faqAccordionItemPanelHiddenExpected; //true - element is hidden, false - element isn't hidden
    private final String faqAccordionItemPanelTextExpected;


    public MainPageFaqTest(String faqAccordionItemId, boolean faqAccordionItemPanelHiddenExpected, String faqAccordionItemPanelTextExpected) {
        this.faqAccordionItemId = faqAccordionItemId;
        this.faqAccordionItemPanelHiddenExpected = faqAccordionItemPanelHiddenExpected;
        this.faqAccordionItemPanelTextExpected = faqAccordionItemPanelTextExpected;
    }

    @Parameterized.Parameters

    public static Object[][] getInputData() {
        return new Object[][] {
                {"accordion__heading-0", false, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"accordion__heading-3", false, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
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
    public void checkAccordionExpand() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage objMainPage = new MainPage(driver, faqAccordionItemId);

        //Куки
        objMainPage.clickCookieAcceptButton();

        //Переход к блоку с вопросами + клик
        objMainPage.clickFaqAccordionItemHeading();

        //Проверка видимости блока
        WebElement element = objMainPage.getFaqAccordionItemPanel();
        boolean visibleActual = Boolean.parseBoolean(element.getAttribute("hidden"));
        assertEquals(visibleActual, faqAccordionItemPanelHiddenExpected);

        //Проверка текста
        String textActual = objMainPage.getFaqAccordionItemPanelText().getText();
        MatcherAssert.assertThat(textActual, containsString(faqAccordionItemPanelTextExpected));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
