package Main;

import java.io.FileNotFoundException;

import HuffmanCoding.FileProcessing;

public class Main {

	public static FileProcessing fp = new FileProcessing();
	
	public static void main(String[] args) throws FileNotFoundException {
		
		fp.start(fp.input);
		//fp.start(fp.input2);
	}

}
