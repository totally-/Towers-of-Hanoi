/**
 * 
 * This class represents the Towers of Hanoi game. The Towers of Hanoi consist of three pegs and a collection of rings that
 * stack on the pegs. The rings are different sizes and are stacked on the first peg in decreasing order of their size in units (in this case, in inches), 
 * and the second and third pegs are initially empty. The goal is to move all the rings from the first peg to the second peg so that they are stacked in decreasing order. 
 * A ring may NOT be placed on top of a ring with a smaller diameter (this rule applies to all three pegs).  There are three pegs so that rings can be temporarily 
 * placed onto a peg and moved as needed.
 * 
 *
 */


class Towers {  //package access so not declared as public, protected, private, etc. 

	//Instance or class variables
	private Peg[] pegs; //this array holds the 1st, 2nd, and 3rd peg
	private static int NUM_OF_PEGS = 3; //we are using three pegs

	private static final int FIVE = 5; //the default constructor stacks five rings on the first peg


	/**
	 * Default constructor (no argument constructor)
	 * Stack five rings on pegOne in decreasing order of their size in inches. pegTwo and pegThree are empty.
	 */
	Towers() { //package access so not declared as public, protected, private, etc. 
		this(FIVE); //chain this constructor to the constructor that is passed an int value 

	} //end of Default constructor

	/**
	 * Alternate constructor
	 * @param n the number of rings a peg can hold
	 * Precondition: 1<=n<=64
	 * Postconditon: The towers have been initialized with n rings on the first peg and no rings on the other two pegs. The diameters of the first
	 * peg's rings are stacked in decreasing order of their size in inches. pegTwo and pegThree are empty.
	 */
	Towers(int n) { //package access so not declared as public, protected, private, etc. 
		
		pegs = new Peg[NUM_OF_PEGS]; //set the number of Peg objects in the pegs array

		//instantiate the 1st, 2nd, and 3rd peg
		for(int i = 0; i < pegs.length; i++) {
			pegs[i] = new Peg(n);
		} //end of enhanced for-loop

		//stack n rings onto 1st peg in descending order
		for(int i = n-1; i >= 0 ; i--) {
			pegs[0].addRing(i+1); // the 0th index of the pegs array holds the 1st peg
		} //end of for loop
		
	} //end of Alternate constructor

	/**
	 * Precondition: pegNumber is 1, 2, or 3
	 * Postcondition: The return value is the number of rings on the specified peg
	 * @param the number of the peg
	 * @return the number of rings on the specified peg
	 */
	int getRingCount(int pegNumber){ //package access so not declared as public, protected, private, etc. 

		//if pegNumer is not 1, 2, or 3 throw an IllegalArgument Exception
		if(pegNumber != 1 && pegNumber!= 2 && pegNumber !=3) {
			throw new IllegalArgumentException();
		} //end of if

		return pegs[pegNumber-1].getRingCount();

		//return -1; //dummy value. this method will never return -1 because, if the user enters a value other than 1, 2, or 3, an exception will be thrown

	} //end of getRingCount

	/**
	 * Precondition: pegNumber is 1, 2, or 3
	 * Postcondition: If the specified peg is not empty, then the return value is the diameter of the top ring on it; otherwise, the return value is zero.
	 * @param pegNumber The peg number that holds the top ring we want to know the diameter of
	 * @return the diameter of the top ring held by the specified peg
	 */
		int getTopDiameter(int pegNumber) { //package access so not declared as public, protected, private, etc. 
	
			if(getRingCount(pegNumber) > 0) {
				return pegs[pegNumber-1].getTopDiameter();
			} //end of if
	
			//return 0 if the user did not pass 1, 2, or 3 when the method was called OR if the user specified peg is empty
			return 0; 
		} //end of getTopDiameter

	/**
	 * 
	 * Precondtion: startPeg is a peg number (1, 2, or 3), the number of rings on startPeg is at least one, endPeg is not the same
	 * peg as startPeg, the diameter of the top ring of the startPeg is less than the diameter of the top ring of the endPeg OR the 
	 * startPeg is not empty and the endPeg is empty.
	 * Postcondition: If precondition is not true, return value is false and pegs are unchanged, otherwise the top ring has been moved from
	 * startPeg to endPeg and the return value is true.
	 * 
	 * @param startPeg the peg we want to move a ring from
	 * @param endPeg the peg we want to move the ring from the starting peg to
	 * @return true if the move was legal (successful) or false if the move was illegal (failed)
	 */
		boolean move(int startPeg, int endPeg) { //package access so not declared as public, protected, private, etc. 
	
			//a ring can be moved from the start peg to the end peg if the following conditions are true
			//if the startPeg is not empty
			//startPeg != endPeg
			//the top diameter of the ring on the startPeg is less than the top diameter of the ring OR startPeg is not empty and the endPeg is empty
			
			if(getRingCount(startPeg) > 0 && endPeg != startPeg && 
					(getTopDiameter(startPeg) < getTopDiameter(endPeg) || getRingCount(endPeg) ==  0)) {
				
				//note: he made an isValidMove method
				/*
				 * private boolean isValidMove(Peg fromOeg, Peg toPeg {
				 *   return fromPeg.getRingCount() > 0 && ( fromPeg.getTopDiameter() < toPeg.getTopDiameter() || toPeg.getRingCount() == 0);fmove
				 * }
				 */
				
				pegs[endPeg-1].addRing(pegs[startPeg-1].getTopDiameter()); //set the value of the diameter of the top ring
				// of the endPeg to the value of the diameter of the top ring of the startPeg
				pegs[startPeg-1].removeRing(); //decrement the number of rings on the startPeg
	
				return true;
			} //end of if
	
			//if the startPeg is equal to the endPeg return false or if the user enters a value that is not 1,2, or 3
			return false; 
	
		} //end of move
		


} //end of Towers class
