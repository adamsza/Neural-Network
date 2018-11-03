package neural;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import network.Network;
import processor.InputProcessor;
import processor.TextProcessor;
import processor.WordProcessor;
import tools.Constants;
import vectors.Vectors;

public abstract class Valami implements Constants{
	//hosszú input vektorok listája
	private ArrayList<ArrayList<double[]>> inputvectors;
	
	//hálózat
	private ArrayList<Network> networklist;
	
	//indexek amikhez a szó ki lett törölvesx
	private ArrayList<Integer> indices;
	
	//szavakhoz tartozó vektorok
	//private ArrayList<double[]> vectors;
	private Vectors vectors;
	
	private ArrayList<String> text;
	
	private ArrayList<TrainSet> setlist;
	
	public Valami(ArrayList<Network> n) {
		inputvectors = new ArrayList<ArrayList<double[]>>();
		indices = new ArrayList<Integer>();
		//vectors = new ArrayList<double[]>();
		vectors = new Vectors();
		text = new ArrayList<String>();
		networklist = n;
		setlist = new ArrayList<TrainSet>();
		for(int i = 0; i<NUM_NETWORK; i++) {
			Trainset set = new TrainSet(INPUT_VECTOR, NUM_NETWORK_OPTIONS);
			setlist.add(set);
		}
	}
	
	//csak elindítja az egész produkciót
	public void execute(String filename) {
		readTextFromFile(filename);
		processWordsForAllNetworks(filename);
		makeInputsForAllNetworks();
		startNetworks();
	}
	
	
	public void readTextFromFile(String filename) {
		TextProcessor tp = new TextProcessor();
		try{
		text = tp.readWords(filename);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//szavak vektorait produkálja
	public void processWordsForAllNetworks(String filename){
		
		//a Stringekből vektorokat csinál minden szónak
		WordProcessor wp = new WordProcessor(text);
		vectors = wp.getAllWordVectors();
		//System.out.println(vectors.getVectors().get(0).size());
		text = wp.getText();
		
		//elkéri a kivett szavak indexeit
		indices = wp.getRemovedIndices();
	}
	
	//hosszú input vektorokat csinál a szavak vektoraiból
	public void makeInputsForAllNetworks() {
		//System.out.println(NUM_NETWORK);
		for(int i = 0; i< NUM_NETWORK; i++) {
			InputProcessor ip = new InputProcessor();
			ArrayList<double[]> list = new ArrayList<>();
			list = ip.getInputVectorsForOneNetwork(vectors.getVectors().get(i));
			inputvectors.add(list);
			//System.out.println(i);
			
		}
		
		//System.out.println(inputvectors.get(0).size());
		//System.out.println(inputvectors.size());
		
	}
	
	public ArrayList<Network> getNetworks() {
		return networklist;
	}
	
	public ArrayList<ArrayList<double[]>> getInputVectors() {
		return inputvectors;
	}
	
	public Vectors getVectors() {
		return vectors;
	}
	
	public ArrayList<Integer> getIndices() {
		return indices;
	}
	
	public ArrayList<String> getText() {
		return text;
	}
	
	public TrainSet getTrainSetList() {
		return setlist;
	}
	
	public abstract void startNetworks();
	
	
}
