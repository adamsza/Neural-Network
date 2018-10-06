package neural;

import java.util.Arrays;

public class Evaluator extends Valami{

	public Evaluator(Network n) {
		super(n);
	}
	
	public void startNetwork() {
		//hosszú input vektorokat megcsinálja
		makeInputVectors();
		
		//odaadja egyesevel a networknek a hosszú input vektorokat
		Network n = getNetwork();
		for(int i=0; i<getInputVectors().size(); i++) {
			double[] output = n.calculate(getInputVectors().get(i));
			System.out.print(Arrays.toString(output));
		}
	}
}
