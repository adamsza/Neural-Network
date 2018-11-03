package network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tools.Constants;

public class NetworkHolder {

	private List<Network> list = new ArrayList<Network>();
	
	public NetworkHolder()
	{
		for(int i = 0; i < Constants.NUM_NETWORK; i++)
		{
			list.add(new Network());
		}
	}
	
	public void save() throws IOException
	{
		String name = "save";
		for(int i = 0; i < list.size(); i++)
		{
			String filename = name + i + ".txt";
			list.get(i).saveState(filename);
		}
	}
	
	public void load() throws IOException
	{
		String name = "save";
		for(int i = 0; i < list.size(); i++)
		{
			String filename = name + i + ".txt";
			list.get(i).loadState(filename);
		}
	}
}
