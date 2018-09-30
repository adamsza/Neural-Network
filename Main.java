package neural;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	
	public static double[] vectorization(String toProcess)
	{
		String[] splitted = toProcess.split(" ");
		ArrayList<String> input = new ArrayList<String>();
		input.add(splitted[0]); input.add(splitted[1]); input.add(splitted[2]);
		Preprocessor pre = new Preprocessor(input);
		pre.removeWords();
		pre.makeWordVectors();
		
		ArrayList<WordVector> vectors = pre.getVectors();
		double[] in = new double[15];
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				double[] vec = vectors.get(i).getVector();
				in[(i*5)+j] = vec[j];
			}
		}
		return in;
	}

	public static void main(String arg[])
	{
		Network net = new Network();
		
		
		double[] target = new double[] {0,1,0,0,0,0};
		double[] in = vectorization("név: Kovács Péter");
		net.train(in, target, 0.6);
		for(int i = 0; i < 100; i++)
		{
			net.train(in, target, 0.6);
		}
		
		target = new double[] {0,0,1,0,0,0};
		in = vectorization("személyi: 723342LA lakcím:");
		net.train(in, target, 0.6);
		
		
		in = vectorization("Baumann László személyi");
		double[] out = net.calculate(in);
		System.out.println(Arrays.toString(out));
	}
}
