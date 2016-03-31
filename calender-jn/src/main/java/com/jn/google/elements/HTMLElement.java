package com.jn.google.elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jn.google.frame.Driver;

/**
 * 
 * Wrapper around WebElement
 * 
 * This makes your life easier. When you want to create an object for this class, you have to pass the locatorKey
 * string defined in OR.properties and WebDriver object
 * 
 * locators in OR.properties will be defined in below format
 * element=<by>::<locator>
 * 
 * examples:
 * home.username.textfield=id::usernameTxt
 * home.password.textfield=id::passwordTxt
 * home.login.button=css::a[class='loginBtn']
 * 
 * 'by' part determines how to find the element in the page, either by id or css or xpath..etc
 * locator part is the actual locator to be used
 * 
 * Based on locatorKey it will get the appropriate locator string from OR.properties and creates the webelement
 * 
 * It reads  OR.properties from classpath, so make sure to keep your OR.properties in classpath
 *
 */
public class HTMLElement implements WebElement{

	private String locatorKey;
	private WebElement webElement;
	private By by;

	public HTMLElement(String locatorKey){
		this.locatorKey  = locatorKey;
	}

	public HTMLElement(WebElement webElement){
		this.webElement  = webElement;
	}
	
	@Override
	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
		return null;
	}


	@Override
	public Rectangle getRect() {
		return null;
	}

	/**
	 *  gets the locator string from OR.properties
	 *  This will be used only inside this class so marked it as private
	 */	
	private String getLocatorFromProperties(){

		Properties orProperty = new Properties();

		// Reads OR.properties file into input stream
		InputStream is = HTMLElement.class.getClassLoader().getResourceAsStream("OR.properties");

		// loads OR.properties from input stream to orProperty object
		try {
			orProperty.load(is);
			is.close();
		} catch (IOException e) {
			throw new RuntimeException("IO error occurred while loading OR.properties");
		}

		// get the locator string
		String orLocatorString = orProperty.getProperty(locatorKey);

		// If orLocatorString is null, it means there is no property by name locatorKey
		if(locatorKey == null){
			throw new RuntimeException("No property found for key " + locatorKey);
		}

		// return the locator string from OR.properties based on key
		return orLocatorString;

	}

	/**
	 * parses locator string in OR.properties and returns By object
	 * 
	 * locators in OR.properties will be defined in below format
	 * element=<by>::<locator>
	 * 
	 * examples:
	 * home.username.textfield=id::usernameTxt
	 * home.password.textfield=id::passwordTxt
	 * home.login.button=css::a[class='loginBtn']
	 * 
	 * by part determines how to find the element in the page, either by id or css or xpath..etc
	 * locator part the actual locator to be used
	 * 
	 * This will be used locally so marked it as private
	 * 
	 */
	private By getBy(){

		// check if By object is already created 
		// else create it newly
		// This verification save use from creating By object again and again
		// Once By object is created for any particular HTML object, it will not be created again
		// This will save us from reading OR.properties file every time we perform some action on HTML element

		if(by == null){

			String orLocatorString = getLocatorFromProperties();

			// now split the string to get 'by' and 'locator'

			if(!orLocatorString.contains("::")){
				throw new RuntimeException("Invalid value defined for key " + locatorKey);
			}

			int index = orLocatorString.indexOf("::");


			// First determine the 'by' strategy 
			String byString = orLocatorString.substring(0, index).toLowerCase();

			// Next get the actual locator
			String actualLocator = orLocatorString.substring(index+2);

			// Now create By object based on 'by' strategy

			switch (byString) {

			/* BY - CSS */
			case "css":
				by = By.cssSelector(actualLocator);
				break;

				/* BY - XPATH */
			case "xpath":
				by = By.xpath(actualLocator);
				break;

				/* BY - ID */
			case "id":
				by = By.id(actualLocator);
				break;

				// you can define other strategies here
				// ...
				// ...	

			default:
				throw new RuntimeException("Unknown 'by' strategy " + byString);
			}
		}


		return by;
	}

	/**
	 * Creates WebElement object and returns to calling function.
	 * 
	 */
	public WebElement getEl(){

		WebElement element =  null;
		
		if(locatorKey!=null){

			// Get By object first
			By by = getBy();

			// Create WebElement
			element = Driver.getDriver().findElement(by);

		}else{
			
			element = this.webElement ;
		
		}
		
		// return it
		return element;
	}

	@Override
	public void click(){
		getEl().click();
	}

	@Override
	public void submit() {
		getEl().submit();
	}

	@Override
	public void sendKeys(CharSequence... keysToSend) {
		getEl().sendKeys(keysToSend);
	}

	@Override
	public void clear() {
		getEl().clear();
	}

	@Override
	public String getTagName() {
		return getEl().getTagName();
	}

	@Override
	public String getAttribute(String name) {
		return getEl().getAttribute(name);
	}

	@Override
	public boolean isSelected() {
		return getEl().isSelected();
	}

	@Override
	public boolean isEnabled() {
		return getEl().isEnabled();
	}

	@Override
	public String getText() {
		return getEl().getText();
	}

	@Override
	public List<WebElement> findElements(By by) {
		return getEl().findElements(by);
	}

	@Override
	public WebElement findElement(By by) {
		return getEl().findElement(by);
	}

	@Override
	public boolean isDisplayed() {

		try{
			return getEl().isDisplayed();

		}catch(NoSuchElementException | StaleElementReferenceException e){
			return false;
		}

	}

	@Override
	public Point getLocation() {
		return getEl().getLocation();
	}

	@Override
	public Dimension getSize() {
		return getEl().getSize();
	}

	@Override
	public String getCssValue(String propertyName) {
		return getEl().getCssValue(propertyName);
	}

	public void mouseover(){

		Actions actions = new Actions(Driver.getDriver());
		WebElement subLink = getEl();
		actions.moveToElement(subLink);
		actions.perform();

	}

	static public class HTMLSelect extends HTMLElement{

		public HTMLSelect(String selector){
			super(selector);
		}

		private Select getSelect(){

			Select sel = new Select(getEl());

			return sel;
		}

		public void selectByValue(String arg0){
			getSelect().selectByValue(arg0);
		}

		public void selectByVisibleText(String arg0){
			getSelect().selectByVisibleText(arg0);
		}

		public void selectByIndex(int arg0){
			getSelect().selectByIndex(arg0);
		}

		public String getSelectedText() {

			return getSelect().getFirstSelectedOption().getText();

		}

		public List<WebElement> getOptionsElements() {

			return getSelect().getOptions();

		}

		public List<String> getOptions() {

			List<String> options = new ArrayList<String>();

			for(WebElement el : getOptionsElements()){
				options.add(el.getText());
			}

			return options;

		}

	}

	static public class HTMLCheckbox extends HTMLElement{

		public HTMLCheckbox(String selector){
			super(selector);
		}

		public void check(){			
			if(!isChecked()){
				toggle();
			}			
		}

		public void uncheck(){
			if(isChecked()){
				toggle();
			}
		}

		public void toggle(){			
			getEl().click();			
		}

		public boolean isChecked(){
			return getEl().isSelected();
		}

	}

	public boolean waitTillAppears(){

		long maxTimeout = 5000;

		return waitTillAppears(maxTimeout);


	}

	public boolean waitTillAppears(long timeoutInMillis){

		try{

			(new WebDriverWait(Driver.getDriver(), (timeoutInMillis/1000))).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getBy()));

			return true;

		}catch(TimeoutException e){

			return false;
		}

	}

}
