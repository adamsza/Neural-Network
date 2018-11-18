package vectors;

import java.io.IOException;

import tree.BST;
import tree.TreeBuilder;

public class Address2WV extends WordVector{
	public Address2WV(String s) {
		super(s);
	}
	
	public double containsSpecialWord() {
		TreeBuilder tb = new TreeBuilder();
		int find = -1;
		try {
			BST bst = tb.buildTree("tree/streets.txt");
			if(bst.get(getWord()) != null)
				find = (int)bst.get(getWord());
		} catch (IOException e) {
		e.printStackTrace();
		}
		if(find > -1)
			return 1;
		else
			return 0;
	}
}
