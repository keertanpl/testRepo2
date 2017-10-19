package com.landg.lqa.utility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.landg.lqa.testflow.TestFlow02;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Verification {

	static String actual;

	public static void verify(String Browser, WebDriver driver,
			String expected, String Locator, String Tag, String Question,
			ExtentTest extentTest, int testr, int testc, String Quoteref,
			String Postcode, String DOB) throws Exception {
		String verifyStatus = "";

		switch (Tag) {

		case "verifyConfirmationScreen":

			actual = driver.findElement(By.xpath(Locator)).getText();
			if (TestFlow02.QUOTE_PURCHASE_TYPE.equalsIgnoreCase("GLD")) {
				if (actual.contains("Gold")) {
					extentTest.log(LogStatus.PASS, Question);
				} else {
					extentTest.log(LogStatus.FAIL, Question + "_"
							+ "    Incorrect Scheme Type");
				}

			}
			if (TestFlow02.QUOTE_PURCHASE_TYPE.equalsIgnoreCase("SVR")) {
				if (actual.contains("Silver")) {
					extentTest.log(LogStatus.PASS, Question);
				} else {
					extentTest.log(LogStatus.FAIL, Question + "_"
							+ "    Incorrect Scheme Type");
				}

			}
			if (actual.contains(TestFlow02.COVERTYPE)) {
				extentTest.log(LogStatus.PASS, Question);
			} else {
				extentTest.log(LogStatus.FAIL, Question + "_"
						+ "    Incorrect Cover Type");
			}
			break;

		case "compareGLDPremiuminc":
			new WebDriverWait(driver, 120).until(ExpectedConditions
					.visibilityOfElementLocated(By
							.xpath("//*[@data-pid='QuoteReferenceTitle']")));

			actual = driver.findElement(By.xpath(Locator)).getText();
			actual = actual.trim();
			if (actual.contains("Annual")) {
				actual = actual.replace("Annual", "");

			}

			actual = actual.replace("£", "");

			if (Double.parseDouble((TestFlow02.QUOTE_PAGE_PREMIUM_GLD).replace(
					"£", "")) < Double.parseDouble(actual)) {
				extentTest.log(LogStatus.PASS, Question);
			} else {
				extentTest.log(LogStatus.FAIL, Question + "_" + "Actual Result"
						+ actual);
			}

			TestFlow02.QUOTE_PAGE_PREMIUM_GLD = actual;
			ExcelUtils.setCellData(TestFlow02.testReportfile, testr, testc,
					actual);

			break;
		case "compareSVRPremiuminc":
			new WebDriverWait(driver, 120).until(ExpectedConditions
					.visibilityOfElementLocated(By
							.xpath("//*[@data-pid='QuoteReferenceTitle']")));

			actual = driver.findElement(By.xpath(Locator)).getText();
			actual = actual.trim();
			if (actual.contains("Annual")) {
				actual = actual.replace("Annual", "");

			}

			actual = actual.replace("£", "");

			if (Double.parseDouble((TestFlow02.QUOTE_PAGE_PREMIUM_SVR).replace(
					"£", "")) < Double.parseDouble(actual)) {
				extentTest.log(LogStatus.PASS, Question);
			} else {
				extentTest.log(LogStatus.FAIL, Question + "_" + "Actual Result"
						+ actual);
			}

			TestFlow02.QUOTE_PAGE_PREMIUM_SVR = actual;

			ExcelUtils.setCellData(TestFlow02.testReportfile, testr, testc,
					actual);
			break;

		case "compareSVRPremiumdec":
			new WebDriverWait(driver, 35).until(ExpectedConditions
					.visibilityOfElementLocated(By
							.xpath("//*[@data-pid='QuoteReferenceTitle']")));

			actual = driver.findElement(By.xpath(Locator)).getText();

			if (actual.contains("Annual")) {
				actual = actual.replace("Annual", "");

			}

			actual = actual.replace("£", "");
			if (Double.parseDouble((TestFlow02.QUOTE_PAGE_PREMIUM_SVR).replace(
					"£", "")) > Double.parseDouble(actual)) {
				extentTest.log(LogStatus.PASS, Question);
			} else {
				extentTest.log(LogStatus.FAIL, Question + "_" + "Actual Result"
						+ actual);
			}

			actual = actual.trim();
			// TestFlow02.QUOTE_PAGE_PREV_PREMIUM_SVR=TestFlow02.QUOTE_PAGE_PREMIUM_SVR;
			TestFlow02.QUOTE_PAGE_PREMIUM_SVR = actual;

			ExcelUtils.setCellData(TestFlow02.testReportfile, testr, testc,
					actual);

			break;
		case "compareGLDPremiumdec":
			new WebDriverWait(driver, 35).until(ExpectedConditions
					.visibilityOfElementLocated(By
							.xpath("//*[@data-pid='QuoteReferenceTitle']")));

			actual = driver.findElement(By.xpath(Locator)).getText();

			if (actual.contains("Annual")) {
				actual = actual.replace("Annual", "");

			}

			actual = actual.replace("£", "");
			if (Double.parseDouble((TestFlow02.QUOTE_PAGE_PREMIUM_GLD).replace(
					"£", "")) > Double.parseDouble(actual)) {
				extentTest.log(LogStatus.PASS, Question);
			} else {
				extentTest.log(LogStatus.FAIL, Question + "_" + "Actual Result"
						+ actual);
			}

			actual = actual.trim();
			// TestFlow02.QUOTE_PAGE_PREV_PREMIUM_GLD=TestFlow02.QUOTE_PAGE_PREMIUM_GLD;
			TestFlow02.QUOTE_PAGE_PREMIUM_GLD = actual;

			ExcelUtils.setCellData(TestFlow02.testReportfile, testr, testc,
					actual);

			break;
		case "comparePremium":
			if (TestFlow02.QUOTE_PURCHASE_TYPE.equalsIgnoreCase("GLD")
					&& Question
							.equalsIgnoreCase("Verification_Compare_BuyPage_Premium")) {

				verifyStatus = verifyText(driver,
						TestFlow02.QUOTE_PAGE_PREMIUM_GLD, Locator);

				actual = actual.trim();

				TestFlow02.QUOTE_PAGE_PREMIUM_GLD = actual;

				ExcelUtils.setCellData(TestFlow02.testReportfile, testr, testc,
						actual);
			}
			if (TestFlow02.QUOTE_PURCHASE_TYPE.equalsIgnoreCase("SVR")
					&& Question
							.equalsIgnoreCase("Verification_Compare_BuyPage_Premium")) {

				verifyStatus = verifyText(driver,
						TestFlow02.QUOTE_PAGE_PREMIUM_SVR, Locator);

				actual = actual.trim();

				TestFlow02.QUOTE_PAGE_PREMIUM_SVR = actual;

				ExcelUtils.setCellData(TestFlow02.testReportfile, testr, testc,
						actual);
			}

			if (Question.equalsIgnoreCase("Verification_Compare_GLD_Premium")) {

				new WebDriverWait(driver, 35)
						.until(ExpectedConditions.visibilityOfElementLocated(By
								.xpath("//*[@data-pid='QuoteReferenceTitle']")));

				verifyStatus = verifyText(driver,
						TestFlow02.QUOTE_PAGE_PREMIUM_GLD, Locator);

				actual = actual.trim();

				TestFlow02.QUOTE_PAGE_PREMIUM_GLD = actual;

				ExcelUtils.setCellData(TestFlow02.testReportfile, testr, testc,
						actual);
			}

			if (Question.equalsIgnoreCase("Verification_Compare_SVR_Premium")) {

				new WebDriverWait(driver, 35)
						.until(ExpectedConditions.visibilityOfElementLocated(By
								.xpath("//*[@data-pid='QuoteReferenceTitle']")));

				verifyStatus = verifyText(driver,
						TestFlow02.QUOTE_PAGE_PREMIUM_SVR, Locator);

				actual = actual.trim();

				TestFlow02.QUOTE_PAGE_PREMIUM_SVR = actual;

				ExcelUtils.setCellData(TestFlow02.testReportfile, testr, testc,
						actual);
			}

			if (verifyStatus.equalsIgnoreCase("Passed")) {

				extentTest.log(LogStatus.PASS, Question);

			} else {

				extentTest.log(LogStatus.FAIL, Question + "_" + "Actual Result"
						+ verifyStatus);
			}
			break;

		case "verifyText":
			verifyStatus = verifyText(driver, expected, Locator);
			if (verifyStatus.equalsIgnoreCase("Passed")) {

				extentTest.log(LogStatus.PASS, Question);

			} else {

				extentTest.log(LogStatus.FAIL, Question + "_" + "Actual Result"
						+ verifyStatus);
			}
			break;

		case "captureData":
			verifyStatus = captureData(driver, Locator, Question);

			if (Question.contains("Confirmation Screen Policy number")) {

				Question = "";
			}
			Question = Question.replace("Verification_", "");

			if (verifyStatus.contains("Annual")) {
				verifyStatus = verifyStatus.replace("Annual", "");

			}
			if (verifyStatus.contains("Ref: ")) {
				verifyStatus = verifyStatus.replace("Ref: ", "");

			}

			if (verifyStatus.contains("Your policy number is")) {

				// System.out.println("Policy number is");

				String policyText[] = verifyStatus
						.split("Your policy number is")[1].split(" ");

				verifyStatus = policyText[1];
				TestFlow02.POLICY_NUMBER = verifyStatus;

			}
			extentTest.log(LogStatus.INFO, Question + verifyStatus);

			if (Question.equalsIgnoreCase("Premium_SVR")) {
				verifyStatus = verifyStatus.trim();

				TestFlow02.QUOTE_PAGE_PREMIUM_SVR = verifyStatus;

			}

			if (Question.equalsIgnoreCase("Premium_GLD")) {
				verifyStatus = verifyStatus.trim();

				TestFlow02.QUOTE_PAGE_PREMIUM_GLD = verifyStatus;

			}

			if (verifyStatus.contains("Verification_Buypage_Premium")) {

				TestFlow02.BUY_PAGE_PREMIUM = verifyStatus;

			}
			ExcelUtils.setCellData(TestFlow02.testReportfile, testr, testc,
					verifyStatus);
			Thread.sleep(3000);
			break;

		case "Capturetimestamp":
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			String timeStamps = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(timestamp);
			ExcelUtils.setCellData(TestFlow02.testReportfile, testr, testc,
					timeStamps);
			break;

		case "verifyElementExist":
			verifyStatus = verifyElementExist(driver, expected, Locator);
			if (verifyStatus.equalsIgnoreCase("Passed")) {

				extentTest.log(LogStatus.PASS, Question);

			} else {

				extentTest.log(LogStatus.FAIL, Question + "_" + "Actual Result"
						+ verifyStatus);
			}
			break;
		case "verifyElementNotExist":

			verifyStatus = verifyElementNotExist(driver, expected, Locator);
			if (verifyStatus.equalsIgnoreCase("Passed")) {

				extentTest.log(LogStatus.PASS, Question);
				// System.out.println(Question);

			} else {

				extentTest.log(LogStatus.FAIL, Question + "_" + "Actual Result"
						+ verifyStatus);
			}
			break;

		case "verifyDropDownValues":
			String expected_[] = expected.split(",");// / Add the code to get

			verifyStatus = verifyDropDownValues(driver, expected_, Locator);
			if (verifyStatus.equalsIgnoreCase("Passed")) {

				extentTest.log(LogStatus.PASS, Question);

			} else {

				extentTest.log(LogStatus.FAIL, Question + "_" + "Actual Result"
						+ verifyStatus);
			}
			break;

		case "EditAddon":
			int count = 0;
			while (count < 6) {
				try {

					Actions build = new Actions(driver);

					List<WebElement> addon = driver.findElements(By
							.xpath(Locator));

					WebElement element = addon.get(Integer.parseInt(""
							+ Question.charAt(Question.length() - 1)) - 1);

					build.moveToElement(element).build().perform();

					new WebDriverWait(driver, 8).until(ExpectedConditions
							.elementToBeClickable(element));

					element = addon.get(Integer.parseInt(""
							+ Question.charAt(Question.length() - 1)) - 1);
					element = addon.get(Integer.parseInt(""
							+ Question.charAt(Question.length() - 1)) - 1);

					Thread.sleep(3000);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", element);
					count = count + 6;

					Thread.sleep(3000);
				} catch (Exception e) {
					// e.printStackTrace();
					count = count + 1;
					if (count == 6) {
						List<WebElement> addon = driver.findElements(By
								.xpath(Locator));

						WebElement element = addon.get(Integer.parseInt(""
								+ Question.charAt(Question.length() - 1)) - 1);
						element = addon.get(Integer.parseInt(""
								+ Question.charAt(Question.length() - 1)) - 1);
						element = addon.get(Integer.parseInt(""
								+ Question.charAt(Question.length() - 1)) - 1);

						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].click();", element);
					}

				}
			}
			break;

		}

	}

	static String verifyText(WebDriver driver, String expected, String Locator) {

		actual = driver.findElement(By.xpath(Locator)).getText();
		if (actual.contains("Annual")) {
			actual = actual.replace("Annual", "");

		}

		actual = actual.trim();
		if (expected.equals(actual)) {

			// System.out.println("Passed");
			return "Passed";

		} else {
			// System.out.println("Failed");

			return actual;

		}

	}

	public static String captureData(WebDriver driver, String Locator,
			String Question) throws InterruptedException {

		try {
			new WebDriverWait(driver, 120).until(ExpectedConditions

			.visibilityOfElementLocated(By.xpath(Locator)));
		} catch (Exception e) {
			if (Question.contains("Policy number Card")) {
				driver.switchTo().frame(0);
			}

		}
		String actual;
		Thread.sleep(3000);
		if (Question.equalsIgnoreCase("Verification_Premium_SVR")
				|| Question.equalsIgnoreCase("Verification_Premium_GLD")) {

			new WebDriverWait(driver, 120).until(ExpectedConditions
					.visibilityOfElementLocated(By
							.xpath("//*[@data-pid='QuoteReferenceTitle']")));

		}

		actual = driver.findElement(By.xpath(Locator)).getText();

		return actual;

	}

	static void openWebRetrievalURL(String Browser, WebDriver driver,
			String Locator, String Quoteref, String Postcode, String DOB) {

		driver.get(Locator);
		driver.switchTo().alert().accept();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		driver.switchTo().frame(0);
		driver.findElement(
				By.xpath("//*[@data-pid='QuoteSearch.quoteReference']"))
				.clear();
		driver.findElement(
				By.xpath("//*[@data-pid='QuoteSearch.quoteReference']"))
				.sendKeys(Quoteref);
		driver.findElement(By.xpath("//*[@data-pid='QuoteSearch.postcode']"))
				.sendKeys(Postcode);
		driver.findElement(By.xpath("//*[@data-pid='QuoteSearch.dateOfBirth']"))
				.sendKeys(DOB);
		return;

	}

	static String verifyElementExist(WebDriver driver, String expected,
			String Locator) {
		if (driver.findElement(By.xpath(Locator)).isEnabled()) {
			return "Passed";

		} else {
			return "Web element not exist";
		}

	}

	static String verifyElementNotExist(WebDriver driver, String expected,
			String Locator) {

		try {

			if (!(driver.findElement(By.xpath(Locator)).isDisplayed())) {
				return "Passed";
			} else {
				return "Web element exist";
			}
		} catch (Exception e)

		{
			return "Passed";
		}

	}

	static String verifyDropDownValues(WebDriver driver, String[] expected,
			String Locator) {

		Select agreement = new Select(driver.findElement(By.xpath(Locator)));
		List<WebElement> agreement_actual = agreement.getOptions();

		int i = 0;

		for (WebElement AE : agreement_actual)

		{

			if (AE.getText().equals(expected[i])) {

			} else {

				return " Not Matched";
			}
			i++;
		}

		return "Passed";
	}
}
