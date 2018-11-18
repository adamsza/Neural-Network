package tree;

import java.io.IOException;
import java.util.ArrayList;

import processor.TextProcessor;

public class TreeBuilder {

	public BST buildTree(String filename) throws IOException {
		BST bst = new BST();
		TextProcessor tp = new TextProcessor();
		ArrayList<String> names = tp.readWords(filename);
		for(int i = 0; i < names.size(); i++) {
			bst.put(names.get(i), i);
		}
		return bst;
	}
	
	
}
