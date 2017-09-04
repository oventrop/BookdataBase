package by.htp.database.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import by.htp.database.dao.BookDao;
import by.htp.database.domain.entity.Book;

public class BookMySqlDao implements BookDao {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "ךששו";
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

	private static final String SQL_SELECT_BOOKS = "select * from book";
	private static final String SQL_SELECT_BOOKS_DATE = "select * from book where date<?";
	private static final String SQL_SELECT_BOOK_ID = "select * from book where id = ?";
	private static final String SQL_DELETE_BOOK_ID = "delete from book where id = ?";

	private static final String SQL_BOOKS_LIKE = "SELECT * FROM BOOK WHERE book_name LIKE ? and pages>?";

	// public static void main(String[] args) {
	// int delBook = 1;
	// BookMySqlDao dao = new BookMySqlDao();
	// dao.fetchBooks();
	// System.out.println("OutBase:");
	// Book deletedBook = dao.fetchSingleBook(delBook);
	// dao.deleteSingleBook(delBook);
	// List<Book> b = dao.fetchBooksLikeQuery("%Boo%", 100);
	// // Book book = null;
	// Iterator<Book> it = b.iterator();
	// while (it.hasNext()) {
	// book = it.next();
	// String t = book.getTitle();
	// int x = book.getPages();
	// if (t.contains("oo") && x > 100) {
	// / System.out.println(1); } else {
	// System.out.println(2);
	// }}}

	@Override
	public List<Book> fetchBooks() {
		List<Book> books = new ArrayList<Book>();
		Connection con = null;
		try {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			Statement st = con.createStatement();

			// st.executeUpdate(SQL חאןנמס);

			// PreparedStatement ps = con.prepareStatement(SQL_SELECT_BOOK_ID);
			// ps.setInt(1, 3);

			ResultSet rs = st.executeQuery(SQL_SELECT_BOOKS);
			Book book = null;
			while (rs.next()) {
				book = new Book();

				int id = rs.getInt(1);
				String title = rs.getString("book_name");
				int pages = rs.getInt("pages");
				Date date = rs.getDate("date");
				System.out.println(id + " " + title + " " + pages + " " + date);
				book.setId(id);
				book.setTitle(title);
				book.setPages(pages);
				book.setDate(date);

				books.add(book);

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
		return books;
	}

	@Override
	public Book fetchSingleBook(int x) {

		Book book = null;
		Connection con = null;
		try {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			// PreparedStatement ps = con.prepareStatement(SQL_SELECT_BOOK_ID);
			// ps.setInt(1, x);
			// ResultSet rs = ps.executeQuery();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL_SELECT_BOOKS);

			book = new Book();

			int id = 0;
			while (id != x) {
				rs.next();
				id = rs.getInt(1);
			}
			// int id = rs.getInt(1);
			String title = rs.getString("book_name");
			int pages = rs.getInt("pages");
			System.out.println(id + " " + title + " " + pages + " ");

			book.setId(id);
			book.setTitle(title);
			book.setPages(pages);

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
		return book;
	}

	@Override
	public void deleteSingleBook(int x) {

		Connection con = null;
		try {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

			PreparedStatement ps2 = con.prepareStatement(SQL_DELETE_BOOK_ID);
			ps2.setInt(1, x);

			ps2.executeUpdate();

		} catch (NullPointerException e) {
			e.printStackTrace();
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
	}

	@Override
	public List<Book> fetchBooksLikeQuery(String s, int pages) {
		List<Book> books = new ArrayList<Book>();
		Connection con = null;
		try {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			Statement st = con.createStatement();

			PreparedStatement ps = con.prepareStatement(SQL_BOOKS_LIKE);
			ps.setString(1, s);
			ps.setInt(2, pages);

			ResultSet rs = ps.executeQuery();
			Book book = null;
			while (rs.next()) {
				book = new Book();

				int id = rs.getInt(1);
				String title = rs.getString("book_name");
				int p = rs.getInt("pages");
				System.out.println(id + " " + title + " " + p + " ");
				book.setId(id);
				book.setTitle(title);
				book.setPages(p);

				books.add(book);

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
		return books;
	}

	@Override
	public List<Book> fetchBooks(Date date) {
		List<Book> books = new ArrayList<Book>();
		Connection con = null;
		try {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			Statement st = con.createStatement();

			PreparedStatement ps = con.prepareStatement(SQL_SELECT_BOOKS_DATE);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			ps.setDate(1, sqlDate);

			ResultSet rs = ps.executeQuery();
			Book book = null;
			while (rs.next()) {
				book = new Book();

				int id = rs.getInt(1);
				String title = rs.getString("book_name");
				int p = rs.getInt("pages");
				Date bookDate = rs.getDate("date");
				// System.out.println(id + " " + title + " " + p + " "+
				// bookDate);
				book.setId(id);
				book.setTitle(title);
				book.setPages(p);
				book.setDate(bookDate);

				books.add(book);

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
		return books;
	}

}
