package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = new LLNode<E>(null);
		tail=new LLNode<E>(null);
		size=0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) {

		if(null==element){
			throw new NullPointerException();
		} else {
		LLNode <E> currentNode = new LLNode<E>(element);
		if(size()==0){ // linked list is empty and curentNode is the first node
			currentNode.prev=head;
			currentNode.next=tail;
			//System.out.println(" hey my tail is "+ tail + "  hey my tail prev is :"+tail.prev);
			//tail.prev=new LLNode <E>();
			tail.prev=currentNode;
			head.next=currentNode;
		} else { //linked list is not empty and currentNode is not the first node
			currentNode.prev=tail.prev;
			currentNode.next=tail;
			tail.prev.next=currentNode;
			tail.prev=currentNode;
		}
		size++;
		//System.out.println( "added node "+currentNode);
		return true;
		}
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) {
		
		/*
		 * Check if index is valid: be mindful of the size value, i.e.
		 * 
		 * if linked list is empty then size =0 or size =-1 , in the former case the
		 * element at index 1 would be found at first iteration and the loop condition would be
		 * index > size 
		 * case 1: linked list empty:size=0 : throw IndexOutOfBoundException
		 * case 2: index > size || index < size of linked list throw IndexOutOfBoundException
		 * Initialize a node as currentNode Initialize a loop to iterate the
		 * list starting from the first element to the "index" of the list Start
		 * iterating the list with the currentNode=head.next
		 * 
		 */
		System.out.println("size of the list ="+size());
		LLNode<E> currentNode = null;
		if(index<0 || (index>=0 && index>=size)){
			//System.out.println(" negative index ="+index);
			throw new IndexOutOfBoundsException();
		}else {
			//System.out.println("index is a proper value = "+index);
			System.out.println("head ="+head);
			currentNode=head;
			for(int i=0;i<=index;i++){
				currentNode=currentNode.next;
			}
		}
		System.out.println("currentNode to be returned= "+currentNode.data + " Prev to currentNode = "+ currentNode.prev +  " next to currentNode ="+ currentNode.next);
		return currentNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if(null==element){
			throw new NullPointerException();
		} else {
			if(index==0 && size()==0){			// empty list
				add(element);
			} else {			// non empty list
				LLNode <E> newNode = new LLNode <E>(element);
				LLNode<E> currentNode=null;
				if(index<0 || (index>0 && index>=size)){
					//System.out.println(" negative index ="+index);
					throw new IndexOutOfBoundsException();
				} else {
					System.out.println("index is a proper value = "+index);
					System.out.println("head ="+head);
					currentNode=head;
					for(int i=0;i<=index;i++){
						currentNode=currentNode.next;
					}
					System.out.println(currentNode + " #$# "+ currentNode.prev);
					newNode.next=currentNode;
					newNode.prev=currentNode.prev;
					currentNode.prev.next=newNode;
					currentNode.prev=newNode;				
					size++;
				}

			}
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
		/*
		 * Algorithm:
		 * Check if the index is not out of bound: (for an empty list) : throw IndexOutOfBoundException
		 * index<0 || index>=size throw IndexOutOfBoundException
		 * initialize currentNode:head
		 * Initialize counter i to iterate the list from the start to index : i=0
		 * for each iteration check:
		 * currentNode=currentNode.next;
		 * 
		 */
		LLNode<E> currentNode=null;
		if(index<0 || (index>=0 && index>=size)){
			//System.out.println(" negative index ="+index);
			throw new IndexOutOfBoundsException();
		}else {
			currentNode=head;
			for(int i=0;i<=index;i++){
				currentNode=currentNode.next;
			}
			if (size()==1){// list has only 1 element
				head.next=currentNode.next;
				tail.prev=currentNode.prev;				
			} else {// list has greater than 1 element
				System.out.println(" #$%$ size="+size());
				System.out.println(" currentNode ="+currentNode +  " @$#@#$@ "+ currentNode.prev + " #$#@ "+currentNode.next);
				
				if(null!=currentNode.prev){
					// check if the element being removed is the first element in the list then prev is null
					currentNode.prev.next=currentNode.next; // setting the currentNode's prev node point to the next node
				} else {// and you can't access the prev through currentNode but head
					head.next=currentNode.next;
				}
				if(null!=currentNode.next){
					// check if the element being removed is the last element in the list then next is null
					currentNode.next.prev=currentNode.prev; //setting the next node to point to currentNode's prev
				} else {// and you can't access the next through currentNode but tail
					tail.prev=currentNode.prev;
				}
				System.out.println("currentNode to be returned= " + currentNode.data + " Prev to currentNode = "
						+ currentNode.prev + " next to currentNode =" + currentNode.next);
			}

			size--; // decreasing the size by 1
		}		
		return currentNode.data;		
	}

	
	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) {
		
		/*
		 * Algorithm: 
		 * check if the index is valid
		 * if valid: get the element : get(index)
		 * set the element value 
		 * 
		 */
		E oldElement=null;
		LLNode<E> currentNode = null;
		if(index<0 || (index>=0 && index>=size)){
			//System.out.println(" negative index ="+index);
			throw new IndexOutOfBoundsException();
		}else {
			if (null==element){
				throw new NullPointerException();
			}
			System.out.println("index is a proper value = "+index);
			System.out.println("head ="+head);
			currentNode=head;			
			for(int i=0;i<=index;i++){
				currentNode=currentNode.next;				
			}
			oldElement = currentNode.getData();
			currentNode.setData(element);
		}

		return oldElement;
	}   
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode() {// added default constructor
		this.data = null;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode(LLNode<E>prev,LLNode<E>next, E element){ //added another constructor to add nodes with prev and next node
		this.data=element;
		this.next=next;
		this.prev=prev;
	}

	public LLNode<E> getPrev() {
		return prev;
	}

	public void setPrev(LLNode<E> prev) {
		this.prev = prev;
	}

	public LLNode<E> getNext() {
		return next;
	}

	public void setNext(LLNode<E> next) {
		this.next = next;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "LLNode [data=" + data + "]";
	}
	
	
}
