package library;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import library.Author;
import library.Book;
import library.Library;
import library.Shelf;

public class LibraryStatisticsTest {

	@Test
	public void testLibraryStatistics() {
		
		Author author1 = new Author("Name1", "Surn1", "342343rfr1");
		Author author2 = new Author("Name2", "Surn2", "342343rfr2");
		Author author3 = new Author("Name3", "Surn3", "342343rfr3");

		ArrayList<Author> authorList1 = new ArrayList<Author>();
		authorList1.add(author1);
		authorList1.add(author2);
		
		ArrayList<Author> authorList2 = new ArrayList<Author>();
		authorList2.add(author2);
		authorList2.add(author3);
		
		Book b1 = new Book("PCD1718", "ISBN1", 2017, 200, authorList1);
		Book b11 = new Book("PCD1819", "ISBN2", 2018, 300, authorList2);		

		ArrayList<Book> bookList1 = new ArrayList<Book>();
		bookList1.add(b1);

		ArrayList<Book> bookList2 = new ArrayList<Book>();
		bookList2.add(b11);
		
		Shelf shelf1 = new Shelf(1, bookList1);
		Shelf shelf2 = new Shelf(1, bookList2);	
		
		Library lib = new Library();
		lib.addShelf(shelf1);
		lib.addShelf(shelf2);
		
		assertEquals("Equal", true, lib.getBooksYearRangeInLibrary()[0] == 2017);
		assertEquals("Equal", true, lib.getBooksYearRangeInLibrary()[1] == 2018);
		assertEquals("Equal", true, lib.getTotalNumberOfDistinctAuthorsInLibrary() == 3);
		assertEquals("Equal", true, lib.getTotalNumberOfBookPagesInLibrary() == 500);
	}
	
}
