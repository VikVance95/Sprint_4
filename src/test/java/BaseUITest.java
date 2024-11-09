import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class BaseUITest {

    protected WebDriver driver;

    //Константа для url главной страницы
    private static final String URL_MAIN_PAGE = "https://qa-scooter.praktikum-services.ru/";

    @Before

    public void startUp() {
        initChrome();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.get(URL_MAIN_PAGE);
    }

    @After

    public void tearDown() {
        driver.quit();
    }

    public void initChrome() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    public void initFirefox() {
        FirefoxOptions options = new FirefoxOptions();
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/webdriver/geckodriver.exe");
        driver = new FirefoxDriver(options);
    }
}
