package com.landg.lqa.utility;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.LogStatus;

public class Init {

	static int i;

	@BeforeSuite
	public static void setObjectRepository() throws Exception {
		try {

			ExcelUtils.setExcelFile(System.getProperty("user.dir")
					+ "\\Object Repository" + "\\Object Repository.xlsx",
					"objectrepository");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setTestDataSheet(String Input, String testPack)
			throws Exception {
		try {

			String spath = Input;

			ExcelUtils.setExcelFile(spath, testPack);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setTestResultParameterSheet() throws Exception {
		try {

			ExcelUtils.setExcelFile(System.getProperty("user.dir") + "\\Config"
					+ "\\ConfigSheet.xlsx", "TestReport");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setObjectRepositoryConfigSheet() throws Exception {
		try {

			ExcelUtils.setExcelFile(System.getProperty("user.dir") + "\\Config"
					+ "\\ConfigSheet.xlsx", "ObjectRepository");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setUrlSheet() throws Exception {
		try {

			ExcelUtils.setExcelFile(System.getProperty("user.dir") + "\\Config"
					+ "\\ConfigSheet.xlsx", "Url");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setEmailIDSheet() throws Exception {
		try {

			ExcelUtils.setExcelFile(System.getProperty("user.dir") + "\\Config"
					+ "\\ConfigSheet.xlsx", "EmailId");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
