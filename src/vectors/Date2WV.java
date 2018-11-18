package vectors;

import java.util.regex.Pattern;

public class Date2WV extends WordVector{
	
	public Date2WV(String s) {
		super(s);
	}
	
	public double containsSpecialWord() {
		return boolToNum(Pattern.compile("dátum|Dátum|év|Év|hó|Hó|nap|Nap|kelt|Kelt").matcher(getWord()).find());
	}
}
