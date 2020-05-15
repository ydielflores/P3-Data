package Main;

import java.io.FileNotFoundException;

import Processing.FileProcessing;

public class HuffManCoding {

	public static FileProcessing fp = new FileProcessing();
	
	public static void main(String[] args) throws FileNotFoundException {
		
		fp.start(fp.input);
		System.out.println("");
		fp.start(fp.input2);
	}

}
