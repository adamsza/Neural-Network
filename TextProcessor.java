package neural;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextProcessor {
	
	//beolvassa a szöveget és szétszedi String listára
	public ArrayList<String> readWords(String filename) throws IOException
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
	
	//beolvassa a target számokat és Integer listát csinál belőle
	public ArrayList<Integer> readTargets(String filename) throws IOException
	{
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		boolean q = true;
		String line = br.readLine();
		ArrayList<Integer> targetlist = new ArrayList<Integer>();
		while(q == true)
		{
			String[] sp = line.split(" ");
			for(int i = 0; i < sp.length; i++)
			{
				targetlist.add(Integer.parseInt(sp[i]));
			}
			line = br.readLine();
			if(line == null) q = false;
		}
		br.close();
		return targetlist;
	}

}
