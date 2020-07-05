package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;
import ru.yandex.qatools.allure.annotations.Step;

public class LogoutPage extends BasePage {
	
	public LogoutPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "login-email")
	private WebElement emailTextBox;
	
	@Step("Check to confirm logout was a success.")
	public boolean isLoginWindow() {
		return isElementDisplayed(emailTextBox);
	}
}
