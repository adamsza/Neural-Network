package vectors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

import tools.Constants;

public class WordVector implements Constants{
	
	//szĂł amit vektorrĂˇ kell varĂˇzsolni
	private String word;
	
	//lĂ¶ emlĂ­tett vektor
	private double [] vector;
	
	public WordVector(String s) {
		word = new String(s);
	}
	
	//vektor produkciĂł
	public void makeVector() {
			vector = new double [WORD_VECTOR];
			vector[0] = containsLetter();
			vector[1] = containsNumber();
			vector[2] = containsCapital();
			vector[3] = containsSymbol();
			vector[4] = containsSpecialWord();
	}

	//van-e benne betĹ±
	public double containsLetter() {
		return boolToNum(Pattern.compile("[a-zA-ZźÀ-ź]").matcher(word).find());
	}
	
	//van-e benne szĂˇm
	public double containsNumber() {
	    return boolToNum(Pattern.compile("[0-9]").matcher(word).find());
	}
	
	//van-e benne kepitĂ¶l szĂˇm
	//nem tudtam itt okosabbat mint felsorolni Ĺ‘ket mert furĂˇn van a kĂłdjuk
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
	
	//fun fun szavak hĂˇtha vannak
	public double containsSpecialWord() {
		//fajta
		//if(Pattern.compile("szerződés").matcher(word).find())
			//return 1;
		//szerzodo fel
		if(Pattern.compile("név|egyrész|másrész|valamint|egyfelől|másfelől").matcher(word).find())
			return 1;
		//szemelyi szam
		/*if(Pattern.compile("személy|igazolvány|ig|szám|okmany|azonositó").matcher(word).find())
			return 3;
		//adoszam
		if(Pattern.compile("adószám|azonositó|jel").matcher(word).find())
			return 4;
		//lakcim
		if(Pattern.compile("lak|cím|lc|szék|hely").matcher(word).find())
			return 5;
		//datum
		if(Pattern.compile("dátum|év|hó|nap|kelt").matcher(word).find())
			return 6;*/

		else return 0;
	}
	
	public double[] getVector() {
		return vector;
	}
	
	//ez is semmi csak szĂłrakoztam
	public void write() {
		for(int i = 0; i<WORD_VECTOR; i++)
			System.out.println(vector[i]);
	}
	
	public void isCommonName() throws IOException
	{
		FileReader fr = new FileReader("name_things/common_names.txt");
		BufferedReader br = new BufferedReader(fr);
		boolean q = true;
		String line = br.readLine();
		while(q)
		{
			
		}
		br.close();
	}

}
