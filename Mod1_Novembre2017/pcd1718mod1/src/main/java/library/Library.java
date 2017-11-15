package library;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class models the library which is comprised of several shelves holding books each of which identified by some parameters.
 */
public class Library {
	private ArrayList<Shelf> library;

	/**
	 * Builds an empty library
	 * */
	public Library() {
		library = new ArrayList<>();
	}

	/**
	 * Builds a library given some shelves as a input parameter.
	 * 
	 * @param library: shelves to be added to this library
	 * 
	 * */
	public Library(ArrayList<Shelf> library) {
		this.library = new ArrayList<>();
		for(Shelf s: library)
			this.library.add(s);
	}
	
	/**
	 * Adds a shelf to the library
	 * 
	 * @param shelf: the shelf
	 */
	public void addShelf(Shelf shelf) {
		this.library.add(shelf);
	}
	
	/**
	 * @return the total number of books present in this library
	 */
	public int getNumberOfBooksInLibrary() {
		int sum =0;
		for(Shelf s: library)
			sum += s.getNumerOfBooksInShelf();
		return sum;
	}

	/**
	 * @return the book year range (min, max) present in this library.
	 * E.g., if the oldest book year is 1200 and a newly added in 2017, the method returns an array of int {1200, 2017}
	 * */
	public int[] getBooksYearRangeInLibrary() {
		int[] range = {9999,-9999};
		for(Shelf s: library){
			ArrayList<Book> bks = s.getBooksInShelf();
			for(Book b: bks) {
				int yea = b.getBookYear();
				if (yea > range[1]) range[1] = yea;
				if(yea < range[0]) range[0] = yea;
			}
		}
		return range;
	}
	
	/**
	 * @return the average number of books stored in the library shelves.
	 * E.g., Shelf_1 contains 1 and Shelf_1 contains 2 contains 2 => Avg = (1+2)/2
	 */
	public double getAvgNumberOfBookPerShelf() {
		double avg=0;
		for(Shelf s: library)
			avg += s.getNumerOfBooksInShelf();
		avg = avg/library.size();
		return avg;
	}

	/**
	 * @return the number of distinct authors present in the library
	 * */
	public int getTotalNumberOfDistinctAuthorsInLibrary() {
		ArrayList<Author> totAuth = new ArrayList<>();
		for(Shelf s: library){
			ArrayList<Book> bks = s.getBooksInShelf();
			for(Book b: bks){
				ArrayList<Author> au = b.getBookAuthors();
				for(Author a: au){
					Iterator it = totAuth.iterator();
					boolean esiste = false;
					while(it.hasNext() && !esiste){
						if((it.next()).equals(a))   esiste = true;
					}
					if(!esiste) totAuth.add(a);
				}
			}
		}
		return totAuth.size();
	}

	/**
	 * @return the total number of book pages present in this library
	 * */
	public int getTotalNumberOfBookPagesInLibrary() {
		int pag=0;
		for (Shelf s : library) {
			ArrayList<Book> bks = s.getBooksInShelf();
			for (Book b : bks) {
				pag+=b.getBookPages();
			}
		}

		return pag;
	}
}
