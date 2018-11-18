package processor;

import java.util.ArrayList;

import tools.Constants;

public class AnswerProcessor implements Constants{
	
	ArrayList<Integer> answers;
	
	public AnswerProcessor() {
		answers = new ArrayList<Integer>();
	}
	
	public ArrayList<String> processAnswers(ArrayList<ArrayList<double[]>> allanswers) {
		evaluateAnswers(allanswers);
		return answersInWords();
	}
	
	public void evaluateAnswers(ArrayList<ArrayList<double[]>> allanswers) {
		for(int i = 0; i<allanswers.get(0).size(); i++) {
			
			double max = 0;
			int idx = 0;
			
			for(int j = 0; j<NUM_NETWORK; j++) {
				if(max < allanswers.get(j).get(i)[1]) {
					max = allanswers.get(j).get(i)[1];
					idx = j;
				}
			}
			
			if(max < YES_NO_BOUNDARY) {
				idx = 9;
			}
			
			answers.add(idx);
		}
		/*
		for(int i = 0; i<answers.size(); i++) {
		System.out.println(answers.get(i));
		}
		*/
	}
	
	public ArrayList<String> answersInWords() {
		ArrayList<String> wordanswers = new ArrayList<String>();
		
		for(int i = 0; i<answers.size(); i++) {
			switch(answers.get(i)) {
			case 0: wordanswers.add("lakcím");
					break;
			case 1: wordanswers.add("szerződés típus");
				break;
			case 2: wordanswers.add("dátum");
				break;
			case 3: wordanswers.add("személyi igazolvány szám");
				break;
			case 4: wordanswers.add("név");
				break;
			case 5: wordanswers.add("adószám");
				break;
			case 6: wordanswers.add("lakcím2");
			break;
			case 7: wordanswers.add("dátum2");
			break;
			case 9: wordanswers.add("----");
				break;
			}
		}
		return wordanswers;
	}
	
	public ArrayList<Integer> getAnswers() {
		return answers;
	}

}
