package testRunner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import utils.ConfigurationSettings;

@RunWith(Cucumber.class)

@CucumberOptions(features = "src/test/java/features/app.feature", glue = "stepDefinition", plugin = { "pretty", "json:test-output/cucumber.json", "junit:test-output/cucumber.xml",
		"html:test-output/AppTest.html" },

		monochrome = true, dryRun = false)

public class TestRunner {

	static ConfigurationSettings configSettings = new ConfigurationSettings();

	@BeforeClass
	public static void beforeClass() throws Exception {

		try {
			configSettings.setUp();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@AfterClass
	public static void driverQuit() {
		//configSettings.discardSetup();
	}

}
