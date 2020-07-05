package listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import base.TestBase;

public class WebEventListener extends TestBase implements WebDriverEventListener {

	@Override
	public void beforeAlertAccept(WebDriver driver) {
		System.out.println("Before accepting alert.");
	}

	@Override
	public void afterAlertAccept(WebDriver driver) {
		System.out.println("After accepting alert.");
	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		System.out.println("After dismissing alert.");
	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		System.out.println("Before dismissing alert.");
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Before navigating to url : " + url);
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		System.out.println("After navigating to url : " + url);
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		System.out.println("Before navigating back.");
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		System.out.println("After navigating back.");
	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		System.out.println("Before navigating forward.");
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		System.out.println("After navigating forward.");
	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		System.out.println("Before navigation refresh.");
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		System.out.println("After navigation refresh.");
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Before findBy element.");
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("After findby element.");
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		System.out.println("Before click on action.");
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("After click on action.");
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		System.out.println("Before changing value to : " + keysToSend);
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		System.out.println("After changing value to : " + keysToSend);
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		System.out.println("Before running the script : " + script);
	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		System.out.println("After running the script : " + script);
	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		System.out.println("Before switching window.");
	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		System.out.println("After switching window.");
	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		System.out.println("Exception happened : ");
		throwable.printStackTrace();
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		System.out.println("Before taking screenshot.");
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		System.out.println("After taking screenshot it is saved at location : " 
			+ target.toString() + " with name : " + screenshot.toString() );
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		System.out.println("Before getting text.");
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		System.out.println("After getting text : " + text);
	}

}