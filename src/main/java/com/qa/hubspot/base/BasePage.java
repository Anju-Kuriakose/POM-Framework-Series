package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.utils.ElementUtil;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author Anju
 *
 */
public class BasePage {//basepage is responsible to create driver,has to initialize driver,properties.
	WebDriver driver;
	Properties prop;
	public ElementUtil elementUtil;
	
	//public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	//public static synchronized WebDriver getDriver() {
		//return tlDriver.get();
	//}
	
	/**
	 * this method is used to initialize the WebDriver on the basis of browser
	 * @param browserName
	 * @return driver
	 */
	//driver is initialized on the basis of browser name
	
	//public WebDriver init_driver(String browserName) 
	public WebDriver init_driver(Properties prop) 
	{
		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			//tlDriver.set(new ChromeDriver());
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			//tlDriver.set(new ChromeDriver());
			}
		else if(browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver=new SafariDriver();
			//tlDriver.set(new ChromeDriver());
			
	}
		//getDriver().manage().deleteAllCookies();
		//getDriver().manage().window().fullscreen();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//driver.get("https://app.hubspot.com/login");
		driver.get(prop.getProperty("url"));
		//getDriver().get(prop.getProperty("url"));//save this url to config.properties ,later we will change all this hard coded values
		//return getDriver();
		
		return driver; //instead of void ,it will become WebDriver as it is returning driver
		
		}
	/**
	 * this method is used to initialize the properties from config.properties file
	 * @return prop
	 */
	public Properties init_prop() {
		prop=new Properties();//it declared at the class level to make it global
		
		//whenever we need to get values from .properties class,use FileInputStream class
		try {
			FileInputStream ip=new FileInputStream(".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties");
			prop.load(ip);;
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return prop;//void is changed to Properties
	}
}
	/**
	 * this method will take the screenshot
	 */
	 
	/*public  String getScreenshot() {//this method is called only when any test case is failed
		
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}*/


