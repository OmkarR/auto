package com.jn.google.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.jn.google.frame.Driver;

public class Commons {

	// [start] Constants
	
	public static final String sTimeFormat12Hr = "1:00pm";
	public static final String sTimeFormat24Hr = "13:00";
	public static final String sTimeFormat_Label = "Time format:";
	public static final String sWeekStartsOn_Label = "Week starts on:";
	public static final String sDefaultEventDuration = "Default event duration:";
	
	public static final List<String> lsTimeFormatOptions = Arrays.asList(sTimeFormat12Hr, sTimeFormat24Hr);
	
	public static final String sSunday = "Sunday";
	public static final String sMonday = "Monday";
	public static final String sSaturday = "Saturday";
	
	public static final List<String> lsWeekStartOnOptions = Arrays.asList(sSunday, sMonday, sSaturday);
	
	public static final List<String> lsExpTimeFormat12Hr = Arrays.asList("12am", "1am", "2am", "3am", "4am",
			"5am", "6am", "7am", "8am", "9am", "10am", "11am", "12pm", "1pm", "2pm", "3pm", "4pm", "5pm",
			"6pm", "7pm", "8pm", "9pm", "10pm", "11pm");
	
	public static final List<String> lsActualTimeFormat24Hr = Arrays.asList("00:00", "01:00", "02:00", "03:00", "04:00",
			"05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
			"18:00", "19:00", "20:00", "21:00", "22:00", "23:00");
	
	public static final List<String> lsDurationOptions = Arrays.asList ("No end time","15 minutes","30 minutes",
			"60 minutes","90 minutes","120 minutes");
	
	// [end]
	
	// [start] Methods
	
	public static String getFirstDayColumnName () {
		
		WebElement firstDay = Driver.getDriver().findElement(By.xpath("//*[@id='topcontainerwk']//*[@class='wk-daynames']//th[1]"));
		
		return firstDay.getAttribute("title");
		
	}
	
	public static void Wait () {
		
		Wait (3000);
		
	}
	
	public static void Wait (long milliseconds) {
		
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			
		}
		
	}
	
	// [end]
}
