package appPages;

import org.openqa.selenium.WebElement;

import utils.BasicOperations;

public class AppPageMethods extends BasicOperations implements IAppLocator {

	boolean returnValue = false;

	/**
	 * Enter login credentials
	 * 
	 * @param user
	 * @return
	 */
	public boolean enterLoginCredentials(String username, String passwd) {
		WebElement emailEle = findElement(emailID, 10);
		WebElement psswdEle = findElement(password, 10);

		emailEle.sendKeys(username);
		psswdEle.sendKeys(passwd);

		returnValue = emailEle.getText().equals(username);

		return returnValue;
	}

	/**
	 * Click on login button
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public boolean clickLoginBtn() throws InterruptedException {
		WebElement signONBtnEle = findElement(signONBtn, 10);

		returnValue = isElementPresent(signONBtn, 10);

		signONBtnEle.click();
		Thread.sleep(1000);

		return returnValue;
	}

	/**
	 * Verify image
	 * 
	 * @return
	 */
	public boolean verifyImageOnLogin() {

		WebElement image = findElement(houseImage, 10);
		returnValue = image.isDisplayed();
		returnValue &= isElementPresent(houseImage, 10);

		return returnValue;
	}
}
