//SkipList by Julianna Ngo
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class SkipList<E> implements List<E>{

	/**
	 * The number of elements stored in this SkipList
	 */
	private int size;
	
	/**
	 * The Random object used to determine value of tower heights for each node
	 */
	private Random r;
	
	/**
	 * The height of the overall list (tallest node tower)
	 */
	public int height;
	
	/**
	 * The head pointer
	 */
	private Node head;
	
	/**
	 * The tail pointer
	 */
	private Node tail;
	
	/**
	 * The first element in the list
	 */
	private Node first;
	
	/**
	 * The last element in the list
	 */
	private Node last;
	
	/**
	 * The maxiumum level for a node tower
	 */
	private final static int MAXLEVEL = 20;
	
	/**
	 * Constructs an empty SkipList
	 */
	public SkipList(){
		head = new Node("-oo");
		tail = new Node("+oo");
		size = 0;
		height = 1;
		head.right = tail;
		tail.left = head;
		first = null;
		last = null;
		head.distance = 1;
		tail.distance = 0;
		r = new Random();
	}
	
	/**
	 * A Helper Method
	 * Adds upper layers to the SkipList and assigns head and tail to tallest left and right nodes
	 */
	private void addTopLayer() {
		Node newHead = new Node("-oo");
		Node newTail = new Node("+oo");
		
		newHead.down = head;
		newHead.right = newTail;
		newTail.left = newHead;
		newTail.down = tail;
		tail.up = newTail;
		head.up = newHead;
		head = newHead;
		tail = newTail;
		
		head.distance = size+1;
		tail.distance = 0;
		height++;
	}
	
	/**
	 * Appends the specified element to the end of this list
	 * 
	 * @param e element to be appended to this list
	 * @throws NullPointerException if e is null
	 * @returns true 
	 */
	public boolean add(E e) {
		if (e == null) {
			throw new NullPointerException();
		}

		//setting new node at bottom layer
		Node newNode = new Node(e);
		if (size == 0) {
			first = newNode;
			last = newNode;
			first.right = tail;
			first.left = head;
			tail.left = newNode;
			head.right = newNode;
			newNode.distance = 1;
		}
		else {
			newNode.right = last.right;
			last.right.left = newNode;
			newNode.left = last;
			last.right = newNode;
			last = newNode;
			newNode.distance = 1;
		}
		
		final int nodeHeight = this.coinFlipper();
		
		//add layers to top of head/tail towers if we need to make space
		while(nodeHeight > height) {
			this.addTopLayer();
		}
		
		//building newNode's tower from bottom to top
		
		int towerCounter = 1; //current tower height
		Node tailPointer = last.right;
		
		//building new tower against the tail tower
		while(tailPointer.up != null) {
			tailPointer = tailPointer.up;
			//adding new copy nodes to the tower while needed
			if (towerCounter < nodeHeight) { 
				Node copyNode = new Node(e);
				copyNode.right = tailPointer;
				copyNode.left = tailPointer.left;
				copyNode.left.right = copyNode;
				tailPointer.left = copyNode;
				copyNode.down = tailPointer.down.left;
				copyNode.down.up = copyNode;
				copyNode.distance = 1;
			}
			//if tower is complete, finish updating the rest of the left tower's distances
			else {
				tailPointer.left.distance++;
			}
			towerCounter++;
		}
		
		size++;
		return true;
	}
	
	/**
	 * Adds an element to the specified index of the list
	 * 
	 * @param index the index where the element will be added
	 * @param element the element being added
	 * @throws NullPointerException if the specified element is null
	 * @throws IndexOutOfBoundsException if the specified index is out of range (index < 0 || index > size)
	 */
	public void add(int index, E element) {
		if ((index < 0) || (index > size)) {
			throw new IndexOutOfBoundsException();
		}
		else if (element == null) {
			throw new NullPointerException();
		}
		
		//if index == size, just call regular add(E element) method to append to the end of the SkipList
		if (index == size) {
			this.add(element);
		}
		
		//otherwise, insert at the specified index without using the tail tower
		else {
			//searching for tower on right side of insertion point
			Node<E> pointer = getNode(index);
		
			//moving to the bottom of tower next to insertion point
			while(pointer.down!= null) {
				pointer = pointer.down;
			}
		
			//inserting newNode at bottom layer of insertion point
			Node<E> newNode = new Node(element);
			newNode.right = pointer;
			newNode.left = pointer.left;
			pointer.left = newNode;
			newNode.left.right = newNode;
			newNode.distance = 1;
			
			//if the index = 0, reassign the first pointer
			if (index == 0) first = newNode;
			
			//call coinFlipper to determine this node tower's height
			int towerHeight = this.coinFlipper();
			
			//adding layers to SkipList if new Node's height is greater than current height
			while(towerHeight > height) {
				addTopLayer();
			}
			
			// current height of the tower
			int nodeHeight = 1;
			// current node's distance
			int distanceCounter = 1;
			
			//boolean to determine if pointer is in a location that will allow new copy node insertion
			boolean canInsert = true;
			
			// adjusting distances up to the top of the skipList
			while(nodeHeight < height) {
				
				canInsert = true;
				// move pointer to the top of the right node tower
				if (pointer.up != null) {
					pointer = pointer.up;
				}
				else {
					
					//once reached top of the current right node tower
						//move pointer to the top of the next right node tower
					while(canInsert) {
						//move pointer to the new right tower
						if (pointer.right != null) {
							distanceCounter += pointer.distance;
							pointer = pointer.right;
						}
						//if not at top of new right tower, move pointer up the new right tower and insert copy node
						if (pointer.up != null) {
							pointer = pointer.up; 
							canInsert = false;
						}
						//insert copy node if tail has been reached
						if ((pointer== tail)) {
							canInsert = false;
						}
					}
				}

				//if nodeHeight has not reached towerHeight, insert the copy node to the top of the tower
				if (nodeHeight < towerHeight) {
					Node<E> copyNode = new Node<E>(element);
					copyNode.down = pointer;
					pointer.up = copyNode;
					copyNode.left = pointer.left;
					copyNode.left.right = copyNode;
					copyNode.right = pointer;
					pointer.left = copyNode;
					copyNode.distance = distanceCounter;
					copyNode.left.distance = (copyNode.left.distance+1) - copyNode.distance;
					newNode = copyNode;
					nodeHeight++;
				}
				//tower is complete and we only need to update the distances of the left tower
				else {
					pointer.left.distance++;
					nodeHeight++;
				}
			}
			size++;
		}
	}
	
	/**
	 * Helper method to return the node at the given index
	 * 
	 * @param i index of element to be returned
	 * @throws IndexOutOfBoundsException if index < 0 or index >= size
	 * @return Node at index i
	 */
	private Node getNode(int i) {
		if ((i < 0) ||(i >= size)) {
			throw new IndexOutOfBoundsException();
		}
		else if (i == 0) {
			return first;
		}
		else if (i == size) {
			return last;
		}
		else {
			//start at the top left of the SkipList
			Node <E> pointer = head;
			
			//destination's distance
			int destination = i + 1;
			
			//distance traveled so far
			int distanceSum = 0;

			while(true) {
				// if going right achieves destination distance, return right node
				if (distanceSum+pointer.distance == destination) {
					return pointer.right;
				}
				// if going right still doesn't achieve destination distance, move pointer right
				else if (distanceSum+pointer.distance < destination) {
					if (pointer.right != null) {
						distanceSum += pointer.distance;
						pointer = pointer.right;
					}
				}
				// if going right over-steps the destination distance, move pointer down
				else {
					if (pointer.down != null) {
						pointer = pointer.down;
					}
				}
			}
		}
	}
	
	
	/**
	 * Returns the element at the specified index
	 * 
	 * @param index the index of the returned element
	 * @throws IndexOutOfBoundsException if index < 0 or index >= size
	 */
	public E get(int index) {
		return (E)getNode(index).element;
	}
	
	
	/**
	 * Returns a string containing the elements of the list enclosed within square brackets and separated by commas.
	 * 
	 * @return a String representation of FileList
	 */
	public String toString() {
		String s = "[";
		int i = 0;
		Node pointer = first;
		while(i < size) {
			if (i != size-1) {
				s += pointer.element.toString();
				s += ", ";
			}
			else {
				s += pointer.element.toString();
			}
			pointer = pointer.right;
			i++;
		}
		s += "]";
		return s;
	}
	
	public Exception someUnneededMethod() {
		throw new UnsupportedOperationException();
		}

	/**
	 * Returns the size of the list
	 * 
	 * @return number of elements in list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * A nested class to create Nodes
	 *
	 * @param <E> type of element stored in Node
	 */
	private class Node<E> {
		private Node right;
		private Node down;
		private Node left;
		private Node up;
		
		private int distance;
		final private E element;
		
		private Node(E e) {
			element = e;
			distance = 0;
		}
		
	}
	
	/**
	 * A helper method to decide how tall to make each node Tower
	 * 
	 * @return towerHeight
	 */
	private int coinFlipper() {
		int result = 1;
		boolean tails = false;
		while((!tails) && (result < MAXLEVEL)) {
			// if the coin flip lands on 1 (heads), add an additional level to the tower
			if (r.nextInt(2) == 1) {
				result++;
			}
			//otherwise, the coin lands on tails, and the current tower height is returned.
			else {
				tails = true;
			}
		}
		return result;
	}

	//------------------------------------------------
	
	@Override
	public boolean isEmpty() {
		this.someUnneededMethod();
		return false;
	}

	@Override
	public boolean contains(Object o) {
		this.someUnneededMethod();
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		this.someUnneededMethod();
		return null;
	}

	@Override
	public Object[] toArray() {
		this.someUnneededMethod();
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		this.someUnneededMethod();
		return null;
	}

	@Override
	public boolean remove(Object o) {
		this.someUnneededMethod();
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		this.someUnneededMethod();
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		this.someUnneededMethod();
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		this.someUnneededMethod();
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		this.someUnneededMethod();
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		this.someUnneededMethod();
		return false;
	}

	@Override
	public void clear() {
		this.someUnneededMethod();
		
	}

	@Override
	public E set(int index, E element) {
		this.someUnneededMethod();
		return null;
	}

	@Override
	public E remove(int index) {
		this.someUnneededMethod();
		return null;
	}

	@Override
	public int indexOf(Object o) {
		this.someUnneededMethod();
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		this.someUnneededMethod();
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		this.someUnneededMethod();
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		this.someUnneededMethod();
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		this.someUnneededMethod();
		return null;
	}
}
