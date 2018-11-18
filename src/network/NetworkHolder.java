package network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tools.Constants;

public class NetworkHolder implements Constants{

	private ArrayList<Network> list = new ArrayList<Network>();
	
	public NetworkHolder()
	{
		for(int i = 0; i < Constants.NUM_NETWORK; i++)
		{
			list.add(new Network());
		}
	}
	
	public void save() throws IOException
	{
		String folder = "save";
		String name = "/save_2add_";
		for(int i = 0; i < list.size(); i++)
		{
			String filename = folder + WORDS_WINDOW + name + i + ".txt";
			list.get(i).saveState(filename);
		}
	}
	
	public void load() throws IOException
	{
		String folder = "save";
		String name = "/save_2add_";
		for(int i = 0; i < list.size(); i++)
		{
			String filename = folder + WORDS_WINDOW + name + i + ".txt";
			list.get(i).loadState(filename);
		}
	}
	
	public ArrayList<Network> getNetworks() {
		return list;
	}
}
