package spelling;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
	public DictionaryLL() {
		this.dict=new LinkedList<String>();
	}
	
	
    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	boolean addWordStatus = false;
    	String lowerCaseWord = word.toLowerCase();
    	//System.out.println(" lowercaseWord ="+lowerCaseWord);
    	//System.out.println(" @#$#@ addWord ....dict ="+dict.size());
    	if(!word.trim().isEmpty() && !isWord(lowerCaseWord)){
    		//System.out.println(" Yes i m the word....");
    		if(null!=dict && dict.size()>=0){
    			//System.out.println(" Hey entered dict.size()  ="+dict.size());
    		dict.add(lowerCaseWord);
    		addWordStatus=true;
    		}
    	}
        return addWordStatus;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
       int size = 0;
       if(null!=dict){
    	   size = dict.size();
       }
       return size;
    }

    /** Is this a word according to this dictionary? */
	public boolean isWord(String s) {
		boolean exists = false;
		Iterator< String> iterator = dict.iterator();
		while(iterator.hasNext()){
			String existingWord = iterator.next();
			if(existingWord.equalsIgnoreCase(s)){
				exists=true;
				break;
			}
		}
	return exists;
	}
    
}
