package vectors;

import java.util.regex.Pattern;

public class AddressWV extends WordVector{
	
	public AddressWV(String s) {
		super(s);
	}
	
	public double containsSpecialWord() {
		return boolToNum(Pattern.compile("lak|cím|lc|szék|hely").matcher(getWord()).find());
	}
}
