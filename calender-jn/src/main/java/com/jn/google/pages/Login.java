package com.jn.google.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jn.google.elements.HTMLElement;
import com.jn.google.frame.Application;

public class Login {
	
	private static Logger log = LoggerFactory.getLogger(Login.class);
		
	//WebDriver d = Driver.getDriver();
	
	private HTMLElement txtEmail = new HTMLElement("login.email");
	
	private HTMLElement btnNext = new HTMLElement("login.next");

	private HTMLElement txtPassword = new HTMLElement("login.password");

	private HTMLElement btnLogin = new HTMLElement("login.signin");
	
	public Calendar loginSuccess(String email, String  password){
		
		txtEmail.sendKeys(email);
		log.info("entered email : "+email);
		btnNext.click();
		txtPassword.sendKeys(password);
		btnLogin.click();
		
		return new Calendar();
		
	}
	
	public Calendar loginAsAdmin(){
		
		String username = Application.getUserNameFromPropertiesFile();
		String password = Application.getPasswordFromPropertiesFile();
		
		return loginSuccess(username, password);
		
	}
	
	public boolean isloaded () {
		txtEmail.waitTillAppears(10000);
		if (txtEmail.isDisplayed()) {
			log.info("Login page is loaded");
			return true;

		}else {
			return false;
		}

	}
	
}
