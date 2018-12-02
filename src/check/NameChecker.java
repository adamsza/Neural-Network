package check;

import java.io.IOException;
import java.util.ArrayList;

import tree.BST;
import tree.TreeBuilder;

//names
//majd visszaad people namest
public class NameChecker {
	private ArrayList<String> name1 = new ArrayList<String>();
	private ArrayList<String> name2 = new ArrayList<String>();
	private int count = 1;

	//nevek ellenorzese
	public ArrayList<String> checkNames(ArrayList<String[]> names, ArrayList<String> text) throws IOException {
		
		int idx = Integer.parseInt(names.get(0)[1]);
		name1.add(names.get(0)[0]);
		
		for(int i = 1; i<names.size(); i++) {
			if(count == 1) idx = sortNames(names, text, idx, i);
			else idx = sortNames2(names, text, idx, i);
			if(idx == -1) break;
			}
		
		ArrayList<String> string_names = new ArrayList<String>();
		string_names.add(checkNamesInTree(name1));
		if(name1.size() != names.size()) string_names.add(checkNamesInTree(name2));
		
		return string_names;
	}

	//elso fel nevenek valogatasa
	public int sortNames(ArrayList<String[]> names, ArrayList<String> text, int idx, int i) {
			if(Integer.parseInt(names.get(i)[1]) == idx+1) {
					name1.add(names.get(i)[0]);
					idx++;
			}
			else if(Integer.parseInt(names.get(i)[1]) == idx+2) {
				name1.add(text.get(idx+1));
				name1.add(names.get(i)[0]);
				idx += 2;
			}
			else {
				count = 2;
				idx = Integer.parseInt(names.get(i)[1]);
				name2.add(names.get(i)[0]);
			}
			return idx;
	}
	
	//masodik fel nevenek valogatasa
	public int sortNames2(ArrayList<String[]> names, ArrayList<String> text, int idx, int i) {
		if(Integer.parseInt(names.get(i)[1]) == idx+1) {
			name2.add(names.get(i)[0]);
			idx++;
		}
		else if(Integer.parseInt(names.get(i)[1]) == idx+2) {
			name2.add(text.get(idx+1));
			name2.add(names.get(i)[0]);
			idx += 2;
		}
		else {
			idx = -1;
		}
		return idx;
	}
	
	//nevek ellenorzese keresztnevek, vezeteknevek, ceg nevek faiban
	public String checkNamesInTree(ArrayList<String> name) throws IOException {
		String str = new String();
		TreeBuilder tb = new TreeBuilder();
		BST bst1 = tb.buildTree("tree/names_random.txt");
		BST bst2 = tb.buildTree("tree/surnames.txt");
		BST bst3 = tb.buildTree("tree/company.txt");
		
		if((bst3.get(name.get(name.size()-1)) != null) || (bst1.get(name.get(name.size()-1)) != null) || (bst2.get(name.get(0)) != null)) {
			str = makeNameString(name);
		}
		else {
			str = "-";
		}
		return str;
	}
	
	//String-e alakitja a neveket
	public String makeNameString(ArrayList<String> name) {
		String n;
		int idx = 1;
		if(Character.isUpperCase(name.get(0).charAt(0))) {
			n = name.get(0);
		}
		else {
			n = name.get(1);
			idx = 2;
		}
		for(int i = idx; i<name.size(); i++) {
			n = n + " " + name.get(i);
		}
		return n;
	}
	
}
