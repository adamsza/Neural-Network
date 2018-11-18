package check;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import data.PersonCK;
import tree.BST;
import tree.TreeBuilder;

public class DataChecker {
	
	private ArrayList<PersonCK> people;
	
	private ArrayList<String[]> names;
	private ArrayList<String[]> addresses;
	private ArrayList<String[]> ids;
	
	private String date;
	private ArrayList<String[]> date2;
	//private String date;
	
	private ArrayList<String> type;
	private String type_string;
	
	
	
	private ArrayList<String> text;
	private ArrayList<Integer> answers;
	
	public DataChecker(ArrayList<String> text, ArrayList<Integer> answers) {
		this.text = text;
		this.answers = answers;
		people = new ArrayList<PersonCK>();
		people.add(new PersonCK());
		people.add(new PersonCK());
		date = new String();
		type = new ArrayList<String>();
		names = new ArrayList<String[]>();
		addresses = new ArrayList<String[]>();
		ids = new ArrayList<String[]>();
		type_string = new String("-");
		date2 = new ArrayList<String[]>();
		//addresses_ready = new ArrayList<String>();
	}
	
	public void getData() {
		extractUsefulData();
		try {
			if(!addresses.isEmpty()) checkAddress();
			if(!type.isEmpty()) checkType();
			checkDate();
			checkIDs();
			if(!names.isEmpty()) checkNames();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writeData();
		
	}
	
	public void extractUsefulData() {
		
		for(int i = 0; i<text.size(); i++) {
			if(answers.get(i) == 0) {
				String[] add = new String[] {text.get(i), Integer.toString(i), "1"};
				addresses.add(add);
			}
			if(answers.get(i) == 1) {
				type.add(text.get(i));
			}
			if(answers.get(i) == 2) {
				date = text.get(i);
			}
			if(answers.get(i) == 3) {
				String[] id = new String[] {text.get(i), "3"};
				ids.add(id);
			}
			if(answers.get(i) == 4) {
				String[] name = new String[] {text.get(i), Integer.toString(i)};
				names.add(name);
			}
			if(answers.get(i) == 5) {
				String[] id = new String[] {text.get(i), "5"};
				ids.add(id);
			}
			if(answers.get(i) == 6) {
				String[] add = new String[] {text.get(i), Integer.toString(i), "2"};
				addresses.add(add);
			}
			if(answers.get(i) == 7) {
				String[] date = new String[] {text.get(i), Integer.toString(i)};
				date2.add(date);
			}
		}
	}
	
	public void checkAddress() throws IOException {
		ArrayList<ArrayList<String[]>> newaddresses = new ArrayList<ArrayList<String[]>>();
		newaddresses.add(new ArrayList<String[]>());
		newaddresses.add(new ArrayList<String[]>());
		
		TreeBuilder tb = new TreeBuilder();
		BST bst1 = tb.buildTree("tree/settlements.txt");
		BST bst2 = tb.buildTree("tree/postcodes.txt");
		BST bst3 = tb.buildTree("tree/streets.txt");
		
		int cnt1 = 1;
		int cnt2 = 1;
		
		for(int i = 0; i<addresses.size(); i++) {
			if(Integer.parseInt(addresses.get(i)[2]) == 1) {
				if((bst1.get(addresses.get(i)[0]) != null) || (bst2.get(addresses.get(i)[0]) != null)) {
					if(cnt1 < 3) {
						String[] s = new String[] {addresses.get(i)[0], addresses.get(i)[1]};
						newaddresses.get(0).add(s);
						cnt1 ++;
					}
					else {
						String[] s = new String[] {addresses.get(i)[0], addresses.get(i)[1]};
						newaddresses.get(1).add(s);
						cnt1 ++;
					}
				}
			}
			
			else {
				if((bst3.get(addresses.get(i)[0]) != null) || Pattern.compile("[0-9]").matcher(addresses.get(i)[0]).find()) {
					if(cnt2 < 3) {
						String[] s = new String[] {addresses.get(i)[0], addresses.get(i)[1]};
						newaddresses.get(0).add(s);
						cnt2 ++;
					}
					else {
						String[] s = new String[] {addresses.get(i)[0], addresses.get(i)[1]};
						newaddresses.get(1).add(s);
						cnt2 ++;
					}
				}
			}
		}
		
		completeAddress(newaddresses);
	}
	
	public void completeAddress(ArrayList<ArrayList<String[]>> newaddresses) {
		for(int i = 0; i< newaddresses.size(); i++) {
			int idx1 = 0;
			int idx2 = 0;
			if(!newaddresses.get(i).isEmpty()) {
				idx1 = Integer.parseInt(newaddresses.get(i).get(1)[1]);
				idx2 = Integer.parseInt(newaddresses.get(i).get(2)[1]); //7
			int plus = idx2-idx1;
			
			String s = people.get(i).getAddress();
			s = text.get(Integer.parseInt(newaddresses.get(i).get(0)[1])); //4
			s = s + " " + text.get(idx1); //5
			for(int j = 1; j<plus; j++) {
				s = s + " " + text.get(idx1+j);
			}
			s = s + " " + text.get(idx2);
			if(newaddresses.get(i).size() <4) {
				s = s + " " + text.get(Integer.parseInt(newaddresses.get(i).get(newaddresses.get(i).size()-1)[1])+1);
			}
			else {
				//s = s + " " + text.get(Integer.parseInt(newaddresses.get(i).get(newaddresses.get(i).size()-1)[1])); //5
				s = s + " " + text.get(idx2+1);
			}
			people.get(i).setAddress(s);
			}
		}
	}
	
	public void checkType() throws IOException {
		TreeBuilder tb = new TreeBuilder();
		BST bst1 = tb.buildTree("tree/contract_types.txt");
		String s = type.get(0).substring(0, 1) + type.get(0).substring(1).toLowerCase();
		if(bst1.get(s) != null) {
			type_string = s;
		}
		
		for(int i = 1; i<type.size(); i++) {
			String str = type.get(i).substring(0, 1) + type.get(i).substring(1).toLowerCase();
			if(bst1.get(str) != null) {
				type_string = type_string + " " + str;
			}
		}
		if(!Pattern.compile("szerződés").matcher(type_string).find()) {
			type_string = type_string + " " + "szerződés";
		}
		
	}
	
	public void checkDate() throws IOException {
		if(date.length() != 0) {
			String[] parts = date.split("\\.");
			ArrayList<String> partslist = new ArrayList<String>();
			for(int i = 0; i<parts.length; i++) {
				String s = parts[i];
				partslist.add(s);
			}
			
			if(partslist.size() != 3) {
			if(partslist.get(0).length() != 4 || partslist.get(1).length() != 2 || partslist.get(2).length() != 2) {
					date = "-";
				}
			}
			
		}
		else if(!date2.isEmpty()) {
			if(date2.size() == 1 && date2.get(0)[0].length() > 8) {
				String[] parts = date2.get(0)[0].split("\\.");
				ArrayList<String> partslist = new ArrayList<String>();
				for(int i = 0; i<parts.length; i++) {
					String s = parts[i];
					partslist.add(s);
				}
				
				if(partslist.size() != 3) {
				if(partslist.get(0).length() != 4 || partslist.get(1).length() != 2 || partslist.get(2).length() != 2) {
						date = "-";
					}
				}
				else {
					date = date2.get(0)[0];
				}
				
			}
			else {
			TreeBuilder tb = new TreeBuilder();
			BST bst1 = tb.buildTree("tree/months.txt");
			String year = new String();
			String month = new String();
			String day = new String();
			
			
			for(int i = 0; i<date2.size(); i++) {
				if(bst1.get(date2.get(i)[0]) != null) {
					month = date2.get(i)[0];
					if(i == 0) {
						String s = text.get(Integer.parseInt(date2.get(i)[1])-1).replace(".", "");
						if(s.length() == 4) {
							year = text.get(Integer.parseInt(date2.get(i)[1])-1);
						}
					}
				}
				else {
					date2.get(i)[0] = date2.get(i)[0].replace(".", "");
					if(date2.get(i)[0].length() == 4 && date2.get(i)[0].matches("\\d+")) {
						year = date2.get(i)[0] + ".";
					}
					if((date2.get(i)[0].length() == 1 || date2.get(i)[0].length() == 2)&& date2.get(i)[0].matches("\\d+")) {
						day = date2.get(i)[0] + ".";
						if(i == 0) {
							String s = text.get(Integer.parseInt(date2.get(i)[1])-2).replace(".", "");
							if(s.length() == 4) {
								year = text.get(Integer.parseInt(date2.get(i)[1])-2);
							}
							String s2 = text.get(Integer.parseInt(date2.get(i)[1])-1);
							if(bst1.get(s2) != null) {
								month = text.get(Integer.parseInt(date2.get(i)[1])-1);
							}
						}
					}
				}
			}
			
			if(day.length() != 0) {
				date = day;
			}
			
			if(month.length() != 0) {
				if(date.length() != 0) {
					date = month + " " + date;
				}
				else {
					date = month;
				}
			}
			
			if(year.length() != 0) {
				if(date.length() != 0) {
					date = year + " " + date;
				}
				else {
					date = year;
				}
			}
			}
		}
		else {
			date = "-";
		}
	}
	
	public void checkIDs() {
		for(int i = 0; i<ids.size(); i++) {
			ids.get(i)[0] = ids.get(i)[0].replace("-", "");
			if(Integer.parseInt(ids.get(i)[1]) == 3 && ids.get(i)[0].length() == 8) {
				people.get(i).setID(ids.get(i)[0]);
			}
			if(Integer.parseInt(ids.get(i)[1]) == 5 && ids.get(i)[0].length() == 11) {
				people.get(i).setTaxID(ids.get(i)[0]);
			}
				
		}
	}
	
	public void checkNames() throws IOException {
		
		ArrayList<String> name1 = new ArrayList<String>();
		ArrayList<String> name2 = new ArrayList<String>();
		
		int idx = Integer.parseInt(names.get(0)[1]);
		name1.add(names.get(0)[0]);
		
		int count = 1;
		
		for(int i = 1; i<names.size(); i++) {
			if(count == 1) {
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
			}
			else {
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
					break;
				}
			}
		}
		
		checkNamesInTree(name1, 1);
		if(name1.size() != names.size()) checkNamesInTree(name2, 2);
	}
	
	public void checkNamesInTree(ArrayList<String> name, int num) throws IOException {
		TreeBuilder tb = new TreeBuilder();
		BST bst1 = tb.buildTree("tree/names_random.txt");
		BST bst2 = tb.buildTree("tree/surnames.txt");
		BST bst3 = tb.buildTree("tree/company.txt");
		
		if(bst3.get(name.get(name.size()-1)) != null) {
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
			if(num == 1) {
				people.get(0).setName(n);
			}
			else {
				people.get(1).setName(n);
			}
		}

		else if((bst1.get(name.get(name.size()-1)) != null) || (bst2.get(name.get(0)) != null)) {
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
			if(num == 1) {
				people.get(0).setName(n);
			}
			else {
				people.get(1).setName(n);
			}
		}
		
		else {
			people.get(0).setName("-");
			people.get(1).setName("-");
		}
	}
	
	
	public void writeData() {
		String format = "%-15s %-5s\n";
		System.out.println();
		for(int i = 0; i<people.size(); i++) {
			System.out.printf(format, "Név:", people.get(i).getName());
			System.out.printf(format, "Cím:", people.get(i).getAddress());
			System.out.printf(format, "Személyi:", people.get(i).getID());
			System.out.printf(format, "Adószám:", people.get(i).getTaxID());
			System.out.println();
		}
		
		System.out.printf(format, "Sz.típus:", type_string);
		System.out.printf(format, "Dátum:", date);
		
	}
	
	public ArrayList<String> getDataList(){
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i<people.size(); i++) {
			list.add(people.get(i).getName());
			list.add(people.get(i).getAddress());
			list.add(people.get(i).getID());
			list.add(people.get(i).getTaxID());
		}
		list.add(type_string);
		list.add(date);
		return list;
	}
	
}
