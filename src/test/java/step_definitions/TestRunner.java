package step_definitions;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;

	@RunWith(Cucumber.class)
	@CucumberOptions(
			features = {"src/"},
			plugin = {"pretty", "junit:target/junit/cucumber.xml", "json:target/json/output.json", "html:target/html/" },
			//format = {"pretty", "junit:target/junit/cucumber.xml", "json:target/json/output.json", "html:target/html/"},
			glue = "step_definitions",
			//"junit:target/cucumber-junit-report/allcukes.xml"},
			monochrome = true,
			tags = {"@Runme"}
			)
	
public class TestRunner {
		
		 @AfterClass
		 public static void tearDown(){
			 
			 
		 }
		
}
	
	