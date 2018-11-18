package neural;

import java.io.IOException;

import java.util.ArrayList;

import network.Network;
import network.NetworkHolder;
import processor.TextProcessor;
import tree.BST;
import tree.TreeBuilder;
import user_interface.GUI;

public class Main {
	
	public static void main(String[] args) throws IOException {

   
		NetworkHolder nh = new NetworkHolder();
		//GUI gui = new GUI();
		nh.load();
		
		String num = "7";
		
		//Valami v3 = new Learner(nh.getNetworks(), "test/target" + num + ".txt");
		//v3.execute("test/test" + num + ".txt");
		
		//Evaluator v1 = new Evaluator(nh.getNetworks());
		
		GUI gui = new GUI(nh.getNetworks());
		
		//v1.execute("test/test" + num + ".txt");
		
		nh.save();
		

		
	}
	
}



