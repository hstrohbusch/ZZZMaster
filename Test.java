  
  
// Name: Hunter Strohbusch
// Email: hstrohbusch@wisc.edu
// Team: AD
// Role: Test Engineer 1
// TA: Sophie Stephenson
// Lecturer: Gary Dahl

import java.util.NoSuchElementException;

public class Test {

	public static void main(String[] args) {
		System.out.println("addTest(): "+addTest());
		System.out.println("addBadTest(): "+addBadTest());
		System.out.println("removeTest(): "+removeTest());
		System.out.println("removeNothingTest(): "+removeNothingTest());
		System.out.println("getTest(): "+getTest());
		System.out.println("getBadTest(): "+getBadTest());
	}
	
	/*
	 * Creates a library using Book_data.csv, then attempts to add a book to that library
	 * If failed to add at correct location, return false, otherwise return true
	 */
	public static boolean addTest(){
		HashTableMap<String, Book> library = new HashTableMap<String, Book>();

		try {
			DataWrangler.readInputFile(library, "Book_data.csv");
		} catch(Exception e) {
			return false;
		}

		String title = "Cool Book";
		String author = "Joe Cool";
		String publisher = "Cool Books LTD";
		int publishYear = 1964;
		String isbn = "1234567890";
		Book temp = new Book(title, author, publisher, publishYear, isbn);

		library.put(isbn, temp);

		if(library.get(isbn)!=temp)
			return false;

		return true;
	}

	/*
	 * attempts to add a clone of an existing book into a pre-existing library
	 * @returns true when bad add fails
	 * @returns false when bad add succeeds
	 */
	public static boolean addBadTest() {
		HashTableMap<String, Book> library = new HashTableMap<String, Book>();

		try {
			DataWrangler.readInputFile(library, "Book_data.csv");
		} catch(Exception e) {
			return false;
		}
		
		String title = "Cool Book";
		String author = "Joe Cool";
		String publisher = "Cool Books LTD";
		int publishYear = 1964;
		String isbn = "1234567890";
		Book filler = new Book(title, author, publisher, publishYear, isbn);
		library.put(isbn, filler);
		
		if(library.put(isbn, filler) == true)
			return false;
		return true;
	}

	/*
	 * Adds a new book to a pre-existing library, then removes it
	 * To check to see if removal was successful the library.get method is called
	 * @returns true if a no such element exception is thrown, showing a proper removal
	 */
	public static boolean removeTest() {
		HashTableMap<String, Book> library = new HashTableMap<String, Book>();

		try {
			DataWrangler.readInputFile(library, "Book_data.csv");
		} catch(Exception e) {
			return false;
		}
		
		String title = "Cool Book";
		String author = "Joe Cool";
		String publisher = "Cool Books LTD";
		int publishYear = 1964;
		String isbn = "1234567890";
		Book filler = new Book(title, author, publisher, publishYear, isbn);
		library.put(isbn, filler);
		
		try {
			library.remove(isbn);
			library.get(isbn);
		} catch(NoSuchElementException e1) {
			if(library.remove(isbn)==null)
				return true;
			return false;
		} catch(Exception e) {
			return false;
		}
		return false;
	}

	/*
	 * Using a pre-existing library, attempt to remove a book that doesn't exist
	 * @return true if library.remove returns null, showing that there is nothing at the requested location
	 * @return false if otherwise
	 */
	public static boolean removeNothingTest() {
		HashTableMap<String, Book> library = new HashTableMap<String, Book>();

		try {
			DataWrangler.readInputFile(library, "Book_data.csv");
		} catch(Exception e) {
			return false;
		}
		 
		String isbn = "1234567890";
		if(library.remove(isbn)!= null)
			return false;
		return true;
	}

	/*
	 * Add a book to a pre-existing library 
	 * Attempt to get the book
	 * 
	 * @returns true if the correct book is retreived
	 * @returns false if otherwise
	 */
	public static boolean getTest() {
		HashTableMap<String, Book> library = new HashTableMap<String, Book>();

		try {
			DataWrangler.readInputFile(library, "Book_data.csv");
		} catch(Exception e) {
			return false;
		}
		
		String title = "Cool Book";
		String author = "Joe Cool";
		String publisher = "Cool Books LTD";
		int publishYear = 1964;
		String isbn = "1234567890";
		Book filler = new Book(title, author, publisher, publishYear, isbn);
		library.put(isbn, filler);
		
		if(library.get(isbn)!=filler)
			return false;
		return true;
	}

	/*
	 * Attempt to get a book from the library that doesn't exist
	 * 
	 * @returns true when a no such element exception is thrown, indicating that the book doesn't exist
	 * @returns false when otherwise
	 */
	public static boolean getBadTest() {
		HashTableMap<String, Book> library = new HashTableMap<String, Book>();

		try {
			DataWrangler.readInputFile(library, "Book_data.csv");
		} catch(Exception e) {
			return false;
		}
		
		try {
			library.get("potatos");
		} catch(NoSuchElementException e1) {
			return true;
		} catch(Exception e) {
			return false;
		}
		return false;
	}

}
