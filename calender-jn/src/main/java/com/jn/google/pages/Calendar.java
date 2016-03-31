package com.jn.google.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jn.google.elements.HTMLElement;
import com.jn.google.frame.Driver;

public class Calendar {

	private static Logger log = LoggerFactory.getLogger(Calendar.class);

	private HTMLElement btnUserInfo = new HTMLElement("calendar.userinfo");

	private HTMLElement btnSignOut = new HTMLElement("calendar.signout");

	private HTMLElement btnCreateEvent = new HTMLElement("calendar.createevent");

	private HTMLElement btnSettings = new HTMLElement("calendar.settings");

	private HTMLElement lnkSettings = new HTMLElement("calendar.settingsmenu");

	public HTMLElement btnDay = new HTMLElement("calendar.daytab");

	public HTMLElement btnWeek = new HTMLElement("calendar.weektab");

	public HTMLElement btn4Days = new HTMLElement("calendar.4days");

	public Login logout () {

		btnUserInfo.click();

		btnSignOut.click();

		log.info("clicked on signout button");

		return new Login();

	}

	public CreateEvent navigatetoCreateEventPage () {

		btnCreateEvent.click();

		log.info("clicked on create event button");

		return new CreateEvent();

	}

	public Settings navigatetoSettingsPage () {

		btnSettings.click();
		lnkSettings.click();
		return new Settings();
	}

	public boolean isloaded () {
		Commons.Wait ();
		btnCreateEvent.waitTillAppears(10000);
		if (btnCreateEvent.isDisplayed()) {
		
			log.info("Calendar page is loaded");
			
			return true;

		}else {
			return false;
		}

	}

	public static String getMiniCalendarFirstMonth () {

		WebElement firstColoumn = Driver.getDriver().findElement(By.xpath("//*[@class='dp-monthtable monthtableBody']//tr[@class='dp-days']/th[1]"));

		String sMonth = "";

		HTMLElement el= new HTMLElement(firstColoumn);

		if(el.isDisplayed()){	

			try{

				sMonth = el.getAttribute("title");
			}catch(StaleElementReferenceException ex){
				// element not visible
			}

		}

		return sMonth;

	}

	public static List<String> getTimeIntervals () {
		
		List<WebElement> lwTimeInt = Driver.getDriver().findElements(By.xpath("//*[@id='tgTable']//*[@class='tg-times-pri']//*[contains(@class,'tg-time-pri')]"));
		
		List<String> lsActualTimeInt = new ArrayList<String>();

		lwTimeInt.stream().forEach(element->{
			
			HTMLElement el= new HTMLElement(element);
			
			if(el.isDisplayed()){	
				
				try{
					
					lsActualTimeInt.add(el.getText());
				}catch(StaleElementReferenceException ex){
					// element not visible
				}
				
			}
		
		});

		return lsActualTimeInt;
	}
	
}
