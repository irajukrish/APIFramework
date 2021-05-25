package cucumber.Options;

import org.testng.annotations.DataProvider;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/java/features", glue = { "stepDefinitions" }, 
				plugin = "json:target/jsonReports/cucumber-report.json")


public class TestRunner extends AbstractTestNGCucumberTests{
	
	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios(){
		return super.scenarios();
		
	}

}
