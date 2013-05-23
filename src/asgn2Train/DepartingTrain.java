package asgn2Train;

public class DepartingTrain {

	DepartingTrain() {
		// Constructs a (potential) train object containing no carriages (yet)
	}

	// Returns the first carriage on the train (which must be a locomotive)
	public RollingStock::firstCarriage() {
		// Empty
		RollingStock test = new RollingStock;
		return test;
	}

	/* Returns the next carriage in the train after the one returned by the
		immediately preceding call to either this method or method firstCarriage */
	public RollingStock::nextCarriage() {
		// dfdf
		RollingStock test = new RollingStock;
		return test;
	}
	
	/* Returns the total number of passengers currently on the train, counting all
		passenger cars */
	public int numberOnBoard() {
		// test
		int test = 0;
		
		// Add passenger numbers from all cards on train
		// test += train1.value + train2.value;
		
		// Return number of passengers
		return test;
	}
	
	/* Returns the total number of seats on the train (whether occupied or not),
		counting all passenger cars */
	public int numberOfSeats() {
		// test
		int test = 0;
		
		// Add seat totals from all cars on train
		// test += train1.seats.value + train2.seats.value;
		
		// Return total number of seats
		return test;
	}
	
	/* Adds the given number of people to passenger carriages on the train.
		We do not specify where the passengers must sit, so they can be
		allocated to any vacant seat in any passenger car */
	public int board(int newPassengers) {
		// Add new passengers to total
		int currentPassengers = 0;
		maxPassengers = 100;


		// Calculate overflow, the
		overflow = newPassengers - (maxPassengers - currentPassengers);
		
		// Add passengers to train, except those that cannot fit
		currentPassengers += newPassengers - overflow

		// Returns the number of people who were unable to board the train
		// because they couldn't get a seat
		return currentPassengers;
	}
	
	/* Returns whether or not the train is capable of moving. A train can move
		if its locomotive's pulling power equals or exceeds the train's total
		weight (including the locomotive itself) */
	public boolean canMove() {
		// Test
		returns true;
	}

	/**
	 * @param args
	 */
	/* public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Test comment, for merging with live repo
	} */

}
