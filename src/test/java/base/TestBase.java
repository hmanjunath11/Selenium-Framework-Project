package base;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listeners.TestListener;
import listeners.WebEventListener;
import models.Config;
import util.YamlReader;

@Listeners({TestListener.class})
public class TestBase {
	
	public static WebDriver driver;
	protected static Config config;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	static {
		config = new YamlReader(getFile("/config.yml").getAbsolutePath()).readConfig();
	}
	
	public static File getFile(String fileName) {
		File file = null;
		try {
			file = new File( TestBase.class.getResource(fileName).toURI() );
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
	
	public TestBase() {
	}
	
	@BeforeMethod
	public void setup(Method method) {
		System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Starting test: " + getClass().getSimpleName() + " --> " + method.getName());
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------------------");
        setBrowser();
        attachWebEventListner();
        setOtherDriverConfigurations(config.isMaximize(), config.isDeleteCookies(), 
        		config.getPageLoadTimeOut(), config.getImplicitTimeOut());
        driver.get("https://www.box.com");
	}
	
	@AfterMethod
	public void tearDown(Method method) {
		System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Ending test: " + getClass().getSimpleName() + " --> " + method.getName());
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------------------");
        driver.quit();
	}

	private void setOtherDriverConfigurations(boolean maximize, boolean deleteCookies, int pageLoadTimeout, int implicitTimeOut) {
		if(maximize == true)
			driver.manage().window().maximize();
		if(deleteCookies == true)
			driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(implicitTimeOut, TimeUnit.SECONDS);
	}

	private void attachWebEventListner() {
		e_driver = new EventFiringWebDriver(driver);
        eventListener = new WebEventListener();
        e_driver.register(eventListener);
        driver = e_driver;
	}

	private void setBrowser() {
		if(config.getBrowser().equals("chrome")) {
        	System.setProperty("webdriver.chrome.driver", config.getDriverPath());	
			driver = new ChromeDriver();
        }
	}

}
