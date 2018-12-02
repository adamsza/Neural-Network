package check;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import tree.BST;
import tree.TreeBuilder;

//type
//type_stringet majd visszaadja
public class ContractTypeChecker {

	public String checkContractType(ArrayList<String> type) throws IOException {
		String type_string = checkInTree(type);
		
		if(!Pattern.compile("szerződés").matcher(type_string).find()) {
			type_string = type_string + " " + "szerződés";
		}
		
		return type_string;
		
	}
	
	//szerzodes tipust megkeresi egy binaris keresofaban
	private String checkInTree(ArrayList<String> type) throws IOException {
		String str = new String();
		TreeBuilder tb = new TreeBuilder();
		BST bst1 = tb.buildTree("tree/contract_types.txt");
		String s = type.get(0).substring(0, 1) + type.get(0).substring(1).toLowerCase();
		
		if(bst1.get(s) != null) str = s;
		else str = "";

		return str = makeString(type, bst1, str);
	}
	
	//String-e alakitja a szerzodes tipust
	private String makeString(ArrayList<String> type, BST bst1, String str) {
		for(int i = 1; i<type.size(); i++) {
			String s2 = type.get(i).substring(0, 1) + type.get(i).substring(1).toLowerCase();
			if(bst1.get(s2) != null) {
				str = str + " " + s2;
			}
		}
		return str;
	}
}
