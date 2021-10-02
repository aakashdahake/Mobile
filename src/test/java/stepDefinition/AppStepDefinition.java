package stepDefinition;

import org.junit.Assert;

import appPages.AppPageMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.BasicOperations;

public class AppStepDefinition extends BasicOperations {

	boolean testResult;
	AppPageMethods pageMethods = new AppPageMethods();

	@Given("Enter username as {string} and password as {string}")
	public void enter_username_as_and_password_as(String user, String passwd) {
		try {
			testResult = pageMethods.enterLoginCredentials(user, passwd);
		} catch (Exception e) {

			testResult &= false;
		}
		Assert.assertEquals(true, testResult);
	}

	@Then("Click on Login Button")
	public void click_on_login_button() {
		try {
			testResult = pageMethods.clickLoginBtn();
		} catch (Exception e) {

			testResult &= false;
		}
		Assert.assertEquals(true, testResult);
	}

	@Then("Verify if house image is available")
	public void verify_if_house_image_is_available() {
		try {
			testResult = pageMethods.verifyImageOnLogin();
		} catch (Exception e) {

			testResult &= false;
		}
		Assert.assertEquals(true, testResult);
	}

}
