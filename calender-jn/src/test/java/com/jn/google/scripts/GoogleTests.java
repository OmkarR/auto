package com.jn.google.scripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.jn.google.frame.Application;
import com.jn.google.pages.Calendar;
import com.jn.google.pages.Commons;
import com.jn.google.pages.CreateEvent;
import com.jn.google.pages.Login;
import com.jn.google.pages.Settings;

public class GoogleTests {

	@Test
	public void AUTO_TC_CTF_001(){

		// Manual Testcases : TC_CTF_001, TC_CTF_002, TC_CTF_003, TC_CTF_004, TC_CTF_005, 
		//                    TC_CTF_006, TC_CTF_007, TC_CTF_008, TC_CTF_009
		
		// [start] TC_CTF_001
		
		Login lgn = Application.launch();

		Calendar calendar = lgn.loginAsAdmin();

		assertTrue(calendar.isloaded(),"calendar page is displayed");

		Settings settings = calendar.navigatetoSettingsPage();

		assertTrue(settings.isloaded(),"settings page is displayed");

		assertEquals(settings.lblTimeFormat.getText(), Commons.sTimeFormat_Label, "Time format label is displayed as expected");

		assertEquals(settings.cmbTimeFormat.getOptions(),Commons.lsTimeFormatOptions, "options are displayed as expected");
	
		// [end]

		// [start] TC_CTF_002
		
		settings.cmbTimeFormat.selectByVisibleText(Commons.sTimeFormat12Hr);

		settings.cmbCustomView.selectByVisibleText("4 Days");

		settings.btnSave.click();

		calendar.btnDay.waitTillAppears();
		
		calendar.btnDay.click();
		
		List<String> lsTimeIntervals = Calendar.getTimeIntervals();
		
		assertTrue (lsTimeIntervals.size()>0,"list is not empty");
		
		for(String interval : lsTimeIntervals){			
			
			assertTrue(interval.contains("am") || interval.contains("pm"), "time is displayed as am/pm format");	
			
			assertFalse (interval.contains(":"), "time is displayed as am/pm format");
		}

		// [end]

		// [start] TC_CTF_003
		
		calendar.btnWeek.waitTillAppears();
		
		calendar.btnWeek.click();

		lsTimeIntervals = Calendar.getTimeIntervals();
		
		assertTrue (lsTimeIntervals.size()>0,"list is not empty");
		
		for(String interval : lsTimeIntervals){			
			
			assertTrue(interval.contains("am") || interval.contains("pm"), "time is displayed as am/pm format");	
			
			assertFalse (interval.contains(":"), "time is displayed as am/pm format");
		}						
		
		// [end]

		// [start] TC_CTF_004
		
		calendar.btn4Days.waitTillAppears();
		
		calendar.btn4Days.click();

		lsTimeIntervals = Calendar.getTimeIntervals();
		
		assertTrue (lsTimeIntervals.size()>0,"list is not empty");
		
		for(String interval : lsTimeIntervals){			
			
			assertTrue(interval.contains("am") || interval.contains("pm"), "time is displayed as am/pm format");	
			
			assertFalse (interval.contains(":"), "time is displayed as am/pm format");
		}									
		
		// [end]
		
		// [start] TC_CTF_005

		CreateEvent create = calendar.navigatetoCreateEventPage();

		assertTrue (create.isloaded(), "create page s opened");

		create.chbAllday.uncheck ();

		assertTrue (create.txtFromTime.isDisplayed(), "From time is displayed");

		assertTrue (create.txtFromTime.getAttribute("value").contains("am")||create.txtFromTime.getAttribute("value").contains("pm"), "time format is displayed properly");
		
		assertFalse (create.txtFromTime.getAttribute("value").contains("am"),"time format is displayed as expected");

		assertTrue (create.txtUntilTime.isDisplayed(), "Until time is displayed");

		assertTrue (create.txtFromTime.getAttribute("value").contains("am")||create.txtFromTime.getAttribute("value").contains("pm"), "time format is displayed properly");

		// [end]

		// [start] TC_CTF_006

		create.lnkFindATime.click();

		create.btnDay.waitTillAppears();
		
		create.btnDay.click();

		lsTimeIntervals = CreateEvent.getTimeIntervals();
		
		assertTrue (lsTimeIntervals.size()>0,"list is not empty");
		
		for(String interval : lsTimeIntervals){			
			
			assertTrue(interval.contains("am") || interval.contains("pm"), "time is displayed as am/pm format");	
			
			assertFalse (interval.contains(":"), "time is displayed as am/pm format");
		}

		// [end]

		// [start] TC_CTF_007

		create.btnWeek.waitTillAppears();
		
		create.btnWeek.click();
		
		lsTimeIntervals = CreateEvent.getTimeIntervals();
		
		assertTrue (lsTimeIntervals.size()>0,"list is not empty");
		
		for(String interval : CreateEvent.getTimeIntervals()){			
			
			assertTrue(interval.contains("am") || interval.contains("pm"), "time is displayed as am/pm format");	
			
			assertFalse (interval.contains(":"), "time is displayed as am/pm format");
		}
		
		// [end]

		// [start] TC_CTF_008

		create.chbAllday.check ();

		create.lnkFindATime.click();

		create.btnDay.waitTillAppears();
		
		create.btnDay.click();
		
		lsTimeIntervals = CreateEvent.getTimeIntervals();
		
		assertTrue (lsTimeIntervals.size()>0,"list is not empty");

		for(String interval : CreateEvent.getTimeIntervals()){			
			
			assertTrue(interval.contains("am") || interval.contains("pm"), "time is displayed as am/pm format");	
			
			assertFalse (interval.contains(":"), "time is displayed as am/pm format");
		}
		
		// [end]

		// [start] TC_CTF_009

		create.btnWeek.waitTillAppears();
		
		create.btnWeek.click();

		lsTimeIntervals = CreateEvent.getTimeIntervals();
		
		assertTrue (lsTimeIntervals.size()>0,"list is not empty");
		
		for(String interval : CreateEvent.getTimeIntervals()){			
			
			assertTrue(interval.contains("am") || interval.contains("pm"), "time si displayed as am/pm format");	
			
			assertFalse (interval.contains(":"), "time si displayed as am/pm format");
		}

		// [end]

	}

	@Test
	public void AUTO_TC_CTF_010(){

		// Manual Testcases : TC_CTF_010, TC_CTF_011, TC_CTF_012, TC_CTF_013, TC_CTF_014, 
		//                    TC_CTF_015, TC_CTF_016, TC_CTF_017
		
		// [start] TC_CTF_010

		Login lgn = Application.launch();

		Calendar calendar = lgn.loginAsAdmin();

		assertTrue(calendar.isloaded(),"calendar page is displayed");

		Settings settings = calendar.navigatetoSettingsPage();

		assertTrue(settings.isloaded(),"settings page is displayed");

		settings.cmbTimeFormat.selectByVisibleText(Commons.sTimeFormat24Hr);

		settings.cmbCustomView.selectByVisibleText("4 Days");

		settings.btnSave.click();

		calendar.btnDay.click();

		List<String> lsTimeIntervals = Calendar.getTimeIntervals();
		
		assertTrue (lsTimeIntervals.size()>0,"list is not empty");
		
		for(String interval : lsTimeIntervals){			
			
			assertFalse(interval.contains("am") || interval.contains("pm"), "time is displayed as am/pm format");	
			
			assertTrue (interval.contains(":"), "time is displayed as am/pm format");
		}		

		// [end] 

		// [start] TC_CTF_011

		calendar.btnWeek.waitTillAppears();
		
		calendar.btnWeek.click();

		lsTimeIntervals = Calendar.getTimeIntervals();
		
		assertTrue (lsTimeIntervals.size()>0,"list is not empty");
		
		for(String interval : lsTimeIntervals){			
			
			assertFalse(interval.contains("am") || interval.contains("pm"), "time is displayed as am/pm format");	
			
			assertTrue (interval.contains(":"), "time is displayed as am/pm format");
		}				

		// [end]

		// [start] TC_CTF_012

		calendar.btnDay.waitTillAppears();
		
		calendar.btn4Days.click();

		lsTimeIntervals = Calendar.getTimeIntervals();
		
		assertTrue (lsTimeIntervals.size()>0,"list is not empty");
		
		for(String interval : lsTimeIntervals){			
			
			assertFalse(interval.contains("am") || interval.contains("pm"), "time is displayed as am/pm format");	
			
			assertTrue (interval.contains(":"), "time is displayed as am/pm format");
		}				

		// [end]

		// [start] TC_CTF_013

		CreateEvent create = calendar.navigatetoCreateEventPage();

		assertTrue (create.isloaded(), "create page s opened");

		create.chbAllday.uncheck ();

		assertTrue (create.txtFromTime.isDisplayed(), "From time is displayed");

		assertFalse (create.txtFromTime.getAttribute("value").contains("am")||create.txtFromTime.getAttribute("value").contains("pm"), "time format is displayed properly");

		assertTrue (create.txtUntilTime.isDisplayed(), "Until time is displayed");

		assertFalse (create.txtFromTime.getAttribute("value").contains("am")||create.txtFromTime.getAttribute("value").contains("pm"), "time format is displayed properly");

		// [end]

		// [start] TC_CTF_014

		create.lnkFindATime.click();

		create.btnDay.click();

		lsTimeIntervals = CreateEvent.getTimeIntervals();
		
		assertTrue (lsTimeIntervals.size()>0,"list is not empty");
		
		for(String interval : lsTimeIntervals){			
			
			assertFalse(interval.contains("am") || interval.contains("pm"), "time is displayed as am/pm format");	
			
			assertTrue (interval.contains(":"), "time is displayed as am/pm format");
		}				

		// [end]

		// [start] TC_CTF_015

		create.lnkFindATime.click();

		create.btnWeek.click();

		lsTimeIntervals = CreateEvent.getTimeIntervals();
		
		assertTrue (lsTimeIntervals.size()>0,"list is not empty");
		
		for(String interval : lsTimeIntervals){			
			
			assertFalse(interval.contains("am") || interval.contains("pm"), "time is displayed as am/pm format");	
			
			assertTrue (interval.contains(":"), "time is displayed as am/pm format");
		}				

		// [end]

		// [start] TC_CTF_016

		create.chbAllday.check ();

		create.lnkFindATime.click();

		create.btnDay.click();

		lsTimeIntervals = CreateEvent.getTimeIntervals();
		
		assertTrue (lsTimeIntervals.size()>0,"list is not empty");
		
		for(String interval : lsTimeIntervals){			
			
			assertFalse(interval.contains("am") || interval.contains("pm"), "time is displayed as am/pm format");	
			
			assertTrue (interval.contains(":"), "time is displayed as am/pm format");
		}				

		// [end]

		// [start] TC_CTF_017

		create.lnkFindATime.click();

		create.btnWeek.click();

		lsTimeIntervals = CreateEvent.getTimeIntervals();
		
		assertTrue (lsTimeIntervals.size()>0,"list is not empty");
		
		for(String interval : lsTimeIntervals){			
			
			assertFalse(interval.contains("am") || interval.contains("pm"), "time is displayed as am/pm format");	
			
			assertTrue (interval.contains(":"), "time is displayed as am/pm format");
		}				

		// [end]

	}

	@Test
	public void AUTO_TC_WSO_001(){

		// Manual Testcases : TC_WSO_001, TC_WSO_002, TC_WSO_003, TC_WSO_004, TC_WSO_005 
		
		// [start] TC_WSO_001

		Login lgn = Application.launch();

		Calendar calendar = lgn.loginAsAdmin();

		assertTrue(calendar.isloaded(),"calendar page is displayed");

		Settings settings = calendar.navigatetoSettingsPage();

		assertTrue(settings.isloaded(),"settings page is displayed");

		assertEquals(settings.lblWeekStartsOn.getText(), Commons.sWeekStartsOn_Label, "Week Starts On label is displayed as expected");

		assertEquals(settings.cmbWeekStartsOn.getOptions(),Commons.lsWeekStartOnOptions, "options are displayed as expected");

		// [end]

		// [start] TC_WSO_002

		settings.cmbWeekStartsOn.selectByVisibleText(Commons.sSunday);

		settings.btnSave.click();

		assertEquals(Calendar.getMiniCalendarFirstMonth(), Commons.sSunday,"First coloumn is displayed as expected");

		// [end]

		// [start] TC_WSO_003

		calendar.btnWeek.click();

		assertTrue(Commons.getFirstDayColumnName ().contains("Sun"), "First coloumn is displayed as expected");

		// [end]

		// [start] TC_WSO_004

		CreateEvent create = calendar.navigatetoCreateEventPage();

		assertTrue (create.isloaded(),"create event page is opened");

		create.chbAllday.uncheck();

		create.lnkFindATime.click();

		create.btnWeek.click();

		assertTrue(Commons.getFirstDayColumnName ().contains("Sun"), "First coloumn is displayed as expected");

		// [end]

		// [start] TC_WSO_005

		create.chbAllday.check();

		create.lnkFindATime.click();

		create.btnWeek.click();

		assertTrue(Commons.getFirstDayColumnName ().contains("Sun"), "First coloumn is displayed as expected");

		// [end]

	}

	@Test
	public void AUTO_TC_WSO_006(){

		// Manual Testcases : TC_WSO_006, TC_WSO_007, TC_WSO_008, TC_WSO_009
		
		// [start] TC_WSO_006

		Login lgn = Application.launch();

		Calendar calendar = lgn.loginAsAdmin();

		assertTrue(calendar.isloaded(),"calendar page is displayed");

		Settings settings = calendar.navigatetoSettingsPage();

		assertTrue(settings.isloaded(),"settings page is displayed");

		settings.cmbWeekStartsOn.selectByVisibleText(Commons.sMonday);

		settings.btnSave.click();

		assertEquals(Calendar.getMiniCalendarFirstMonth(), Commons.sMonday,"First coloumn is displayed as expected");

		// [end]

		// [start] TC_WSO_007

		calendar.btnWeek.click();

		assertTrue(Commons.getFirstDayColumnName ().contains("Mon"), "First coloumn is displayed as expected");

		// [end]

		// [start] TC_WSO_008

		CreateEvent create = calendar.navigatetoCreateEventPage();

		assertTrue (create.isloaded(),"create event page is opened");

		create.chbAllday.uncheck();

		create.lnkFindATime.click();

		create.btnWeek.click();

		assertTrue(Commons.getFirstDayColumnName ().contains("Mon"), "First coloumn is displayed as expected");

		// [end]

		// [start] TC_WSO_009

		create.chbAllday.check();

		create.lnkFindATime.click();

		create.btnWeek.click();

		assertTrue(Commons.getFirstDayColumnName ().contains("Mon"), "First coloumn is displayed as expected");

		// [end]

	}

	@Test
	public void AUTO_TC_WSO_010(){

		// Manual Testcases : TC_WSO_010, TC_WSO_011, TC_WSO_012, TC_WSO_013
		
		// [start] TC_WSO_010

		Login lgn = Application.launch();

		Calendar calendar = lgn.loginAsAdmin();

		assertTrue(calendar.isloaded(),"calendar page is displayed");

		Settings settings = calendar.navigatetoSettingsPage();

		assertTrue(settings.isloaded(),"settings page is displayed");

		settings.cmbWeekStartsOn.selectByVisibleText(Commons.sSaturday);

		settings.btnSave.click();

		assertEquals(Calendar.getMiniCalendarFirstMonth(), Commons.sSaturday,"First coloumn is displayed as expected");

		// [end]

		// [start] TC_WSO_011

		calendar.btnWeek.click();

		assertTrue(Commons.getFirstDayColumnName ().contains("Sat"), "First coloumn is displayed as expected");

		// [end]

		// [start] TC_WSO_012

		CreateEvent create = calendar.navigatetoCreateEventPage();

		assertTrue (create.isloaded(),"create event page is opened");

		create.chbAllday.uncheck();

		create.lnkFindATime.click();

		create.btnWeek.click();

		assertTrue(Commons.getFirstDayColumnName ().contains("Sat"), "First coloumn is displayed as expected");

		// [end]

		// [start] TC_WSO_013

		create.chbAllday.check();

		create.lnkFindATime.click();

		create.btnWeek.click();

		assertTrue(Commons.getFirstDayColumnName ().contains("Sat"), "First coloumn is displayed as expected");

		// [end]
	}

	@Test
	public void AUTO_TC_DSM_001(){

		// Manual Testcases : TC_DSM_001, TC_DSM_002, TC_DSM_003
		
		// [start] TC_DSM_001
		
		Login lgn = Application.launch();

		Calendar calendar = lgn.loginAsAdmin();

		assertTrue(calendar.isloaded(),"calendar page is displayed");

		Settings settings = calendar.navigatetoSettingsPage();

		assertTrue(settings.isloaded(),"settings page is displayed");

		assertEquals (settings.lblEventDuration.getText(), Commons.sDefaultEventDuration, "Event duration label is displayed as expected");
		
		assertTrue(settings.chbSpeedyMeeting.isDisplayed(), "speedy meeting checkbox is displayed");

		// [end]
		
		// [start] TC_DSM_002

		if (settings.chbSpeedyMeeting.isChecked()) {

			settings.chbSpeedyMeeting.uncheck();

		}

		settings.cmbEventDuration.selectByVisibleText("60 minutes");

		settings.chbSpeedyMeeting.click();

		assertEquals (settings.cmbEventDuration.getSelectedText(),"50 minutes", "value is changed when speedy meeting is checked");

		String sFormat = settings.cmbTimeFormat.getSelectedText();

		settings.btnSave.click();

		// [end]
		
		// [start] TC_DSM_003

		CreateEvent create = calendar.navigatetoCreateEventPage();

		assertTrue (create.isloaded(),"create event page is opened");

		create.chbAllday.uncheck();

		Date d1 = null;
		Date d2 = null;
		if (sFormat.equals(Commons.sTimeFormat12Hr)) {
			SimpleDateFormat format = new SimpleDateFormat("hh:mma"); 
			try {
				d1 = format.parse(create.txtFromTime.getAttribute("value"));
				d2 = format.parse(create.txtUntilTime.getAttribute("value"));
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

		else if (sFormat.equals(Commons.sTimeFormat24Hr)) {
			SimpleDateFormat format = new SimpleDateFormat("HH:mm"); 
			try {
				d1 = format.parse(create.txtFromTime.getAttribute("value"));
				d2 = format.parse(create.txtUntilTime.getAttribute("value"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		assertEquals (Math.abs(d1.getTime() - d2.getTime())/ 1000/60, 50,"time difference is as expected");

	}

	@Test
	public void AUTO_TC_DSM_004(){

		// Manual Testcases : TC_DSM_004, TC_DSM_005
		
		// [start] TC_DSM_004
		
		Login lgn = Application.launch();

		Calendar calendar = lgn.loginAsAdmin();

		assertTrue(calendar.isloaded(),"calendar page is displayed");

		Settings settings = calendar.navigatetoSettingsPage();

		assertTrue(settings.isloaded(),"settings page is displayed");

		assertEquals (settings.lblEventDuration.getText(), Commons.sDefaultEventDuration, "Event duration label is displayed as expected");
		
		assertTrue(settings.chbSpeedyMeeting.isDisplayed(), "speedy meeting checkbox is displayed");

		if (settings.chbSpeedyMeeting.isChecked()) {

			settings.chbSpeedyMeeting.uncheck();

		}
		settings.cmbTimeFormat.selectByVisibleText(Commons.sTimeFormat24Hr);

		settings.cmbEventDuration.selectByVisibleText("30 minutes");

		settings.chbSpeedyMeeting.click();

		assertEquals (settings.cmbEventDuration.getSelectedText(),"25 minutes", "value is changed when speedy meeting is checked");

		String sFormat = settings.cmbTimeFormat.getSelectedText();

		settings.btnSave.click();

		// [end]
		
		// [start] TC_DSM_005

		CreateEvent create = calendar.navigatetoCreateEventPage();

		assertTrue (create.isloaded(),"create event page is opened");

		create.chbAllday.uncheck();

		Date d1 = null;
		Date d2 = null;
		if (sFormat.equals(Commons.sTimeFormat12Hr)) {
			SimpleDateFormat format = new SimpleDateFormat("hh:mma"); 
			try {
				d1 = format.parse(create.txtFromTime.getAttribute("value"));
				d2 = format.parse(create.txtUntilTime.getAttribute("value"));
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

		else if (sFormat.equals(Commons.sTimeFormat24Hr)) {
			SimpleDateFormat format = new SimpleDateFormat("HH:mm"); 
			try {
				d1 = format.parse(create.txtFromTime.getAttribute("value"));
				d2 = format.parse(create.txtUntilTime.getAttribute("value"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		assertEquals (Math.abs(d1.getTime() - d2.getTime())/ 1000/60, 25,"time difference is as expected");

	}
	
	@Test
	public void AUTO_TC_DED_001(){

		// Manual Testcases : TC_DED_001, TC_DED_002
		
		// [start] TC_DED_001
		
		Login lgn = Application.launch();

		Calendar calendar = lgn.loginAsAdmin();

		assertTrue(calendar.isloaded(),"calendar page is displayed");

		Settings settings = calendar.navigatetoSettingsPage();

		assertTrue(settings.isloaded(),"settings page is displayed");
		
		if (settings.chbSpeedyMeeting.isChecked()) {
			
			settings.chbSpeedyMeeting.uncheck();
			
		}

		assertEquals (settings.lblEventDuration.getText(), Commons.sDefaultEventDuration, "Event duration label is displayed as expected");
		
		assertEquals(settings.cmbEventDuration.getOptions(), Commons.lsDurationOptions, "options are displayed as expected");

		// [end]
		
		// [start] TC_DED_002

		if (settings.chbSpeedyMeeting.isChecked()) {

			settings.chbSpeedyMeeting.uncheck();

		}

		settings.cmbEventDuration.selectByVisibleText("90 minutes");

		String sFormat = settings.cmbTimeFormat.getSelectedText();

		settings.btnSave.click();

		CreateEvent create = calendar.navigatetoCreateEventPage();

		assertTrue (create.isloaded(),"create event page is opened");

		create.chbAllday.uncheck();

		Date d1 = null;
		Date d2 = null;
		if (sFormat.equals(Commons.sTimeFormat12Hr)) {
			SimpleDateFormat format = new SimpleDateFormat("hh:mma"); 
			try {
				d1 = format.parse(create.txtFromTime.getAttribute("value"));
				d2 = format.parse(create.txtUntilTime.getAttribute("value"));
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

		else if (sFormat.equals(Commons.sTimeFormat24Hr)) {
			SimpleDateFormat format = new SimpleDateFormat("HH:mm"); 
			try {
				d1 = format.parse(create.txtFromTime.getAttribute("value"));
				d2 = format.parse(create.txtUntilTime.getAttribute("value"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		assertEquals (Math.abs(d1.getTime() - d2.getTime())/ 1000/60, 90,"duration is as expected");

	}

	
	@AfterMethod
	public void closeApplication (){
		
		Application.close();
		
	}

}
