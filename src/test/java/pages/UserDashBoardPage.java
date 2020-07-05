package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;
import ru.yandex.qatools.allure.annotations.Step;

public class UserDashBoardPage extends BasePage{
	
	public UserDashBoardPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "button[data-resin-target='helpicon']")
	private WebElement dashBoardVerifier;
	
	@FindBy(css = "button[data-resin-target*='accountmenu']")
	private WebElement accountMenuButton;
	
	@FindBy(css = "a[href*='logout']")
	private WebElement logOutButton;
	
	@FindBy(css = "a[href*='folder'][title='TEST']")
	private WebElement folderName;
	
	@FindBy(xpath = "//a[@title='TEST']/ancestor::div[@class='item-list-name']/parent::div/preceding-sibling::div")
	private WebElement selectFolderElement;
	
	@FindBy(css = "b[class*='selected-item-name']")
	private WebElement selectFolderFileCheckElement;
	
	@FindBy(css = "div[class*='notification info wrap']>span")
	private WebElement notificationChecker;
	
	@FindBy(css = "button[class*='create-dropdown-menu-toggle']")
	private WebElement newDropDownButton;
	
	@Step("Wait and check for User Dashboard.")
	public boolean isUserDashBoard() {
		return isElementDisplayed(dashBoardVerifier);
	}
	
	@Step("Perform logout operation.")
	public LogoutPage performLogout() {
		clickOnElement(accountMenuButton);
		clickOnElement(logOutButton);
		return PageFactory.initElements(driver, LogoutPage.class);
	}
	
	@Step("Check whether a folder is present or not.")
	public boolean isFolderPresent() {
		return isElementDisplayed(folderName);
	}
	
	@Step("Folder found, performing select action.")
	public void selectFolder() {
		clickOnElementWithoutCheckingClickability(selectFolderElement);
	}
	
	@Step("Check and confirm whether folder is selected or not.")
	public boolean isFolderSelected() {
		return isElementDisplayed(selectFolderFileCheckElement);
	}
	
	@Step("Perform click on button: {0} after selecting folder.")
	public PopUpPage clickOnActionAfterSelectingFolder(String action) {
		String cssSelector = "button[data-resin-target='{{button}}']";
		switch(action) {
		case "Trash":
			cssSelector = cssSelector.replace("{{button}}", "trash");
			break;
		case "Move or Copy":
			cssSelector = cssSelector.replace("{{button}}", "movecopy");
			break;
		}
		clickOnElement(driver.findElement(By.cssSelector(cssSelector)));
		return PageFactory.initElements(driver, PopUpPage.class);
	}
	
	@Step("Check notification text after performing action.")
	public String getNotificationText() {
		explicitWait.visibilityWait(notificationChecker);
		String message = notificationChecker.getText();
		explicitWait.invisibilityWait(notificationChecker);
		return message;
	}
	
	@Step("Click on New drop down button.")
	public void clickNewDropDownButton() {
		clickOnElement(newDropDownButton);
	}
	
	@Step("Select option: {0} from New drop down menu.")
	public PopUpPage selectOptionFromNewDropDown(String option) {
		String cssSelector = "li[aria-label*='{{option}}']";
		clickOnElement(driver.findElement(By.cssSelector(cssSelector.replace("{{option}}", option))));
		return PageFactory.initElements(driver, PopUpPage.class);
	}
	
	
	
	
}
