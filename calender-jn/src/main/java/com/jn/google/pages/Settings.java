package com.jn.google.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jn.google.elements.HTMLElement;
import com.jn.google.elements.HTMLElement.HTMLCheckbox;
import com.jn.google.elements.HTMLElement.HTMLSelect;

public class Settings {

	private static Logger log = LoggerFactory.getLogger(Settings.class);
	
	public HTMLElement lblTimeFormat = new HTMLElement("settings.timeformatlabel");
	public HTMLSelect cmbTimeFormat = new HTMLSelect("settings.timeformat");
	public HTMLElement lblEventDuration = new HTMLElement("settings.eventdurationlabel");
	public HTMLSelect cmbEventDuration = new HTMLSelect("settings.eventduration");
	public HTMLCheckbox chbSpeedyMeeting = new HTMLCheckbox("settings.speedymeeting");
	public HTMLElement lblWeekStartsOn = new HTMLElement("settings.weekstartsonlabel");
	public HTMLSelect cmbWeekStartsOn = new HTMLSelect("settings.weekstartson");
	public HTMLElement btnSave = new HTMLSelect("settings.savebutton");
	public HTMLSelect cmbCustomView = new HTMLSelect("settings.customview");
	
	public boolean isloaded () {
		cmbTimeFormat.waitTillAppears(10000);
		if (cmbTimeFormat.isDisplayed()) {
			log.info("Settings page is loaded");
			return true;

		}else {
			return false;
		}

	}
	
}
