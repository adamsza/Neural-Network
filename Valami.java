package neural;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import network.Network;
import network.TrainSet;
import processor.InputProcessor;
import processor.TextProcessor;
import processor.WordProcessor;
import tools.Constants;

public abstract class Valami {
	//hosszú input vektorok listája
	private ArrayList<double[]> inputvectors;
	
	//hálózat
	private Network network;
	
	//indexek amikhez a szó ki lett törölve
	private ArrayList<Integer> indices;
	
	//szavakhoz tartozó vektorok
	private ArrayList<double[]> vectors;
	
	private ArrayList<String> text;
	
	private TrainSet set;
	
	public Valami(Network n) {
		inputvectors = new ArrayList<double[]>();
		indices = new ArrayList<Integer>();
		vectors = new ArrayList<double[]>();
		text = new ArrayList<String>();
		set = new TrainSet(Constants.INPUT_VECTOR, Constants.NUM_OPTIONS);
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
		text = wp.getText();
		
		//elkéri a kivett szavak indexeit
		indices = wp.getIndices();
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
	
	public ArrayList<Integer> getIndices() {
		return indices;
	}
	
	public ArrayList<String> getText() {
		return text;
	}
	
	public TrainSet getTrainSet()
	{
		return set;
	}
	
	public abstract void startNetwork();
	
	
}
