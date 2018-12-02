package data;

//szerzodes egy felehez tartozo adatok
public class Person {

	private String name;
	private String id;
	private String taxid;
	private String address;
	
	public Person() {
		name = new String("-");
		address = new String("-");
		id = new String("-");
		taxid = new String("-");
	}

	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String s) {
		address = s;
	}
	
	public String getID() {
		return id;
	}
	
	public void setID(String s) {
			id = s;
	}
	
	public String getTaxID() {
		return taxid;
	}
	
	public void setTaxID(String s) {
		taxid = s;
	}
	

}