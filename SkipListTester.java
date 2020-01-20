//Tester for SkipList by Julianna Ngo
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;

public class SkipListTester {

	//All the exception tests:
	
	@Test(expected = NullPointerException.class)
	public void testAddNull() {
			SkipList<Number> sl1 = new SkipList<>();
			sl1.add(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddIndexNull() {
		SkipList<Number> sl2 = new SkipList<>();
		sl2.add(0, null);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddIndexBoundsNeg() {
		SkipList<Number> sl2 = new SkipList<>();
		sl2.add(-1, 5);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddIndexBoundsPos() {
		SkipList<Number> sl2 = new SkipList<>();
		sl2.add(1, 5);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getIndexEmpty() {
		SkipList<Number> sl2 = new SkipList<>();
		sl2.get(0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getIndexNeg() {
		SkipList<Number> sl2 = new SkipList<>();
		sl2.add(4);
		sl2.get(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getIndexPos() {
		SkipList<Number> sl2 = new SkipList<>();
		sl2.add(4);
		sl2.get(1);
	}
	
	@Test
	public void addThree() {
		SkipList sl2 = new SkipList<>();
		LinkedList ll2 = new LinkedList<>();
		
		sl2.add(0);
		ll2.add(0);
		sl2.add(1);
		ll2.add(1);
		sl2.add(2);
		ll2.add(2);
		
		assertEquals("added 3 elements incorrectly" ,ll2.toString(), sl2.toString());
	}
	
	@Test
	public void addIndexStart() {
		SkipList sl2 = new SkipList<>();
		LinkedList ll2 = new LinkedList<>();
		
		sl2.add(0);
		ll2.add(0);
		sl2.add(1);
		ll2.add(1);
		sl2.add(2);
		ll2.add(2);
		sl2.add(3);
		ll2.add(3);
		sl2.add(4);
		ll2.add(4);
		
		
		ll2.add(0, 5);
		sl2.add(0, 5);
		assertEquals("added to start index incorrectly" ,ll2.toString(), sl2.toString());
	}
	
	@Test
	public void addIndexStartSize() {
		SkipList sl2 = new SkipList<>();
		LinkedList ll2 = new LinkedList<>();
		
		sl2.add(0);
		ll2.add(0);
		sl2.add(1);
		ll2.add(1);
		sl2.add(2);
		ll2.add(2);
		sl2.add(3);
		ll2.add(3);
		sl2.add(4);
		ll2.add(4);
		
		
		ll2.add(0, 5);
		sl2.add(0, 5);
		
		assertEquals("added to start index incorrectly" ,ll2.size(), sl2.size());
	}
	
	@Test
	public void addIndexMid1() {
		SkipList sl2 = new SkipList<>();
		LinkedList ll2 = new LinkedList<>();
		
		sl2.add(0);
		ll2.add(0);
		sl2.add(1);
		ll2.add(1);
		sl2.add(2);
		ll2.add(2);
		
		ll2.add(2, -1);
		sl2.add(2, -1);
		
		assertEquals("added to middle index incorrectly" ,ll2.toString(), sl2.toString());
	}
	
	@Test
	public void addIndexMidSize1() {
		SkipList sl2 = new SkipList<>();
		LinkedList ll2 = new LinkedList<>();
		
		sl2.add(0);
		ll2.add(0);
		sl2.add(1);
		ll2.add(1);
		sl2.add(2);
		ll2.add(2);
		
		ll2.add(2, -1);
		sl2.add(2, -1);
		
		assertEquals("added to middle index incorrectly" ,ll2.size(), sl2.size());
	}
	
	@Test
	public void addIndexMid2() {
		SkipList sl2 = new SkipList<>();
		LinkedList ll2 = new LinkedList<>();
		
		sl2.add(0);
		ll2.add(0);
		sl2.add(1);
		ll2.add(1);
		sl2.add(2);
		ll2.add(2);
		sl2.add(3);
		ll2.add(3);
		sl2.add(4);
		ll2.add(4);
		sl2.add(5);
		ll2.add(5);
		
		ll2.add(3, -1);
		sl2.add(3, -1);
		
		assertEquals("added to middle index incorrectly" ,ll2.toString(), sl2.toString());
	}
	
	@Test
	public void addIndexMidSize2() {
		SkipList sl2 = new SkipList<>();
		LinkedList ll2 = new LinkedList<>();
		
		sl2.add(0);
		ll2.add(0);
		sl2.add(1);
		ll2.add(1);
		sl2.add(2);
		ll2.add(2);
		sl2.add(3);
		ll2.add(3);
		sl2.add(4);
		ll2.add(4);
		sl2.add(5);
		ll2.add(5);
		sl2.add(6);
		ll2.add(6);
		sl2.add(7);
		ll2.add(7);
		sl2.add(8);
		ll2.add(8);
		
		ll2.add(3, -1);
		sl2.add(3, -1);
		
		ll2.add(7, -2);
		sl2.add(7, -2);
		
		assertEquals("added to middle index incorrectly" ,ll2.size(), sl2.size());
	}
	
	@Test
	public void addIndexEnd() {
		SkipList sl2 = new SkipList<>();
		LinkedList ll2 = new LinkedList<>();
		
		sl2.add(0);
		ll2.add(0);
		sl2.add(1);
		ll2.add(1);
		sl2.add(2);
		ll2.add(2);
		
		ll2.add(3, -1);
		sl2.add(3, -1);
		
		assertEquals("added to end index incorrectly" ,ll2.toString(), sl2.toString());
	}
	
	@Test
	public void addIndexEndSize() {
		SkipList sl2 = new SkipList<>();
		LinkedList ll2 = new LinkedList<>();
		
		sl2.add(0);
		ll2.add(0);
		sl2.add(1);
		ll2.add(1);
		sl2.add(2);
		ll2.add(2);
		
		ll2.add(3, -1);
		sl2.add(3, -1);
		
		assertEquals("size of added to end index incorrectly" ,ll2.size(), sl2.size());
	}
	
	@Test
	public void getBeginningCheck() {
		SkipList sl2 = new SkipList<>();
		LinkedList ll2 = new LinkedList<>();
		
		sl2.add(0);
		ll2.add(0);
		sl2.add(1);
		ll2.add(1);
		sl2.add(2);
		ll2.add(2);
		
		ll2.get(0);
		sl2.get(0);
		
		assertEquals("getBeginning altered list" ,ll2.toString(), sl2.toString());
	}
	
	@Test
	public void getMiddleCheck() {
		SkipList sl2 = new SkipList<>();
		LinkedList ll2 = new LinkedList<>();
		
		sl2.add(0);
		ll2.add(0);
		sl2.add(1);
		ll2.add(1);
		sl2.add(2);
		ll2.add(2);
		
		ll2.get(1);
		sl2.get(1);
		
		assertEquals("getMiddle altered list" ,ll2.toString(), sl2.toString());
	}
	
	@Test
	public void getEndCheck() {
		SkipList sl2 = new SkipList<>();
		LinkedList ll2 = new LinkedList<>();
		
		sl2.add(0);
		ll2.add(0);
		sl2.add(1);
		ll2.add(1);
		sl2.add(2);
		ll2.add(2);
		
		ll2.get(2);
		sl2.get(2);
		
		assertEquals("getEnd altered list" ,ll2.toString(), sl2.toString());
	}
	
	@Test
	public void getBeginning() {
		SkipList sl2 = new SkipList<>();
		LinkedList ll2 = new LinkedList<>();
		
		sl2.add(0);
		ll2.add(0);
		sl2.add(1);
		ll2.add(1);
		sl2.add(2);
		ll2.add(2);
		
		assertEquals("returned wrong element" ,ll2.get(0), sl2.get(0));
	}
	
	@Test
	public void getMiddle1() {
		SkipList sl2 = new SkipList<>();
		LinkedList ll2 = new LinkedList<>();
		
		sl2.add(0);
		ll2.add(0);
		sl2.add(1);
		ll2.add(1);
		sl2.add(2);
		ll2.add(2);
		
		int getLL3 = (int) ll2.get(1);
		int getSL3 = (int) sl2.get(1);
		
		assertEquals("returned wrong element" ,getLL3, getSL3);
	}
	
	@Test
	public void getMiddle2() {
		SkipList sl3 = new SkipList<>();
		LinkedList ll3 = new LinkedList<>();
		
		sl3.add(0);
		ll3.add(0);
		sl3.add(1);
		ll3.add(1);
		sl3.add(2);
		ll3.add(2);
		sl3.add(3);
		ll3.add(3);
		sl3.add(4);
		ll3.add(4);
		sl3.add(5);
		ll3.add(5);
		
		int getLL3 = (int) ll3.get(3);
		int getSL3 = (int) sl3.get(3);
		
		assertEquals("returned wrong element" ,getLL3, getSL3);
	}
	
	@Test
	public void getEnd() {
		SkipList sl2 = new SkipList<>();
		LinkedList ll2 = new LinkedList<>();
		
		sl2.add(0);
		ll2.add(0);
		sl2.add(1);
		ll2.add(1);
		sl2.add(2);
		ll2.add(2);
		
		int getLL2 = (int) ll2.get(2);
		int getSL2 = (int) sl2.get(2);
		
		assertEquals("returned wrong element" , getLL2, getSL2);
	}
}
