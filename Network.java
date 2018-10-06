package neural;

import java.io.IOException;
import java.util.Arrays;

public class Network implements Constants{
	private NetworkLoadAndSave loadsave;

	public int[] layer_sizes;
	public int output_size;
	public int input_size;
	public int network_size;
	
	private double[][] output;
	private double[][] bias;
	private double[][][] weights;
	
	private double[][] error_signal;
	private double[][] output_derivative;
	
	public Network()
	{
		layer_sizes = new int[]{INPUT_VECTOR,LAYER_1,LAYER_2,NUM_OPTIONS};
		network_size = layer_sizes.length;
		input_size = layer_sizes[0];
		output_size = layer_sizes[network_size-1];
		
		output = new double[network_size][];
		bias = new double[network_size][];
		weights = new double[network_size][][];
		
		error_signal = new double[network_size][];
		output_derivative = new double[network_size][];
		
		for(int i = 0; i < network_size; i++)
		{
			output[i] = new double[layer_sizes[i]];
			error_signal[i] = new double[layer_sizes[i]];
			output_derivative[i] = new double[layer_sizes[i]];
			bias[i] = NetworkTools.createRandomArray(layer_sizes[i], 0, 1);
			if(i > 0)
			{
				weights[i] = NetworkTools.createRandomArray(layer_sizes[i],layer_sizes[i-1], 0, 1);
			}
		}
		
		loadsave = new NetworkLoadAndSave(layer_sizes);
	}
	
	public double[] calculate(double[] inp)
	{
		if(inp.length != input_size) return null;
		output[0] = inp;
		for(int layer = 1; layer < network_size; layer++)
		{
			for(int neuron = 0; neuron < layer_sizes[layer]; neuron++)
			{
				double sum = 0;
				for(int prevneuron = 0; prevneuron < layer_sizes[layer-1]; prevneuron ++)
				{
					sum += output[layer-1][prevneuron] * weights[layer][neuron][prevneuron];
				}
				sum += bias[layer][neuron];
				output[layer][neuron] = sigmoid(sum);
				output_derivative[layer][neuron] = output[layer][neuron] * (1-output[layer][neuron]);
			}
		}
		return output[network_size-1];
	}
	
	private double sigmoid(double x)
	{
		return 1d/(1 + Math.exp(-x));
	}
	
	public void train(double[] inp, double[] target)
	{
		if(inp.length != input_size || target.length != output_size) return;
		calculate(inp);
		backpropError(target);
		updateWeights();
	}
	
	public void backpropError(double[] target)
	{
		for(int neuron = 0; neuron < output_size; neuron++)
		{
			error_signal[network_size-1][neuron] = (output[network_size-1][neuron] - target[neuron]) * output_derivative[network_size-1][neuron];
		}
		
		for(int layer = network_size-2; layer > 0; layer--)
		{
			for(int neuron = 0; neuron < output_size; neuron++)
			{
				double sum = 0;
				for(int nextneuron = 0; nextneuron < layer_sizes[layer+1]; nextneuron++)
				{
					sum += weights[layer+1][nextneuron][neuron]* error_signal[layer+1][nextneuron];
				}
				error_signal[layer][neuron] = sum * output_derivative[layer][neuron];
			}
			
		}
	}
	
	public void updateWeights()
	{
		for(int layer = 1; layer < network_size; layer++)
		{
			for(int neuron = 0; neuron < layer_sizes[layer]; neuron++)
			{
				double delta = -ETA * error_signal[layer][neuron];
				bias[layer][neuron] += delta;
				for(int prevneuron = 0; prevneuron < layer_sizes[layer-1]; prevneuron++)
				{
					weights[layer][neuron][prevneuron] += delta * output[layer-1][prevneuron];
				}
				
			}
		}
	}
	
	public void saveState(String filename) throws IOException
	{
		loadsave.save(filename, bias, weights);
	}
	
	public void loadState(String filename) throws IOException
	{
		loadsave.load(filename, this);
	}
	
	public void setbias(double[][] new_bias)
	{
		bias = new_bias;
	}
	
	public void setweights(double[][][] new_weights)
	{
		weights = new_weights;
	}
}
