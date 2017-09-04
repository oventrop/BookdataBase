package by.htp.database.service;



import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import by.htp.database.domain.vo.Catalog;
import by.htp.database.service.impl.DefaultCatalogImpl;

public class CatalogServiceTest {
	private static CatalogService service;
	
	@BeforeClass
	public static void initCatalogServiceTest() {
		service = new DefaultCatalogImpl();
	}

	@Test
	public void testCatalogNotNull() {
		Catalog catalog = service.getCatalog();
		assertNotNull ("The catalog was not created", catalog);
	}
	@Test
	public void testCatalogEmptyFields() {
		Catalog catalog = service.getCatalog();
		assertNotNull("Catalog title was not filled", catalog.getTitle());
		assertNotNull("Date of crtn was not filled",catalog.getDateCreation());
		assertNotNull("Book list was not attached to catalog item",catalog.getBooks());
	}
	@Test
	public void testCatalogEmptyBooks() {
		Catalog catalog = service.getCatalog();
		assertNotNull("Book list was not attached to catalog item",catalog.getBooks());
		assertTrue ("The books list is empty", catalog.getBooks().size()>0);
		
	}
}
