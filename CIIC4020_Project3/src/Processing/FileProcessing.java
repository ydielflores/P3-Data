package Processing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ClassADT.*;
import ClassADT.BinaryTreeNodePackage.BTNode;
import ClassADT.BinaryTreeNodePackage.BinaryTreePrinter;
import ClassADT.SortedList.SortedArrayList;

public class FileProcessing {

	public File input;
	public File input2;
	public File stringData;

	public FileProcessing() {

		input  = new File("inputData/input.txt");
		input2 = new File("inputData/input2.txt");
		stringData = new File("inputData/stringData.txt");

	}

	public void start(File input) throws FileNotFoundException {
		//TODO DELETE
		String inputAsString = loadData(input);
		
		Map<String,Integer> mapWithFrequencies = computeFD(inputAsString);
		Map<String,String> mapWithTheHuffmanCoding = huffmanCode(huffmanTree(mapWithFrequencies));
		
		String encodedString = encode(mapWithTheHuffmanCoding,inputAsString);
		
		processResults(mapWithTheHuffmanCoding,inputAsString,mapWithFrequencies,encodedString);
		
	}

	@SuppressWarnings("resource")
	public String loadData(File input) throws FileNotFoundException {

		Scanner sc = new Scanner(input);
		String result = sc.nextLine();
		if(result.length() == 0) {
			throw new IllegalArgumentException("File is empty.");
		}
		sc.close();

		return result;

	}

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
		//TODO DELETE
		result.print(System.out);

		return result;

	}

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

		BinaryTreePrinter.print(sortedNodes.get(0));
		return sortedNodes.get(0);

	}

	public Map<String,String> huffmanCode(BTNode<Integer, String> root){
		HashTableSCFactory<String, String> htscf = new  HashTableSCFactory<String, String>();

		Map<String,String> result =  htscf.getInstance(10);
		
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
	
	public String encode(Map<String,String> codeMap, String input) {
		
		String encodedInput = "";
		String toString;
		
		for (int i = 0; i < input.length(); i++) {
			toString = String.valueOf(input.charAt(i));
			
			encodedInput += codeMap.get(toString);
		}
		
		return encodedInput;
		
	}

	public void processResults(Map<String,String> codeMap, String inputString, Map<String, Integer> mapWithFrequencies, String encodedString) {
		System.out.print("Symbol" + "\t\t" + "Frequency" + "\t" + "Code");
		System.out.println();
		System.out.println("______          _________       ____");
		
		
		System.out.println("Original strin: ");
		System.out.println(inputString);
		System.out.println();
		System.out.println("Encoded String: ");
		System.out.println(encodedString);
		System.out.println();
		System.out.println("The original string requieres "   + inputString.getBytes().length   + " bytes.");
		System.out.println("The encoded string requieres "    + encodedString.getBytes().length + " bytes.");
		double differencePercent = encodedString.length()/inputString.length();
		System.out.println("Difference in space requires is " + differencePercent + ".");
		
	} 


}





