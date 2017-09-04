package by.htp.database.dao;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import by.htp.database.dao.impl.BookFileDao;
import by.htp.database.domain.entity.Book;

public class BookDaoFileTest {

	private BookFileDao daoFile = new BookFileDao();

	@Test
	public void testBooksFromFile() {
		int z = 0;
		int x = 0;
		List<Book> b = daoFile.fetchBooks();
		Iterator<Book> it = b.iterator();
		while (it.hasNext()) {
			it.next();
			x++;
		}
		try {
			FileReader fr = new FileReader("D:/Books.txt");
			BufferedReader br = new BufferedReader(fr);

			while (br.ready()) {
				br.readLine();
				z++;
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertTrue("Quantity of books doesnt match", x == z);

	}

	@Test
	public void testBooksFromFileByDate() {
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		int x = 0;
		try {
			Date date = formatter.parse("05.01.2004");

			List<Book> b = daoFile.fetchBooks(date);
			Iterator<Book> it = b.iterator();
			Book book = null;

			while (it.hasNext()) {
				book = it.next();
				if (book.getDate().compareTo(date) >= 0) {
					x = 1;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		assertTrue("Dates of books doesnt match query", x == 0);

	}
}
