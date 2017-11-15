package library;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class models a book in our library context
 * 
 * */
public class Book implements Cloneable {
	private String title, ISBN;
	private int year, pages;
	ArrayList<Author> authors;

	/**
	 * Sole class constructor.
	 * 
	 * @param title: 	the book title
	 * @param ISBN:  	the book ISBN identifier
	 * @param year:  	the year the book was published
	 * @param pages: 	the number of pages in the book
	 * @param authors:	a list of authors who wrote the book
	 * 
	 * @throws IllegalArgumentException: in case parameters are not valid
	 */
	public Book(String title, String ISBN, int year, int pages, ArrayList<Author> authors) throws IllegalArgumentException{
		if( title == null || ISBN == null || year == 0 || pages == 0 || authors == null ) throw  new IllegalArgumentException("Book has all or some parameters which are not valid");
		this.title = title; this.ISBN = ISBN;
		this.year = year; this.pages = pages;
		this.authors = new ArrayList<>();
		for(Author a: authors){
			this.authors.add(a.clone());
		}
	}
	
	/**
	 * @return the book title
	 * */
	public String getBookTitle() {
		return title;
	}
	
	/**
	 * @return the book ISBN identifier
	 * */
	public String getBookISBN() {
		return ISBN;
	}
	
	/**
	 * @return the book year
	 * */
	public int getBookYear() {
		return year;
	}
	
	/**
	 * @return the book pages
	 * */
	public int getBookPages() {
		return pages;
	}
	
	/**
	 * @return the book authors
	 * */
	public ArrayList<Author> getBookAuthors() {
		return authors;
	}
	
	/**
	 * @return the book author number
	 * */
	public int getBookAuthorNumber() {
		return authors.size();
	}
	
	/**
	 * Adds an Author to this book
	 * 
	 * @param author: author to add
	 * */
	public void addBookAuthor(Author author) {
		this.authors.add(author);
	}

	public boolean equals(Book b){
		boolean t = b.pages == pages && b.year == year &&
			b.ISBN == ISBN && b.title == title;
		if(b.authors.size() == authors.size()){
			Iterator a = authors.iterator();
			Iterator d = b.authors.iterator();
			while(a.hasNext()){
				if(!((Author)a.next()).equals((Author)d.next()))
					return false;
			}
			return t;
		}
		else return false;
	}

	public Book clone(){
		ArrayList<Author> au = new ArrayList<>();
		for(Author a: authors)
			au.add(a.clone());
		return new Book(title,ISBN,year,pages,au);
	}
}