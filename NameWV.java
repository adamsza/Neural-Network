package vectors;

import java.util.regex.Pattern;

public class NameWV extends WordVector{
	
	public NameWV(String s) {
		super(s);
	}
	
	public double containsSpecialWord() {
		return boolToNum(Pattern.compile("név|egyrész|másrész|valamint|egyfelől|másfelől").matcher(getWord()).find());
	}
}
