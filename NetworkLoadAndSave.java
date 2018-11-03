package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NetworkLoadAndSave {
	
	private int[] layer_sizes;
	
	public NetworkLoadAndSave(int[] layer_sizes)
	{
		this.layer_sizes = layer_sizes;
	}
	
	public void save(String filename, double[][] bias, double[][][] weights) throws IOException
	{
		FileWriter fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("bias:");
		bw.newLine();
		for(int layer = 0; layer < layer_sizes.length; layer++)
		{
			for(int neuron = 0; neuron < layer_sizes[layer]; neuron++)
			{
				bw.write(Double.toString(bias[layer][neuron]));
				bw.write(' ');
			}
			bw.newLine();;
		}
		
		for(int layer = 1; layer < layer_sizes.length; layer++)
		{
			bw.write("layer" + layer + ":");
			bw.newLine();
			for(int neuron = 0; neuron < layer_sizes[layer]; neuron++)
			{
				for(int prevneuron = 0; prevneuron < layer_sizes[layer-1]; prevneuron++)
				{
					bw.write(Double.toString(weights[layer][neuron][prevneuron]));
					bw.write(' ');
				}
			bw.newLine();
			}
		}
		
		bw.close();
	}
	
	public void load(String filename, Network network) throws IOException
	{
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		boolean q = true;
		String line = br.readLine();
		line = br.readLine();
		double[][] bias = new double[layer_sizes.length][];
		int layer = 0;
		while(q == true)
		{
			String[] sp = line.split(" ");
			bias[layer] = new double[layer_sizes[layer]];
			for(int neuron = 0; neuron < sp.length; neuron++)
			{
				bias[layer][neuron] = Double.parseDouble(sp[neuron]);
			}
			line = br.readLine();
			if(line.equals("layer1:")) q = false;
			layer++;
		}
		network.setbias(bias);
		
		layer = 0;
		q = true;
		double[][][] weights = new double[layer_sizes.length][][];
		int neuron = 0;
		while(q == true)
		{
			if(line.contains("layer"))
			{
				layer++;
				line = br.readLine();
				neuron = 0;
				weights[layer] = new double[layer_sizes[layer]][layer_sizes[layer-1]];
			}
			String[] sp = line.split(" ");
			for(int prevneuron = 0; prevneuron < sp.length; prevneuron++)
			{
				weights[layer][neuron][prevneuron] = Double.parseDouble(sp[prevneuron]);
			}
			line = br.readLine();
			neuron++;
			if(line == null) q = false;
			
		}
		network.setweights(weights);
		
		br.close();
		
	}
}
