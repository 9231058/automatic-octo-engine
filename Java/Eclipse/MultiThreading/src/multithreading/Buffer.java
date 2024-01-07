package multithreading;

public interface Buffer {
	// Fig. 23.6: Buffer.java
	// Buffer interface specifies methods called by Producer and Consumer.
	public void set(int value); // place value into Buffer

	public int get(); // return value from Buffer
} // end interface Buffer

