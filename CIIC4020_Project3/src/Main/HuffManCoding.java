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
		fp.randomizer(20);
	}
	
}
