package Main;

import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.Random;

import Processing.FileProcessing;

public class HuffManCoding {

	public static FileProcessing fp = new FileProcessing();
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//fp.start(fp.input);
		System.out.println("");
		//fp.start(fp.input2);
		String hold = generateStringForTesting();
		System.out.println(hold + "\n" + hold.length());
		fp.start(hold);
	}
	/**
	 * I found this method online that generates random Strings
	 * This strings are used to test the program.
	 * @author Eugen Paraschiv
	 */
	/////////////////////////////////////////////////////////////////////////////////
	public static String generateStringForTesting() {							//||
		Random r = new Random();												//||
		byte[] bytes = new byte[r.nextInt(1001)];								//||
		r.nextBytes(bytes);														//||
		String generateString = new String(bytes, Charset.forName("UTF-8"));	//||
		return generateString;													//||	
	}																			//||
	////////////////////////////////////////////////////////////////////////////////
}
