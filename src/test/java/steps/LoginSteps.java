package steps;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        driver = new ChromeDriver();
        driver.get("https://www.themoviedb.org/login");
        loginPage = new LoginPage(driver);
        Assertions.assertTrue(loginPage.isPageLoaded(), "Login page is not displayed");
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter password {string}")
    public void iEnterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLogin();
    }

    @Then("I should be redirected to the user page")
    public void iShouldBeRedirectedToTheUserPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.urlContains("/u/"));
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("/u/"), "Redirection to user page failed. Current URL: " + currentUrl);
        driver.quit();
    }


    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorMessageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginPage.getErrorMessageLocator())
        );
        String actualMessage = errorMessageElement.getText().trim();
        Assertions.assertEquals(expectedMessage, actualMessage, "Error message mismatch");
        driver.quit();
    }


}
