package by.htp.database.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import by.htp.database.domain.entity.Book;

public interface BookDao {
	List<Book> fetchBooks();
	Book fetchSingleBook(int numBook);
	void deleteSingleBook(int numBook);
	List<Book> fetchBooksLikeQuery(String s, int pages);
	List <Book> fetchBooks(Date date);
	//Set <Book> fetchBooksDate();
	
}
