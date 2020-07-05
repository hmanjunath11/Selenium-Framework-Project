package tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.HomePage;
import pages.LogInPage;
import pages.LogoutPage;
import pages.PopUpPage;
import pages.UserDashBoardPage;
import ru.yandex.qatools.allure.annotations.Description;

public class FolderBasedTest extends TestBase {
	
	HomePage homePage;
	LogInPage loginPage;
	UserDashBoardPage userDashBoardPage;
	PopUpPage popUpPage;
	LogoutPage logOutPage;
	
	@BeforeMethod()
	public void testSetUp() {
		homePage = PageFactory.initElements(driver, HomePage.class);
		loginPage = homePage.clickLogin();
		loginPage.enterUsername(System.getProperty("userEmail"));
		loginPage.nextOrSubmit();
		loginPage.enterPassword(System.getProperty("userPassword"));
		userDashBoardPage = loginPage.nextOrSubmit();
	}
	
	@Test
	@Description("To check whether we can create folder.")
	public void createFolderTest() {
		if(userDashBoardPage.isFolderPresent()) {
			userDashBoardPage.selectFolder();
			assertEquals(userDashBoardPage.isFolderSelected(), true);
			popUpPage = userDashBoardPage.clickOnActionAfterSelectingFolder("Trash");
			assertEquals(popUpPage.isDeletePopUpWindow(), true);
			userDashBoardPage = popUpPage.confirmDeletion();
			assertEquals(userDashBoardPage.getNotificationText(), "Item successfully moved to trash.");
		}
		userDashBoardPage.clickNewDropDownButton();
		popUpPage = userDashBoardPage.selectOptionFromNewDropDown("Folder");
		assertEquals(popUpPage.isCreateFolderWindow(), true);
		popUpPage.enterFolderNameToBeCreate("TEST");
		popUpPage.confirmFolderCreation();
		assertEquals(userDashBoardPage.getNotificationText(), "\"TEST\" was created successfully.");
		assertEquals(userDashBoardPage.isFolderPresent(), true);
	}
	
	@Test
	@Description("To check whether we can delete folder.")
	public void deleteFolderTest() {
		if(!userDashBoardPage.isFolderPresent()) {
			userDashBoardPage.clickNewDropDownButton();
			popUpPage = userDashBoardPage.selectOptionFromNewDropDown("Folder");
			assertEquals(popUpPage.isCreateFolderWindow(), true);
			popUpPage.enterFolderNameToBeCreate("TEST");
			popUpPage.confirmFolderCreation();
			assertEquals(userDashBoardPage.getNotificationText(), "\"TEST\" was created successfully.");
			assertEquals(userDashBoardPage.isFolderPresent(), true);
		}
		userDashBoardPage.selectFolder();
		assertEquals(userDashBoardPage.isFolderSelected(), true);
		popUpPage = userDashBoardPage.clickOnActionAfterSelectingFolder("Trash");
		assertEquals(popUpPage.isDeletePopUpWindow(), true);
		userDashBoardPage = popUpPage.confirmDeletion();
		assertEquals(userDashBoardPage.getNotificationText(), "Item successfully moved to trash.");
	}
	
	@AfterMethod
	public void testTearDown() {
		logOutPage = userDashBoardPage.performLogout();
	}

}
