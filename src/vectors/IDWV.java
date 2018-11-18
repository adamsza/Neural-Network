package vectors;

import java.util.regex.Pattern;

public class IDWV extends WordVector{
	
	public IDWV(String s) {
		super(s);
	}
	
	public double containsSpecialWord() {
		return boolToNum(Pattern.compile("személy|Személy|igazolvány|Igazolvány|ig|Ig|szám|Szám|okmány|Okmány|azonositó|Azonosító").matcher(getWord()).find());
	}
}
