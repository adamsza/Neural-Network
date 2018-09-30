package neural;

import java.util.ArrayList;

public class InputProcessor implements Constants {

	private ArrayList<double []> output;
	
	public InputProcessor() {
		output = new ArrayList<double []>();
	}
	
	public ArrayList<double []> getInputVectors(ArrayList<double []> input) {
		input = filler(input);
		makeInputVectors(input);
		return output;
	}
	
	public ArrayList<double []> filler(ArrayList<double []> input) {
		
		double[] zero = new double[WORD_VECTOR];
		
		for(int l=0; l<(WORDS_WINDOW-1)/2; l++) {
			for(int m=0; m<WORD_VECTOR; m++) {
				zero[m]=0;
			}
			input.add(0, zero);
			input.add(zero);
		}
		return input;
	}
	
	public void makeInputVectors(ArrayList<double []> input){
		
		double[] out = new double[WORDS_WINDOW*WORD_VECTOR];

		for(int i=0; i<input.size()-(WORDS_WINDOW-1); i++) {
			for(int j=i; j<i+WORDS_WINDOW; j++) {
				for(int k=0; k<WORD_VECTOR; k++) {
					double[] vec = input.get(i);
					out[(j*WORD_VECTOR)+k] = vec[k];
				}
				output.add(out);
			}
		}
	}

}
