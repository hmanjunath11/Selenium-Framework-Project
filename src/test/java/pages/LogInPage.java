package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;
import ru.yandex.qatools.allure.annotations.Step;

public class LogInPage extends BasePage{

	public LogInPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "login-email")
	private WebElement emailTextBox;
	
	@FindBy(css = "button[id*='login-submit']")
	private WebElement nextAndSubmitButton;
	
	@FindBy(id = "password-login")
	private WebElement passwordTextBox;
	
	@Step("Check to confirm login window.")
	public boolean isLoginWindow() {
		return isElementDisplayed(emailTextBox);
	}
	
	@Step("Enter Username {0}")
    public void enterUsername(String username) {
        writeTextInField(emailTextBox, username);
    }

    @Step("Enter Password")
    public void enterPassword(String password) {
        writeTextInField(passwordTextBox, password);
    }

    @Step("Click on next/login")
    public UserDashBoardPage nextOrSubmit() {
        clickOnElement(nextAndSubmitButton);
        return PageFactory.initElements(driver, UserDashBoardPage.class);
    }

}
