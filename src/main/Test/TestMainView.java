import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestMainView {
    private WebDriver webDriver;

//    @BeforeAll
    @Test
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "/Users/listya/Documents/Lis/Lib/geckodriver 2");
        webDriver = new FirefoxDriver();
        String baseUrl = "http://demo.guru99.com/test/newtours/";
        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = "";

        webDriver.get(baseUrl);
        actualTitle = webDriver.getTitle();
        System.out.println(actualTitle);


    }

}

