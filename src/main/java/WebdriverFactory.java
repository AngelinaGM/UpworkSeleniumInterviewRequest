import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class WebdriverFactory {

    private static WebDriver driver;

    public static WebDriver getChromeDriver() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "chromedriver";
        System.setProperty("webdriver.chrome.driver", appConfigPath);
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--incognito");
        options.addArguments("--user-data-dir=/Users/agorbach/Documents/Profile");
        options.addArguments("--profile-directory=Profile 1");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        return driver;
    }
}
