import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UpworkTest {
    private static WebDriver driver;
    private static Properties testProps;

    @BeforeAll
    public static void init() throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "testdata.properties";

        testProps = new Properties();
        testProps.load(new FileInputStream(appConfigPath));

        driver = WebdriverFactory.getChromeDriver();
        driver.get(testProps.getProperty("url"));
    }


    @Test
    public void sampleTest() {
        HomePage homePage = new HomePage(driver);
        homePage.search(testProps.getProperty("text"));

        Assertions.assertTrue(homePage.verifySearchResults(testProps.getProperty("text")));
//        homePage.verifySearchResults(testProps.getProperty("text"));

        Assertions.assertTrue(homePage.clickOnAnyResult());
    }

    @AfterAll
    public static void afterAll() {
        driver.close();
    }
}
