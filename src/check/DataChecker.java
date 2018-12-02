package check;

import java.io.IOException;
import java.util.ArrayList;
import data.Person;

public class DataChecker {
	
	private ArrayList<Person> people;
	private ArrayList<String[]> names;
	private ArrayList<String[]> addresses;
	private ArrayList<String[]> ids;
	private ArrayList<String[]> date2;
	private String date;
	private ArrayList<String> type;
	private String type_string;
	
	private ArrayList<String> text;
	private ArrayList<Integer> answers;
	
	public DataChecker(ArrayList<String> text, ArrayList<Integer> answers) {
		this.text = text;
		this.answers = answers;
		
		people = new ArrayList<Person>();
		people.add(new Person());
		people.add(new Person());
		
		date = new String();
		date2 = new ArrayList<String[]>();
		
		type = new ArrayList<String>();
		type_string = new String("-");
		
		names = new ArrayList<String[]>();
		addresses = new ArrayList<String[]>();
		ids = new ArrayList<String[]>();
	}
	
	public void getData() {
		extractUsefulData();
		try {
			checkAddress();
			checkType();
			checkDate();
			checkIDs();
			checkNames();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writeData();
		
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
	
	
	private void extractUsefulData() {
		
		for(int i = 0; i<text.size(); i++) {
			switch(answers.get(i)) {
			case 0:	String[] add = new String[] {text.get(i), Integer.toString(i), "1"};
					addresses.add(add);
					break;

			case 1:	type.add(text.get(i));
					break;
			
			case 2:	date = text.get(i);
					break;
			
			case 3:	String[] id = new String[] {text.get(i), "3"};
					ids.add(id);
					break;
			
			case 4:	String[] name = new String[] {text.get(i), Integer.toString(i)};
					names.add(name);
					break;
			
			case 5:	String[] taxid = new String[] {text.get(i), "5"};
					ids.add(taxid);
					break;
			
			case 6:	String[] add2 = new String[] {text.get(i), Integer.toString(i), "2"};
					addresses.add(add2);
					break;
			
			case 7:	String[] date = new String[] {text.get(i), Integer.toString(i)};
					date2.add(date);
					break;
			}
		}
	}
	
	private void checkAddress() throws IOException {
		if(!addresses.isEmpty()) {
			AddressChecker ac = new AddressChecker();
			ArrayList<String> adds = ac.checkAddress(addresses, text);
			for(int i = 0; i<people.size(); i++) {
				people.get(i).setAddress(adds.get(i));
			}
		}
	}
	
	private void checkType() throws IOException {
		if(!type.isEmpty()) {
			ContractTypeChecker ctc = new ContractTypeChecker();
			type_string = ctc.checkContractType(type);
		}
	}
	
	private void checkDate() throws IOException {
		DateChecker dc = new DateChecker(date, date2);
		date = dc.checkDate(text);
	}
	
	private void checkIDs() {
		IDChecker idc = new IDChecker();
		people = idc.checkIDs(ids, people);
	}
	
	private void checkNames() throws IOException {
		if(!names.isEmpty()) {
			NameChecker nc = new NameChecker();
			ArrayList<String> str = nc.checkNames(names, text);
			for(int i = 0; i<str.size(); i++) {
				people.get(i).setName(str.get(i));
			}
		}
	}
	
	private void writeData() {
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
	
}
