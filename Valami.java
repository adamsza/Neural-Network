package neural;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Valami {
	//hosszú input vektorok listája
	private ArrayList<double[]> inputvectors;
	
	//hálózat
	private Network network;
	
	//indexek amikhez a szó ki lett törölve
	private ArrayList<Integer> indexes;
	
	//szavakhoz tartozó vektorok
	private ArrayList<double[]> vectors;
	
	public Valami(Network n) {
		inputvectors = new ArrayList<double[]>();
		indexes = new ArrayList<Integer>();
		vectors = new ArrayList<double[]>();
		network = n;
	}
	
	//csak elindítja az egész produkciót
	public void execute(String filename) {
		makeInput(filename);
		startNetwork();
	}
	
	//szavak vektorait produkálja
	public void makeInput(String filename){
		//a szöveg filenevével meghívja a String lista csinálót
		TextProcessor tp = new TextProcessor();
		ArrayList<String> separated = new ArrayList<String>();
		try{
		separated = tp.readWords(filename);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		//a Stringekből vektorokat csinál minden szónak
		WordProcessor wp = new WordProcessor(separated);
		vectors = wp.getWordVectors();
		
		//elkéri a kivett szavak indexeit
		indexes = wp.getIndexes();
	}
	
	//hosszú input vektorokat csinál a szavak vektoraiból
	public void makeInputVectors() {
		InputProcessor ip = new InputProcessor();
		inputvectors = ip.createInputVectors(vectors);
	}
	
	public Network getNetwork() {
		return network;
	}
	
	public ArrayList<double[]> getInputVectors() {
		return inputvectors;
	}
	
	public ArrayList<double[]> getVectors() {
		return vectors;
	}
	
	public ArrayList<Integer> getIndexes() {
		return indexes;
	}
	
	public abstract void startNetwork();
	
	
}
