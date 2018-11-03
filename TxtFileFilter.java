package user_interface;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class TxtFileFilter extends FileFilter{

	@Override
	public boolean accept(File f) {
		String extension = f.getPath();
		if(extension.endsWith("txt") || f.isDirectory()) return true;
		else return false;
	}

	@Override
	public String getDescription() {
		
		return ".txt";
	}

}
