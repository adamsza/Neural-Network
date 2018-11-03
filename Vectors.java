package vectors;

import java.util.ArrayList;

import tools.Constants;

public class Vectors implements Constants{

	private ArrayList<ArrayList<double[]>> vectors;
	
	public Vectors() {
		vectors = new ArrayList<ArrayList<double[]>>();
		for(int i=0; i<NUM_NETWORK; i++) {
			ArrayList<double[]> v = new ArrayList<double[]>();
			vectors.add(v);
		}
	}
	
	public void makeVectorForAllNetworks(String s) {
		
		WordVector address = new AddressWV(s);
		address.makeVector();
		vectors.get(0).add(address.getVector());
		
		WordVector type = new ContractTypeWV(s);
		type.makeVector();
		vectors.get(1).add(type.getVector());
		
		WordVector date = new DateWV(s);
		date.makeVector();
		vectors.get(2).add(date.getVector());
		
		WordVector id = new IDWV(s);
		id.makeVector();
		vectors.get(3).add(id.getVector());
		
		WordVector name = new NameWV(s);
		name.makeVector();
		vectors.get(4).add(name.getVector());
		
		WordVector tax = new TaxIDWV(s);
		tax.makeVector();
		vectors.get(5).add(tax.getVector());
		
	}
	
	public ArrayList<ArrayList<double[]>> getVectors(){
		return vectors;
	}
}
