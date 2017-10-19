package com.landg.lqa.utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.xwpf.usermodel.Document;

import com.landg.lqa.testflow.TestFlow02;
import com.landg.lqa.utility.*;
import com.relevantcodes.extentreports.ExtentReports;

public class Utils {
	public static WebDriver driver = null;
	public static Properties prop = new Properties();
	public static String folderName = "", sTestCaseName;
	public static int count = 0, Screenscount;
	public static String screenshotPath = "";
	private static String baseUrl;
	public static int screesnhot_count = 1;
	public static URL resource;

	static String env, address = "";
	private static int pass_var = 0, test_var = 0;
	public int iTestRow;
	static int r = 1, c = 1;

	public static void resetBrowserZoom() throws Exception {
		try {
			new WebDriverWait(driver, 120).until(ExpectedConditions
					.presenceOfElementLocated(By.tagName("html")));
			Thread.sleep(2500);
			// to reset zoom level to 100%
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
			html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
			Thread.sleep(2000);
		} catch (Exception e) {

			throw (e);
		}
	}

	public static void loadPropertyFile() throws Exception {
		try {
			String filepath = System.getProperty("user.dir")
					+ "\\config.properties";

			InputStream in = new FileInputStream(filepath);
			prop.load(in);
		} catch (IOException e) {

			throw e;
		}
	}

	public static WebDriver OpenFirefoxBrowser() throws Exception {
		try {

			driver = new FirefoxDriver();

			driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);

			driver.manage().window().maximize();

			resetBrowserZoom();

			count = 0;

		} catch (Exception e) {

		}
		return driver;
	}

	public static WebDriver OpenIEBrowser() throws Exception {
		try {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\Drivers"
							+ "\\IEDriverServer.exe");

			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\Drivers"
							+ "\\IEDriverServer.exe");
			DesiredCapabilities capabilities = DesiredCapabilities
					.internetExplorer();

			capabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			capabilities.setCapability("ignoreZoomSetting", true);
			driver = new InternetExplorerDriver(capabilities);

			driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);

			driver.manage().window().maximize();

			resetBrowserZoom();

			count = 0;

		} catch (Exception e) {

		}
		// System.out.println(driver);
		return driver;
	}

	public static WebDriver OpenChromeBrowser() throws Exception {
		try {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\Drivers"
							+ "\\chromedriver.exe");

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\Drivers"
							+ "\\chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability("nativeEvents", false);
			driver = new ChromeDriver(capabilities);

			// Log.info("New driver instantiated");
			driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
			// Log.info("Implicit wait applied on the driver for 25 seconds");

			driver.manage().window().maximize();

			resetBrowserZoom();

			count = 0;

			Thread.sleep(2000L);

			// Log.info("Web application launched successfully");
		} catch (Exception e) {
			// Log.error("Class Utils | Method OpenBrowser | Exception desc : "
			// +
			// e.getMessage());
		}
		return driver;
	}

	public static WebDriver OpenSafariBrowser() throws Exception {
		try {
			// System.out.println("firefox");

			driver = new SafariDriver();

			// Log.info("New driver instantiated");
			driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
			// Log.info("Implicit wait applied on the driver for 25 seconds");

			driver.manage().window().maximize();

			resetBrowserZoom();

			count = 0;

			// Log.info("Web application launched successfully");
		} catch (Exception e) {

		}
		return driver;
	}

	public static ArrayList<String> setScreenshotCapturePoints(
			WebDriver driver, int r, int c,
			ArrayList<String> Screenshot_CapturePoint, String question)
			throws Exception {

		Init.setTestResultParameterSheet();
		do {
			Screenshot_CapturePoint.add(ExcelUtils.getCellData(question, r, c));
			r = r + 1;
		} while (ExcelUtils.getCellData(question, r, c) != "");

		return Screenshot_CapturePoint;
	}

	public static HashMap<Integer, String> getObjectRepository(
			WebDriver driver, HashMap<Integer, String> objectRepository)
			throws Exception {

		FileInputStream objectFileStream;
		ObjectInputStream objectFile;
		objectFileStream = new FileInputStream(System.getProperty("user.dir")
				+ "\\Object Repository" + "\\Object_0.ser");
		objectFile = new ObjectInputStream(objectFileStream);
		objectRepository = (HashMap<Integer, String>) objectFile.readObject();
		return objectRepository;
	}

	public static String getScreenshotFolderName(WebDriver driver,
			String screenshotFolderName) throws Exception {

		DateFormat df = new SimpleDateFormat("ddMMyy HHmmss");

		df.setTimeZone(TimeZone.getTimeZone("Europe/London"));

		Date dateobj = new Date();
		screenshotFolderName = df.format(dateobj);

		return screenshotFolderName;
	}

	public static ExtentReports createExtentTestReport(WebDriver driver,
			ExtentReports extent, String screenshotFolderName) throws Exception {

		new File(System.getProperty("user.dir") + "Test Report"
				+ "\\NQATestReport" + screenshotFolderName + ".html");
		TestFlow02.extentReportFile = System.getProperty("user.dir")
				+ "\\Test Report" + "\\NQATestReport" + screenshotFolderName
				+ ".html";
		extent = new ExtentReports(TestFlow02.extentReportFile, true);
		return extent;

	}

	public static WebDriver openBrowser(String browser) throws Exception {

		if (browser.compareTo("Firefox") == 0) {

			try {
				driver = Utils.OpenFirefoxBrowser();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (browser.compareTo("Chrome") == 0) {
			try {
				driver = Utils.OpenChromeBrowser();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (browser.compareTo("Safari") == 0) {
			try {
				driver = Utils.OpenSafariBrowser();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				driver = Utils.OpenIEBrowser();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return driver;

	}

	public static String createTestReportFile(String Input) throws Exception {

		DateFormat df = new SimpleDateFormat("ddMMyy HHmmss");
		df.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		Date dateobj = new Date();

		String reportFilename = df.format(dateobj);
		reportFilename = "Report_" + reportFilename + ".xlsx";

		FileUtils.copyFile(new File(Input),
				new File(System.getProperty("user.dir") + "\\Test Report File"
						+ "\\" + reportFilename));
		Input = System.getProperty("user.dir") + "\\Test Report File" + "\\"
				+ reportFilename;

		// Init.setTestDataSheet(Input);
		TestFlow02.testReportfile = Input;
		return Input;

	}

	public static void addCustomisedSteps(String question, WebDriver driver)
			throws Exception {

		// Switching the frame

		if (question.equalsIgnoreCase("Address")
				|| question.equalsIgnoreCase("Search QuoteReference")) {
			Thread.sleep(1000);
			driver.switchTo().frame(0);
		}
		if (question.equalsIgnoreCase("First Name")
				|| question.equalsIgnoreCase("Quote Number")
				|| question.equalsIgnoreCase("View")
				|| question.equalsIgnoreCase("Edit Schedule")
				|| question.equalsIgnoreCase("Change PCD")
				|| question.equalsIgnoreCase("Confirm Changes")
				|| question.equalsIgnoreCase("PCD OK")
				|| question.equalsIgnoreCase("Policy Number")
				|| question.equalsIgnoreCase("Payment Schedule")
				|| question.equalsIgnoreCase("Edit Installment")
				|| question.equalsIgnoreCase("Edit Installment dates")) {
			Thread.sleep(5000);
		}

		if (question.contains("Web Direct Card")) {

			Thread.sleep(1000);

			driver.switchTo().frame(
					driver.findElement(By.xpath("//*[@title='form']")));
			// driver.switchTo().frame(0);
			// System.out.println("frame swirched");
		}

		if (question.contains("Next2")) {
			Thread.sleep(5000);

			driver.switchTo().frame(1);
			Thread.sleep(2000);

		}

		if (question.contains("Previous")) {
			// Waiting for page to load
			Thread.sleep(3000);
			if (driver.findElement(By.xpath("//h1")).getText().length() > 0) {
				Thread.sleep(1000);
			} else {
				// Thread.sleep(50000);
				TestFlow02.wait.until(ExpectedConditions

				.visibilityOfElementLocated(By.xpath("//h1")));
				Thread.sleep(2000);
			}

		}

		if (question.contains("Verification_Quote number")) {

			try {
				TestFlow02.wait
						.until(ExpectedConditions.visibilityOfElementLocated(By
								.xpath("//*[@data-pid='QuoteReferenceTitle']")));
			} catch (Exception e) {
				driver.switchTo().frame(0);
			}

		}

		if (question.contains("Verification_Quote number")
				|| question.contains("UPP Remove")
				|| question.contains("Bicycle Remove All")
				|| question.contains("SPP Remove All")
				|| question.contains("Family legal protection")) {

			Thread.sleep(1000);

			// for reteiveal

			TestFlow02.wait.until(ExpectedConditions
					.visibilityOfElementLocated(By
							.xpath("//*[@data-pid='QuoteReferenceTitle']")));

		}

		if (question.contains("From Inception")) {
			Thread.sleep(1000);

			driver.switchTo().frame(
					driver.findElement(By.xpath("//*[@title='form']")));

		}

		if (question.contains("Change Payment Details")) {
			Thread.sleep(10000);

		}

		if (question.contains("Monthly Direct Debit")
				|| question.contains("Annual card payment")
				|| question.contains("Annual Direct Debit")) {
			Thread.sleep(1000);

			driver.switchTo().frame(0);

		}

		if (question.contains("View Policy Summary")) {
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
		}

		// To come out of tests

		if (question.equalsIgnoreCase("Card number")) {

			// Thread.sleep(2000);
			TestFlow02.wait
					.until(ExpectedConditions
							.frameToBeAvailableAndSwitchToIt("wp-cl-customhtml-iframe"));

			// driver.switchTo().frame();
		}
		if (question.equalsIgnoreCase("Are all of these assumptions true?")) {
			TestFlow02.wait.until(ExpectedConditions

			.visibilityOfElementLocated(By.xpath("//h1")));

		}

		if (question.equalsIgnoreCase("Commit")) {

			Thread.sleep(5000);
		}
		if (question.equalsIgnoreCase("Next")) {

			Thread.sleep(5000);
		}
		return;

	}

	public static String takeScreenshot(WebDriver driver, int sTestCaseName,
			String Output, String foldername, String Browser) throws Exception {
		Thread.sleep(3000);

		File srcfile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(srcfile, new File(Output + "\\" + "Screenshot"
				+ "\\" + "Screenshot" + "TC_" + sTestCaseName + "_"
				+ foldername + "_" + Browser + "\\" + "screenshot" + "TC_"
				+ sTestCaseName + "_" + screesnhot_count + "_" + ".jpg"));
		// Shutterbug.shootPage(driver,ScrollStrategy.BOTH_DIRECTIONS).save("D:\\Automate Test\\project_24062017");

		screesnhot_count++;
		Screenscount = screesnhot_count - 1;

		return (Output + "\\" + "Screenshot" + "\\" + "Screenshot" + "TC_"
				+ sTestCaseName + "_" + foldername + "_" + Browser + "\\"
				+ "screenshot" + "TC_" + sTestCaseName + "_" + Screenscount
				+ "_" + ".jpg");

	}

	public static String getCurrentDate() throws Exception {

		DateFormat df = new SimpleDateFormat("ddMMyy");
		df.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		Date dateobj = new Date();

		String date = df.format(dateobj);

		return date;

	}

	public static void sendPassMail(String extentReportFile, String Status)
			throws Exception {

		Init.setEmailIDSheet();

		do {
			if (address.length() < 1) {
				address = ExcelUtils.getCellData("Question", r, c);
			} else {
				address = address + ","
						+ ExcelUtils.getCellData("Question", r, c);
			}

			r = r + 1;
		} while (ExcelUtils.getCellData("Question", r, c) != "");

		InternetAddress[] adr = null;
		try {
			adr = InternetAddress.parse(address);
		} catch (AddressException e) {
			e.printStackTrace();
		}

		// Sender's email ID needs to be mentioned
		// String from = "keertan.l@landg.com";

		String from = ExcelUtils.getCellData("Question", 1, 0);

		// Assuming you are sending email from localhost
		String host = "KWPIMAIL.lgnet.co.uk";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));

			message.setRecipients(Message.RecipientType.TO, adr);

			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			df.setTimeZone(TimeZone.getTimeZone("Europe/London"));
			Date dateobj = new Date();
			String Date_ = df.format(dateobj);
			message.setSubject("NQA Smoke Test Report for date" + Date_);
			BodyPart messageBodyPart = new MimeBodyPart();
			if (Status.equalsIgnoreCase("Passed")) {
				String path = System.getProperty("user.dir") + "\\Test Report";
				Status = "<div>Hi All,</div><div>Smoke test is completed :</div>"
						+ "<font size=\"5\" color=\"green\">"
						/* + Status */
						+ "</font>"
						+ "<div>Please refer the attatched document & mentioned path for test results</div>"
						+ path;
			} else {
				String path = System.getProperty("user.dir") + "\\Test Report";
				Status = "<div>Hi All,</div><div>Smoke test is completed:</div>"
						+ "<font size=\"5\" color=\"red\">"
						/* + Status */
						+ "</font>"
						+ "<div>Please refer the attatched document & mentioned path for test results</div>"
						+ path;
			}

			messageBodyPart.setContent(Status, "text/html; charset=utf-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			String filename = extentReportFile;
			DataSource source = new FileDataSource(filename);

			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Sent status  message successfully....");
			return;

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
