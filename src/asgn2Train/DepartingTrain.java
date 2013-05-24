package asgn2Train;

import asgn2RollingStock.RollingStock;
import asgn2Exceptions.TrainException;

/**
 * 
 * @author Steven Lomas
 * 
 */

public class DepartingTrain extends Object {
	
	RollingStock train;

	DepartingTrain() {
		// Constructs a (potential) train object containing no carriages (yet)
		// empty RollingStock array?
	}

	// Returns the first carriage on the train (which must be a locomotive)
	public RollingStock firstCarriage() {
		// Create new RollingStock object for testing
		RollingStock firstCar = new RollingStock(10);
		return firstCar;
	}

	/* Returns the next carriage in the train after the one returned by the
		immediately preceding call to either this method or method firstCarriage */
	public RollingStock nextCarriage() {
		// Create new RollingStock object for testing
		RollingStock test = new RollingStock(10);
		return test;
	}
	
	/* Returns the total number of passengers currently on the train, counting all
		passenger cars */
	public int numberOnBoard() {
		int availableSeats = 0;

		// Find number of FREE seats on ALL current passenger cars
		// availableSeats = car1.numberOnBoard();
		// availableSeats += car2.numberOnBoard();
		
		// Return number of passengers
		return availableSeats;
	}
	
	/* Returns the total number of seats on the train (whether occupied or not),
		counting all passenger cars */
	public int numberOfSeats() {
		int seats = 0;
		
		// Add seat totals from all cars on train
		// car1.numberOfSeats();
		// car2.numberOfSeats();
		
		// Return total number of seats
		return seats;
	}
	
	/* Adds the given number of people to passenger carriages on the train.
		We do not specify where the passengers must sit, so they can be
		allocated to any vacant seat in any passenger car */
	public int board(int newPassengers) {
		int availableSeats = numberOnBoard();
		
		// Board passengers from front to back
		// car1.board(newPassengers - (car1.numberOfSeats() - car1.numberOnBoard());
		// car2.board(newPassengers - (car2.numberOfSeats() - car2.numberOnBoard());
		
		// Any leftover passengers are returned
		if (newPassengers - availableSeats < 0) {
			return 0;
		} else {
			return newPassengers - availableSeats;
		}
		
		/* OUTDATED
		// Add new passengers to total
		int currentPassengers = 0;
		int maxPassengers = 100;


		// Calculate overflow, the
		int overflow = newPassengers - (maxPassengers - currentPassengers);
		
		// Add passengers to train, except those that cannot fit
		currentPassengers += newPassengers - overflow;

		// Returns the number of people who were unable to board the train
		// because they couldn't get a seat
		return currentPassengers; */
	}
	
	/* Returns whether or not the train is capable of moving. A train can move
		if its locomotive's pulling power equals or exceeds the train's total
		weight (including the locomotive itself) */
	public boolean canMove() {
		// Find totalWeight of train
		// for number of RollingStock objects getGrossWeight
		
		// if totalWeight < locomotive.power() return true else return false;
		return true;
	}
	
	/**
	 * Adds a new carriage to the end of the train. However, a new carriage
	 * may be added only if the resulting train configuration is valid, as
	 * per the rules listed above. Furthermore, shunting operations may not
	 * be performed if there are passengers on the train.
	 * 
	 * Hint: You may find Java's in-built instanceof operator useful when
	 * implementing this method (and others in this class).
	 * 
	 * @param newCarriage the new carriage to be added
	 * @throws TrainException if adding the new carriage would produce an
	 * invalid train configuration, or if there are passengers on the train
	 */
	public void addCarriage(RollingStock newCarriage)
			throws TrainException {
		// Add newCarriage RollingStock object to end of train
	}
	
	/**
	 * Removes the last carriage from the train. (This may be the locomotive
	 * if it is the only item of rolling stock on the train.) However,
	 * shunting operations may not be performed if there are passengers
	 * on the train.
	 * 
	 * @throws TrainException if there is no rolling stock on the "train",
	 * or if there are passengers on the train.
	 */
	public void removeCarriage()
            throws TrainException {
		// Remove RollingStock object from end of train
	}
	
	/**
	 * Returns a human-readable description of the entire train.
	 * This has the form of a hyphen-separated list of carriages,
	 * starting with the locomotive on the left. The description
	 * is thus a string "a-b-...-z", where a is the human-readable
	 * description of the first carriage (the locomotive), b is the
	 * description of the second carriage, etc, until the description
	 * of the last carriage z. (Note that there should be no hyphen
	 * after the last carriage.) For example, a possible train
	 * description may be "Loco(6D)-Passenger(13/24)-Passenger(16/16)-Freight(G)".
	 * 
	 * In the degenerate case of a "train" with no carriages,
	 * the empty string is returned.
	 * 
	 * @overrides toString in class Object
	 * @returns a human-readable description of the entire train
	 */
	public String toString() {
		// toString override
		return "";
	}
	
	
	// Test

	/**
	 * @param args
	 */
	/* public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Test comment, for merging with live repo
	} */

}
