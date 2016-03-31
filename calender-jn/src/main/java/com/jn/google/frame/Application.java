package com.jn.google.frame;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jn.google.pages.Calendar;
import com.jn.google.pages.Login;

/**
 * 
 * This class takes care of initializing driver and launching the application.
 * 
 * It reads application.properties file and gets the application url to be launched
 *
 */
public class Application {

	/**
	 * Logger for logging
	 * 
	 * I'm using slf4j logger and not log4j.
	 * 
	 */
	private static Logger log = LoggerFactory.getLogger(Application.class); // pass the class name
	
	
	private static Properties appProperty;
	private static Properties frameProperty;
	
	
	public static Login launch(){
		
		// get browser name from frame.properties file
		
		String browser = getBrowserFromPropertiesFile();
		
		WebDriver driver = Driver.initialize(browser);
		
		String url = getUrlFromPropertiesFile();

		log.info("Navigating to url {}", url);
		driver.get(url); // navigate to url
		
		// Once the application is launched, you need to check if application is really opened
		Login lgn = new Login();
		
/*		if(! lgn.isPageOpened()){
			throw new RuntimeException("Application is not opened!");
		}*/
		
		log.info("Application opened successfully");
		return lgn;
	}
	
	/**
	 * Closes the application.
	 * 
	 * this makes sure that the user is logged out before closing the application
	 * 
	 */
	
	public static void close(){
		Calendar calendar = new Calendar();
		
		log.info("Logging out of application");
		
		calendar.logout();
		
		log.info("Closing driver");
		
		Driver.getDriver().close();
		
		Driver.getDriver().quit();
	}
	
	
	/**
	 * reads application.properties file and gets the url
	 * 
	 */
	private static String getUrlFromPropertiesFile(){
		
		if(appProperty == null){
			
			InputStream is = Application.class.getClassLoader().getResourceAsStream("application.properties");
			try {
				appProperty = new Properties();
				appProperty.load(is);
				is.close();
			} catch (IOException e) {
				throw new RuntimeException("IO error occurred while loading application.properties");
			}
		}
		
		return appProperty.getProperty("application.url");
		
	}
	

	/**
	 * reads application.properties file and gets the username
	 * 
	 */
	public static String getUserNameFromPropertiesFile(){
		
		if(appProperty == null){
			
			InputStream is = Application.class.getClassLoader().getResourceAsStream("application.properties");
			try {
				appProperty = new Properties();
				appProperty.load(is);
				is.close();
			} catch (IOException e) {
				throw new RuntimeException("IO error occurred while loading application.properties");
			}
		}
		
		return appProperty.getProperty("login.username");
		
	}
	
	/**
	 * reads application.properties file and gets the password
	 * 
	 */
	public static String getPasswordFromPropertiesFile(){
		
		if(appProperty == null){
			
			InputStream is = Application.class.getClassLoader().getResourceAsStream("application.properties");
			try {
				appProperty = new Properties();
				appProperty.load(is);
				is.close();
			} catch (IOException e) {
				throw new RuntimeException("IO error occurred while loading application.properties");
			}
		}
		
		return appProperty.getProperty("login.password");
		
	}
	
	/**
	 * reads application.properties file and gets the browser name
	 * 
	 */
	private static String getBrowserFromPropertiesFile(){
		
		if(frameProperty == null){
			
			InputStream is = Application.class.getClassLoader().getResourceAsStream("frame.properties");
			try {
				frameProperty = new Properties();
				frameProperty.load(is);
				is.close();
			} catch (IOException e) {
				throw new RuntimeException("IO error occurred while loading frame.properties");
			}
		}
		
		return frameProperty.getProperty("driver.browser.name");
		
	}
	
}
