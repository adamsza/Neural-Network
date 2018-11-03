package vectors;

import java.util.regex.Pattern;

import tools.Constants;

public abstract class WordVector implements Constants{
	
	//szó amit vektorrá kell varázsolni
	private String word;
	
	//lö említett vektor
	private double [] vector;
	
	public WordVector(String s) {
		word = new String(s);
	}
	
	//vektor produkció
	public void makeVector() {
			vector = new double [WORD_VECTOR];
			vector[0] = containsLetter();
			vector[1] = containsNumber();
			vector[2] = containsCapital();
			vector[3] = containsSymbol();
			//vector[4] = pos;
			vector[4] = containsSpecialWord();
			//vector[5] = letterRatio();
			//vector[7] = word.length();
		
	}

	//van-e benne betű
	public double containsLetter() {
		return boolToNum(Pattern.compile("[a-zA-ZźÀ-ź]").matcher(word).find());
	}
	
	//van-e benne szám
	public double containsNumber() {
	    return boolToNum(Pattern.compile("[0-9]").matcher(word).find());
	}
	
	//van-e benne kepitöl szám
	//nem tudtam itt okosabbat mint felsorolni őket mert furán van a kódjuk
	//de ha tudsz jobbat akkor nyugodtan
	public double containsCapital() {
		return boolToNum(Pattern.compile("[A-Z]|Á|É|Ó|Ö|Ő|Ú|Ü|Ű").matcher(word).find());
	}

	//van-e benne - vagy / vagy .
	public double containsSymbol() {
		return boolToNum(Pattern.compile("-|/|[.]").matcher(word).find());
	}
	
	//igaz: 1, hamis: 0
	public double boolToNum(boolean b) {
		if(b==true)
			return 1;
		else
			return 0;
	}
	
	public double letterRatio() {
		int count = 0;
		for (int i = 0; i < word.length(); i++) {
		    if (Character.isDigit(word.charAt(i))) {
		        count++;
		    }
		}
		double out = word.length()-count;
		return out/word.length();
	}
	
	//fun fun szavak hátha vannak
	public abstract double containsSpecialWord();
		//fajta
		/*
		if(Pattern.compile("szerződés").matcher(word).find())
			return 1;
		//szerzodo fel
		if(Pattern.compile("név|egyrész|másrész|valamint|egyfelől|másfelől").matcher(word).find())
			return 2;
		//szemelyi szam
		if(Pattern.compile("személy|igazolvány|ig|szám|okmany|azonositó").matcher(word).find())
			return 3;
		//adoszam
		if(Pattern.compile("adószám|azonositó|jel").matcher(word).find())
			return 4;
		//lakcim
		if(Pattern.compile("lak|cím|lc|szék|hely").matcher(word).find())
			return 5;
		//datum
		if(Pattern.compile("dátum|év|hó|nap|kelt").matcher(word).find())
			return 6;

		else return 0;
		*/

	
	public double[] getVector() {
		return vector;
	}
	
	//ez is semmi csak szórakoztam
	public void write() {
		for(int i = 0; i<WORD_VECTOR; i++)
			System.out.println(vector[i]);
	}
	
	public String getWord() {
		return word;
	}

}
