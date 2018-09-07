package library;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import library.Author;

public class AuthorUnitTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	  
	  
	@Test
	public void testSingleParameterAuthorConstructor() {
	    exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("Author parameters should not be empty or null");
		new Author("");
	}

	@Test
	public void testMultipleParameterAuthorConstructor() {
	    exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("Author parameters should not be empty or null");
		new Author("", "", "");
	}

	@Test
	public void testAuthorEquality() {
		Author author_1 = new Author("A", "B", "SSN");
		Author author_2 = new Author("A", "B", "SSN");
		assertEquals("Equal", true, author_1.equals(author_2));

		Author author_3 = new Author("B", "C", "SSN");
		assertEquals("NotEqual", false, author_1.equals(author_3));
	}
}
