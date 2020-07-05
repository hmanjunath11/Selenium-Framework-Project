package base;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import models.Config;
import ru.yandex.qatools.allure.annotations.Attachment;
import util.GenericExplicitWait;
import util.YamlReader;

public class BasePage {
	protected WebDriver driver;
	protected Set<String> handles;
	protected Config config;
	protected GenericExplicitWait explicitWait;
	
	 public BasePage(WebDriver driver) {
	        this.driver = driver;
	        saveScreenshot();
	        config = new YamlReader(getFile("/config.yml").getAbsolutePath()).readConfig();
	        explicitWait = new GenericExplicitWait(driver, config.getExplicitTimeOut());
	 }
	 
	 public File getFile(String fileName) {
			File file = null;
			try {
				file = new File( TestBase.class.getResource(fileName).toURI() );
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return file;
		}

	@Attachment(value = "Page screenshot", type = "image/png")
	protected byte[] saveScreenshot() {
		driver = TestBase.driver;
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		}
	
	protected void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }
	
	protected void scrollToAndClickOnElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }
	
	public void selectFromDropdown(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }
	
	public void clickOnElement(WebElement element) {
        explicitWait.presenceWait(element);
        explicitWait.clickableWait(element);
        element.click();
    }
	
	public void clickOnElementWithoutCheckingClickability(WebElement element) {
		explicitWait.presenceWait(element);
		element.click();
	}
	
	public boolean isElementDisplayed(WebElement element) {
		try {
			explicitWait.presenceWait(element);
			return element.isDisplayed();
		}
		catch (Exception e) {
			System.out.println(element + " not displayed.");
		}
		return false;
	}
	
	public void writeTextInField(WebElement element, String text) {
        System.out.println("Typing :" + text + ", on element: " + element);
        explicitWait.presenceWait(element);
        explicitWait.clickableWait(element);
        element.click();
        element.sendKeys(text);
    }
	
	
}
