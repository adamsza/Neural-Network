package neural;

public interface Constants {

	//hány szó megy be egyszerre a networkbe
	public static final int WORDS_WINDOW = 3;
	
	//milyen hosszú egy szó vektora
	public static final int WORD_VECTOR = 6;
	
	//kimenet opciók száma
	public static final int NUM_OPTIONS = 7;
	
	//neurális magic
	public static final double ETA = 0.6;
	
	//hosszú input vektor
	public static final int INPUT_VECTOR = WORDS_WINDOW*WORD_VECTOR;
	
	//két belső szint
	public static final int LAYER_1 = 6;
	public static final int LAYER_2 = 6;
}
