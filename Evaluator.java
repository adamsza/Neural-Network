package neural;

import java.util.Arrays;

import network.Network;

public class Evaluator extends Valami{

	public Evaluator(Network n) {
		super(n);
	}
	
public void startNetwork() {
		//hosszú input vektorokat megcsinálja
		makeInputVectors();
		
		//odaadja egyesevel a networknek a hosszú input vektorokat
		Network n = getNetwork();
		Classifier c = new Classifier();
		for(int i=0; i<getInputVectors().size(); i++) {
			double[] output = n.calculate(getInputVectors().get(i));
			System.out.println(Arrays.toString(output));
			c.add(getText().get(i), output);
		}
		c.printAll();
	}
}
