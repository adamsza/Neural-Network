package neural;

import java.util.ArrayList;
import java.util.Arrays;

public class Preprocessor {
	private ArrayList<String> words;
	private ArrayList<String> text;
	private ArrayList<WordVector> vectors;
	
	public Preprocessor(ArrayList<String> input) {
		words = new ArrayList<String>(Arrays.asList("de","hogy","azaz", "ugyanis", "eszerint"));
		//for(int i=0; i<words.size(); i++)
		//	System.out.print(words.get(i));
		text = new ArrayList<>();
		//System.arraycopy(input, 0, text, 0, input.size());
		for(int i=0; i<input.size(); i++) {
			text.add(input.get(i));
		}
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
			//newWordVector(i);
			//System.out.print(text.size());
			double j = i+1;
			double pos = j/text.size();
			//System.out.print(pos);
			WordVector wordvec = new WordVector(text.get(i));
			wordvec.makeVector(pos);
			vectors.add(wordvec);
		}
	}
	/*
	public void newWordVector(int i) {
		double pos = (i+1)/text.size();
		WordVector wordvec = new WordVector(text.get(i));
		wordvec.makeVector(pos);
		vectors.add(wordvec);
	}
	*/
	public ArrayList<String> getWords(){
		return words;
	}
	
	public ArrayList<String> getText(){
		return text;
	}
	
	public ArrayList<WordVector> getVectors(){
		return vectors;
	}
	
	public void writeAll() {
		for(int i=0; i<vectors.size(); i++)
			vectors.get(i).writeSth();
			
	}
}
