import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TestMainView {
    private WebDriver webDriver;

//    @BeforeAll
    @Test
    public void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        webDriver = new ChromeDriver();
        String baseUrl = "http://demo.guru99.com/test/newtours/";
        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = "";

        webDriver.get(baseUrl);
        actualTitle = webDriver.getTitle();
        System.out.println(actualTitle);


    }

}

