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

		// look up first sense of the word "dog"

		WordSearchUtil.wordExists(dict, input);

		IIndexWord idxWord = dict.getIndexWord(input,
				WordSearchUtil.getPOS(input));
		IWordID wordID = idxWord.getWordIDs().get(0);

		IWord word = dict.getWord(wordID);

		System.out.println("Id = " + wordID);
		System.out.println("Defintion: " + word.getSynset().getGloss());
	}

	public void permute(String input) {
		int inputLength = input.length();
		boolean[] used = new boolean[inputLength];
		StringBuffer outputString = new StringBuffer();
		char[] in = input.toCharArray();

		doPermute(in, outputString, used, inputLength, 0);

	}

	public void doPermute(char[] in, StringBuffer outputString, boolean[] used,
			int inputlength, int level) {
		if (level == inputLength) {
			System.out.println(outputString.toString());
			return;
		}

		for (int i = 0; i < inputLength; ++i) {

			if (used[i])
				continue;

			outputString.append(in[i]);
			used[i] = true;
			doPermute(in, outputString, used, length, level + 1);
			used[i] = false;
			outputString.setLength(outputString.length() - 1);
		}
	}
}
