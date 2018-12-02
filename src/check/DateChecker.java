package check;

import java.io.IOException;
import java.util.ArrayList;

import tree.BST;
import tree.TreeBuilder;

//date, date, text
public class DateChecker {

	private String date;
	private ArrayList<String[]> date2;
	private String month;
	private String year;
	private String day;
	
	public DateChecker(String date, ArrayList<String[]> date2) {
		this.date = date;
		this.date2 = date2;
	}
	public String checkDate(ArrayList<String> text) throws IOException {
		if(date.length() != 0) {
			splitDate();
			
		}
		else if(!date2.isEmpty()) {
			splitDate2(text);
			makeStrings();
		}
		
		else {
			date = "-";
		}
		return date;
	}

	public void splitDate(){
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
	
	public void splitDate2(ArrayList<String> text) throws IOException {
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
			checkInTree(text);
		}
	}
	
	public void checkInTree(ArrayList<String> text) throws IOException {
		TreeBuilder tb = new TreeBuilder();
		BST bst1 = tb.buildTree("tree/months.txt");
		
		for(int i = 0; i<date2.size(); i++) {
			if(bst1.get(date2.get(i)[0]) != null) {
				foundInTree(text, i);
			}
			else {
				checkLengths(text, i, bst1);
			}
		}
	}
		
	public void foundInTree(ArrayList<String> text, int i) {
		month = date2.get(i)[0];
		if(i == 0) {
			String s = text.get(Integer.parseInt(date2.get(i)[1])-1).replace(".", "");
			if(s.length() == 4) {
				year = text.get(Integer.parseInt(date2.get(i)[1])-1);
			}
		}
	}
	
	public void checkLengths(ArrayList<String> text, int i, BST bst1) {
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
	
	public void makeStrings() {
		if(day.length() != 0) date = day;
		
		if(month.length() != 0) {
			if(date.length() != 0) date = month + " " + date;
			else date = month;
		}
		
		if(year.length() != 0) {
			if(date.length() != 0) date = year + " " + date;
			else date = year;
		}
	}

}

