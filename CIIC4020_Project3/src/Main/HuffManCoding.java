package Main;

import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.Random;

import Processing.FileProcessing;

public class HuffManCoding {

	public static FileProcessing fp = new FileProcessing();
	
	public static void main(String[] args) throws FileNotFoundException {
		
		/*vvv There's a file in the res folder named stringData.txt. The first line of this File can edited as you wished for testing purposes. vvv*/ 
		fp.start(fp.stringData);
		
		
		/*vvv This line builds the first example the Professor sent us. vvv*/ 
		//fp.start(fp.input);
		
		
		/*vvv This line builds the second example the Professor sent us. vvv*/
		//fp.start(fp.input2);
		
		/*You can use the randomizer(int) to generate a random Alpha Numeric String and build the Huffman Code from it.
		 *The parameter represents the length of the String.*/ 
		//fp.randomizer(20);
	}
	
}
