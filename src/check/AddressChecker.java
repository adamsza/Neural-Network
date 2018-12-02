package check;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import tree.BST;
import tree.TreeBuilder;

public class AddressChecker {

	public ArrayList<String> checkAddress(ArrayList<String[]> addresses, ArrayList<String> text) throws IOException {
		ArrayList<ArrayList<String[]>> newaddresses = checkInTree(addresses);
		newaddresses = findMissing(newaddresses, text);
		ArrayList<String> string_addresses = completeAddress(newaddresses, text);
		return string_addresses;
	}
	
	//cim iranyitoszama, telepulese, utca tipusa ellenorzese binaris keresofaban
	public ArrayList<ArrayList<String[]>> checkInTree(ArrayList<String[]> addresses) throws IOException {
		TreeBuilder tb = new TreeBuilder();
		BST bst1 = tb.buildTree("tree/settlements.txt");
		BST bst2 = tb.buildTree("tree/postcodes.txt");
		BST bst3 = tb.buildTree("tree/streets.txt");
	
		ArrayList<ArrayList<String[]>> newaddresses = new ArrayList<ArrayList<String[]>>();
		newaddresses.add(new ArrayList<String[]>());
		newaddresses.add(new ArrayList<String[]>());
		
		int cnt1 = 1;
		int cnt2 = 1;
		
		for(int i = 0; i<addresses.size(); i++) {
			String[] s = new String[] {addresses.get(i)[0], addresses.get(i)[1]};
			if(Integer.parseInt(addresses.get(i)[2]) == 1) {
				int p = sortAddresses1(addresses, bst1, bst2, cnt1, i);
				if(p >= 0) {
					newaddresses.get(p).add(s);
					cnt1 ++;
				}
			}
			else {
				int p = sortAddresses2(addresses, bst3, cnt2, i);
				if(p >= 0) {
					newaddresses.get(p).add(s);
					cnt2++;
					cnt1++;
				}
			}
		}
		return newaddresses;
	}
	
	//elso tipusu cim valogatasa (iranyitoszam, telepules)
	public int sortAddresses1(ArrayList<String[]> addresses, BST bst1, BST bst2, int cnt1, int i) {
			if((bst1.get(addresses.get(i)[0]) != null) || (bst2.get(addresses.get(i)[0]) != null)) {
				if(cnt1 < 3) return 0;
				else return 1;
			}
			else return -1;
	}
	
	//masodik tipusu cim valogatasa (utca tipus, hazszam)
	public int sortAddresses2(ArrayList<String[]> addresses, BST bst3, int cnt2, int i) {
				if((bst3.get(addresses.get(i)[0]) != null) || Pattern.compile("[0-9]").matcher(addresses.get(i)[0]).find()) {
					if(cnt2 < 3) return 0;
					else return 1;
				}
				else return -1;
	}

	//telepules es utca tipus kozott utca nev kitoltese
	public ArrayList<String> completeAddress(ArrayList<ArrayList<String[]>> newaddresses, ArrayList<String> text) {
		ArrayList<String> string_addresses = new ArrayList<String>();
		for(int i = 0; i< newaddresses.size(); i++) {
			string_addresses.add(new String());
			int idx1 = 0;
			int idx2 = 0;
			if(!newaddresses.get(i).isEmpty()) {
				idx1 = Integer.parseInt(newaddresses.get(i).get(1)[1]);
				idx2 = Integer.parseInt(newaddresses.get(i).get(2)[1]);
			
			String s = makeString(newaddresses, text, idx1, idx2, i);
			string_addresses.set(i, s);
			}
		}
		return string_addresses;
	}
	
	//String-e alakitja a cimeket
	public String makeString(ArrayList<ArrayList<String[]>> newaddresses, ArrayList<String> text, int idx1, int idx2, int i) {
		int plus = idx2-idx1;
		String s = text.get(Integer.parseInt(newaddresses.get(i).get(0)[1]));
		s = s + " " + text.get(idx1);
		
		for(int j = 1; j<plus; j++) {
			s = s + " " + text.get(idx1+j);
		}
		
		s = s + " " + text.get(idx2);
		if(newaddresses.get(i).size() <4) s = s + " " + text.get(Integer.parseInt(newaddresses.get(i).get(newaddresses.get(i).size()-1)[1])+1);
		else s = s + " " + text.get(idx2+1);
		
		return s;
	}
	
	public ArrayList<ArrayList<String[]>> findMissing(ArrayList<ArrayList<String[]>> newaddresses, ArrayList<String> text) throws IOException {
		TreeBuilder tb = new TreeBuilder();
		BST bst1 = tb.buildTree("tree/settlements.txt");
		BST bst2 = tb.buildTree("tree/postcodes.txt");
		//BST bst3 = tb.buildTree("tree/streets.txt");
		
		if(newaddresses.size() == 2) {
			for(int i = 0; i<newaddresses.size(); i++) {
				if(bst2.get(newaddresses.get(i).get(0)[0]) == null) {
					if(bst1.get(newaddresses.get(i).get(1)[0]) != null) {
						int idx = Integer.parseInt(newaddresses.get(i).get(1)[1]);
						String postcode = text.get(idx-1);
						newaddresses.get(i).add(0, new String[]{postcode, Integer.toString(idx-1)});
					}
				}
				if(bst1.get(newaddresses.get(i).get(1)[0]) == null) {
					if(bst2.get(newaddresses.get(i).get(0)[0]) != null) {
						int idx = Integer.parseInt(newaddresses.get(i).get(0)[1]);
						String settlement = text.get(idx+1);
						newaddresses.get(i).add(1, new String[]{settlement, Integer.toString(idx+1)});
					}
				}
			}
		}
		return newaddresses;
	}
}
