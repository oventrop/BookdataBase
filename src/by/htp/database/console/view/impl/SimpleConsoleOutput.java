package by.htp.database.console.view.impl;

import by.htp.database.console.view.ConsoleOutput;
import by.htp.database.domain.entity.Book;
import by.htp.database.domain.vo.Catalog;

public class SimpleConsoleOutput implements ConsoleOutput {

	@Override
	public void printCatalog(Catalog catalog) {

		if (catalog != null) {
			System.out.println("Catalog title:" + catalog.getTitle());
			System.out.println("Catalog date:" + catalog.getDateCreation());

			for (Book book : catalog.getBooks()) {
				System.out.println(book);
			}
		}
	}

}
