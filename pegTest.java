import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PegTest {

	private static final int MAX_NUM_RINGS = 64;
	private static final long SEED = 20170604001L;
	private static final Random RAND = new Random(SEED);



	@Test
	public void testConstructor() {

		//test constructor of Peg class using a legal value in the range but not on either boundary value
		Peg p = new Peg(3);
		int[] rings = p.getRings();
		assertEquals(3, rings.length);

	}  //end of testConstructor


	@Test
	public void testConstructorLowerBound() {

		//test constructor using lower bound
		Peg p = new Peg(1);
		int[] rings = p.getRings();
		assertEquals(1, rings.length);

	}  //end of testConstructorLowerBound

	@Test
	public void testConstructorUpperBound() {

		//test constructor using lower bound
		Peg p = new Peg(MAX_NUM_RINGS);
		int[] rings = p.getRings();
		assertEquals(MAX_NUM_RINGS, rings.length);

	}  //end of testConstructorUpperBound


	@Test
	public void testConstructorOutOfRange() {

		Peg p = null;

		//test illegal value below lower bound
		try {
			p = new Peg(0); //illegal value
			fail("Expected exception");
		}
		catch(IllegalArgumentException iae) {
			//expected
		} //end of catch

		//test illegal value exceeding upper bound
		try {
			p = new Peg(MAX_NUM_RINGS + 1); //illegal value
			fail("Expected exception");
		}
		catch(IllegalArgumentException iae) {
			//expected
		} //end of catch

	} //end of testConstructorOutOfRange

	@Test
	public void testPreConditionAddRing() {
		Peg p = new Peg(5); //instantiate rings array of type int to length 5

		//legal adds
		assertTrue(p.addRing(1)); //ringCount is now 1
		assertEquals(1, p.getRingCount());
		assertTrue(p.addRing(2)); //ringCount is now 2
		assertEquals(2, p.getRingCount());
		assertTrue(p.addRing(3)); //ringCount is now 3
		assertEquals(3, p.getRingCount());
		assertTrue(p.addRing(4)); //ringCount is now 4
		assertEquals(4, p.getRingCount());
		assertTrue(p.addRing(5)); //ringCount is now 5 and at capacity
		assertEquals(5, p.getRingCount());

		//illegal add
		assertFalse(p.addRing(6)); //rings array is at capacity so cannot addRing to it
		assertEquals(5, p.getRingCount());
	} //end of testAddRing


	@Test public void testPreConditonRemoveRing() {
		Peg p = new Peg(3);

		//illegal remove
		assertFalse(p.removeRing()); //ringCount is 0 so cannot remove a ring from an empty array

		//add rings to rings array until it is at capacity
		assertTrue(p.addRing(1));
		assertEquals(1, p.getRingCount());
		assertTrue(p.addRing(2));
		assertEquals(2, p.getRingCount());
		assertTrue(p.addRing(3));
		assertEquals(3, p.getRingCount()); //rings array is at capacity

		//legal removal
		assertTrue(p.removeRing()); //ringCount is 2
		assertEquals(2, p.getRingCount()); 
		assertTrue(p.removeRing()); //ringCount is 1
		assertEquals(1, p.getRingCount()); 
		assertTrue(p.removeRing()); //ringCount is 0
		assertEquals(0, p.getRingCount());

		//illegal remove
		assertFalse(p.removeRing()); //cannot remove non-existent ring from array
		assertEquals(0, p.getRingCount());

	} //end of testPreConditionRemoveRing

	@Test
	public void testPreConditionGetTopDiameter() {
		Peg p = new Peg(2);

		//test preCondition (ringCount >0)
		assertEquals(0,p.getTopDiameter()); //there are no rings in the rings array so this will return 0

		assertTrue(p.addRing(2)); //add ring with diameter 2 to rings array
		assertEquals(1, p.getRingCount()); //ringCount is 1
		assertEquals(2,p.getTopDiameter()); // top diameter is 2

		assertTrue(p.addRing(1)); //add ring with diameter 1 to rings array
		assertEquals(2,p.getRingCount()); //ringCount is 2
		assertEquals(1,p.getTopDiameter()); //top diameter is now 1

		assertTrue(p.removeRing()); //remove top ring from rings array
		assertEquals(1,p.getRingCount()); //ringCount is now 1
		assertEquals(2,p.getTopDiameter()); //top diameter is now 2

		assertTrue(p.removeRing()); //remove last ring from rings array (rings array is now empty)
		assertEquals(0,p.getRingCount()); //ringCount is now zero
		assertEquals(0,p.getTopDiameter()); //rings array is empty to getTopDiameter method returns zero

	} //end of testPreConditionGetTopDiameter

	@Test 
	public void testGetRingCount() {
		Peg p = new Peg(2);

		assertEquals(0, p.getRingCount()); //rings array is empty so getRingCount returns zero

		assertTrue(p.addRing(3));
		assertEquals(1, p.getRingCount()); //rings array now has one ring in it
		assertTrue(p.addRing(2));
		assertEquals(2, p.getRingCount()); //rings array now has two rings in it and is at capacity
		assertFalse(p.addRing(1)); //cannot add ring to rings array because it is at capacity
		assertEquals(2,p.getRingCount()); //rings array still holds two rings
		assertEquals(2, p.getTopDiameter());
	} //end of testGetRingCount

	@Test 
	public void testPreConditionGetRing() {
		Peg p = new Peg(3);

		//test illegal value below lower bound
		try {
			p.getRing(-1); //-1 is not a legal index
			fail("Expected exception");
		}
		catch(IllegalArgumentException iae) {
			//expected
		} //end of catch

		//test illegal value below lower bound
		try {
			p.getRing(3); //3 is not a legal index
			fail("Expected exception");
		}
		catch(IllegalArgumentException iae) {
			//expected
		} //end of catch

		//add rings to rings array 
		assertTrue(p.addRing(3)); //index 0
		assertTrue(p.addRing(2)); //index 1
		assertTrue(p.addRing(1)); //index 2

		//passing legal "ith" indexes of the rings array to test the getRing method
		assertEquals(3, p.getRing(0));
		assertEquals(2, p.getRing(1));
		assertEquals(1, p.getRing(2));

	} //end of testPreConditionGetRing

	@Test
	public void testGetRings() {
		Peg p = new Peg(3);

		//add rings to rings array 
		assertTrue(p.addRing(3)); //index 0
		assertTrue(p.addRing(2)); //index 1
		assertTrue(p.addRing(1)); //index 2

		int[] rings = p.getRings();
		
		assertFalse(rings == p.getRings()); //rings and p.getRings() point to different memory addresses so this
		// will return false
		
		//show that same index of each array has the same value 
		for(int i = 0; i < rings.length; i++) {
			assertEquals(rings[i], p.getRing(i));
		} //end of enhanced for-loop
		
		//make a change to rings
		rings[0] = 44;
		
		//the change was only made to index 0 of rings array and not the 0th index of the rings array of instance p
		assertTrue(rings[0] != p.getRing(0)); 
		
		
		
	} //end of testGetRings


} //end of PegTest

