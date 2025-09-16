package stepDefinitions;


import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LabCorpSteps {

	WebDriver driver;
    WebDriverWait wait;

    @Given("I open the LabCorp home page")
    public void openHomepage() {
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();  
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://www.labcorp.com");
    }

    @When("I click on the Careers link")
    public void clickCareersLink() {
        WebElement careersLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Careers")));
        careersLink.click();
    }

    @When("I search for job titled {string}")
    public void searchForJob(String jobTitle) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-field-keyword")));
        searchInput.sendKeys(jobTitle);
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.search-submit")));
        searchBtn.click();
    }

    @Then("I should see the job posting details containing {string}")
    public void verifyJobDetails(String jobTitle) {
        WebElement jobResult = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//a[contains(text(),'" + jobTitle + "')]")));
        assertTrue(jobResult.isDisplayed());
        jobResult.click();
        WebElement desc = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".job-description")));
        assertTrue(desc.getText().toLowerCase().contains(jobTitle.toLowerCase()));
        driver.quit();
    }
	
}
