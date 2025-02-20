package steps;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DataParsing;
import io.cucumber.datatable.DataTable;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;

public class SearchSteps {

    private final WebDriver driver;
    private List<String> castRealName;
    private List<String> castCharacter;

    public SearchSteps() {
        driver = new ChromeDriver();
    }

    @Given("^I am on the film page for \"([^\"]*)\"$")
    public void iAmOnTheFilmPage(String movieName) {
        DataParsing dataParsing = new DataParsing();
        String data = dataParsing.readYml("data/film_urls.yml", movieName);
        String baseUrl = "https://www.themoviedb.org";
        driver.get(baseUrl + "/movie/" + data);
    }

    @When("I accept all cookies if prompted")
    public void iAcceptAllCookiesIfPrompted() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement acceptCookies = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler"))
            );
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookie consent button did not appear; continuing.");
        }
    }

    @When("^I open the full cast and crew page$")
    public void iOpenFullCastAndCrewPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement fullCastLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Full Cast & Crew"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fullCastLink);
        fullCastLink.click();
    }


    @And("^I make note of all the cast$")
    public void iMakeNoteOfAllTheCast() {
        castRealName = new ArrayList<>();
        castCharacter = new ArrayList<>();
        // again would put the locators in a class file like done with LoginPage. Only done like this due to time
        List<WebElement> castNameElements = driver.findElements(By.cssSelector("ol.people.credits li p a"));
        for (WebElement element : castNameElements) {
            castRealName.add(element.getText());
        }

        List<WebElement> castCharacterElements = driver.findElements(By.cssSelector("li p.character"));
        for (WebElement element : castCharacterElements) {
            castCharacter.add(element.getText());
        }
    }

    @Then("^I check that the cast list includes:$")
    public void iCheckThatTheCastListIncludes(DataTable table) {

        List<List<String>> rows = table.asLists(String.class);
        List<String> expectedNames = rows.get(1);

        for (String actorName : expectedNames) {
            Assertions.assertTrue(
                    castRealName.contains(actorName),
                    () -> "Expected actor name '" + actorName + "' not found in " + castRealName
            );
        }
        driver.quit();
    }


    @Then("^I expect the Hot Fuzz characters to be listed$")
    public void iExpectHotFuzzCharactersToBeListed() {
        DataParsing dataParsing = new DataParsing();
        List<String> expectedCharacters = dataParsing.readJson("data/hot_fuzz.json");
        for (String character : expectedCharacters) {
            Assertions.assertTrue(castCharacter.contains(character), "Expected character not found: " + character);
        }
        driver.quit();
    }

    @Given("I open the The Movie dB homepage")
    public void iOpenTheTheMovieDBHomepage() {
        driver.get("https://www.themoviedb.org/");
    }


    @Given("^I search for an film \"([^\"]*)\"$")
    public void iSearchForAnFilm(String film) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchField = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("inner_search_v4"))
        );
        searchField.clear();
        searchField.sendKeys(film);

        WebElement searchButton = driver.findElement(By.cssSelector("#inner_search_form > input"));
        searchButton.click();

    }

    @When("^I click the first movie poster result$")
    public void iClickTheFirstResult() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> results = wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(
                        By.cssSelector("div.poster a.result[data-media-type='movie']")
                )
        );

        results.get(0).click();
    }


    @Then("^I should see the film title \"([^\"]*)\"$")
    public void iShouldSeeTheFilmTitle(String expectedTitle) {
        WebElement titleElement = driver.findElement(By.cssSelector("#original_header h2 > a"));
        String actualTitle = titleElement.getText();
        Assertions.assertEquals(expectedTitle, actualTitle, "Film title does not match.");
        driver.quit();
    }

    @Then("^I should see the film director \"([^\"]*)\"$")
    public void iShouldSeeTheFilmDirector(String expectedDirector) {
        WebElement directorElement = driver.findElement(By.cssSelector("div.film-details span.film-director"));
        String actualDirector = directorElement.getText();
        Assertions.assertEquals(expectedDirector, actualDirector, "Film director does not match.");
    }

    @Then("^I should see the following cast members:$")
    public void iShouldSeeTheFollowingCastMembers(DataTable dataTable) {
        List<String> expectedCast = dataTable.asList(String.class);

        List<WebElement> castElements = driver.findElements(By.cssSelector("ul.cast-list li.cast-member"));
        List<String> actualCast = castElements.stream()
                .map(WebElement::getText)
                .toList();

        // Verify each expected cast member appears in the actual cast list.
        for (String expectedMember : expectedCast) {
            Assertions.assertTrue(actualCast.contains(expectedMember),
                    "Expected cast member '" + expectedMember + "' not found in actual cast: " + actualCast);
        }
    }

}

