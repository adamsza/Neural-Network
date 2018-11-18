package processor;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;

import tools.Constants;
import vectors.Vectors;
import vectors.WordVector;

public class WordProcessor implements Constants{
	//szavak amiket ki kell szedni
	private ArrayList<String> words;
	
	//szó Stringek a szövegből
	private ArrayList<String> text;
	
	//szavak vektorai
	private Vectors vectors;
	
	//indexek listája amelyik szavakat kivett
	private ArrayList<Integer> indices;
	
	public WordProcessor(ArrayList<String> input) {
		indices = new ArrayList<Integer>();
		words = new ArrayList<String>();
		TextProcessor tp = new TextProcessor();
		try {
			words = tp.readWords("words.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		text = new ArrayList<>();
		for(int i=0; i<input.size(); i++) {
			text.add(input.get(i));
		}
	}
	
	//produkció
	//kiköpi a szavak vektorainak listáját
	public Vectors getAllWordVectors() {
		removeUselessWords();
		makeAllWordsToVectorsForAllNetworks();
		return vectors;
	}
	
	//kiveszi a haszontalan szavakat
	public void removeUselessWords() {
		for(int i=0; i<text.size(); i++) {
			for(int j=0; j<words.size(); j++) {
				if(text.get(i).equals(words.get(j))) {
					text.remove(i);
					indices.add(i);
				}
			}
		}	
	}
	
	//megcsinálja a szavak vektorait egyenként
	//berakja a listába
	public void makeAllWordsToVectorsForAllNetworks() {
		vectors = new Vectors();
		for(int i=0; i<text.size(); i++) {
			vectors.makeVectorForAllNetworks(text.get(i));
		}
		
		
		/*
		vectors = new ArrayList<>();
		for(int i=0; i<text.size(); i++) {
			double j = i+1;
			double pos = j/text.size();
			WordVector wordvec = new WordVector(text.get(i));
			wordvec.makeVector();
			vectors.add(wordvec.getVector());
		}
		*/
	}
	
	public ArrayList<String> getUselessWords(){
		return words;
	}
	
	public ArrayList<String> getText(){
		return text;
	}
	
	public ArrayList<Integer> getRemovedIndices(){
		return indices;
	}

}
