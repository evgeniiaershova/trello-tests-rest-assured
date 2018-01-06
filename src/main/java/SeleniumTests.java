import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.containsString;


public class SeleniumTests {

    private WebDriver driver;



    @BeforeClass
    public void getDriver() {
        String browser = System.getProperty("browser");
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
    }


    @Test
    public void getGoogle() {
        driver.get("http://google.com");
        assertThat(driver.getCurrentUrl(), containsString("google.ru"));
    }

    @AfterClass
    public void closeDriver() {
        driver.close();
        driver.quit();
    }
}
