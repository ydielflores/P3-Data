package Processing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ClassADT.*;
import ClassADT.BinaryTreeNodePackage.BTNode;
import ClassADT.BinaryTreeNodePackage.BinaryTreePrinter;
import ClassADT.SortedList.SortedArrayList;
import ClassADT.SortedList.SortedList;


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
		System.out.println(loadData(input));
		huffmanCode(huffmanTree(computeFD(loadData(input))));
		
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
		String code = "";
		
		return result;
		
	}
	private Map<String,String> huffmanCodeAux(BTNode<Integer,String> root, Map<String,String> result, String code){
		
		if(root.getLeft() != null) {
			return huffmanCodeAux(root.getLeftChild(), result, "0" + code );
		}else if(root.getRightChild() != null) {
			return huffmanCodeAux(root.getRightChild(), result, "1" + code );
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
