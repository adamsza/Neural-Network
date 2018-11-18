package neural;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import check.DataChecker;
import data.Data;
import network.Network;
import processor.AnswerProcessor;

public class Evaluator extends Valami{
	private ArrayList<String> answerlist;

	public Evaluator(ArrayList<Network> n) {
		super(n);
		answerlist = new ArrayList<String>();
	}
	
	public void startNetworks() {

		ArrayList<ArrayList<double[]>> answers = new ArrayList<ArrayList<double[]>>();
		//Classifier c = new Classifier();
		
		for(int i=0; i<NUM_NETWORK; i++) {
			ArrayList<double[]> list = new ArrayList<double[]>();
			answers.add(list);
	
			
			for(int j = 0; j<getInputVectors().get(0).size(); j++) {
				double[] output = getNetworks().get(i).calculate(getInputVectors().get(i).get(j));
			
				System.out.print(output[0] + "  ");
				System.out.print(output[1]);
				System.out.println();
				
				answers.get(i).add(new double[] {output[0], output[1]});
			
				//System.out.println(Arrays.toString(output));
				//c.add(getText().get(i), output);
			}
			
			System.out.println();
			
		}

		
		//c.printAll();
		ArrayList<String> wordanswers = new ArrayList<String>();
		AnswerProcessor ap = new AnswerProcessor();
		wordanswers = ap.processAnswers(answers);
		
		String format = "%-15s %-5s %-7s\n";
		
		for(int i = 0; i<getText().size(); i++) {
			System.out.printf(format, getText().get(i), ap.getAnswers().get(i), wordanswers.get(i));
		}
		DataChecker dc = new DataChecker(getText(), ap.getAnswers());
		dc.getData();
		answerlist = dc.getDataList();
		
		//Data data = new Data(ap.getAnswers(), getText());
		//data.collectData();
	}
	
	public ArrayList<String> getDataList(){
		return answerlist;
	}
}
