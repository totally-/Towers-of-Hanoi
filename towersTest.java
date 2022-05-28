package edu.metrostate.ics240.p2.towers;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class TowersTest {

	private static final int MAX_NUM_RINGS = 64;
	private static final long SEED = 20170604001L;
	private static final Random RAND = new Random(SEED);

	@Test
	public void testDefaultConstructor() {

		//create instance of Towers class using default constructor
		Towers t = new Towers();

		//test getRingCount method for each peg
		assertEquals(5, t.getRingCount(1));
		assertEquals(0, t.getRingCount(2));
		assertEquals(0, t.getRingCount(3));

		//test getTopDiameter method for each peg
		assertEquals(1, t.getTopDiameter(1));
		assertEquals(0, t.getTopDiameter(2));
		assertEquals(0, t.getTopDiameter(3));

	} //end of testDefaultConstructor

	@Test
	public void testAlternateConstructor() {

		//create instance of Towers class using alternate constructor
		Towers t = new Towers(3);
		assertEquals(3, t.getRingCount(1));
		assertEquals(0, t.getRingCount(2));
		assertEquals(0, t.getRingCount(3));

		//test getTopDiameter method for each peg
		assertEquals(1, t.getTopDiameter(1));
		assertEquals(0, t.getTopDiameter(2));
		assertEquals(0, t.getTopDiameter(3));

	}  //end of testAlternateConstructor

	// test constructors for out of range values -------------------------------------------------
	@Test
	public void testAlternateConstructorOutOfRange() {

		Towers t = null;

		//test illegal value below lower bound
		try {
			t = new Towers(0); //illegal value
			fail("Expected exception");
		}
		catch(IllegalArgumentException iae) {
			//expected
		} //end of catch

		//test illegal value exceeding upper bound
		try {
			t = new Towers(MAX_NUM_RINGS + 1); //illegal value
			fail("Expected exception");
		}
		catch(IllegalArgumentException iae) {
			//expected
		} //end of catch

	} //end of testAlternateConstructorOutOfRange
	
	//test upper and lower bounds of alternate constructor ------------------------------------------------
	@Test
	public void testAlternateConstructorLowerBound() {

		Towers t = new Towers(1);

		//test getRingCount method for each peg
		assertEquals(1, t.getRingCount(1));
		assertEquals(0, t.getRingCount(2));
		assertEquals(0, t.getRingCount(3));

		//test getTopDiameter method for each peg
		assertEquals(1, t.getTopDiameter(1));
		assertEquals(0, t.getTopDiameter(2));
		assertEquals(0, t.getTopDiameter(3));

	} //end of testAlternateConstructorLowerBound

	@Test
	public void testAlternateConstructorUpperBound() {

		Towers t = new Towers(MAX_NUM_RINGS);

		//test getRingCount method for each peg
		assertEquals(64, t.getRingCount(1));
		assertEquals(0, t.getRingCount(2));
		assertEquals(0, t.getRingCount(3));

		//test getTopDiameter method for each peg
		assertEquals(1, t.getTopDiameter(1));
		assertEquals(0, t.getTopDiameter(2));
		assertEquals(0, t.getTopDiameter(3));


	} //end of testAlternateConstructorUpperBound
	
	
	// test precondition getRingCount ----------------------------------------- test precondition getRingCount
	@Test
	public void testPreConditionGetRingCountDefaultConstructor() {
		
		Towers t = new Towers();

		try {
			t.getRingCount(0); //illegal value
			fail("Expected exception");
		}
		catch(IllegalArgumentException iae) {
			//expected
		} //end of catch

		try {
			t.getRingCount(4); //illegal value
			fail("Expected exception");
		}
		catch(IllegalArgumentException iae) {
			//expected
		} //end of catch
		
	} //end of testPreConditionGetRingCountDefaultConstructor
	
	
	@Test
	public void testPreConditionGetRingCountAlternateConstructor() {
		
		Towers t = new Towers(45);

		try {
			t.getRingCount(0); //illegal value
			fail("Expected exception");
		}
		catch(IllegalArgumentException iae) {
			//expected
		} //end of catch

		try {
			t.getRingCount(4); //illegal value
			fail("Expected exception");
		}
		catch(IllegalArgumentException iae) {
			//expected
			
		} //end of catch
		
	} //end of testPreConditionGetRingCountAlternateConstructor

	// test precondition getTopDiameter  ---------------------------------------------------------- test precondition getTopDiameter
	@Test
	public void testPreConditionGetTopDiameterDefaultConstructor() {
		Towers t = new Towers();

		try {
			t.getTopDiameter(0); //illegal value
			fail("Expected exception");
		}
		catch(IllegalArgumentException iae) {
			//expected
		} //end of catch

		try {
			t.getTopDiameter(4); //illegal value
			fail("Expected exception");
		}
		catch(IllegalArgumentException iae) {
			//expected
			
		} //end of catch
		
		
	} //end of testPreConditionGetTopDiameterDefaultConstructor
	
	
	
	@Test
	public void testPreConditionGetTopDiameterAlternateConstructor() {
		Towers t = new Towers(58);

		try {
			t.getTopDiameter(0); //illegal value
			fail("Expected exception");
		}
		catch(IllegalArgumentException iae) {
			//expected
		} //end of catch

		try {
			t.getTopDiameter(4); //illegal value
			fail("Expected exception");
		}
		catch(IllegalArgumentException iae) {
			//expected
			
		} //end of catch
		

	} //end of testPreConditionGetTopDiameterAlternateConstructor
	
	
	// test move method ----------------------------------------------------------------- test move method
	@Test
	public void testMove() {
		int numRings = RAND.nextInt(MAX_NUM_RINGS);
		
		Towers t = new Towers(numRings);
		
		//test legal moves
		assertTrue(t.move(1, 2));
		assertEquals(numRings-1, t.getRingCount(1));
		assertEquals(1, t.getRingCount(2));
		assertEquals(0, t.getRingCount(3));
		
		assertEquals(2, t.getTopDiameter(1));
		assertEquals(1, t.getTopDiameter(2));
		assertEquals(0, t.getTopDiameter(3));
		
		assertTrue(t.move(1, 3));
		assertEquals(numRings-2, t.getRingCount(1));
		assertEquals(1, t.getRingCount(2));
		assertEquals(1, t.getRingCount(3));
		
		assertEquals(3, t.getTopDiameter(1));
		assertEquals(1, t.getTopDiameter(2));
		assertEquals(2, t.getTopDiameter(3));
		
		assertTrue(t.move(2, 3));
		assertEquals(numRings-2, t.getRingCount(1));
		assertEquals(0, t.getRingCount(2));
		assertEquals(2, t.getRingCount(3));
		
		assertEquals(3, t.getTopDiameter(1));
		assertEquals(0, t.getTopDiameter(2));
		assertEquals(1, t.getTopDiameter(3));
		
	} //end of testMove
	
	
	@Test
	public void testIllegalMoves() {
		
		Towers t = new Towers();
		
		//illegal moves
		assertFalse(t.move(3, 1)); //pegThree is empty (can't move a non-existent ring)
		assertFalse(t.move(2, 3)); //pegTwo is empty (can't move a non-existent ring)
		
		//legal moves
		assertTrue(t.move(1, 2));
		assertTrue(t.move(1,3));
		
		//test illegal moves
		assertFalse(t.move(1,1)); //can't move to itself
		assertFalse(t.move(1, 2)); //can't move larger ring to smaller ring
		assertFalse(t.move(1, 3)); //can't move larger ring to smaller ring
		assertFalse(t.move(3,2));  //can't move larger ring to smaller ring
		
	} //end of testMove
} //end of TowersTest class
