import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.containsString;


public class SeleniumTests {
//    RemoteWebDriver driver;
//    Capabilities chromeCapabilities = DesiredCapabilities.chrome();
//    Capabilities firefoxCapabilities = DesiredCapabilities.firefox();

    WebDriver driver;


    @BeforeClass
    public void getDriver() throws MalformedURLException {
        String browser = System.getProperty("browser");
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
//            driver = new RemoteWebDriver(new URL("http://localhost:32772/wd/hub"), chromeCapabilities);
        }
        if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
//            driver = new RemoteWebDriver(new URL("http://localhost:32772/wd/hub"), firefoxCapabilities);
        }
    }


    @Test
    public void getGoogle() {
        driver.get("http://google.com");
        System.out.println(driver.getCurrentUrl());
        assertThat(driver.getCurrentUrl(), containsString("google.ru"));
    }

    @Test
    public void getYandex() {
        driver.get("http://google.com");
        System.out.println(driver.getCurrentUrl());
        assertThat(driver.getCurrentUrl(), containsString("yandex.ru"));
    }

    @AfterClass
    public void closeBrowser() {
        if (driver !=null)
        {
            driver.close();
            driver.quit();
        }
    }
}
