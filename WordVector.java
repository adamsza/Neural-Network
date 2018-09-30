package neural;

import java.util.regex.Pattern;

public class WordVector {
	
	private String word;
	private double [] vector;
	
	public WordVector(String s) {
		word = new String(s);
	}
	
	public void makeVector(double pos) {
			vector = new double [5];
			vector[0] = containsLetter();
			vector[1] = containsNumber();
			vector[2] = containsCapital();
			vector[3] = pos;
			vector[4] = containsSpecial();
		
	}

	public double containsLetter() {
		return boolToNum(Pattern.compile("[a-zA-Z]").matcher(word).find() == true);
	}
	
	public double containsNumber() {
	    return boolToNum(Pattern.compile("[0-9]").matcher(word).find());
	}
	
	public double containsCapital() {
		return boolToNum(Pattern.compile("[A-Z]").matcher(word).find());
	}
	
	public double boolToNum(boolean b) {
		if(b==true)
			return 1;
		else
			return 0;
	}
	
	public double containsSpecial() {
		//fajta
		if(Pattern.compile("szerzodes").matcher(word).find())
			return 1;
		//szerzodo fel
		if(Pattern.compile("nev|egyresz|masresz|valamint").matcher(word).find())
			return 2;
		//szemelyi szam
		if(Pattern.compile("szemely|igazolvany|szam|okmany|azonosito").matcher(word).find())
			return 3;
		//adoszam
		if(Pattern.compile("adoszam|azonosito").matcher(word).find())
			return 4;
		//lakcim
		if(Pattern.compile("lak|cim|lc|szekhely").matcher(word).find())
			return 5;
		//datum
		if(Pattern.compile("datum").matcher(word).find())
			return 6;

		else return 0;
	}
	
	public double[] getVector() {
		return vector;
	}

	public void writeSth() {
		for(int i=0; i<5; i++)
			System.out.print(vector[i] + "\n");
		System.out.print(word);
		System.out.println("\n");
	}
	
}
