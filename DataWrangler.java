// Name: Michael Kukovec
// Email: kukovec@wisc.edu
// Team: AD
// Role: Data Wrangler 2
// TA: Sophie Stephenson
// Lecturer: Gary Dahl
// Notes to Grader: requires class HashTableMap to compile

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataWrangler {
	public static boolean readInputFile(HashTableMap<String, Book> table_name, String file_name)
			throws FileNotFoundException {
		// Title, Author, Publisher, Publication Year, ISBN
		final int info_types = 5;

		// Assumes .csv if no extension provided
		if (!file_name.substring(file_name.length() - 4).equals(".csv")
				&& !file_name.substring(file_name.length() - 4).equals(".txt")) {
			file_name += ".csv";
		}

		// NOTE: if the file is not reading, check that this line is creating the
		// correct directory path.
		// Finds current directory
		String file_path = System.getProperty("user.dir");

		// Open the file, if no file found return false
		try {
			// Checks if path uses forward or backslash, default is forward
			for (int i = 0; i < file_path.length(); i++) {
				if (file_path.charAt(i) == '/') {
					file_path += file_path.charAt(i) + file_name;
					break;
				}
				if (file_path.charAt(i) == '\\') {
					file_path += file_path.charAt(i) + file_name;
					break;
				}
				if (i == file_path.length() - 1) {
					file_path += '/' + file_name;
				}
			}

			// Attempt to open the file
			File read_file = new File(file_path);
			Scanner scan = new Scanner(read_file);
			String line = scan.nextLine();

			while (scan.hasNextLine()) {
				String[] info = new String[info_types];
				for (int i = 0; i < info_types; i++) {
					info[i] = "";
				}
				line = scan.nextLine();

				// Create book objects with line data
				int i = 0;
				for (int count = 0; count < info_types; count++) {
					// checks for <","> delimiter
					if (line.charAt(i) == '\"') {
						i++;
						while (line.charAt(i) != '\"') {
							info[count] += line.charAt(i);
							i++;
							if (i > line.length() - 1) {
								break;
							}
						}
						i += 2;
						// Default delimiter is <,>
					} else {
						while (line.charAt(i) != ',') {
							info[count] += line.charAt(i);
							i++;
							if (i > line.length() - 1) {
								break;
							}
						}
						i++;
					}
				}
				// Create book
				Book newBook = new Book(info[0], info[1], info[2], Integer.parseInt(info[3]), info[4]);
				// Back-End function, adds Book to hash table
				table_name.put(newBook.getISBN(), newBook);
			}

			scan.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("File not found at " + file_path);
			return false;
		}
	}
}