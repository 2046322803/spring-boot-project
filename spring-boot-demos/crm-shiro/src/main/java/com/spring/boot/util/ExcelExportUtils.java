package com.spring.boot.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExportUtils {

	private static final int XSL_MAX_SHEET_ROW_SIZE = 65536;

	public static void writeExcel(final OutputStream os, final List<?> data, final LinkedHashMap<String, String> header,
			final String sheetName, final ExcelType excelType) throws IOException {
		final Workbook workbook = getWorkBook(excelType);
		final boolean hasHeader = header != null && !header.isEmpty();
		int sheetCount = 1;
		int maxRowSize = data.size();
		if (workbook instanceof HSSFWorkbook) {
			maxRowSize = (hasHeader ? 65535 : XSL_MAX_SHEET_ROW_SIZE);
			sheetCount = (int) Math.ceil(data.size() * 1.0 / maxRowSize);
		}
		if (sheetCount == 1) {
			final Sheet sheet = workbook.createSheet(sheetName);
			setExcelHeader(workbook, sheet, header);
			setExcelContent(sheet, data, header, 0, data.size());
		} else {
			for (int i = 1; i <= sheetCount; ++i) {
				final Sheet sheet2 = workbook.createSheet(sheetName + i);
				setExcelHeader(workbook, sheet2, header);
				final int startIndex = (i - 1) * maxRowSize;
				final int endIndex = (i == sheetCount) ? data.size() : (startIndex + maxRowSize);
				setExcelContent(sheet2, data, header, startIndex, endIndex);
			}
		}
		workbook.write(os);
	}

	private static Workbook getWorkBook(final ExcelType excelType) {
		if (excelType == ExcelType.XLS) {
			return (Workbook) new HSSFWorkbook();
		}
		return (Workbook) new XSSFWorkbook();
	}

	private static void setExcelHeader(final Workbook workbook, final Sheet sheet,
			final LinkedHashMap<String, String> header) {
		if (header != null && !header.isEmpty()) {
			final CellStyle cellStyle = workbook.createCellStyle();
			final Font font = workbook.createFont();
			font.setBold(true);
			cellStyle.setFont(font);
			final Row row = sheet.createRow(0);
			int i = 0;
			for (final Map.Entry<String, String> entry : header.entrySet()) {
				final Cell cell = row.createCell(i++);
				cell.setCellValue((String) entry.getValue());
				cell.setCellStyle(cellStyle);
			}
		}
	}

	private static void setExcelContent(final Sheet sheet, final List<?> data,
			final LinkedHashMap<String, String> header, int startIndex, final int endIndex) {
		for (int size = endIndex - startIndex, rowIndex = 0; rowIndex < size; ++rowIndex) {
			final Object object = data.get(startIndex++);
			if (object instanceof Map) {
				int cellIndex = 0;
				@SuppressWarnings("unchecked")
				final Map<String, Object> rowData = (Map<String, Object>) object;
				if (header != null && !header.isEmpty()) {
					final Row row = sheet.createRow(rowIndex + 1);
					for (final String key : header.keySet()) {
						final Cell cell = row.createCell(cellIndex++);
						cell.setCellValue(rowData.get(key).toString());
					}
				} else {
					final Row row = sheet.createRow(rowIndex);
					for (final Map.Entry<String, Object> entry : rowData.entrySet()) {
						final Cell cell = row.createCell(cellIndex++);
						cell.setCellValue(entry.getValue().toString());
					}
				}
			}
		}
	}

	public enum ExcelType {
		XLS("xls"), XLSX("xlsx");

		private String value;

		private ExcelType(final String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

}
