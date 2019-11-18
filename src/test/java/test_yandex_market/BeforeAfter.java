package test_yandex_market;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

public class BeforeAfter {
    public static ChromeDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        driver=new ChromeDriver();

    }


    @After
    public void close(){
        driver.quit();
    }
}
