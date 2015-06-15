import java.io.*;
import java.net.*;
import java.util.*;

import edu.mit.jwi.*;
import edu.mit.jwi.Dictionary;
import edu.mit.jwi.item.*;

public class BoggleSolver {

	public static void main(String[] args) throws IOException {
		// construct URL to WordNet Dictionary directory on the computer
		String path = "WordNet-3.0/dict";
		URL url = new URL("file", null, path);

		// construct the Dictionary object and open it
		IDictionary dict = new Dictionary(url);
		dict.open();

		Scanner scan = new Scanner(System.in);
		System.out
				.println("Enter the all the letters in one continues segment");
		String input = scan.nextLine();

		ArrayList<String> words = new ArrayList<String>();
		permute(words, input, "");
	
		//removes non words
		for (int i = 0; i < words.size(); i ++){
			if (!WordSearchUtil.wordExists(dict, words.get(i))){
				words.remove(i);
			}
		}
		
		removeDuplicates(words);

		System.out.println("Anagrams: " + words);
		
		for (String s: words){
			IIndexWord idxWord = dict.getIndexWord(s,
					WordSearchUtil.getPOS(s));
			IWordID wordID = idxWord.getWordIDs().get(0);
			IWord word = dict.getWord(wordID);

			

			System.out.println("Id = " + wordID);
			System.out.println("Defintion: " + word.getSynset().getGloss());
		}
				
	}

	public static ArrayList<String> permute(ArrayList<String> words, String s1,
			String s2) {

		if (s1.length() == 0) {
			words.add(s2);
		}

		for (int i = 0; i < s1.length(); i++) {

			permute(words,
					s1.substring(0, i) + s1.substring(i + 1, s1.length()),
					s1.charAt(i) + s2);
		}
		return words;
	}
	public static ArrayList<String> removeDuplicates(ArrayList<String> array){

		Set<String> hs = new HashSet<>();
		hs.addAll(array);
		array.clear();
		array.addAll(hs);
		return array;
	}
}
