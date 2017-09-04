package by.htp.database.service.impl;

import java.util.Date;
import java.util.List;

import by.htp.database.dao.BookDao;
import by.htp.database.dao.impl.BookFileDao;
import by.htp.database.dao.impl.BookMySqlDao;
import by.htp.database.domain.entity.Book;
import by.htp.database.domain.vo.Catalog;
import by.htp.database.service.CatalogService;

public class DefaultCatalogImpl implements CatalogService {
	private BookDao dao;
	private BookFileDao daoFile;
	{// change to factory
		dao = new BookMySqlDao();
		daoFile = new BookFileDao();
	}

	@Override
	public Catalog getCatalog() {
		Catalog catalog = new Catalog();		
		List <Book> books = dao.fetchBooks();
		catalog.setTitle("NewCatalog");
		catalog.setDateCreation(new Date());
		catalog.setBooks(books);
		return catalog;
	}

	@Override
	public Catalog getCatalog(Date date) {
		Catalog catalog = new Catalog();		
		List <Book> books = dao.fetchBooks(date);
		catalog.setTitle("NewCatalog by date");
		catalog.setDateCreation(new Date());
		catalog.setBooks(books);
		return catalog;
	}
	@Override
	public Catalog getCatalogFromFile() {
		Catalog catalog = new Catalog();		
		List <Book> books = daoFile.fetchBooks();
		catalog.setTitle("NewCatalog");
		catalog.setDateCreation(new Date());
		catalog.setBooks(books);
		return catalog;
	}

	@Override
	public Catalog getCatalogFromFileByDate(Date date) {
		Catalog catalog = new Catalog();		
		List <Book> books = daoFile.fetchBooks(date);
		catalog.setTitle("NewCatalog by date");
		catalog.setDateCreation(new Date());
		catalog.setBooks(books);
		return catalog;
	}
}
