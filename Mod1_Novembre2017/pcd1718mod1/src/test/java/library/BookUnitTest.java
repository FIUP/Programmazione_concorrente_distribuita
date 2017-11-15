package library;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import library.Author;
import library.Book;

public class BookUnitTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testBookConstructor() {
	    exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("Book has all or some parameters which are not valid");
	    new Book("title", "ISBN", 1984, 300, null);		
	}
	
	@Test
	public void testBookEquality() {
		ArrayList<Author> authList1 = new ArrayList<Author>();
		authList1.add(new Author("A", "B", "SSN"));
	    Book book1 = new Book("title", "ISBN", 1984, 300, authList1);		
	    
		ArrayList<Author> authList2 = new ArrayList<Author>();
		authList2.add(new Author("A", "B", "SSN"));
	    Book book2 = new Book("title", "ISBN", 1984, 300, authList2);	
	    
	    assertEquals("Equal", true, book1.equals(book2));
	    
	    
		ArrayList<Author> authList3 = new ArrayList<Author>();
		authList3.add(new Author("A", "B", "SSNxyz"));
	    Book book3 = new Book("title", "ISBN", 1984, 300, authList3);	
	    
	    assertEquals("NotEqual", false, book3.equals(book2));
	}
	
	@Test
	public void testBookCalleSideEffects() {
		
		ArrayList<Author> authList1 = new ArrayList<Author>();
		authList1.add(new Author("A", "B", "SSN"));
	    Book book1 = new Book("title", "ISBN", 1984, 300, authList1);
	    
	    authList1.add(new Author("side", "effect", "callee"));
	    
	    assertEquals("Side effects from the callee should not be possible. For addition use the appropriate method", 
	    		true, book1.getBookAuthorNumber() == 1);
	}


}
