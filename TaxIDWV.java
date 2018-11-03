package vectors;

import java.util.regex.Pattern;

public class TaxIDWV extends WordVector{

	public TaxIDWV(String s) {
		super(s);
	}
	
	public double containsSpecialWord() {
		return boolToNum(Pattern.compile("adószám|azonositó|jel").matcher(getWord()).find());
	}
}
