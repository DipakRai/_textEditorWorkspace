package document;

import java.util.ArrayList;
import java.util.List;

/** 
 * A naive implementation of the Document abstract class. 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class BasicDocument_Copy extends Document 
{
	/** Create a new BasicDocument object
	 * 
	 * @param text The full text of the Document.
	 */
	public BasicDocument_Copy(String text)
	{
		super(text);
	}
	
	
	/**
	 * Get the number of words in the document.
	 * "Words" are defined as contiguous strings of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z
	 * 
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords()
	{
		//a-zA-Z
		//a-zA-Z_0-9
		List <String> tokens = getTokens("[a-zA-z]+");
		
	    return tokens.size();
	}
	
	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 * 
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences()
	{
		List <String> tokens = getTokens("[^.!?]+");
	    return tokens.size();        
	}
	
	/**
	 * Get the number of syllables in the document.
	 * Words are defined as above.  Syllables are defined as:
	 * a contiguous sequence of vowels, except for a lone "e" at the 
	 * end of a word if the word has another set of contiguous vowels, 
	 * makes up one syllable.   y is considered a vowel.
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables()
	{
		int numSyllables =0;
		List <String> wordTokens = getTokens("[a-zA-z]+");
		System.out.println("@$@#$ tokens size ="+wordTokens.size());
		for (String token : wordTokens) {
			System.out.println("@$@#$  token = "+token);
			numSyllables += countSyllables(token);
		}		
		//List <String> tokens = getTokens("[aeiouy]+[^[b-df-hj-np-tv-ze[ ,.;:!?]]]");// option 1:[aeiouy]+[^e[ ,.:;!?]]
		//List <String> words = getTokens("[a-zA-Z]+");// option 1:[aeiouy]+[^e[ ,.:;!?]];Option 2: [a-zA-z]+([^ ,.:;!?]
		//List<String> syllables = new ArrayList<String>();
		//System.out.println("string syllables ="+tokens);
		return numSyllables ; //countSyllables(getTokens("[a-zA-Z]+"));
	}
	
	
	/* The main method for testing this class. 
	 * You are encouraged to add your own tests.  */
	public static void main(String[] args)
	{
		testCase(new BasicDocument_Copy("This is a test.  How many???  "    + "Senteeeeeeeeeences are here... there should be 5!  Right?"),	16, 13, 5);
		testCase(new BasicDocument_Copy(""), 0, 0, 0);
		testCase(new BasicDocument_Copy("sentence, with, lots, of, commas.!  " + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
		testCase(new BasicDocument_Copy("many???  Senteeeeeeeeeences are"), 6, 3, 2);
		testCase(new BasicDocument_Copy("Here is a series of test sentences. Your program should " + "find 3 sentences, 33 words, and 49 syllables. Not every word will have "	+ "the correct amount of syllables (example, for example), "+ "but most of them will."), 49, 33, 3);
		testCase(new BasicDocument_Copy("Segue"), 2, 1, 1);
		testCase(new BasicDocument_Copy("Sentence"), 2, 1, 1);
		testCase(new BasicDocument_Copy("Sentences?!"), 3, 1, 1);
		testCase(new BasicDocument_Copy("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."), 32, 15, 1);
		
		
		
		
	}
	
}
