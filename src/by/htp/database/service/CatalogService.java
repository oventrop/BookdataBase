package by.htp.database.service;

import java.util.Date;

import by.htp.database.domain.vo.Catalog;

public interface CatalogService {

	public Catalog getCatalog();
	public Catalog getCatalog(Date date);
	public Catalog getCatalogFromFile();
	public Catalog getCatalogFromFileByDate(Date date);
	
	
}
