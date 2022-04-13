package preticketmanager.System;

import java.io.File;
import java.io.IOException;
import jxl.*;
import jxl.read.biff.BiffException;

public class ExcelFileSystem {
	private Workbook workbook;
	private Sheet sheet;
	private String route;
	
	
	public ExcelFileSystem(){
		workbook = null;
		sheet = null;
		route = "./src/preticketmanager/data/";
	}
	public void initRoute(){
		route = "./src/preticketmanager/data/";
	}
	public void setWorkbook(){
		try {
			workbook = Workbook.getWorkbook(new File(route));
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    finally
	    {
	        if( workbook != null)
	        {
	            workbook.close();
	        }
	    }
	}
	public void setWorkbook(String route){
		try {
			workbook = Workbook.getWorkbook(new File(this.route + route));
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setSheet(String sheetName){
		int i;
		for(i=0; i < workbook.getNumberOfSheets() && !sheetName.equals(workbook.getNumberOfSheets()); i++)
			;
		sheet = workbook.getSheet(i);
	}
	public void setSheet(int item){
		sheet = workbook.getSheet(item);
	}
	public void setRoute(String route){
		this.route = route;
	}
	public void addRoute(String route){
		this.route += route + "/";
	}
	public Workbook getWorkbook(){
		return workbook;
	}
	public Sheet getSheet(){
		return sheet;
	}
	public String getRoute(){
		return route;
	}
	public String getCell(int column, int row){
		return sheet.getCell(column, row).getContents();
	}
	public int getRows(){
		return sheet.getRows();
	}
	public int getColumns(){
		return sheet.getColumns();
	}
	public int getNumberOfSheets(){
		return workbook.getNumberOfSheets();
	}
	public void closeFile(){
		if(workbook != null)
			workbook.close();
	}
}