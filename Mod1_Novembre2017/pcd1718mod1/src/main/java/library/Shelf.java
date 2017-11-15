package library;

import java.util.ArrayList;

/**
 * This class models a shelf in our library context
 * 
 * */
public final class Shelf implements Cloneable {
	private int capacity;
	private ArrayList<Book> shelf;
	/**
	 * Builds a shelf of a certain capacity as specified by the input parameter
	 * 
	 * @param capacity: positive value denoting the maximum number of books the shelf can contain
	 * */
	public Shelf(int capacity) throws  IllegalArgumentException  {
		if(capacity <= 0)   throw new IllegalArgumentException("Shelf parameters are not valid");
		this.capacity = capacity;
	}
	
	/**
	 * Builds a shelf of a certain capacity and a certain number of books
	 * 
	 * @param capacity: capacity of this shelf
	 * @param books:	books to store in this shelf
	 * 
	 * @throws IllegalArgumentException: in case parameters are not valid i.e., capacity not positive or capacity exceeded
	 * */
	public Shelf(int capacity, ArrayList<Book> books) throws  IllegalArgumentException {
		if (capacity <=0 ||books == null || books.size()>capacity) throw new IllegalArgumentException("Shelf parameters are not valid");
		this.capacity = capacity;
		shelf = new ArrayList<>(capacity);
		for(Book b: books)
			shelf.add(b.clone());
	}


	/**
	 * Adds the specified book in the shelf
	 * 
	 * @param b: book to add
	 * 
	 * @return true if the book was added in the shelf, false otherwise
	 */
	public boolean addBookInShelf(Book b) {
		if(shelf.size() < capacity){ capacity++; shelf.add(b); return true;}
		return false;
	}

	/**
	 * Adds the books specified in the parameter to this shelf
	 * 
	 * @param book:	an array of books to add to this shelf
	 * 
	 * @return the effective number of books added to this shelf
	 */
	public int addBooksInSheld(Book[] book) {
		int aggiunti = 0;
		for(int i=0 ;shelf.size()<capacity && i<book.length;i++){
			capacity ++; aggiunti++;
			shelf.add(book[i]);
		}
		return aggiunti;
	}


	/**
	 * @return the shelf capacity
	 * */
	public int getShelfCapacity() {
		return capacity;
	}
	
	/**
	 * @return a list containing the books stored in this shelf
	 * */
	public ArrayList<Book> getBooksInShelf() {
		ArrayList<Book> retur = new ArrayList<>();
		for(Book b: shelf)
			retur.add(b);
		return retur;
	}

	/**
	 * @return the number of books stored in this shelf
	 */
	public int getNumerOfBooksInShelf() {
		return shelf.size();
	}
	
	/**
	 * @return true if the shelf is full (cannot contain any more books), false otherwise
	 * */
	public boolean shelfFull() {
		return capacity == shelf.size();
	}
}
