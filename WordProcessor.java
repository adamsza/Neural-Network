package neural;

import java.util.ArrayList;
import java.util.Arrays;

public class WordProcessor implements Constants{
	//szavak amiket ki kell szedni
	private ArrayList<String> words;
	
	//szó Stringek a szövegből
	private ArrayList<String> text;
	
	//szavak vektorai
	private ArrayList<double[]> vectors;
	
	//indexek listája amelyik szavakat kivett
	private ArrayList<Integer> indices;
	
	public WordProcessor(ArrayList<String> input) {
		public WordProcessor(ArrayList<String> input) {
		indices = new ArrayList<Integer>();
		words = new ArrayList<String>();
		TextProcessor tp = new TextProcessor();
		try {
			words = tp.readWords("words.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(words.size());
		//ömm ja....
		/*
		words = new ArrayList<String>(Arrays.asList(
				"ahhoz",
				"akár",
				"amennyiben",
				"azaz",
				"avagy",
				"azért",
				"azonban",
				"aztán",
				"ám",
				"bár",
				"csak",
				"csakhogy",
				"de",
				"ehhez",
				"ellenben",
				"ennélfogva",
				"eszerint",
				"ezért",
				"ha",
				"hanem",
				"hát",
				"hiszen",
				"hogy",
				"hogyha",
				"is",
				"így",
				"meg",
				"mert",
				"még",
				"mégis",
				"mégse",
				"mintha",
				"mivel",
				"míg",
				"nemcsak",
				"pedig",
				"se",
				"sem",
				"sőt",
				"szintén",
				"tehát",
				"ugyanis",
				"vagy",
				"vagyis",
				"viszont"));
				*/
		text = new ArrayList<>();
		for(int i=0; i<input.size(); i++) {
			text.add(input.get(i));
		}
	}

	}
	
	//produkció
	//kiköpi a szavak vektorainak listáját
	public ArrayList<double[]> getWordVectors() {
		removeWords();
		makeWordVectors();
		return vectors;
	}
	
	//kiveszi a haszontalan szavakat
	public void removeWords() {
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
	
	public ArrayList<Integer> getIndices(){
		return indices;
	}
	
	//ez semmi csak szórakoztam
	public void writeAll() {
		for(int i = 0; i<vectors.size(); i++) {
			double[] db = new double[WORD_VECTOR];
			db = vectors.get(i);
			for(int j = 0; j<WORD_VECTOR; j++) {
			System.out.println(db[j]);
			}
			System.out.println();
		}
	}

}
