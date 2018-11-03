package neural;

import java.io.IOException;
import java.util.ArrayList;

import network.Network;
import processor.TargetProcessor;
import processor.TextProcessor;

public class Learner extends Valami{
	//válaszok file neve
	private String targetfilename;
	

	public Learner(ArrayList<Network> n, String f) {
		super(n);
		targetfilename = f;
	}
	

	public void startNetworks() {
		//beolvastatja a fileból a target integereket
		TextProcessor txp = new TextProcessor();
		ArrayList<Integer> targets = new ArrayList<Integer>();
		try {
		targets = txp.readTargets(targetfilename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//kiveszi azokat a számokat amiknek a szava már ki lett törölve
		targets = fixTargetVectors(targets);
		TargetProcessor tp = new TargetProcessor();
		
		ArrayList<ArrayList<double[]>> targetvectors = new ArrayList<ArrayList<double[]>>();
		targetvectors = tp.makeYesNoTargetsForAllNetworks(targets);
		
		
		
		for(int i=0; i<NUM_NETWORK; i++) {
			for(int j = 0; j<getInputVectors().get(i).size(); j++) {
				getNetworks().get(i).train(getInputVectors().get(i).get(j), targetvectors.get(i).get(j));
			}

		}
	}
	
	//kiveszi azokat a számokat amiknek a szava már ki lett törölve
	public ArrayList<Integer> fixTargetVectors(ArrayList<Integer> targets) {
		ArrayList<Integer> idx = new ArrayList<Integer>();
		idx = getIndices();
		
		for(int i = 0; i<idx.size(); i++) {
			targets.remove((int)idx.get(i));
		}

		return targets;	
	}
	
}
