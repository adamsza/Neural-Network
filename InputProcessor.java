package processor;

import java.util.ArrayList;

import tools.Constants;

public class InputProcessor implements Constants {

	private ArrayList<double []> output;
	
	public InputProcessor() {
		output = new ArrayList<double []>();
	}
	
	//visszaadja a hosszú inputok listáját
	public ArrayList<double []> getInputVectorsForOneNetwork(ArrayList<double []> input) {
		//System.out.println(input.size());
		input = fillFrontAndEnd(input);
		makeLongInputVectors(input);
		return output;
	}
	
	//kitölti a szöveg elejét és végét nullákkal,
	//hogy az első hosszú input vektor középső szava a szöveg első szava legyen
	//ugyanígy a végén is
	public ArrayList<double []> fillFrontAndEnd(ArrayList<double []> input) {
		
		double[] zero = new double[WORD_VECTOR];
		
		for(int l=0; l<(WORDS_WINDOW-1)/2; l++) {
			for(int m=0; m<WORD_VECTOR; m++) {
				zero[m]=0;
			}
			input.add(0, zero);
			input.add(zero);
			/*
			zero[0]=1;
			zero[WORD_VECTOR-1]=1;
			for(int m=1; m<(WORD_VECTOR-1); m++) {
				zero[m]=0;
			}
			input.add(0, zero);
			input.add(zero);
			*/
		}
		//System.out.println(input.size());
		return input;
	}
	
	//hosszú vektorokká összefűzzi a valahány bemeneti szó vektorát
	public void makeLongInputVectors(ArrayList<double []> input){

		//System.out.println(input.size());
		for(int i=0; i<(input.size()-(WORDS_WINDOW-1)); i++) {
			double[] out = new double[WORDS_WINDOW*WORD_VECTOR];
			for(int j=0; j<WORDS_WINDOW; j++) {
				double[] vec = input.get(i+j);
				for(int k=0; k<WORD_VECTOR; k++) {
					out[(j*WORD_VECTOR)+k] = vec[k];
				}
			}
			output.add(out);
			
		}
		//System.out.println(output.size());
	}

}