/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
//			System.out.println();
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("got an index out of bound for empty list emptyList.get(0)");
		}
		
		// test short list, first contents, then out of bounds`
		System.out.println( " shortList size ...."+shortList.size + " checking first A "+shortList.toString());
		assertEquals("Check first", "A", shortList.get(0));
		System.out.println("move on A");
		assertEquals("Check second", "B", shortList.get(1));
		System.out.println("move on B");
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		System.out.println( " got an index out of bound for shortList.get(-1).." );
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		assertEquals("Remove: check if head is correct",list1.head,list1.head.next.prev);
		System.out.println(" : testRemove list1 : "+list1.toString());
		int a2 = list1.remove(1);
		
		assertEquals("Remove: check a is correct ", 42, a2);
		try{
			list1.get(1);
			fail("check out of bound");
		} catch(Exception e){
			
		}
		
		assertEquals("Remove: check size is correct ", 1, list1.size());
		assertEquals("Remove: check if prev is correct",(Integer)21,list1.get(0));
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		try {
			emptyList.add(null);
			fail("Null Pointer Exception");
		}
		catch (NullPointerException e) {
		System.out.println("got a null pointer");
		}
		assertEquals("Add to an empty list:", true, emptyList.add(1));//adding to an empty list
		
		assertEquals("Add to an empty list:size:", 1, emptyList.size());
		
		assertEquals("Add to short list:", true, shortList.add("C"));
		assertEquals("Add to short list:size:", 3, shortList.size());
		assertEquals("Add to an short list:size:", "B", shortList.get(1));
		
		
        // TODO: implement this test
		
	}

	
	/** Test the size of the list 
	 * @throws Exception */
	@Test
	public void testSize() throws Exception
	{
		this.setUp();

		// try getting size of an empty list: returns 0
		assertEquals("get size of an empty list", 0,emptyList.size);
		
		// try getting size of a list after add at end : size should be +1
		assertEquals("get size of a list before add ",2,shortList.size);
		assertEquals("get size of a list after add ",true,shortList.add("C"));
		assertEquals("get size of a list before add ",3,shortList.size);
		assertEquals("get size of a list after add ","A",shortList.remove(0));
		assertEquals("get size of a list before add ",2,shortList.size);
		assertEquals("get size of a list after add ",true,shortList.add("D"));
		assertEquals("get size of a list after add ","C",shortList.remove(1));
		assertEquals("get size of a list before add ",2,shortList.size);
		assertEquals("get size of a list after add ","B",shortList.remove(0));
		assertEquals("get size of a list before add ",1,shortList.size);
		assertEquals("get size of a list before add ","D",shortList.remove(0));
		assertEquals("get size of a list before add ",0,shortList.size);
		
	
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		// test for the index if valid: negative
		try {
			emptyList.add(-1, 1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		System.out.println("Index out of bounds....");
		}
		
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // check if valid index: index<0 ; index>=size
		
		try {
			emptyList.set(1, 3);
		} catch(IndexOutOfBoundsException e){
			System.out.println("gotcha..");
		}
		
		// check if element is not null
		try {
			emptyList.set(0, 1);
		} catch(IndexOutOfBoundsException e){
			System.out.println("gotcha..");
		}	
	    
	}
		
}
