package tools;

public interface Constants {

	//hány szó megy be egyszerre a networkbe
	public static final int WORDS_WINDOW = 5;
	
	//milyen hosszú egy szó vektora
	public static final int WORD_VECTOR = 5;
	
	//kimenet opciók száma
	public static final int NUM_OPTIONS = 2;
	
	public static final int NUM_NETWORK = 2;
		
	//neurális magic
	public static final double ETA = 0.6;
	
	//hosszú input vektor
	public static final int INPUT_VECTOR = WORDS_WINDOW*WORD_VECTOR;
	
	//két belső szint
	public static final int LAYER_1 = 10;
	public static final int LAYER_2 = 5;
	
	//ennel jobb kell hogy legyen a tipp
	public static final double BOUNDARY = 0.3;
}
