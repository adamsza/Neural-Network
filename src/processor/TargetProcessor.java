package processor;

import java.util.ArrayList;

import tools.Constants;

public class TargetProcessor implements Constants{

	public TargetProcessor() {
		
	}
	
	//integer listából válasz vektor dolgokat csinál
	//0: semmi, 1: sz. fajta, 2: név, 3: személyi szám, 4: adószám, 5: cím, 6: dátum
	//0 -> 1000000, 1 -> 0100000, stb
	public ArrayList<double[]> makeTargets(ArrayList<Integer> targets){
		
		ArrayList<double[]> newtargets = new ArrayList<double[]>();
		for(int i = 0; i<targets.size(); i++) {
			int digit = targets.get(i);
			
			
			double[] vector = new double[NUM_OPTIONS];
			for(int j = 0; j<digit; j++) {
				vector[j] = 0;
			}
			vector[digit] = 1;
			for(int k = digit+1; k<NUM_OPTIONS; k++) {
				vector[k] = 0;
			}
			newtargets.add(vector);
			
			
		}
		
		return newtargets;
	}
	
	public ArrayList<ArrayList<double[]>> makeYesNoTargetsForAllNetworks (ArrayList<Integer> targetlist) {
		ArrayList<ArrayList<double[]>> newlist = new ArrayList<>();
		
		for(int i = 0; i<NUM_NETWORK; i++) {
			ArrayList<double[]> list = new ArrayList<>();
			newlist.add(list);
		}
		
		for(int j = 0; j<targetlist.size(); j++) {
			int curr = targetlist.get(j);
			for(int k = 0; k<NUM_NETWORK; k++) {
				double[] db = new double[2];
				if(k == curr) {
					db[0] = 0;
					db[1] = 1;
				}
				else {
					db[0] = 1;
					db[1] = 0;
				}
				newlist.get(k).add(db);
			}
		}
		return newlist;
	}
			
}
