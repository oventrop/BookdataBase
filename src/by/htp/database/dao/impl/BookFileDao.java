package by.htp.database.dao.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import by.htp.database.dao.BookDao;
import by.htp.database.domain.entity.Book;

public class BookFileDao implements BookDao {
	@Override
	public List<Book> fetchBooks() {
		List<Book> booksList = new LinkedList<Book>();

		try {
			FileReader fr = new FileReader("D:/Books.txt");
			BufferedReader br = new BufferedReader(fr);

			while (br.ready()) {
				Book book = null;
				String s = (String) br.readLine();
				String[] words = s.split(",");
				int id = Integer.parseInt(words[0]);
				String name = words[1];
				int pages = Integer.parseInt(words[2]);

				Date d = (new SimpleDateFormat("yyyy.MM.dd").parse(words[3]));
				book = new Book(id, name, pages, d);
				booksList.add(book);

			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return booksList;

	}

	@Override
	public Book fetchSingleBook(int numBook) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSingleBook(int numBook) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Book> fetchBooksLikeQuery(String s, int pages) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @Override public Set<Book> fetchBooksDate() { Set<Book> booksList = new
	 * TreeSet<Book>(new Comparator<Book>() {
	 * 
	 * @Override public int compare(Book o1, Book o2) { return
	 * o1.getDate().compareTo(o2.getDate());
	 * 
	 * } });
	 * 
	 * try { FileReader fr = new FileReader("D:/Books.txt"); BufferedReader br =
	 * new BufferedReader(fr);
	 * 
	 * 
	 * while (br.ready()) { Book book = null; String s = (String) br.readLine();
	 * String[] words = s.split(","); int id = Integer.parseInt(words[0]);
	 * String name = words[1]; int pages = Integer.parseInt(words[2]);
	 * SimpleDateFormat format = new SimpleDateFormat("YYYY.mm.dd"); Date d =
	 * format.parse(words[3]); book = new Book(id, name, pages, d);
	 * booksList.add(book); } br.close();
	 * 
	 * } catch (IOException e) { e.printStackTrace(); } catch (ParseException e)
	 * { // TODO Auto-generated catch block e.printStackTrace(); } return
	 * booksList; }
	 */

	@Override
	public List<Book> fetchBooks(Date date) {
		List<Book> booksList = new LinkedList<Book>();

		try {
			FileReader fr = new FileReader("D:/Books.txt");
			BufferedReader br = new BufferedReader(fr);

			while (br.ready()) {
				Book book = null;
				String s = (String) br.readLine();
				String[] words = s.split(",");
				int id = Integer.parseInt(words[0]);
				String name = words[1];
				int pages = Integer.parseInt(words[2]);

				Date d = (new SimpleDateFormat("yyyy.MM.dd").parse(words[3]));
				book = new Book(id, name, pages, d);
				if (book.getDate().compareTo(date) < 0) {
					booksList.add(book);
				}

			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return booksList;

	}
}
