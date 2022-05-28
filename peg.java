import java.util.Stack;

/**
 * 
 * This class represents a peg that can have rings stacked on it. 
 * 
*/

public class Peg {

	//instance or class variables
	private int [] rings; //each element in this array of type int represents the diameter of a ring in inches. they will be stacked in descending order
	private int ringCount; //the number of rings on a peg

	/**
	 * Constructor
	 * Precondition: 1<=n<=64
	 * Postcondition: The rings[] array has been initialized to size n
	 * @param n the number of rings a peg can hold
	 *
	 */
	Peg(int n) { //package access so not declared as public, protected, private, etc.
		if(n < 1 || n > 64) {
			throw new IllegalArgumentException();
		} //end of if
		else{
			rings = new int[n];
		} //end of else
	} //end of Peg

	/**
	 * 
	 * @return number of rings the peg has stacked on it
	 */
	int getRingCount() { //package access so not declared as public, protected, private, etc.
		return ringCount;
	} //end of getRingCount

	/**
	 * Precondition: then number of rings must be greater than or equal to zero AND the the peg cannot be at capacity AND argument ring must be
	 * greater than zero.
	 * Postcondition:  If ringCount >=0 AND the peg is not at capacity AND ring >0, insert ring to the top of 
	 * the peg, increment ringCount, and return true; otherwise, return false
	 * @param ring represents a ring that we want to stack on the peg. the ring value represents the diameter of the ring
	 */
	boolean addRing(int ring) { //package access so not declared as public, protected, private, etc.
		if( ringCount >= 0 && ringCount < rings.length && ring > 0) {
			rings[ringCount++] = ring;
			return true;
		} //end of if
		return false;
	} //end of addRings

	/**
	 * Precondition: the peg is not empty
	 * Postcondition: if the peg is not empty, a ring is removed from it (ringCount is decremented)
	 * @return return true if a ring was removed from the peg; otherwise, return false if there was no removal
	 */
	boolean removeRing() { //package access so not declared as public, protected, private, etc.
		if(ringCount >0) {
			ringCount--;
			return true;
		} //end of if
		return false;
	} //end of removeRing

	/**
	 * Precondition: the peg is not empty
	 * Postcondition: return value of top ring diameter OR return 0
	 * @return if the peg is not empty, return the value of the diameter of the ring on the top of the stack; otherwise,
	 * return 0 if the peg is empty
	 */
	int getTopDiameter() {  //package access so not declared as public, protected, private, etc.
		if(ringCount >0) {
			return rings[ringCount-1];
		} //end of getTopDiameter

		return 0; 
	} //end of getTopDiameter

	/**
	 * Precondition: index is within the the inclusive range from the 0th index to the last index of the rings array
	 * @param index the index of the rings array that we want to know the int value of
	 * @return the int value at the specified index of the rings array
	 */
	int getRing(int index) { //package access so not declared as public, protected, private, etc.
		if(index < 0 || index >= rings.length) {
			throw new IllegalArgumentException();
		} //end of if
		return rings[index];
	} //end of getRing


	/**
	 * 
	 * @return deep copy of rings[] array
	 */
	int[] getRings() { //package access so not declared as public, protected, private, etc.
		int[] tempRings = new int[rings.length];
		for(int i = 0; i < rings.length; i++) {
			tempRings[i] = rings[i];
		} //end of enhanced for-loop
		return tempRings;
	} //end of getRings



} //end of Peg class
