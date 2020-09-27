// --== CS400 File Header Information ==--
// Name: Saul Brodkey
// Email: smbrodkey@wisc.edu
// Team: AD
// TA: Sophie
// Lecturer: Gary Dahl
// Notes to Grader: Not my work here! MapADT was given!
import java.util.NoSuchElementException;
public interface MapADT<KeyType,ValueType> {
	public boolean put(KeyType key, ValueType value);  
	public ValueType get(KeyType key) throws NoSuchElementException;   
	public int size(); 
	public boolean containsKey(KeyType key); 
	public ValueType remove(KeyType key);   
	public void clear();
	}