package library;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import library.Author;
import library.Book;
import library.Shelf;


public class ShelfUnitTest {


	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testPositiveCpacityShelfConstructor() {
	    exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("Shelf parameters are not valid");
	    new Shelf(0, new ArrayList<Book>());
	}
	
	
	@Test
	public void testExceedCapacityShelfConstructor() {
	    exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("Shelf parameters are not valid");
	    
	    ArrayList<Book> books = new ArrayList<Book>();
		ArrayList<Author> authList = new ArrayList<Author>();
		authList.add(new Author("A", "B", "SSN"));
	    Book book1 = new Book("title1", "ISBN1", 1984, 300, authList);	
	    Book book2 = new Book("title2", "ISBN2", 1984, 300, authList);	
	    books.add(book1);
	    books.add(book2);	    
	    
	    new Shelf(1, books);
	}

	@Test
	public void testShelfCalleeSideEffects() {

	    ArrayList<Book> books = new ArrayList<Book>();
		ArrayList<Author> authList = new ArrayList<Author>();
		authList.add(new Author("A", "B", "SSN"));
	    Book book1 = new Book("title", "ISBN", 1984, 300, authList);	
	    books.add(book1);
	    
	    Shelf shelf = new Shelf(2, books);
	    
	    ArrayList<Book> booksInShelf = shelf.getBooksInShelf();
	    booksInShelf.add(new Book("title", "ISBN", 1984, 300, authList));
	    
	    assertEquals("No side effects from callee. Use appropriate method for book insertion", 
	    		true, shelf.getNumerOfBooksInShelf() ==1 );
	}
}
