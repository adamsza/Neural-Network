package neural;

import java.util.ArrayList;

public class InputProcessor implements Constants {

	private ArrayList<double []> output;
	
	public InputProcessor() {
		output = new ArrayList<double []>();
	}
	
	//visszaadja a hosszú inputok listáját
	public ArrayList<double []> createInputVectors(ArrayList<double []> input) {
		input = filler(input);
		makeInputVectors(input);
		return output;
	}
	
	//kitölti a szöveg elejét és végét nullákkal,
	//hogy az első hosszú input vektor középső szava a szöveg első szava legyen
	//ugyanígy a végén is
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
	
	//hosszú vektorokká összefűzzi a valahány bemeneti szó vektorát
	public void makeInputVectors(ArrayList<double []> input){
		
		double[] out = new double[WORDS_WINDOW*WORD_VECTOR];
		System.out.println(input.size());
		for(int i=0; i<(input.size()-(WORDS_WINDOW-1)); i++) {
			for(int j=0; j<WORDS_WINDOW; j++) {
				for(int k=0; k<WORD_VECTOR; k++) {
					double[] vec = input.get(i);
					out[(j*WORD_VECTOR)+k] = vec[k];
				}
				System.out.println(output.size());
			}
			output.add(out);
		}

	}

}
