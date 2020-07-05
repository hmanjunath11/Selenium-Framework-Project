package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GenericExplicitWait {
	
	protected WebDriverWait waitElement;
	
	public GenericExplicitWait(WebDriver driver, int explicitTimeOut) {
		waitElement = new WebDriverWait(driver, explicitTimeOut);
	}
	
	public void visibilityWait(WebElement element) {
		waitElement.until(ExpectedConditions.visibilityOfElementLocated(toByVal(element)));
	}
	
	public void invisibilityWait(WebElement element) {
		waitElement.until(ExpectedConditions.invisibilityOfElementLocated(toByVal(element)));
	}
	
	public void presenceWait(WebElement element) {
		waitElement.until(ExpectedConditions.presenceOfElementLocated(toByVal(element)));
	}
	
	public void alertWait() {
		waitElement.until(ExpectedConditions.alertIsPresent());
	}
	
	public void clickableWait(WebElement element) {
		waitElement.until(ExpectedConditions.elementToBeClickable(toByVal(element)));
	}
	
	public void selectableWait(WebElement element) {
		waitElement.until(ExpectedConditions.elementToBeSelected(toByVal(element)));
	}
	
    public By toByVal(WebElement we) {
        String locator = null;
        String term = null; 
        try {
            String[] data = we.toString().split(" -> ");
            data[1] = data[1].substring(0, data[1].length()-1);
            data = data[1].split(": ");
            locator = data[0];
            term = data[1]; 
        }
        catch (Exception e) {
            String[] data = we.toString().split(":");
            data[1] = data[1].split("\\.")[1];
            data[2] = data[2].substring(1, data[2].length()-1);
            locator = data[1];
            term = data[2];
        }
        switch (locator) {
            case "xpath":
            return By.xpath(term);
            case "css selector":
            case "cssSelector":
            return By.cssSelector(term);
            case "id":
            return By.id(term);
            case "tag name":
            return By.tagName(term);
            case "name":
            return By.name(term);
            case "link text":
            return By.linkText(term);
            case "class name":
            return By.className(term);
        }
        return (By) we;
    }
}