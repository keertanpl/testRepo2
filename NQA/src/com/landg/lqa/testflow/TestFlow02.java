package com.landg.lqa.testflow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

//import bsh.util.Util;

import com.landg.*;
import com.landg.lqa.objectrepository.CreateObjectRepository;

import org.openqa.selenium.*;

import com.landg.lqa.utility.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sqlite.date.ExceptionUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.TestNGAntTask;

import com.beust.jcommander.Parameter;

public class TestFlow02 {

	/**
	 * Variable declaration
	 */
	WebDriver driver;
	public static String STATUS = "Passed";
	public static String QUOTEREF = "";
	public static String DOB = "";
	public static String POSTCODE = "";
	public static String QUOTESTATUS = "";
	public static String PREV_QUESTION = "";
	public static String POLICY_NUMBER = "";
	public static String QUOTE_PAGE_PREMIUM_GLD = "";
	public static String QUOTE_PAGE_PREMIUM_SVR = "";
	public static String QUOTE_PURCHASE_TYPE = "";
	public static String BUY_PAGE_PREMIUM = "";
	public static String COVERTYPE = "";

	// public static String QUOTE_PAGE_PREV_PREMIUM_SVR="";
	// public static String QUOTE_PAGE_PREV_PREMIUM_GLD="";

	// Screenshot_i = 0
	int testRow, testCol, testCount = 0, r = 1, c = 1;
	public static String testReportfile = "", extentReportFile;
	String question, dataValue, locator, webElementType, Screenshot,
	verifyStatus, dataSheetPath, screenshotFolderName;
	FileInputStream objectFileStream;
	ObjectInputStream objectFile;
	public HashMap<Integer, String> objectRepository;
	ExtentTest extentTest;
	ArrayList<String> Screenshot_CapturePoint = new ArrayList<String>();
	ExtentReports extent;
	Logger Log;
	public static WebDriverWait wait;

	/**
	 * Variable declaration End
	 */

	/**
	 * This function do below items Updating the object repository Creation of
	 * screenshot folder name Creation of extent report file Opening the Object
	 * repository
	 */

	@BeforeTest
	@Parameters({ "Input", "Output", "Browser" })
	public void startSelenium(String Input, String Output, String Browser)
			throws Exception {

		CreateObjectRepository.Create();
		// Setting the Screenshot Capture points
		Screenshot_CapturePoint = Utils.setScreenshotCapturePoints(driver, 1,
				0, Screenshot_CapturePoint, question);
		// Creation of screenshot folder name
		screenshotFolderName = Utils.getScreenshotFolderName(driver,
				screenshotFolderName);
		// Creation of extent report file
		extent = Utils.createExtentTestReport(driver, extent,
				screenshotFolderName);

		// Opening the Object repository
		objectRepository = Utils.getObjectRepository(driver, objectRepository);
	}

	/**
	 ** Sending the Test run status email End the extent report
	 */

	@AfterTest
	public void stopSelenium() throws Exception {

		// End the extent Report
		extent.endTest(extentTest);
		extent.flush();
		// Sending the Test run status email
		Utils.sendPassMail(extentReportFile, STATUS);
		JOptionPane.showMessageDialog(null, "Test Run Completed");

	}

	@Test
	@Parameters({ "Input", "Output", "Browser", "Testcase", "Environment",
	"testPack" })
	public void testNQATestng(String Input, String Output, String Browser,
			String testcase, String environment, String testPack)
					throws Exception {

		// Opening the Log object

		Log = Logger.getLogger(testPack);
		Input = Utils.createTestReportFile(Input);
		Init.setTestDataSheet(Input, testPack);

		testRow = 1;
		testCol = 1;

		// Calculate the number of tests
		do {

			testCount++;
			testCol++;
		} while (ExcelUtils.getCellData(question, 0, testCol).compareTo("") != 0);

		// To check test data sheet is empty or not?
		if (testCol < 1) {
			JOptionPane.showMessageDialog(null,
					"Test cases are not available in Data sheet");
		}

		// When Run All option chosen
		if (testcase.length() < 1) {
			testCol = 1;
		}

		else {
			// When Run by Selected test is selected
			testCount = 1;

			testCol = Integer.parseInt(testcase);
		}

		// Test suite starts from here
		do {

			STATUS = "Passed";
			QUOTESTATUS = "";
			PREV_QUESTION = "";
			// POLICY_NUMBER = "";
			QUOTE_PAGE_PREMIUM_GLD = "";
			QUOTE_PAGE_PREMIUM_SVR = "";
			QUOTE_PURCHASE_TYPE = "";
			BUY_PAGE_PREMIUM = "";
			COVERTYPE = "";

			testRow = 1;

			// To open the browser

			driver = Utils.openBrowser(Browser);

			wait = new WebDriverWait(driver, 90);

			// Opening the URL
			driver.navigate().to(
					ExcelUtils.getURL(Input, testRow, testCol, environment,
							testPack));

			// To copy the test data sheet to location \\Test Report File

			Init.setTestDataSheet(Input, testPack);

			// Start the extent test report

			extentTest = extent.startTest(
					ExcelUtils.getCellData(question, 0, testCol), "Smoke Test");
			extentTest.log(LogStatus.INFO, "Browser Launched");
			extentTest.log(LogStatus.INFO, "Report file Created:" + Input);

			testRow = 2;

			// Individual tests starts here

			try {

				do {
					testRow = testRow - 1;
					do {

						testRow++;
						// Read the data from test data sheet
						dataValue = ExcelUtils.getCellData(question, testRow,
								testCol);

						// Read the Question from test data sheet

						question = ExcelUtils.getCellData(question, testRow, 0);

						// To end the Test case
						if (question.length() < 1) {

							question = "End";
							break;
						}

					} while (dataValue.length() == 0);// To check data is empty
					// or not, If it empty
					// then don't execute

					/***************************** Additional Steps Eg.: Switching the frame,Wait time etc ******************************************************/
					Utils.addCustomisedSteps(question, driver);

					// ***********************************************************************************************//
					/* End of additional steps */

					// To come out of tests
					if (question.equalsIgnoreCase("End")) {
						break;
					}

					if ((objectRepository.get(question.hashCode()
							+ dataValue.hashCode() + 10)) == null) {
						// Reading the Web element type from Object repository
						webElementType = objectRepository.get(question
								.hashCode() + 10);

					} else {
						// Reading the Web element type from Object repository
						webElementType = (objectRepository.get(question
								.hashCode() + dataValue.hashCode() + 10));

					}

					if (webElementType.compareTo("Button") == 0) {
						// Reading the locator of question from object
						// repository
						locator = objectRepository
								.get((question.hashCode() + dataValue
										.hashCode()));

					} else {
						// Reading the locator of question from object
						// repository
						locator = objectRepository.get((question.hashCode()));

					}

					// *********** Store Post code & DOB value for
					// retrieval**************//

					if (question.equalsIgnoreCase("Address")) {
						POSTCODE = dataValue;

					} else if (question.equalsIgnoreCase("DOB")) {
						DOB = dataValue;

					} else if (question.equalsIgnoreCase("Select Policy"))

					{
						Thread.sleep(1000);
						QUOTESTATUS = driver.findElement(By.xpath("//h2"))
								.getText();
						Thread.sleep(1000);
					} else if (question.equalsIgnoreCase("Purchase")) {
						TestFlow02.QUOTE_PURCHASE_TYPE = dataValue;
					}

					else if (question
							.equalsIgnoreCase("Verification_Quote number")) {
						QUOTEREF = Verification.captureData(driver, locator,
								question);
						QUOTEREF = QUOTEREF.replace("Ref: ", "");

					} else if (question.equalsIgnoreCase("Cover Type")) {
						COVERTYPE = dataValue;
					}

					// To check data is empty or not , If empty no need to
					// execute

					if (dataValue.length() > 0) {

						if (webElementType.compareTo("Button") == 0) {

							// *********************For capturing the
							// screenshot**********************//

							if (Screenshot_CapturePoint.contains(question))

							{
								Screenshot = Utils.takeScreenshot(driver,
										testCol, Output, screenshotFolderName,
										Browser);
								Thread.sleep(2000);

								try {
									extentTest
									.log(LogStatus.PASS,
											driver.findElement(
													By.xpath(objectRepository
															.get(("Screenshot Capture Point"
																	.hashCode()))))
																	.getText()
																	+ extentTest
																	.addScreenCapture(Screenshot));

								}
								// if h1 tag is missing
								catch (Exception e) {
									extentTest
									.log(LogStatus.PASS,
											""
													+ extentTest
													.addScreenCapture(Screenshot));
								}
							}

							// If web element type is button,Send the click
							// command to browser
							BrowserAction.sendCommand(driver, webElementType,
									dataValue, locator, question, testRow,
									testCol);
						} else {

							// If web element type is not a button, Send the
							// sendkeys & select command to browser

							// Quote retrieval:

							if (dataValue.contains("quoteRef")) {
								dataValue = QUOTEREF;
							} else if (dataValue.contains("dob")) {
								dataValue = DOB;
							} else if (dataValue.contains("postCode")) {
								dataValue = POSTCODE;
							} else if (dataValue.contains("policyNumber")) {
								dataValue = POLICY_NUMBER;
							} else if (dataValue.contains("$currentDay1")) {
								dataValue = Utils.getCurrentDate().substring(0,
										2)
										+ "th";

							} else if (dataValue.contains("$currentDay")) {
								dataValue = Utils.getCurrentDate().substring(0,
										2);
							} else if (dataValue.contains("$currentMonth")) {
								dataValue = Utils.getCurrentDate().substring(2,
										4);
							} else if (dataValue.contains("$currentYear")) {
								dataValue = Utils.getCurrentDate().substring(4,
										6);
							}

							BrowserAction.sendCommand(driver, webElementType,
									dataValue, locator, question, testRow,
									testCol);
						}

						if (question.contains("Verification")) {

							Verification.verify(Browser, driver, dataValue,
									locator, webElementType, question,
									extentTest, testRow, testCol, QUOTEREF,
									POSTCODE, DOB);

						}
					}

					testRow = testRow + 1; // To move next question
					PREV_QUESTION = ExcelUtils.getCellData(question,
							testRow - 1, 0);

				} while (!(ExcelUtils.getCellData(question, testRow, testCol)
						.equalsIgnoreCase("End"))); // /validate end

			} catch (Exception e) {

				STATUS = "Failed";
				Screenshot = Utils.takeScreenshot(driver, testCol, Output,
						screenshotFolderName, Browser);
				// extentTest.log(LogStatus.FAIL, "Step failed");
				extentTest
				.log(LogStatus.FAIL,
						"Test is failed due to Script/Application issue,Refer the log file for more info");
				// Writing the exception to log file
				Log.error("***************************************************************************************************");
				Log.error(ExcelUtils.getCellData(question, 0, testCol));
				Log.error(e.toString());
				Log.error(" ", e);

			}

			Thread.sleep(2000);
			testCount = testCount - 1;
			testCol++;
			Thread.sleep(2000);

			Screenshot = Utils.takeScreenshot(driver, testCol - 1, Output,
					screenshotFolderName, Browser);
			if (STATUS.equalsIgnoreCase("Failed")) {
				extentTest.log(LogStatus.FAIL,
						"" + extentTest.addScreenCapture(Screenshot));
			} else {
				extentTest.log(LogStatus.PASS,
						"" + extentTest.addScreenCapture(Screenshot));
			}

			driver.quit();
			extent.endTest(extentTest);

			// writing everything to document.
			extent.flush();
		} while (testCount > 0);

	}

}
