package neural;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) throws IOException {
		//csak fájt hogy piros volt mindig, de ez kamu string
		//String targetfilename = new String();
		//String filename = new String();
		
		
		//gondolom valami ilyesmi lenne majd amúgy a dolog:

		Network n = new Network();
		
		BST bst = new BST();
		TextProcessor tp = new TextProcessor();
		ArrayList<String> names = tp.readWords("names.txt");
		for(int i = 0; i < names.size(); i++) {
			bst.put(names.get(i), i);
		}
		
		/*
		try {
			n.loadState("saved.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//SZEVASZ HAVER, tanulni akarsz vagy csak darálni?
		
		//tanulni:
		//aggyá nekem file-t amiből kinézzem a válaszokat, köszi
		
		/*
		Valami v1 = new Learner(n, "target1.txt");
		for (int i=0; i<10000; i++) {
			v1.execute("test1.txt");
		}
		
		
		//darálni:
		Valami v2 = new Evaluator(n);
		v2.execute("test1.txt");
		
		
		Valami v3 = new Learner(n, "target2.txt");
		for (int i=0; i<10000; i++) {
			v3.execute("test2.txt");
		}
		
		
		//darálni:
		Valami v4 = new Evaluator(n);
		v4.execute("test2.txt");
		
		Valami v5 = new Learner(n, "target3.txt");
		for (int i=0; i<10000; i++) {
			v5.execute("test3.txt");
		}
		
		
		//darálni:
		Valami v6 = new Evaluator(n);
		v6.execute("test3.txt");
		*/
		
		
		Valami v7 = new Learner(n, "target.txt");
		for (int i=0; i<10000; i++) {
			v7.execute("text.txt");
		}
		
		
		//darálni:
		Valami v8 = new Evaluator(n);
		v8.execute("text.txt");
		
		
		
		try {
			n.saveState("saved.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}
	
}



