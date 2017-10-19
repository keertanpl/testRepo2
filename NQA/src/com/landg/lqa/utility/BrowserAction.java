package com.landg.lqa.utility;

import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.TimeZone;

import org.apache.poi.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.landg.lqa.testflow.TestFlow02;

public class BrowserAction {

	public static void sendCommand(WebDriver driver, String Tag,
			String data_value, String Locator, String Question, int testr,
			int testc) throws Exception {

		int testrow = 0, testcol = 19;
		int data;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		if (Tag.compareTo("Button") == 0) {

			Actions build = new Actions(driver);

			if (Locator.contains("/")) {

				int count = 0;

				while (count < 5) {
					try {
						build.moveToElement(
								driver.findElement(By.xpath(Locator))).build()
								.perform();

						WebElement element = driver.findElement(By
								.xpath(Locator));
						element = driver.findElement(By.xpath(Locator));
						element = driver.findElement(By.xpath(Locator));
						JavascriptExecutor js = (JavascriptExecutor) driver;

						Thread.sleep(500);
						js.executeScript("arguments[0].click();", element);

						count = count + 5;

					} catch (Exception e) {

						count = count + 1;

						// *****************************For switching the frame
						// for editing the quote**************************//

						if (TestFlow02.PREV_QUESTION.contentEquals("View")
								&& TestFlow02.QUOTESTATUS
								.equalsIgnoreCase("QUOTES")) {
							Thread.sleep(1000);
							// System.out.println("Inside");
							driver.switchTo().frame(0);

							Thread.sleep(3000);
							WebElement element = driver.findElement(By
									.xpath(Locator));

							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].click();",

									element);

							TestFlow02.PREV_QUESTION = "";
							TestFlow02.QUOTESTATUS = "";

							// **************************End**********************************************************************//
						}
						if (count == 5) {

							try {

								new WebDriverWait(driver, 10)
								.until(ExpectedConditions.visibilityOfElementLocated(By
										.xpath("//*[@data-pid='QuoteReferenceTitle']")));

								WebElement element = driver.findElement(By
										.xpath(Locator));
								JavascriptExecutor js = (JavascriptExecutor) driver;
								js.executeScript("arguments[0].click();",
										element);
								// }
							} catch (Exception e1) {

								WebElement element = driver.findElement(By
										.xpath(Locator));
								JavascriptExecutor js = (JavascriptExecutor) driver;
								js.executeScript("arguments[0].click();",
										element);

							}

						}

					}

				}

			} else {

				build.moveToElement(driver.findElement(By.id(Locator))).build()
				.perform();

				int count = 0;
				while (count < 5) {
					try {

						driver.findElement(By.id(Locator)).click();
						count = count + 5;
					} catch (StaleElementReferenceException e) {

						count = count + 1;

					}

				}

			}

		} else if (Tag.compareTo("Input") == 0) {

			if (Locator.contains("/")) {

				// current date, Policy starte date
				if (ExcelUtils.getCellData(Question, testr, testc)
						.equalsIgnoreCase("TODAY()")) {

					data_value = ExcelUtils.getCurrentDate(testr, testc);

				}

				if (XSSFCell.CELL_TYPE_NUMERIC == ExcelUtils.getCellType(testr,
						testc).getCellType()) {

					// Read date

					if (DateUtil.isCellDateFormatted(ExcelUtils.getCellType(
							testr, testc))) {

						data_value = ExcelUtils.getDate(testr, testc);

					}
				}

				int count = 0;

				while (count < 3) {
					try {

						if (Question.equalsIgnoreCase("Telephone Number")) {
							driver.findElement(By.xpath(Locator)).clear();
							int x = 0;
							String c;
							do {
								c = "" + data_value.charAt(x);
								driver.findElement(By.xpath(Locator)).sendKeys(
										c);
								x++;
							} while (x < data_value.length());
						}

						Thread.sleep(1000);
						driver.findElement(By.xpath(Locator)).clear();
						driver.findElement(By.xpath(Locator)).sendKeys(
								data_value);
						Thread.sleep(500);
						driver.findElement(By.xpath(Locator))
						.sendKeys(Keys.TAB);
						driver.findElement(By.xpath(Locator))
						.sendKeys(Keys.TAB);

						count = count + 3;
					} catch (Exception e) {
						count = count + 1;

					}
				}

			} else {

				driver.findElement(By.id(Locator)).clear();

				driver.findElement(By.id(Locator)).sendKeys(data_value);
				Thread.sleep(500);
				driver.findElement(By.id(Locator)).sendKeys(Keys.TAB);
				driver.findElement(By.id(Locator)).sendKeys(Keys.TAB);

			}

		} else if (Tag.compareTo("Dropdown") == 0) {

			if (Locator.contains("/")) {

				if (!(driver.findElement(By.xpath(Locator)).isDisplayed())) {

				}

				wait.until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath(Locator)));
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath(Locator)));
				new Select(driver.findElement(By.xpath(Locator)))
				.selectByVisibleText(data_value);

			} else

			{
				if (!(driver.findElement(By.id(Locator)).isDisplayed())) {

				}

				wait.until(ExpectedConditions.visibilityOfElementLocated(By
						.id(Locator)));
				wait.until(ExpectedConditions.elementToBeClickable(By
						.id(Locator)));
				new Select(driver.findElement(By.id(Locator)))
				.selectByVisibleText(data_value);
			}

		}
	}
}
