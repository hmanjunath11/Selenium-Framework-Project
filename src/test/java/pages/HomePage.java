package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;
import ru.yandex.qatools.allure.annotations.Step;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "a[href*='login']")
	private WebElement logInButton;
	
	@Step("Click on Login button on Home Page")
	public LogInPage clickLogin() {
		System.out.println(logInButton.isDisplayed());
		clickOnElement(logInButton);
		return PageFactory.initElements(driver, LogInPage.class);
	}
}
