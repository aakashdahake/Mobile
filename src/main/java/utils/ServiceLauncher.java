package utils;

import java.io.IOException;

import org.junit.Test;

public class ServiceLauncher {

	static Process appiumProcess = null;
	static Process avdProcess = null;

	/**
	 * This method is responsible for starting AVD server on Windows.
	 */

	public void startAndroidVirtualDeviceManagerWindows() {

		System.out.println("Starting Android Virtual Device Manager");
		ProcessBuilder pb;
		String[] command = { "cmd.exe", "/C", "C:\\Users\\Aakash\\AppData\\Local\\Android\\Sdk\\emulator\\emulator.exe", "@Pixel" };

		if (avdProcess == null) {
			pb = new ProcessBuilder(command);
			try {
				avdProcess = pb.start();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		if (avdProcess != null) {
			System.out.println("AVD Started");
		}
	}

	/**
	 * This method is responsible for starting appium server on Windows.
	 */
	@Test
	public void appiumStartWindows() {

		String[] command = { "cmd.exe", "/C", "Start", "appium" };
		ProcessBuilder pb;
		if (appiumProcess == null) {
			pb = new ProcessBuilder(command);
			try {
				appiumProcess = pb.start();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		if (appiumProcess != null) {
			System.out.println("SERVER STARTED");
		}
	}

	/**
	 * This method is responsible for starting appium server on mac.
	 */
	public void appiumStartMac() {

		String[] command = { "/usr/bin/osascript", "-e", "tell app \"Terminal\"", "-e", "set currentTab to do script " + "(\"appium\")", "-e", "end tell" };
		final ProcessBuilder processBuilder;
		if (appiumProcess == null) {
			try {
				processBuilder = new ProcessBuilder(command);
				appiumProcess = processBuilder.start();
				appiumProcess.waitFor();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (appiumProcess != null) {
			System.out.println("SERVER STARTED");
		}
	}

	public void destroySetup() {

		if (appiumProcess != null & avdProcess != null) {
			System.out.println("Destroying processes");
			appiumProcess.descendants().forEach(s -> s.destroy());
			avdProcess.destroy();
		}
	}

}
