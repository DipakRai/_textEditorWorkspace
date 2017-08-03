package spelling;

import java.util.ArrayList;
import java.util.List;

public class DiraiTester {

	public static void main(String[] args) {
	boolean wordsOnly=true;
	List<String>currentList = new ArrayList<String>();
		String s="i";
		for(int index = 0; index <= s.length(); index++){
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				// use StringBuffer for an easy interface to permuting the 
				// letters in the String
				StringBuffer sb = new StringBuffer(s);
				sb.insert(index, (char)charCode);
				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if(!currentList.contains(sb.toString()) && 
						(wordsOnly && !s.equals(sb.toString()))) {
					currentList.add(sb.toString());
				}
				
			}
		}
		System.out.println(currentList.size());
		for (String string : currentList) {
			System.out.println(" string = "+string);
		}
	}
}
