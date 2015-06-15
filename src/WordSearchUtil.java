import edu.mit.jwi.*;
import edu.mit.jwi.item.*;

public class WordSearchUtil {

	private static IDictionary dict;
	public static boolean wordExists(IDictionary dictionary, String input) {
		
		dict = dictionary;
		boolean isWord = false;
		POS pos = null;
			
		for(POS c: POS.values()){
			if (isWordType(c, input) && !isWord){
				pos = c;
				isWord = true;
			}
		}
		return isWord;
	}
	
	private static boolean isWordType(POS pos, String input){
		if (dict.getIndexWord(input, pos) != null){
			return true;
		}
		else {return false;}
	}
	public static POS getPOS(String input){ // only cal for words that are words 
		
		POS pos = null;
		
		for(POS c: POS.values()){
			if (isWordType(c, input)){
				pos = c;
			}
		}
		return pos;
	}
}
