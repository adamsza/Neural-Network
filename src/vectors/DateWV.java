package vectors;

import java.util.regex.Pattern;

public class DateWV extends WordVector{
	
	public DateWV(String s) {
		super(s);
	}
	
	public double containsSpecialWord() {
		return boolToNum(Pattern.compile("dátum|Dátum|év|Év|hó|Hó|nap|Nap|kelt|Kelt").matcher(getWord()).find());
	}
}
