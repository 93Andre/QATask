package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object Model for the Login Page.
 * If more time would do for the other pages but this is just for a quick demonstrate of POM
 * Page Factory for @FindBy
 */
public class LoginPage {

    @FindBy(id = "login_form")
    private WebElement loginForm;

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login_button")
    private WebElement loginButton;

    @FindBy(css = ".error_status.card .content li:first-of-type")
    private WebElement errorMessage;


    // Constructor initializes elements with PageFactory
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Verifies that the login form is displayed.
     *
     * @return true if the login form is visible; otherwise false.
     */
    public boolean isPageLoaded() {
        return loginForm.isDisplayed();
    }

    /**
     * Enters the username into the username input field.
     *
     * @param username the username to enter.
     */
    public void enterUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    /**
     * Enters the password into the password input field.
     *
     * @param password the password to enter.
     */
    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    /**
     * Clicks the login button.
     */
    public void clickLogin() {
        loginButton.click();
    }

    /**
     * Retrieves the error message displayed on the login page.
     *
     * @return the error message text.
     */
    public By getErrorMessageLocator() {
        return By.cssSelector(".error_status.card span:first-of-type");
    }



    /**
     * Performs the complete login action.
     *
     * @param username the username to use.
     * @param password the password to use.
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}
