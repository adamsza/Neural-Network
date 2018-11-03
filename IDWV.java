package vectors;

import java.util.regex.Pattern;

public class IDWV extends WordVector{
	
	public IDWV(String s) {
		super(s);
	}
	
	public double containsSpecialWord() {
		return boolToNum(Pattern.compile("személy|igazolvány|ig|szám|okmany|azonositó").matcher(getWord()).find());
	}
}
