package vectors;

import java.util.regex.Pattern;

public class NameWV extends WordVector{
	
	public NameWV(String s) {
		super(s);
	}
	
	public double containsSpecialWord() {
		return boolToNum(Pattern.compile("név|Név|egyrész|Egyrész|másrész|Másrész|valamint|Valamint|egyfelől|Egyfelől|másfelől|Másfelől|nev|Nev|vállalkozó|Vállalkozó").matcher(getWord()).find());
	}
}
