package com.jn.google.pages;

import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.jn.google.elements.HTMLElement;
import com.jn.google.elements.HTMLElement.HTMLCheckbox;
import com.jn.google.frame.Driver;

public class CreateEvent {

	private static Logger log = LoggerFactory.getLogger(CreateEvent.class);

	public HTMLElement txtFromTime = new HTMLElement("createevent.fromtime");
	public HTMLElement txtUntilTime = new HTMLElement("createevent.untiltime");
	public HTMLCheckbox chbAllday = new HTMLCheckbox("createevent.allday");
	public HTMLElement lnkFindATime = new HTMLElement("createevent.findatime");
	public HTMLElement btnDay = new HTMLElement("createevent.daytab");
	public HTMLElement btnWeek = new HTMLElement("createevent.weektab");

	public boolean isloaded () {
		txtFromTime.waitTillAppears(10000);
		if (txtFromTime.isDisplayed()) {
			log.info("Create Event page is loaded");
			return true;

		}else {
			return false;
		}

	}
	
	public static List<String> getTimeIntervals () {

		List<WebElement> lwTimeInt = Driver.getDriver().findElements(By.xpath("//*[@id='scTgTable']//*[contains(@class,'tg-times-pri')]//*[contains(@class,'tg-time-pri')]"));

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
