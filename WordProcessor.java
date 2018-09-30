package neural;

import java.util.ArrayList;
import java.util.Arrays;

public class WordProcessor {
	private ArrayList<String> words;
	private ArrayList<String> text;
	private ArrayList<double[]> vectors;
	
	public WordProcessor(ArrayList<String> input) {
		words = new ArrayList<String>(Arrays.asList("de","hogy","azaz", "ugyanis", "eszerint"));
		text = new ArrayList<>();
		for(int i=0; i<input.size(); i++) {
			text.add(input.get(i));
		}
	}
	
	public ArrayList<double[]> getWordVectors() {
		removeWords();
		makeWordVectors();
		return vectors;
	}
	
	public void removeWords() {
		for(int i=0; i<text.size(); i++) {
			for(int j=0; j<words.size(); j++) {
				if(text.get(i).equals(words.get(j)))
					text.remove(i);
			}
		}	
	}
	
	public void makeWordVectors() {
		vectors = new ArrayList<>();
		for(int i=0; i<text.size(); i++) {
			double j = i+1;
			double pos = j/text.size();
			WordVector wordvec = new WordVector(text.get(i));
			wordvec.makeVector(pos);
			vectors.add(wordvec.getVector());
		}
	}
	
	public ArrayList<String> getWords(){
		return words;
	}
	
	public ArrayList<String> getText(){
		return text;
	}

}
