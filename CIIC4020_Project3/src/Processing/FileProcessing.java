package Processing;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

import ClassADT.*;
import ClassADT.BinaryTreeNodePackage.BTNode;
import ClassADT.BinaryTreeNodePackage.BinaryTreePrinter;
import ClassADT.SortedList.SortedArrayList;

/**
 * This class represents the process to build the Huffman Code given a File with at least and most one line of text. It accepts every character under UTF-8.
 * @author Ydiel Z. Flores Torres
 * @see Map
 * @see File
 * @see BTNode
 * @see String
 * @see Integer
 * 
 */
public class FileProcessing {

	public File input;
	public File input2;
	public File stringData;
	/**
	 * Constructs the object FileProcessing and load sample Files.  
	 **/
	public FileProcessing() {

		input  = new File("inputData/input.txt");
		input2 = new File("inputData/input2.txt");
		stringData = new File("inputData/stringData.txt");

	}
	/**
	 * Receives an input File and calls all the other methods in the class to complete the Huffman Tree.
	 * @param  input Receives a File object as parameter.  
	 * @throws FileNotFoundException
	 */
	public void start(File input) throws FileNotFoundException {

		String inputAsString = loadData(input);

		Map<String,Integer> mapWithFrequencies = computeFD(inputAsString);
		Map<String,String> mapWithTheHuffmanCoding = huffmanCode(huffmanTree(mapWithFrequencies));

		String encodedString = encode(mapWithTheHuffmanCoding,inputAsString);

		processResults(mapWithTheHuffmanCoding,inputAsString,mapWithFrequencies,encodedString);

	}	
	/**
	 * Receives a File and returns the first line as String.
	 * @param input Receives a File object as parameter.
	 * @return String Returns the first line of the parameter as a string.
	 * @throws FileNotFoundException Throws an exception if the input is not found.
	 */
	@SuppressWarnings("resource")
	public String loadData(File input) throws FileNotFoundException {

		Scanner sc = new Scanner(input);
		String result = sc.nextLine();
		if(result.length() == 0) {
			throw new IllegalArgumentException("Please input a file with at least one line of text.");
		}
		sc.close();

		return result;

	}
	/**
	 * Builds a Map with String and Integer pairing. 
	 * @param input String to build a Map from. 
	 * @return Map The Character from the parameter will be the Keys of this map as Strings. The value of each key will represent
	 * the frequency of each key in the input parameter.
	 */
	public Map<String,Integer> computeFD(String input){

		HashTableSCFactory<String, Integer> htscf = new  HashTableSCFactory<String, Integer>();
		Map<String,Integer> result =  htscf.getInstance(input.length()*2);

		String toString;

		for(int i = 0; i < input.length(); i++) {

			toString = String.valueOf(input.charAt(i));

			if(result.containsKey(toString)) {
				result.put(toString,result.get(toString)+1);
			}else {
				result.put(toString, 1);
			}
		}

		return result;

	}
	/**
	 * Builds the binary tree representing the Huffman Tree.
	 * @param inputMap Receives a Map containing Strings as keys and Integers as values. The integers must be the frequencies of the keys in a String.  
	 * @return BTNode Returns the root of the Huffman Tree (Binary Tree).
	 */
	public BTNode<Integer, String> huffmanTree(Map<String,Integer> inputMap){

		List<String> keysAsList =  inputMap.getKeys();

		if(inputMap.size() == 1) {

			return new BTNode<Integer,String>(inputMap.get(keysAsList.get(0)),keysAsList.get(0));
		}



		SortedArrayList<BTNode<Integer,String>> sortedNodes = new SortedArrayList<BTNode<Integer,String>>(inputMap.size());

		for(String keys : keysAsList) {
			sortedNodes.add(new BTNode<Integer,String>(inputMap.get(keys),keys));
		}


		int limiter = sortedNodes.size()-1;


		for(int i = 0; i < limiter; i++) {

			BTNode<Integer,String> nodeThatLinks = new BTNode<Integer,String>();
			BTNode<Integer,String> nodeOne = sortedNodes.removeIndex(0);
			BTNode<Integer,String> nodeTwo = sortedNodes.removeIndex(0);
			if(nodeOne.compareTo(nodeTwo)<0) {
				nodeThatLinks.setRightChild(nodeTwo);
				nodeThatLinks.setLeftChild(nodeOne);
			}else {
				nodeThatLinks.setRightChild(nodeOne);
				nodeThatLinks.setLeftChild(nodeTwo);
			}

			nodeThatLinks.setKey(nodeThatLinks.getRightChild().getKey() + nodeThatLinks.getLeftChild().getKey());
			nodeThatLinks.setValue(nodeThatLinks.getLeftChild().getValue().concat(nodeThatLinks.getRightChild().getValue()));

			sortedNodes.add(nodeThatLinks);

		}

		return sortedNodes.get(0);

	}
	/**
	 * Builds the mapping for the code that will represent each character of a String. 
	 * @param root The parameter will be the root of a Binary Tree (object BTNode) representing the Huffman Tree.
	 * @return Map Returns a Map with Strings as keys and values. The keys will be each character in a String and the value will be the Huffman code for each character. 
	 */
	public Map<String,String> huffmanCode(BTNode<Integer, String> root){

		HashTableSCFactory<String, String> htscf = new  HashTableSCFactory<String, String>();

		Map<String,String> result =  htscf.getInstance(11);

		huffmanCodeAux(root,"",result);

		return result;

	}

	private void huffmanCodeAux(BTNode<Integer, String> root, String code, Map<String,String> result){
		if(root == null) {
			return;
		}
		if(root.getLeftChild() == null && root.getRightChild() == null) {
			result.put(root.getValue(), code);
		}else {
			huffmanCodeAux(root.getLeftChild(),  code +  "0", result);
			huffmanCodeAux(root.getRightChild(), code +  "1", result);
		}
	}
	/**
	 * Builds a String that represents the encoded version of the input String. 
	 * @param codeMap Receives a Map containing Strings as values and keys. The keys will be each character of a given String and the keys will be the Huffman Code for each key. 
	 * @param input The string to be encoded.
	 * @return Returns the encoded input.
	 */
	public String encode(Map<String,String> codeMap, String input) {

		String encodedInput = "";
		String toString;

		for (int i = 0; i < input.length(); i++) {
			toString = String.valueOf(input.charAt(i));

			encodedInput += codeMap.get(toString);
		}

		return encodedInput;

	}
	/**
	 *  Prints the results of the Huffman Code into the console. 
	 * @param codeMap Receives a Map containing Strings as values and keys. The keys will be each character of a given String and the keys will be the Huffman Code for each key. 
	 * @param inputString	Receives the initial input String.
	 * @param mapWithFrequencies	Receives a Map containing Strings as keys and Integers as values. The integers must be the frequencies of the keys in a String.
	 * @param encodedString Receives the encoded String of the initial input String.
	 */
	public void processResults(Map<String,String> codeMap, String inputString, Map<String, Integer> mapWithFrequencies, String encodedString) {
		System.out.print("Symbol" + "\t\t" + "Frequency" + "\t" + "Code" + "\n");
		System.out.println("______          _________       ____" );


		for(String keys : codeMap.getKeys()) {
			System.out.println(keys + "\t\t" + mapWithFrequencies.get(keys) + "\t\t" + codeMap.get(keys)); 
		}

		System.out.println("Original string: ");
		System.out.println(inputString);
		System.out.println();
		System.out.println("Encoded String: ");
		System.out.println(encodedString);
		System.out.println();
		System.out.println("The original string requieres "   + inputString.getBytes().length   + " bytes.");

		double bytesForTheEncodedString = Math.round(encodedString.length()/8.0);

		System.out.println("The encoded string requieres "    + (int) bytesForTheEncodedString + " bytes.");


		double differencePercent = (1 - (bytesForTheEncodedString/(inputString.length() + 0.0))) * 100;
		DecimalFormat df =  new DecimalFormat("#.##");


		System.out.println("Difference in space requires is " + df.format(differencePercent) + "%.");

	} 


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////||
	//Below you will find methods used to test the algorithm. 														//||	
																													//||	
	/**																												//||
	 * This method generates a random string and starts the process of making the encoding for the Huffman Code.	//||
	 * 																												//||
	 * @author GeekForGeeks																							//||
	 * @param stringSize This inputs represents the length of the string to be generated. 							//||
	 */																												//||
	public void randomizer(int stringSize){																			//||
																													//||
		String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+ "0123456789"+ "abcdefghijklmnopqrstuvxyz";				//||
		StringBuilder sb = new StringBuilder(stringSize);															//||
		for(int i = 0; i < stringSize; i++) {																		//||
			int index = (int)(alphaNumeric.length()*Math.random());													//||
			sb.append(alphaNumeric.charAt(index));																	//||
		}																											//||
		startString(sb.toString());   																				//||
	}																												//||
																													//||
	private void startString(String input){																			//||
																													//||
		String inputAsString = input;																				//||
																													//||
		Map<String,Integer> mapWithFrequencies = computeFD(inputAsString);											//||
		Map<String,String> mapWithTheHuffmanCoding = huffmanCode(huffmanTree(mapWithFrequencies));					//||
																													//||
		String encodedString = encode(mapWithTheHuffmanCoding,inputAsString);										//||
																													//||
		processResults(mapWithTheHuffmanCoding,inputAsString,mapWithFrequencies,encodedString);						//||
																													//||
		System.out.println("This is the decoded string: \n" + decodeHuff(encodedString,mapWithTheHuffmanCoding));	//||
	}																												//||
																													//||
	/**																												//||
	 * @author Fabiola E Robles Vega																				//||
	 */																												//||
	private String decodeHuff(String output, Map<String, String> huffMap) {											//||
		String result = "";																							//||
		int start = 0;																								//||
		List<String>  huffCodes = huffMap.getValues();																//||
		List<String> symbols = huffMap.getKeys();																	//||
																													//||
		/*looping through output until a huffcode is found on map and												//||
		 * adding the symbol that the huffcode represents to result */												//||
		for(int i = 0; i<= output.length();i++){																	//||
																													//||
			String searched = output.substring(start, i);															//||
																													//||
			int index = huffCodes.firstIndex(searched);																//||
																													//||
			if(index>=0) { //Found it																				//||
				result= result + symbols.get(index);																//||
				start = i;																							//||
			}																										//||
		}																											//||
		return result;																								//||   
	}																												//||
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////||
}





