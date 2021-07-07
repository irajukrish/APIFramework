package cucumber.Options;

import org.testng.annotations.DataProvider;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = "src/test/java/features",
		glue = { "stepDefinitions" },
		plugin = {"pretty",
				"json:target/jsonReports/cucumber-report.json",
				"html:target/cucumber-reports.html"},
		monochrome = true
		,tags = "@AddPlace")


public class TestRunner extends AbstractTestNGCucumberTests{
	
	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios(){
		return super.scenarios();
		
	}

}
