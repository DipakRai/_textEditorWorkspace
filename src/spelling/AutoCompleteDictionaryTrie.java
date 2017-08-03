package spelling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
		boolean addWordStatus=false;
		word = word.toLowerCase();
		if (!word.isEmpty()) {
			TrieNode next = null;
			TrieNode currentNode = null;
			currentNode = root;
			char [] wordChar =word.toCharArray();
			char ch;
			for (int i=0; i< word.length(); i++) {
				ch=wordChar[i];
				next = currentNode.insert(ch);
				if (null != next) {// ch node does not exist: a new node with ch has been created and returned:move currNode to this and check for next char
					currentNode = next;
					if(i==wordChar.length-1){
						currentNode.setEndsWord(true);
						size++;
						addWordStatus=true;
					}
					//counter++;
				} else { // node ch exists as a child of the currentNode: move currentNode to the child of currentNode
					if(i==wordChar.length-1 && !currentNode.getChild(ch).endsWord()){
						currentNode.getChild(ch).setEndsWord(true);
						size++;
						addWordStatus=true;
					}
					currentNode = currentNode.getChild(ch);
				}
				//System.out.println( " currentNode ="+currentNode.getText() + " #$#$ " +currentNode.getValidNextCharacters().toString());
			}
			return addWordStatus;
		} else {
			return addWordStatus;
		}
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
		
		TrieNode currentNode=null;
		TrieNode childNode=null;
		currentNode=root;
		s=s.toLowerCase();
		//System.out.println(" @#@# s="+s);
		if(!s.trim().isEmpty()){
			for(char ch:s.toCharArray()){
				//System.out.println("ch="+ch);
				if(currentNode.getValidNextCharacters().contains(ch)){
					childNode=currentNode.getChild(ch);
					currentNode=childNode;
					//System.out.println("@#@ currentNode ="+currentNode.toString());
				} else {
					return false;
				}
			}
			if(currentNode.endsWord()){
				return true;
			} else {
				return false;
			}				
		} else {
			return false;
		}
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 List<String> completionList = new ArrayList<String>();	    	 //    Create a list of completions to return (initially empty)
    	 LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
    	 TrieNode currentNode=null;
    	 TrieNode next=null;
    	 currentNode=root;
    	 char [] prefixChar=null;
    	 prefix=prefix.toLowerCase();
    	 prefixChar=prefix.toCharArray();
    	 char ch='\0';
    	 for(int i=0;i<prefix.length();i++){
					ch=prefixChar[i];
					next = currentNode.getChild(ch);
					if (null != next) {
						currentNode = next;						
						if(i==prefix.length()-1){//Once the stem is found, perform a breadth first search to generate completions  using the following algorithm:
							System.out.println(" @#@ prefix.equalsIgnoreCase(currentNode.getText()) ="+prefix.equalsIgnoreCase(currentNode.getText()));
							System.out.println(" !@#@@ stem currentNode="+currentNode.toString());
							queue.add(currentNode);//Create a queue (LinkedList) and add the node that completes the stem to the back of the queue
						}				
					} else { // Find the stem in the trie.  If the stem does not appear in the trie, return an empty list						
						return completionList;
					}
			}
	    	 int count=0;
	    	 TrieNode wordNode=null;
	    	 System.out.println(" @#$#@ First part of algo is over ....");
	    	 System.out.println(" @#$@#$#@ queue size ="+queue.size());
	    	 while(!queue.isEmpty()&&count<numCompletions){	    	 //    While the queue is not empty and you don't have enough completions:
	    		 System.out.println(" count ="+count);
	    		 wordNode=queue.remove();
	    		 for (Character c : wordNode.getValidNextCharacters()) {	  //  Add all of its child nodes to the back of the queue
						System.out.println(" c ="+c + "  @#$ wordNode.getChild(c) ="+wordNode.getChild(c));	
						queue.add(wordNode.getChild(c));
					}
	    		 System.out.println(" wordNode removed from the queue ="+wordNode.getText() );
				if (wordNode.endsWord()) {// If it is a word, add it to the completions list
					completionList.add(wordNode.getText());
					count++;
					System.out.println(" @#$#@ wordNode.getValidNextCharacters() ="+wordNode.getValidNextCharacters());
					System.out.println(" added words to queue and queue size ="+queue.size());
				}
	    	 }
	    	 System.out.println(" Final completion list ="+completionList.toString());
         return completionList;
     }
     
    // For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		//System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}