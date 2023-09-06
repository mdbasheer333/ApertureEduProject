package com.aperture.enterprises.utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

	XSSFWorkbook xssfWorkbook;
	XSSFSheet xssfSheet;
	XSSFRow xssfRow;
	XSSFRow xssfRowHeader;
	XSSFCell xssfCell;

	String filePath;
	String sheetName;

	public ExcelReader(String sheetName) {
		filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\TestData.xlsx";
		this.sheetName = sheetName;
	}

	public void init() {
		try (FileInputStream fin = new FileInputStream(filePath)) {
			xssfWorkbook = new XSSFWorkbook(fin);
			xssfSheet = xssfWorkbook.getSheet(this.sheetName);
			if (xssfSheet == null) {
				return;
			}
			xssfRowHeader = xssfSheet.getRow(0);
		} catch (IOException e) {
			// throw new RuntimeException(e);
		}
	}

	public void flush() {
		try {
			xssfWorkbook.close();
		} catch (IOException e) {
			// throw new RuntimeException(e);
		}
	}

	public Object[][] getAllData() {
		init();
		Object data[][] = new Object[xssfSheet.getLastRowNum()][xssfSheet.getRow(0).getLastCellNum()];
		if (xssfWorkbook == null || xssfSheet == null) {
			return null;
		}
		for (int i = 1; i <= xssfSheet.getLastRowNum(); i++) {
			xssfRow = xssfSheet.getRow(i);
			if (xssfRow == null) {
				continue;
			}
			for (int j = 0; j < xssfRow.getLastCellNum(); j++) {
				xssfCell = xssfRow.getCell(j);
				if (xssfCell == null) {
					continue;
				}
				data[i-1][j] = xssfCell.getStringCellValue().trim();
			}

		}
		flush();
		return data;
	}

}
