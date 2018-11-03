package vectors;

import java.util.regex.Pattern;

public class ContractTypeWV extends WordVector{
	
	public ContractTypeWV(String s) {
		super(s);
	}
	
	public double containsSpecialWord() {
		return boolToNum(Pattern.compile("szerződés").matcher(getWord()).find());
	}
}
