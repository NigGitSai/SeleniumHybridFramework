package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Factory.Report;

public class ExcelReader extends Report  {

	static String filePath="";
	static String sheetname = "";
	static FileInputStream fis;
	static XSSFWorkbook workbook ;
	static XSSFSheet sheet;
	static XSSFRow row;
	static XSSFCell cell;
	static int rowCount;
	static int colCount;
	static int rowNO;
	static String colName;
	static int colNO;
	static String cellValue ="";
	static CellType cellType;
	static boolean writeDataStatus = false;
	
	Logger log ;
	public  ExcelReader(String filePath)
	{
		this.filePath = filePath;
		log = Logger.getLogger(ExcelReader.class);
	}
	
	
	public synchronized void initializeExcel(String sheetName) throws IOException
	{
		 fis= new FileInputStream(filePath);
		 workbook = new XSSFWorkbook(fis);
		 sheet = workbook.getSheet(sheetName);
		 log.info( "Excel is intialized");
		 testStatus("info", "Excel is intialized", "Not req");
	}
	
	public synchronized int returnRowCount() {
		rowCount = sheet.getLastRowNum();
		return rowCount;
		
	}
	public synchronized int returnColCount(int rowNO) {
		colCount = sheet.getRow(rowNO).getLastCellNum();
		return colCount;
		
	}
	
	public synchronized int returnColumnNO(String colName)
	{
		colNO=-1;
		colCount = sheet.getRow(0).getLastCellNum();
		for(int i=0; i<colCount; i++)
		{
			if(sheet.getRow(0).getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
			{
				colNO = i;
				break;
			}
			
		}
		return colNO;
	}
	
	public synchronized void closeExcelWorkbook() throws IOException
	{
		workbook.close();
		fis.close();
	}
	public synchronized String readCellData(int rowNO, String colName, String sheetName) throws IOException
	{
		initializeExcel(sheetName);
		colCount = sheet.getRow(0).getLastCellNum();
		for(int i=0; i<colCount; i++)
		{
			if(sheet.getRow(0).getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
			{
				colNO = i;
				break;
			}
		}
		
		cell = sheet.getRow(rowNO).getCell(colNO);
		cellType =cell.getCellType();
		switch(cellType)
		{
		case STRING:
		{
			cellValue = cell.getStringCellValue();
			break;
			
		}
		case NUMERIC:
		{
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		}
		case BOOLEAN:
		{
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		}
		default :
		{
			System.out.println(" no matching type");
			break;
		}

		}
		closeExcelWorkbook();
		return cellValue;
		
	}
	public synchronized String getCellData(int rowNO, int colNo,String sheetName) throws IOException
	{
		initializeExcel(sheetName);
		colCount = sheet.getRow(0).getLastCellNum();
		/*for(int i=0; i<colCount; i++)
		{
			if(sheet.getRow(0).getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
			{
				colNO = i;
				break;
			}
		}*/
		
		cell = sheet.getRow(rowNO).getCell(colNo);
		cellType =cell.getCellType();
		switch(cellType)
		{
		case STRING:
		{
			cellValue = cell.getStringCellValue();
			break;
			
		}
		case NUMERIC:
		{
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		}
		case BOOLEAN:
		{
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		}
		default :
		{
			System.out.println(" no matching type");
			break;
		}

		}
		closeExcelWorkbook();
		return cellValue;
		
	}
	public synchronized String getCellData(int rowNO, String columnName,String sheetName) throws IOException
	{
		initializeExcel(sheetName);
		colNO = -1;
		colCount = sheet.getRow(0).getLastCellNum();
		for(int i=0; i<colCount; i++)
		{
			if(sheet.getRow(0).getCell(i).getStringCellValue().trim().equalsIgnoreCase(columnName))
			{
				colNO = i;
				System.out.println("Column NO "+colNO);
				break;
			}
		}
		if(colNO==-1)
		{
			System.out.println("No column available need to create new column");
			 log.info( "Column Name does not exists in excel sheet passed");
			 testStatus("info", "Column Name does not exists in excel sheet passed", "Not req");
			
		}
		else
		{
			
		cell = sheet.getRow(rowNO).getCell(colNO);
		cellType =cell.getCellType();
		switch(cellType)
		{
		case STRING:
		{
			cellValue = cell.getStringCellValue();
			break;
			
		}
		case NUMERIC:
		{
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		}
		case BOOLEAN:
		{
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		}
		default :
		{
			System.out.println(" no matching type");
			break;
		}

		}}
		
		return cellValue;
		
	}
	public synchronized boolean writeCellData(int rowNO, String columnName, Object value, String sheetName) throws IOException
	{
		colNO = -1;
		initializeExcel(sheetName);
		colCount = sheet.getRow(0).getLastCellNum();
		System.out.println("No of columns "+colCount);
		for(int i=0; i<colCount; i++)
		{
			if(sheet.getRow(0).getCell(i).getStringCellValue().trim().equalsIgnoreCase(columnName))
			{
				colNO = i;
				System.out.println("Column NO "+colNO);
				break;
			}
		}
		if(colNO==-1)
		{
			 log.info( "Column Name does not exists in excel sheet passed");
			 testStatus("info", "Column Name does not exists in excel sheet passed", "Not req");
			System.out.println("No column available need to create new column");
			colNO = colCount;	
			System.out.println("Column NO "+colNO);
			sheet.getRow(0).createCell(colNO).setCellValue(columnName);
			log.info( "Created Column with Column Name");
			 testStatus("info", "Created Column with Column Name", "Not req");
		}
		System.out.println("Row NO "+rowNO);
		rowNO = rowNO -1;
		if(sheet.getRow(rowNO)==null)
		{
			row = sheet.createRow(rowNO);
		}
		else
		{
			row = sheet.getRow(rowNO);
		}
		
		cell = row.createCell(colNO);
		if(value instanceof String)
		{
			cell.setCellValue((String) value);
			writeDataStatus = true;
		}
		else if(value instanceof Integer)
		{
			cell.setCellValue((Integer) value);
			writeDataStatus = true;
		}
		else if(value instanceof Boolean)
		{
			cell.setCellValue((Boolean) value);
			writeDataStatus = true;
		}
		else
		{
			System.out.println("No data written");
		}
		FileOutputStream fo = new FileOutputStream(filePath);
		workbook.write(fo);
		workbook.close();
		fo.close();
		
		return writeDataStatus;
		
	}
	
	
}
