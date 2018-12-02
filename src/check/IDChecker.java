package check;

import java.util.ArrayList;

import data.Person;

//ids
//people id-t ad vissza
public class IDChecker {

	//szemelyi igazolvany szam es adoszamok hosszanak ellenorzese
	public ArrayList<Person> checkIDs(ArrayList<String[]> ids, ArrayList<Person> people) {
		
		for(int i = 0; i<ids.size(); i++) {
			ids.get(i)[0] = ids.get(i)[0].replace("-", "");
			
			int id = Integer.parseInt(ids.get(i)[1]);
			int idlength = ids.get(i)[0].length();
			
			if(id == 3 && idlength == 8) people.get(i).setID(ids.get(i)[0]);
			if(id == 5 && idlength == 11) people.get(i).setTaxID(ids.get(i)[0]);
		}
		return people;
	}
}
