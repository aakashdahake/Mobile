package utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class ConfigurationSettings {

	DesiredCapabilities capabilities;
	ServiceLauncher service = new ServiceLauncher();
	public WebDriver driver;
	
	@Test
	public void setUp() throws IOException {

		ConfigManager.loadConfig();

		if (ConfigManager.getProperty("ExecutionPlatform").equalsIgnoreCase("Mobile")) {

			
			if (ConfigManager.getProperty("MachineName").equalsIgnoreCase("Windows")) {
				
				service.startAndroidVirtualDeviceManagerWindows();
				service.appiumStartWindows();

			} else if (ConfigManager.getProperty("MachineName").equalsIgnoreCase("Mac")) {
				service.appiumStartMac();
			}
			
			
	
			if (ConfigManager.getProperty("PlatformName").equalsIgnoreCase("Android")) {

				capabilities = new DesiredCapabilities();
				capabilities.setCapability("emulator", true);
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigManager.getProperty("DeviceName"));
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, ConfigManager.getProperty("PlatformName"));
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, ConfigManager.getProperty("PlatformVersion"));
				capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + ConfigManager.getProperty("ApkPath"));
				capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60000");

				try {
					driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			} else if (ConfigManager.getProperty("PlatformName").equalsIgnoreCase("iOS")) {
				boolean DriverNoResetFlag = true;
				capabilities = new DesiredCapabilities();
				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, ConfigManager.getProperty("PlatformVersion"));
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, ConfigManager.getProperty("PlatformName"));
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigManager.getProperty("DeviceName"));
				capabilities.setCapability(MobileCapabilityType.APP, ConfigManager.getProperty("ApkPath"));
				capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60000");
				if (DriverNoResetFlag) {
					capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
					capabilities.setCapability(MobileCapabilityType.NO_RESET, false);

				}
				if (true) {
					capabilities.setCapability("autoAcceptAlerts", true);
				}
				try {
					driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	public void discardSetup() {
		service.destroySetup();
	}
}
