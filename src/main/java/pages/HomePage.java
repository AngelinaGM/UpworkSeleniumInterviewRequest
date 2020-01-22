package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class HomePage {
    private static final Logger log = Logger.getLogger(HomePage.class);

    private WebDriver driver;

    private By searchInput = By.xpath("//up-header-visitor-primary-nav//div[@class='navbar-form']//input[@type='search']");
    private By searchResults = By.xpath("//section[@id='oContractorResults']//section");
    private By profileSlider = By.xpath("//div[@class='profile-slider-body']");
    private By profileName = By.xpath("//div[contains(@class,'cfe-ui-application')]//span[@itemprop='name']");
    private By searchResultsName = By.xpath("//section[@id='oContractorResults']//section//h4//span");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void search(String text) {
        driver.findElement(searchInput).sendKeys(text);
        driver.findElement(searchInput).sendKeys(Keys.ENTER);
    }

    public boolean verifySearchResults(String text) {
        List<WebElement> results = driver.findElements(searchResults);
        log.info("Results list size:: " + results.size());
        for (WebElement element : results) {
            String fullDescription = element.getText();
            if (!fullDescription.toLowerCase().contains(text.toLowerCase())) {
                log.info("This is not contains searching text:: " + fullDescription);
                return false;
            }
        }
        return true;
    }

    public boolean clickOnAnyResult() {
        Random random = new Random();
        int ind = random.ints(0, (10)).findFirst().getAsInt();
        log.info("Random profile is: " + ind);
        String name = null;

        String resultName = driver.findElements(searchResultsName).get(ind).getText();
        log.info("Profile name in list is: " + resultName);
        driver.findElements(searchResults).get(ind).click();
        WebElement profile = driver.findElement(profileSlider);
        if (profile.isDisplayed()) {
            name = profile.findElement(profileName).getText();
            log.info("Profile name is: " + name);
        }
        // confirm that the entry clicked on the search results has the same name that was in the list
        return name.equals(resultName);
    }


}
