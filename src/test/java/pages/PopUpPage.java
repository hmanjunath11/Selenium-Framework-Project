package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;
import ru.yandex.qatools.allure.annotations.Step;

public class PopUpPage extends BasePage {
	
	public PopUpPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//span[contains(text(),'Delete Item')]")
	WebElement deletePopUp;
	
	@FindBy(css = "div[class='modal-actions']>button[type='submit']")
	WebElement confirmDeleteButton;
	
	@FindBy(xpath = "//span[contains(text(),'Create a New Folder')]")
	WebElement createFolderPopUp;
	
	@FindBy(css = "input[name='folder-name']")
	WebElement folderNameTextBox;
	
	@Step("Check for delete pop up window.")
	public boolean isDeletePopUpWindow() {
		return isElementDisplayed(deletePopUp);
	}
	
	@Step("Take confirm action on delete window popup.")
	public UserDashBoardPage confirmDeletion() {
		clickOnElement(confirmDeleteButton);
		return PageFactory.initElements(driver, UserDashBoardPage.class);
	}
	
	@Step("Check for Create folder pop up to be displayed.")
	public boolean isCreateFolderWindow() {
		return isElementDisplayed(createFolderPopUp);
	}
	
	@Step("Enter folder name as: {0}")
	public void enterFolderNameToBeCreate(String folderName) {
		writeTextInField(folderNameTextBox, folderName);
	}
	
	@Step("Take confirm action on create folder window popup.")
	public UserDashBoardPage confirmFolderCreation() {
		clickOnElement(confirmDeleteButton);
		return PageFactory.initElements(driver, UserDashBoardPage.class);
	}
}
