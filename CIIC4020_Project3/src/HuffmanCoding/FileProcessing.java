package HuffmanCoding;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ClassADT.HashTableSC;
import ClassADT.HashTableSCFactory;
import ClassADT.Map;
import ClassADT.Tree.BinaryTree;
import ClassADT.Tree.LinkedBinaryTree2;


public class FileProcessing {

	public File input;
	public File input2;
	
	public FileProcessing() {

		input  = new File("res/input.txt");
		input2 = new File("res/input2.txt");

	}

	public void start(File input) throws FileNotFoundException {

		System.out.println(loadData(input));
		huffmanTree(computeFD(loadData(input)));
		
		
			
		
	}

	private String loadData(File input) throws FileNotFoundException {

		Scanner sc = new Scanner(input);
		String result = sc.nextLine();
				
		sc.close();

		return result;

	}

	private Map<String,Integer> computeFD(String input){
		
		HashTableSCFactory<String, Integer> htscf = new  HashTableSCFactory<String, Integer>();

		Map<String,Integer> result = htscf.getInstance(input.length()*2);
		
		String toString;
		
		for(int i = 0; i < input.length(); i++) {
			
			toString = String.valueOf(input.charAt(i));
			
			if(result.containsKey(toString)) {
				result.put(toString,result.get(toString)+1);
			}else {
				result.put(toString, 1);
			}
		}
		result.print(System.out);
		
		return result;

	}
	
	private BinaryTree<String> huffmanTree(Map<String,Integer> inputMap){
		BinaryTree<String> binaryTree = new LinkedBinaryTree2<String>();
		
		
		
		
		
		return binaryTree;
		
	}


}
