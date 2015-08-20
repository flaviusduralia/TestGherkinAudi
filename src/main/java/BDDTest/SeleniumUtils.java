package BDDTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;



import cucumber.api.Scenario;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;

public class SeleniumUtils {
	
	public WebDriver driver;
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	private String seleniumGridHubUrl;
	private String browserName;
	private String[] browserNames;
	private ArrayList<String> browsersArray = new ArrayList<String>();
	
	private static final Logger log4j = LogManager.getLogger(SeleniumUtils.class.getName());
	
	// Needed to instantiate browsers
	protected DesiredCapabilities capability;
	
	// method for initialization of
	public SeleniumUtils() {
		initialize();
	}

	private void initialize(){
		if(System.getProperty("browsers") != null){
			log4j.info("Starting initialization function.");
			log4j.info("Browsers: "  + System.getProperty("browsers"));
			browserNames = System.getProperty("browsers").split(",");
		}
		
		seleniumGridHubUrl = System.getProperty("hubUrl");
		log4j.info("Hub URL: "  + System.getProperty("hubUrl"));
		addBrowsersToArray();
		if(browsersArray.size() > 0){
			for(String browserName : browsersArray){
				driver = null;
				log4j.info("Creating driver for browser: " + browserName);
				runBeforeMethod(browserName.trim());
				browsersArray.remove(browserName);
				break;
			}
		}else{
			runBeforeMethod("firefox");
		}
		createDriver(capability);
	}
	
	private void addBrowsersToArray(){
		if(browserNames != null){
			for(String browser : browserNames){
				browsersArray.add(browser);
			}
		}
	}
	
	public void runBeforeMethod(String browserName) {
		log4j.info("The parameter of browser name is: " + browserName);
		this.capability = new DesiredCapabilities();
		this.setBrowserName(browserName);
		if (browserName.startsWith("internet explorer")){
			this.capability.setBrowserName("internet explorer");
			this.capability.setVersion(browserName.substring(18));
			this.capability.setCapability("ignoreZoomSetting", true);
			this.capability.setCapability("enablePersistentHover", true);
			this.capability.internetExplorer();		
		}
		
		{
			this.capability.setBrowserName(browserName);
			if(browserName.startsWith("chrome")){
				this.capability.setBrowserName("chrome");
				//this.capability.setVersion(browserName.substring(18));
				this.capability.setCapability("ignoreZoomSetting", true);
				this.capability.setCapability("enablePersistentHover", true);
				this.capability.chrome();

				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				this.capability.setCapability(ChromeOptions.CAPABILITY, options);
			}
		}
		this.capability.setCapability("timeoutInSeconds", 90);		
	}
	
	/**
	 * 
	 * @param capabilities
	 */
	private void createDriver(DesiredCapabilities capabilities){
		if (seleniumGridHubUrl == null || seleniumGridHubUrl.length() == 0){
			log4j.info("Create chrome driver");
			File file = new File("src//chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
			driver = new ChromeDriver();
		}else{
			log4j.info("Create remote driver");
			try{
				log4j.info("Create grid driver");
				driver = new RemoteWebDriver(new URL(seleniumGridHubUrl), capability);
			}catch (MalformedURLException e){
				e.getMessage();
			}
		}
	}
	
	public enum FindOption{
		URL, CLASS, ID, XPATH, NAME, TAGNAME, CSSSELECTOR, LINKTEXT, PARTIALLINKTEXT
	}
	
	
	/**
	 * Method for finding web elements 
	 * @param findOption - select by what you want to search (e.g. ID, XPATH, URL)
	 * @param findParam - enter effectively the path or ID of what you search(e.g "name" or ".//*[@id='navy']")
	 * @return a web element
	 */
	public WebElement findElementOnPage(FindOption findOption, String findParam) {
		switch (findOption){
			case CLASS:{
				return driver.findElement(By.className(findParam));
			}
			
			case ID:{
				return driver.findElement(By.id(findParam));
			}
			
			case XPATH:{
				return driver.findElement(By.xpath(findParam));
			}
			
			case NAME:{
				return driver.findElement(By.name(findParam));
			}
			
			case TAGNAME:{
				return driver.findElement(By.tagName(findParam));
			}
			
			case CSSSELECTOR:{
				return driver.findElement(By.cssSelector(findParam));
			}
			
			case LINKTEXT:{
				return driver.findElement(By.linkText(findParam));
			}
			
			case PARTIALLINKTEXT:{
				return driver.findElement(By.partialLinkText(findParam));
			}
			
			default :{
				return null;
			}
		}
	}
	
	public enum AttributeOption{
		VALUE,TYPE,SRC
	}
	
	
	/**
	 * Method for getting the value of the specified attribute for the WebElement 
	 * @param attributeOption - select the attribute you want to get the value from (e.g. VALUE, TYPE, SRC)
	 * @param element - the WebElement from which the attribute is taken 
	 * @return a string with the value of the attribute
	 */
	public String findAttribute(AttributeOption attributeOption,WebElement element){
		switch(attributeOption){
			case VALUE:{
				return element.getAttribute("value");
			}
			
			case TYPE:{
				return element.getAttribute("type");
			}
			
			case SRC:{
				return element.getAttribute("src");
			}
	
			default :{
				return null;
			}
		
		}
	}
	
	public String findAttribute(String searchOption,WebElement element){
		return element.getAttribute(searchOption);	
	}
	
	public enum NavigationDirection{
		FORWARD, BACK, REFRESH
	}
	
	/**
	 * Method to reach a site page
	 * @param urlToReach - this parameter take an URL (e.g. "http://google.com")
	 */
	public void getToURL(String urlToReach) {
		driver.get(urlToReach);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		log4j.info("The current page is " + urlToReach);
	}
	
	
	/**
	 * Method for general navigation from a page: back, forward and refresh
	 * @param navigationType - this parameter take the direction where you want to go or refresh
	 */
	public void navigate(NavigationDirection navigationDirection) {
		switch (navigationDirection){
			case FORWARD:{
				driver.navigate().forward();
				break;
			}
			
			case BACK:{
				driver.navigate().back();
				break;
			}
			
			case REFRESH:{
				driver.navigate().refresh();
				break;
			}
			
			default :{
				break;
			}
		
		}
	}
	
	/**
	 * Method which execute drag and drop action
	 * @param source - source element to emulate button down at. 
	 * @param target - target element to move to and release the mouse at.
	 */
	public void dragNdrop(String source, String target){
		WebElement element = driver.findElement(By.name(source));
		WebElement targetElement = driver.findElement(By.name(target));
		
		(new Actions(driver)).dragAndDrop(element, targetElement).perform();	
	}
	
	/**
	 * Makes the driver to wait for some reason
	 * @param waitTime The amount of time to wait (in seconds).
	 */
	public void wait(int waitTime){	
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);

	}
	
	
	public enum MouseActions{
		CLICK, RIGHTCLICK, DOUBLECLICK, MOUSEOVER
	}
	
	/**
	 * Method for mouse action 
	 * @param mouseAction What you want to do with the mouse (e.g. Click, MouseOver)
	 * @param element The element you want to take the action of the mouse.
	 */
	public void mouseAction(MouseActions mouseAction, WebElement element){
		switch (mouseAction){
			case CLICK:{
				element.click();
				break;
			}
			
			case RIGHTCLICK:{
				Actions action = new Actions(driver);
				action.moveToElement(element).contextClick();	
				break;
			}
			
			case DOUBLECLICK:{
				Actions action = new Actions(driver);
				action.contextClick(element).perform();	
				break;
			}
			
			case MOUSEOVER:{
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
				break;
			}
			
			default :{
				break;

			}
		}
	}
	
	public enum SelectListElementBy{
		VALUE, TEXT, INDEX
	}
	
	/*
	 * Function to perform selection on a Select Web Element
	 * @param selectListElementBy - option to perform select by
	 * @param webElement - current web element to work with
	 * @param valueOfSelection - value to perform select by
	 */	
	public void selectElementFromList(SelectListElementBy selectListElementBy, WebElement webElement, String valueOfSelection){
		
		Select select = new Select(webElement);
		
		switch (selectListElementBy) {
			
			case INDEX:{
				select.selectByIndex(Integer.parseInt(valueOfSelection));
				break;
			}
			
			case TEXT:{
				select.selectByVisibleText(valueOfSelection);
				break;
			}
				
			case VALUE:{
				select.selectByValue(valueOfSelection);
				break;
			}
	
			default:
				break;
			}
	}
	
	public enum ValidateSelectionBy{
		TEXT_EQUALS, TEXT_CONTAINS
	}
		
	
	/**
	 * Method for pressing a key over an element.
	 * @param keyName
	 * @param element
	 */
	public void keyPress(String keyName, WebElement element){
		element.sendKeys(getKey(keyName));
	}
	
	
	// Helper function to get the key to be pressed from a String
	// 
	protected Keys getKey(String keyName){
		Keys key=Keys.NULL;
		try{
			key=Keys.valueOf(keyName.toUpperCase());
			}
		catch(Exception e) {
			
		}
		
		return key;
		}
	
		
	// Inserting a message in space
	public void runEnterMessage(String message, WebElement element){
		element.sendKeys(message);
	}
	
	
    public void takeDriverSnapShot(String screenSnapshotName) {
        File browserFile = new File(System.getProperty("java.io.tmpdir") + File.separator +screenSnapshotName + ".png");
        snapshotBrowser((TakesScreenshot) driver, screenSnapshotName, browserFile);
    }
 
    private static void snapshotBrowser(TakesScreenshot driver, String screenSnapshotName, File browserFile) {
        try {
        		File scrFile = driver.getScreenshotAs(OutputType.FILE);
        		log4j.info("PNG browser snapshot file name: \"{}\"", browserFile.toURI().toString());
 
        		FileUtils.deleteQuietly(browserFile);
        		FileUtils.moveFile(scrFile, browserFile);
        	} catch (Exception e) {
        		log4j.error("Could not create browser snapshot: " + screenSnapshotName, e);
        	}
    }
    
    
    public enum SeleniumValidateOption{
		TEXT_EQUALS, TEXT_CONTAINS, STYLE, IS_DISPLAYED
	}

	public void runValidate(String expectedText, WebElement element, SeleniumValidateOption validateOption) {
		
		Select select = null;
		try {
			select = new Select(element);
		} catch (Exception e) {
			log4j.error(e.getMessage());
		}
		
		switch (validateOption){

			case TEXT_EQUALS:{
				if(select != null && select.getFirstSelectedOption() != null && !select.getFirstSelectedOption().getText().isEmpty()){
					Assert.assertEquals("Text does not match!",expectedText, select.getFirstSelectedOption().getText());
				}else{
					Assert.assertEquals("Text does not match!",expectedText, element.getText());
				}
				break;
			}

			case TEXT_CONTAINS:{
				if(select != null && select.getFirstSelectedOption() != null && !select.getFirstSelectedOption().getText().isEmpty()){
					Assert.assertTrue("Text is not contained!",select.getFirstSelectedOption().getText().contains(expectedText));
				}else{
					Assert.assertTrue("Text is not contained!",element.getText().contains(expectedText));
				}
				break;
			}
			
			case IS_DISPLAYED:{
				Assert.assertTrue("Web Element is not visible!", element.isDisplayed());
				break;
			}
			
			default:{
				break;
			}
		}
	}
	
	// A method to get the page title
	public String runGetPageTitle()
	{
		String title = driver.getTitle();
		return title;		
	}
	
	// A method to get the page URL
	public String runGetCurrentPageURL()
	{
		String currentPageURL = driver.getCurrentUrl();
		return currentPageURL;
	}
	
	
	public void tearDown(Scenario scenario) {
	    if (scenario.isFailed()) {
	      final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	      scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
	    }
	    
	}
}
