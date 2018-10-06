package neural;

import java.io.IOException;
import java.util.ArrayList;

public class Learner extends Valami{
	//válaszok file neve
	private String targetfilename;
	

	public Learner(Network n, String f) {
		super(n);
		targetfilename = f;
	}
	

	public void startNetwork() {
		//beolvastatja a fileból a target integereket
		TextProcessor txp = new TextProcessor();
		ArrayList<Integer> targets = new ArrayList<Integer>();
		try {
		targets = txp.readTargets(targetfilename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//kiveszi azokat a számokat amiknek a szava már ki lett törölve
		fixTargetVectors(targets);
		TargetProcessor tp = new TargetProcessor();
		ArrayList<double[]> targetvectors = new ArrayList<double[]>();
		targetvectors = tp.makeTargets(targets);

		//hosszú input vektorokat megcsinálja
		makeInputVectors();
		
		//odaadja egyesevel a networknek a hosszú input vektorokat a targettel
		Network n = getNetwork();
		for(int i=0; i<getInputVectors().size(); i++) {
			n.train(getInputVectors().get(i), targetvectors.get(i));
		}
	}
	
	//kiveszi azokat a számokat amiknek a szava már ki lett törölve
	public ArrayList<Integer> fixTargetVectors(ArrayList<Integer> targets) {
		ArrayList<Integer> idx = new ArrayList<Integer>();
		idx = getIndices();
		for(int i = 0; i<idx.size(); i++) {
			targets.remove(idx.get(i));
		}
		return targets;	
	}
	
}
