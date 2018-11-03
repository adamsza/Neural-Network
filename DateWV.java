package vectors;

import java.util.regex.Pattern;

public class DateWV extends WordVector{
	
	public DateWV(String s) {
		super(s);
	}
	
	public double containsSpecialWord() {
		return boolToNum(Pattern.compile("dátum|év|hó|nap|kelt").matcher(getWord()).find());
	}
}
