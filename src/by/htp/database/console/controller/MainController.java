package by.htp.database.console.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import by.htp.database.console.view.ConsoleOutput;
import by.htp.database.console.view.impl.SimpleConsoleOutput;
import by.htp.database.domain.vo.Catalog;
import by.htp.database.service.CatalogService;
import by.htp.database.service.impl.DefaultCatalogImpl;

public class MainController {

	public static void main(String[] args) {
		ConsoleOutput output= new SimpleConsoleOutput();
		CatalogService service = new DefaultCatalogImpl();
		
	//	Catalog catalog = service.getCatalog();
	//	output.printCatalog(catalog);
		
	//	Catalog catalogDate = service.getCatalog(new Date());
	//	output.printCatalog(catalogDate);
		
		Catalog catalogFile = service.getCatalogFromFile();
		output.printCatalog(catalogFile);
		
		DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		Date d;
		try {
			d = format.parse("2004.01.01");
			Catalog catalogFileDate = service.getCatalogFromFileByDate(d);
			output.printCatalog(catalogFileDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}

}
