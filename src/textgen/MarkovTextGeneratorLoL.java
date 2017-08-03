package textgen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		if(!sourceText.trim().isEmpty()){
		String prevWord=null;
//		tokensList=getTokens("[!?.]+|[a-zA-Z]+",sourceText);
		String [] wordArray = sourceText.split("[\\s]+");
		List <String> wordTokens = new ArrayList<String>();
		wordTokens=Arrays.asList(wordArray);
		/*
		for (String string : tokensList) {
			if(isWord(string)){
				wordTokens.add(string);
			}
		}*/
		starter=wordTokens.get(0);
		prevWord=starter;
		ListNode prevWordNode = null;
		String w=null;
		for(int i=1;i<wordTokens.size();i++){
			w=wordTokens.get(i);
			prevWordNode=new ListNode(prevWord);
			ListNode existingPrevListNode = getExistingNode(prevWord);
			if (null!= existingPrevListNode){			//check to see if prevWord is already a node in the list
				existingPrevListNode.addNextWord(w);		//add w as a nextWord to the prevWord node
			} else {
				wordList.add(prevWordNode);			//add a node to the List with "prevWord" as the node's word 
				prevWordNode.addNextWord(w);		//add w as a nextWord to the prevWord node
			}
			prevWord=w;
		}
		prevWordNode=new ListNode(prevWord);
		wordList.add(prevWordNode);
		prevWordNode.addNextWord(starter);
		}
	}
	
	private ListNode getExistingNode(String prevWord){
		ListNode existingNode = null;
		for(int i=0;i<wordList.size();i++){// iterate through the wordList and get the node  containing word==prevWord
			if(wordList.get(i).getWord().equalsIgnoreCase(prevWord)){
				existingNode=wordList.get(i);
				break;
			}
		}		
		return existingNode;
	}
	private List<String> getTokens(String pattern,String sourceText)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(sourceText);
		while (m.find()) {
			tokens.add(m.group());
		}
		return tokens;
	}
	
	private boolean isWord(String tok)
	{
	    // Note: This is a fast way of checking whether a string is a word
	    // You probably don't want to change it.
		return !(tok.indexOf("!") >=0 || tok.indexOf(".") >=0 || tok.indexOf("?")>=0);
	}
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		String output="";
		System.out.println(""+wordList.size());
		if(null!=wordList && wordList.size()!=0 && numWords>0){
			//System.out.println(""+wordList.size());
			System.out.println(" hey did i enter here...");
	    String currWord = starter;
	    System.out.println("starter ="+starter);
		output=output.concat(currWord);
		ListNode node =null;
		
		for(int i=0;i<numWords;i++){
			node = getExistingNode(currWord);
			String w=node.getRandomNextWord(rnGenerator);
			output=output.concat(" " + w.trim());
			currWord=w;
		}
		}
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		this.train(sourceText);
		
	}
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString2 = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		//String textString2 = "hi there hi Leo";
		System.out.println(textString2);
		gen.train(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(10));
		String textString3 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString3);
		gen.retrain(textString3);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		int randomIndex = generator.nextInt(this.nextWords.size());
		String randomWord = this.nextWords.get(randomIndex);
	    return randomWord;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListNode other = (ListNode) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
		
}