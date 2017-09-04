package by.htp.database.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import by.htp.database.dao.impl.BookMySqlDao;
import by.htp.database.domain.entity.Book;

public class BookDaoTest {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "ךששו";
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

	private static final String SQL_SELECT_BOOKS = "select * from book";
	private static final String SQL_SELECT_BOOK_ID = "select * from book where id = ?";
	private static final String SQL_DELETE_BOOK_ID = "delete from book where id = ?";

	private static BookDao dao;

	@BeforeClass
	public static void initDao() {
		dao = new BookMySqlDao();
	}

	/*@Test
	public void testNullList() {
		List<Book> books = dao.fetchBooks();
		assertNotNull("Returned list is null", books);
	}

	@Test
	public void testEmptyList() {
		List<Book> books = dao.fetchBooks();

		assertTrue("Returned list is empty, no books", books.size() > 0);

	}

	@Test
	public void testEmptyBook() {
		Book book = dao.fetchSingleBook(1);

		assertTrue("Returned list is empty, no books", book.getId() != 0);
	}

	@Test
	public void testDeleteBook() {
		int x = 2;
		int y = testBaseLength();
		dao.deleteSingleBook(x);
		int z = testBaseLength();
		assertTrue("Book is still in base", testBase(x) != 0);
		assertTrue("DBsize did not change", y - z != 0);
	}*/

	@Test
	public void testBooksQuery() {
		String s = "Boo";
		int pages = 100;
		int z=0;
		List<Book> b = dao.fetchBooksLikeQuery("%Boo%", pages);
		Book book = null;
		Iterator<Book> it = b.iterator();
		while (it.hasNext()) {
			book = it.next();
			String t = book.getTitle();
			int x = book.getPages();
			if (t.contains(s) && x > pages) {
				z = 1;
			} else {
				z = 0;
			}

		}
		assertTrue("Some books dont match query", z == 1);

	}

	public static int testBase(int x) {
		Connection con = null;
		try {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL_SELECT_BOOKS);
			int id = 0;
			while (rs.next()) {
				id = rs.getInt(1);
				if (id == x) {
					return 0;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return 1;
	}

	public static int testBaseLength() {
		Connection con = null;
		int i = 0;
		try {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL_SELECT_BOOKS);

			while (rs.next()) {
				i++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return i;
	}
}
