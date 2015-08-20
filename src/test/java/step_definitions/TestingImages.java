package step_definitions;

import BDDTest.SeleniumUtils;
import BDDTest.SeleniumUtils.FindOption;
import BDDTest.SeleniumUtils.SeleniumValidateOption;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class TestingImages {
	
	private SeleniumUtils seleniumUtils;
	private String startPageUrlDealer,secondPageUrlDealer,thirdPageUrlDealer;
	private String startPageUrlClient,secondPageUrlClient,thirdPageUrlClient;

	public TestingImages(){
		this.seleniumUtils=new SeleniumUtils();
		startPageUrlDealer="http://audi-a4-preview.message.ch/dealer/de/";
		secondPageUrlDealer="http://audi-a4-preview.message.ch/dealer/it/";
		thirdPageUrlDealer="http://audi-a4-preview.message.ch/dealer/fr/";
		startPageUrlClient="http://audi-a4-preview.message.ch/client/de/";
		secondPageUrlClient="http://audi-a4-preview.message.ch/client/it/";
		thirdPageUrlClient="http://audi-a4-preview.message.ch/client/fr/";
	}
	
	@Given("^I am on audi DE dealer homepage$")
	public void I_am_on_audi_DE_dealer_homepage(){
		seleniumUtils.getToURL(startPageUrlDealer);
	
	}
	
	@Then("^Some Audi image should be visible$")
	public void Some_Audi_image_should_be_visible(){
		seleniumUtils.runValidate("", seleniumUtils.findElementOnPage(FindOption.XPATH, ".//img[@src='/media/1001/audiimage.jpg']"), SeleniumValidateOption.IS_DISPLAYED);		
	
	}
	
	@Then("^Some Audi logo should be visible$")
	public void Some_Audi_logo_should_be_visible(){
		seleniumUtils.runValidate("", seleniumUtils.findElementOnPage(FindOption.XPATH, ".//img[@src='/images/logo.png']"), SeleniumValidateOption.IS_DISPLAYED);		
				
	}
	
	@Then("^I go to IT dealer homepage$")
	public void I_go_to_IT_dealer_homepage(){
		seleniumUtils.getToURL(secondPageUrlDealer);
		
	}
	
	@Then("^Logo should be there$")
	public void Logo_should_be_there(){
		seleniumUtils.runValidate("", seleniumUtils.findElementOnPage(FindOption.XPATH, ".//img[@src='/images/logo.png']"), SeleniumValidateOption.IS_DISPLAYED);		
		
	}
	
	@Then("^Image should be there$")
	public void Image_should_be_there(){
		seleniumUtils.runValidate("", seleniumUtils.findElementOnPage(FindOption.XPATH, ".//img[@src='/media/1001/audiimage.jpg']"), SeleniumValidateOption.IS_DISPLAYED);		
	
	}
	
	@Then("^I go to FR dealer homepage$")
	public void I_go_to_FR_dealer_homepage(){
		seleniumUtils.getToURL(thirdPageUrlDealer);
	}
	
	@Then("^The logo should be visible$")
	public void The_logo_should_be_visible(){
		seleniumUtils.runValidate("", seleniumUtils.findElementOnPage(FindOption.XPATH, ".//img[@src='/images/logo.png']"), SeleniumValidateOption.IS_DISPLAYED);		
		
	}
	
	@Then("^The Image should be visible$")
	public void Image_should_be_visible(){
		seleniumUtils.runValidate("", seleniumUtils.findElementOnPage(FindOption.XPATH, ".//img[@src='/media/1001/audiimage.jpg']"), SeleniumValidateOption.IS_DISPLAYED);		
		
	}

	@Then("^I go to DE client page$")
	public void I_go_to_DE_client_page(){
		seleniumUtils.getToURL(startPageUrlClient);
		
	}
	
	@Then("^Some Audi image should be visible client DE$")
	public void Some_Audi_image_should_be_visible_client_DE(){
		seleniumUtils.runValidate("", seleniumUtils.findElementOnPage(FindOption.XPATH, ".//img[@src='/media/1001/audiimage.jpg']"), SeleniumValidateOption.IS_DISPLAYED);		
	
	}
	
	@Then("^Some Audi logo should be visible client DE$")
	public void Some_Audi_logo_should_be_visible_client_DE(){
		seleniumUtils.runValidate("", seleniumUtils.findElementOnPage(FindOption.XPATH, ".//img[@src='/images/logo.png']"), SeleniumValidateOption.IS_DISPLAYED);		
				
	}
	
	@Then("^I go to IT client page$")
	public void I_go_to_IT_client_page(){
		seleniumUtils.getToURL(secondPageUrlClient);
		
	}
	
	@Then("^The logo should be visible client IT$")
	public void The_logo_should_be_visible_client_IT(){
		seleniumUtils.runValidate("", seleniumUtils.findElementOnPage(FindOption.XPATH, ".//img[@src='/images/logo.png']"), SeleniumValidateOption.IS_DISPLAYED);		
		
	}
	
	@Then("^The Image should be visible client IT$")
	public void The_Image_should_be_visible_client_IT(){
		seleniumUtils.runValidate("", seleniumUtils.findElementOnPage(FindOption.XPATH, ".//img[@src='/media/1001/audiimage.jpg']"), SeleniumValidateOption.IS_DISPLAYED);		
	
	}
	
	@Then("^I go to FR client page$")
	public void I_go_to_FR_client_page(){
		seleniumUtils.getToURL(thirdPageUrlClient);
	}
	
	@Then("^The logo should be visible client FR$")
	public void The_logo_should_be_visible_client_FR(){
		seleniumUtils.runValidate("", seleniumUtils.findElementOnPage(FindOption.XPATH, ".//img[@src='/images/logo.png']"), SeleniumValidateOption.IS_DISPLAYED);		
		
	}
	
	@Then("^The Image should be visible client FR$")
	public void Image_should_be_visible_client_FR(){
		seleniumUtils.runValidate("", seleniumUtils.findElementOnPage(FindOption.XPATH, ".//img[@src='/media/1001/audiimage.jpg']"), SeleniumValidateOption.IS_DISPLAYED);		
		
	}
	
}
