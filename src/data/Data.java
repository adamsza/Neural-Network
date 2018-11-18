package data;

import java.util.ArrayList;

public class Data {
	
	private ArrayList<Integer> answers;
	private ArrayList<String> text;
	private ArrayList<Integer> filtered;
	
	private Person p1;
	
	private Person p2;
	
	private String type;
	private ArrayList<String> date;
	
	public Data(ArrayList<Integer> answers, ArrayList<String> text) {
		this.answers = answers;
		this.text = text;
		filtered = new ArrayList<>();
		p1 = new Person();
		p2 = new Person();
		date = new ArrayList<>();
		type = new String();
	}
	
	public void collectData() {
		filterAnswers();
		findAddresses();
		findType();
		findDate();
		findIDs();
		findNames();
		//findTaxIDs();
		displayData();
	}
	
	public void filterAnswers() {
		for(int i = 0; i<answers.size(); i++) {
			if(answers.get(i) != 9) {
				filtered.add(i);
			}
		}
	}
	
	public void findAddresses() {
		int addresscnt = 1;
		for(int i = 0; i<filtered.size(); i++) {
			int j = (int)filtered.get(i);
			
			if(answers.get(j) == 0 && addresscnt == 1) {
				p1.getAddress().add(text.get(j));
				if(answers.get(j+1) != 0) {
					addresscnt = 2;
				}
			}
			
			else if(answers.get(j) == 0 && addresscnt == 2) {
				p2.getAddress().add(text.get(j));
			}
		}
	}
	
	public void findType() {
		for(int i = 0; i<filtered.size(); i++) {
			int j = (int)filtered.get(i);

			if(answers.get(j) == 1) {
				type = text.get(j);
			}
		}
	}
	
	public void findDate() {
		int datecnt = 1;
		for(int i = filtered.size()-1; i > 0; i--) {
			int j = (int)filtered.get(i);
			
			if(answers.get(j) == 2 && datecnt == 1) {
				date.add(text.get(j));
				if(j == answers.size()-1 || answers.get(j+1) != 2) {
					datecnt = 2;
				}
			}
			
			else if(answers.get(j) == 2 && datecnt == 2) {
				date.add(text.get(j));
			}
		}
	}
	
	
	public void findIDs() {
		int idcnt = 1;
		for(int i = 0; i<filtered.size(); i++) {
			int j = (int)filtered.get(i);
			
			if((answers.get(j) == 3 || answers.get(j) == 5) && idcnt == 1) {
				p1.setID(text.get(j), answers.get(j));
				if(answers.get(j+1) != 4) {
					idcnt = 2;
				}
			}
			
			else if((answers.get(j) == 3 || answers.get(j) == 5) && idcnt == 2) {
				p2.setID(text.get(j), answers.get(j));
			}
		}
	}
	
	public void findNames() {
		int namecnt = 1;
		for(int i = 0; i<filtered.size(); i++) {
			int j = (int)filtered.get(i);
			
			if(answers.get(j) == 4 && namecnt == 1) {
				p1.getName().add(text.get(j));
				if(answers.get(j+1) != 4) {
					namecnt = 2;
				}
			}
			
			else if(answers.get(j) == 4 && namecnt == 2) {
				p2.getName().add(text.get(j));
			}
		}
	}
	
	/*
	public void findTaxIDs() {
		int taxidcnt = 1;
		for(int i = 0; i<filtered.size(); i++) {
			int j = (int)filtered.get(i);
			
			if(answers.get(j) == 5 && taxidcnt == 1) {
				p1.setTaxID(text.get(j));
				if(answers.get(j+1) != 4) {
					taxidcnt = 2;
				}
			}
			
			else if(answers.get(j) == 5 && taxidcnt == 2) {
				p2.setTaxID(text.get(j));
			}
		}
	}
	*/
	
	public void displayData() {
		System.out.println();
		System.out.println();
		
		System.out.println("tipus: " + type);
		
		System.out.print("nev1: ");
		for(int i = 0; i<p1.getName().size(); i++)
			System.out.print(p1.getName().get(i) + " ");
		System.out.println();
		
		System.out.println("szemelyi1: " + p1.getID());
		
		System.out.println("ado1: " + p1.getTaxID());
		
		System.out.print("cim1: ");
		for(int i = 0; i<p1.getAddress().size(); i++)
			System.out.print(p1.getAddress().get(i) + " ");
		System.out.println();
		
		System.out.println();
		
		System.out.print("nev2: ");
		for(int i = 0; i<p2.getName().size(); i++)
			System.out.print(p2.getName().get(i) + " ");
		System.out.println();
		
		System.out.println("szemelyi2: " + p2.getID());
		
		System.out.println("ado2: " + p2.getTaxID());
		
		System.out.print("cim2: ");
		for(int i = 0; i<p2.getAddress().size(); i++)
			System.out.print(p2.getAddress().get(i) + " ");
		System.out.println();
		
		System.out.println();
		
		System.out.print("datum: ");
		for(int i = 0; i<date.size(); i++)
			System.out.print(date.get(i) + " ");
		System.out.println();
		
		
	}
	
}
