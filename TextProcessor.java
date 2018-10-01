package neural;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextProcessor {
	
	
	public ArrayList<String> separatewords(String filename) throws IOException
	{
		
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		boolean q = true;
		String line = br.readLine();
		ArrayList<String> wordlist = new ArrayList<String>();
		while(q == true)
		{
			line = line.replaceAll("\\s+", " ");
			line = line.replaceAll(",", " ");
			String[] sp = line.split(" ");
			for(int i = 0; i < sp.length; i++)
			{
				if(!sp[i].equals("")) wordlist.add(sp[i]);
			}
			line = br.readLine();
			if(line == null) q = false;
		}
		br.close();
		return wordlist;
	}
	
	public ArrayList<Double> separatetargets(String filename) throws IOException
	{
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		boolean q = true;
		String line = br.readLine();
		ArrayList<Double> targetlist = new ArrayList<Double>();
		while(q == true)
		{
			String[] sp = line.split(" ");
			for(int i = 0; i < sp.length; i++)
			{
				targetlist.add(Double.parseDouble(sp[i]));
			}
			line = br.readLine();
			if(line == null) q = false;
		}
		br.close();
		return targetlist;
	}
}
