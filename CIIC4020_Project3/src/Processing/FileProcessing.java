package Processing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ClassADT.*;
import ClassADT.BinaryTreeNodePackage.BTNode;
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

		System.out.println(loadData(input));
		huffmanTree(computeFD(loadData(input)));
		
	}

	public String loadData(File input) throws FileNotFoundException {

		Scanner sc = new Scanner(input);
		String result = sc.nextLine();
				
		sc.close();

		return result;

	}

	public Map<String,Integer> computeFD(String input){
		
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
	
	public void huffmanTree(Map<String,Integer> inputMap){
		
		BTNode<String,Integer> nodes;// = new BTNode<String,Integer>();

		List<String> keysAsList = inputMap.getKeys();
		ArrayList<BTNode<String,Integer>> sortedNodes = new ArrayList<BTNode<String,Integer>>(inputMap.size());
		
		for(String keys : keysAsList) {
			nodes = new BTNode<String,Integer>(keys,inputMap.get(keys));
			sortedNodes.add(nodes);
		}
		
		sortList(sortedNodes);
		
		for(int i = 0; i < sortedNodes.size(); i++) {
			System.out.println(sortedNodes.get(i).getText()); 
		}
		
		//return nodes;
		
	}
	
	//==========================================================================================
	//Hello, I made quickSort!!!!! Excited.
	private void sortList(List<BTNode<String,Integer>> list) {								//||
		qs(list,0,list.size()-1);															//||		
	}																						//||
																							//||
	private void qs(List<BTNode<String,Integer>> list, int first, int last) {				//||
		if(first<last) {																	//||
			int pi = partition(list,first,last);											//||
																							//||
			qs(list,first,pi-1);															//||
			qs(list,pi+1,last);																//||	
		}																					//||
	}																						//||
																							//||
	private int partition(List<BTNode<String, Integer>> list, int first, int last) {		//||
		// TODO Auto-generated method stub													//||
		BTNode<String,Integer> pivot = list.get(last);										//||
		int i = first - 1;																	//||
		for(int j = first; j <= last - 1; j++) {											//||
			if(list.get(j).getValue() < pivot.getValue()) {									//||
				i++;																		//||
				BTNode<String,Integer> swapI = list.get(i);									//||
				list.set(i, list.get(j));													//||
				list.set(j, swapI);															//||
			}																				//||
		}																					//||
		BTNode<String,Integer> swapI = list.get(i+1);										//||
		list.set(i+1, list.get(last));														//||
		list.set(last, swapI);																//||
		return i+1;																			//||
	}																						//||
	//==========================================================================================
}
