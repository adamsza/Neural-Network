public class Main2 {
	
	public static void main(String[] args) {
		//csak fájt hogy piros volt mindig, de ez kamu string
		String targetfilename = new String();
		String filename = new String();
		
		
		//gondolom valami ilyesmi lenne majd amúgy a dolog:

		Network n = new Network();
		//SZEVASZ HAVER, tanulni akarsz vagy csak darálni?
		
		//tanulni:
		//aggyá nekem file-t amiből kinézzem a válaszokat, köszi
		Valami v1 = new Learner(n, targetfilename);
		v1.execute(filename);
		
		//darálni:
		Valami v2 = new Evaluator(n);
		v2.execute(filename);
    
  	}
	
}