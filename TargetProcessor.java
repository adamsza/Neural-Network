package processor;

import java.util.ArrayList;
import java.util.Arrays;

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
	
public ArrayList<double[]> newmakeTargets(ArrayList<Integer> targets){
		
		ArrayList<double[]> newtargets = new ArrayList<double[]>();
		for(int i = 0; i<targets.size(); i++) {
			int digit = targets.get(i);
			newtargets.add(new double[] {digit});
		}
		return newtargets;
	}
			
}
