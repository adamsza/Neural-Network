package neural;

import java.util.ArrayList;

import tools.Constants;

public class Classifier implements Constants{

	private ArrayList<String> words;
	private ArrayList<Integer> values;
	private ArrayList<String> type;
	
	public Classifier() {
		words = new ArrayList<String>();
		values = new ArrayList<Integer>();
		type = new ArrayList<String>();
	}
	
	public void add(String s, double[] d) {
		words.add(s);
		makeValue(d);
	}
	
	public void makeValue(double[] d) {
		double max = 0;
		int idx = -1;
		for(int i = 0; i<NUM_OPTIONS; i++) {
			if((d[i] > max) && d[i] > BOUNDARY) {
				max = d[i];
				idx = i;
			}
		}
		values.add(idx);
		makeType(idx);
	}
	
	public void makeType(int i) {
		switch(i) {
		case -1: type.add("nem tudom");
				break;
		case 0: type.add("semmi");
				break;
		case 1: type.add("fajta");
			break;
		case 2: type.add("nev");
			break;
		case 3: type.add("szemelyi");
			break;
		case 4: type.add("ado");
			break;
		case 5: type.add("lakcim");
			break;
		case 6: type.add("datum");
			break;
		}
	}
	
	public void printAll() {
		String format = "%-15s %-5s %-7s\n";
		for(int i = 0; i<words.size(); i++) {
			/*
			System.out.print(words.get(i) + "\t\t");
			System.out.print(values.get(i) + "\t\t");
			System.out.print(type.get(i) + "\t\t");
			System.out.println();
			*/
			System.out.printf(format, words.get(i), values.get(i), type.get(i));
		}
	}
}
