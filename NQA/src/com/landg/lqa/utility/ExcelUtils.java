package com.landg.lqa.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.landg.lqa.utility.*;

public class ExcelUtils {
	public static XSSFSheet ExcelWSheet;
	public static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;

	public static void setExcelFile(String Path, String SheetName)
			throws Exception {
		try {

			FileInputStream ExcelFile = new FileInputStream(Path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			// System.out.println(Path);
			System.out.println("data sheet can't open");
		}

	}

	public static XSSFCell getCellType(int RowNum, int ColNum) throws Exception {
		FormulaEvaluator evaluator;
		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

		} catch (Exception e) {

		}
		return Cell;

	}

	public static String getCellData(String Question, int RowNum, int ColNum)
			throws Exception {
		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String Data = Cell.toString();

			if (Data.length() < 50) {
				if (Data.contains(".9") || Data.contains(".0")
						|| (Data.endsWith("E10"))) {
					long CellData = (long) Cell.getNumericCellValue();
					Data = Long.toString(CellData);
					// System.out.println(Data);
					return Data;
				}

			}
			// System.out.println(Cell.toString());
			return Cell.toString();

		} catch (Exception e) {
			// e.printStackTrace();

		}
		return "";

	}

	public static String getURL(String Input, int RowNum, int ColNum,
			String environment, String testPack) throws Exception {
		try {
			String journey, journey_i = null, environment_i = null;
			int i, j;
			Init.setTestDataSheet(Input, testPack);
			journey = ExcelWSheet.getRow(RowNum).getCell(ColNum).toString();

			Init.setUrlSheet();

			int urlRow, urlCol;
			i = 0;
			j = 1;

			do {
				journey_i = ExcelWSheet.getRow(i).getCell(j).toString();
				urlCol = j;
				j = j + 1;

			} while (!(journey_i.equalsIgnoreCase(journey)));

			i = 1;
			j = 0;

			do {
				environment_i = ExcelWSheet.getRow(i).getCell(j).toString();
				i = i + 1;
				urlRow = i - 1;

			} while (!(environment_i.equalsIgnoreCase(environment)));

			// System.out.println(urlRow);
			// System.out.println(urlCol);
			Cell = ExcelWSheet.getRow(urlRow).getCell(urlCol);

			return Cell.toString();

		} catch (Exception e) {

		}
		return "";

	}

	public static String getCellDateMonth(int RowNum, int ColNum)
			throws Exception {
		String month;
		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		Date CellData = Cell.getDateCellValue();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(CellData);
		int iMonth = calendar.get(Calendar.MONTH);
		switch (iMonth) {

		case 0:
			month = "January";
			break;
		case 1:
			month = "February";
			break;
		case 2:
			month = "March";
			break;
		case 3:
			month = "April";
			break;
		case 4:
			month = "May";
			break;
		case 5:
			month = "June";
			break;
		case 6:
			month = "July";
			break;
		case 7:
			month = "August";
			break;
		case 8:
			month = "September";
			break;
		case 9:
			month = "October";
			break;
		case 10:
			month = "November";
			break;
		case 11:
			month = "December";
			break;
		default:
			month = "Not a valid month";
			break;
		}
		return month;
	}

	public static int getCellDateYear(int RowNum, int ColNum) throws Exception {

		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		Date CellData = Cell.getDateCellValue();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(CellData);
		int Year = calendar.get(Calendar.YEAR);
		return Year;
	}

	public static int getCellDateMonth_(int RowNum, int ColNum)
			throws Exception {

		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		Date CellData = Cell.getDateCellValue();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(CellData);
		int month = calendar.get(Calendar.MONTH);
		// System.out.println("Month:::");
		// System.out.println(month);
		return month + 1;
	}

	public static int getCellDateDay(int RowNum, int ColNum) throws Exception {

		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		;
		Date CellData = Cell.getDateCellValue();
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(CellData);
		int Day = calendar.get(Calendar.DAY_OF_MONTH);

		return Day;
	}

	public static void setCellData(String Path, int RowNum, int ColNum,
			String status) throws Exception {
		try {
			Row = ExcelWSheet.getRow(RowNum);

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(status);
			} else {
				Cell.setCellValue(status);
			}

			XSSFCellStyle st = ExcelWBook.createCellStyle();

			Font font = ExcelWBook.createFont();
			font.setColor((short) 10);
			st.setFont(font);

			Cell.setCellStyle(st);

			FileOutputStream fileOut = new FileOutputStream(Path);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {

			throw e;
		}
	}

	public static String getCurrentDate(int RowNum, int ColNum)
			throws Exception {
		String data_value = "";

		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		Date date = calendar.getTime();
		int day = calendar.get(Calendar.DATE);
		// Note: +1 the month for current month
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		if (day < 10) {
			data_value = "0" + day;
		} else {
			data_value = "" + day;

		}
		if (month < 10) {
			data_value = data_value + "0" + month;
		} else {
			data_value = data_value + month;
		}

		data_value = data_value + year;

		return data_value;

	}

	public static String getDate(int testr, int testc) throws Exception {
		String data_value;
		int data = ExcelUtils.getCellDateDay(testr, testc);
		// System.out.println(data);

		if (data < 10) {
			data_value = "0" + data;
		} else {
			data_value = "" + data;
		}

		data = ExcelUtils.getCellDateMonth_(testr, testc);

		// System.out.println(data);

		if (data < 10) {
			data_value = data_value + "0" + data;
		} else {
			data_value = data_value + data;
		}

		data_value = data_value + ExcelUtils.getCellDateYear(testr, testc);
		// System.out.println(data);
		return data_value;

	}

}