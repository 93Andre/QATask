package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MockExampleSteps {
    private final WebDriver driver;
    public MockExampleSteps() {
        driver = new ChromeDriver();
    }

    @Given("I open the mock film page")
    public void iOpenTheFilmDetailsPageMock() {
        driver.get("http://localhost:8080/index.html");
    }


    @Then("I should see the film director mock {string}")
    public void iShouldSeeTheFilmDirectorMock(String expectedDirector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement directorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#film-director"))
        );
        String actualDirector = directorElement.getText().trim();
        Assertions.assertEquals(expectedDirector, actualDirector, "Film director does not match.");
    }

    @Then("I should see the following cast members mock:")
    public void iShouldSeeTheFollowingCastMembersMock(DataTable table) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> castElements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#film-cast li"))
        );

        // Extract and trim the text from each list item
        List<String> actualCast = castElements.stream()
                .map(element -> element.getText().trim())
                .toList();

        // Convert the DataTable to a list of expected cast names
        List<String> expectedCast = table.asList(String.class);

        // Verify that each expected cast member is present in the actual cast list
        for (String expectedName : expectedCast) {
            Assertions.assertTrue(actualCast.contains(expectedName),
                    "Expected cast member '" + expectedName + "' was not found in the actual cast list: " + actualCast);
        }
        driver.quit();
    }
}
