package data;

import java.util.ArrayList;

public class Person {

	private ArrayList<String> name;
	private String id;
	private String taxid;
	private ArrayList<String> address;
	
	public Person() {
		name = new ArrayList<>();
		address = new ArrayList<>();
		id = new String();
		taxid = new String();
	}
	
	public ArrayList<String> getName() {
		return name;
	}
	
	public ArrayList<String> getAddress() {
		return address;
	}
	
	public String getID() {
		return id;
	}
	
	public void setID(String s, int i) {
		if(i == 3)
			id = s;
		if(i == 5)
			taxid = s;
	}
	
	public String getTaxID() {
		return taxid;
	}
	
	public void setTaxID(String s) {
		taxid = s;
	}
}
