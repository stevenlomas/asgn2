package asgn2Train;

import asgn2RollingStock.*;
import asgn2Exceptions.TrainException;
import java.util.Vector;

/**
 * @author Steven Lomas
 */

public class DepartingTrain extends Object {
	
	private Vector<RollingStock> train;
	private int iterator;
	// OLD - private RollingStock Carriage;

	// Constructs a (potential) train object containing no carriages (yet)
	DepartingTrain() {
		iterator = 0;
		
		// OLD - this.nextCar = new PassengerCar(50, 24);
		// OLD - this.firstCar = new Locomotive(100, "6D");
	}

	/**
	 * Returns the first carriage on the train (which must be a locomotive).
	 * Special value null is returned if there are no carriages on the train at all.
	 * 
	 * @return the first carriage in the train, or null if there are no carriages
	 * @throws TrainException
	 */
	public RollingStock firstCarriage() {
		if (train.isEmpty()) {
			return null;
		} else {
			return train.firstElement();
		}
	}

	/**
	 * Returns the next carriage in the train after the one returned by the
	 * immediately preceding call to either this method or method firstCarriage.
	 * Special value null is returned if there is no such carriage. If there has
	 * been no preceding call to either firstCarriage or nextCarriage, this
	 * method behaves like firstCarriage, i.e., it returns the first carriage
	 * in the train, if any.
	 * NB: When combined with method firstCarriage, this method gives us a simple
	 * ability to iteratively examine each of the train's carriages.
	 * 
	 * @return the train's next carriage after the one returned by the immediately
	 * preceding call to either firstCarriage or nextCarriage, or null if there
	 * is no such carriage
	 * @throws TrainException
	 */
	public RollingStock nextCarriage() {
		if (train.isEmpty()) { // Return null - no Carriages
			return null;
		} else if (iterator > train.size() - 1) { // Out of range?
			return null;
		} else {
			iterator += 1;
			return train.get(iterator);
		}
	}
	
	/**
	 * Returns the total number of passengers currently on the train,
	 * counting all passenger cars
	 * 
	 * @return the number of passengers on the train
	 */
	public int numberOnBoard() {
		int passengers = 0;
		
		for (int i = 0; i < train.size(); i++) { // Loop through train
			if (train.elementAt(i) instanceof PassengerCar) { // Passenger Carriage
				passengers += ((PassengerCar)train.elementAt(i)).numberOnBoard();
			}
		}

		return passengers;
	}
	
	/**
	 * Returns the total number of seats on the train (whether occupied or not),
	 * counting all passenger cars
	 * 
	 * @return the number of seats on the train
	 */
	public int numberOfSeats() {
		int seats = 0;
		
		for (int i = 0; i < train.size(); i++) { // Loop through train
			if (train.elementAt(i) instanceof PassengerCar) { // Passenger Carriage
				seats += ((PassengerCar)train.elementAt(i)).numberOfSeats();
			}
		}
		
		return seats;
	}
	
	/**
	 * Adds the given number of people to passenger carriages on the train.
	 * We do not specify where the passengers must sit, so they can be
	 * allocated to any vacant seat in any passenger car
	 * 
	 * @param newPassengers - the number of people wish to board the train
	 * @return the number of people who were unable to board the train because
	 * they couldn't get a seat
	 * @throws TrainException - if the number of new passengers is negative
	 */
	public int board(int newPassengers)
		throws TrainException {
		if (newPassengers < 0) {
			throw new TrainException("Cannot board negative passengers.");
		}
		
		/* Iterate through train. Push new passengers into first available passenger
		 * carriage. Returned passengers is new total */
		for (int i = 0; i < train.size(); i++) { // Loop through train
			if (train.elementAt(i) instanceof PassengerCar) { // Passenger Carriage
				newPassengers = ((PassengerCar)train.elementAt(i)).board(newPassengers);
			}
		}
		
		return newPassengers; // Return passengers who didn't find a seat
	}
	
	/**
	 * Returns whether or not the train is capable of moving. A train can move if
	 * its locomotive's pulling power equals or exceeds the train's total weight
	 * (including the locomotive itself).
	 * In the degenerate case of a "train" which doesn't have any rolling stock
	 * at all yet, the method returns true.
	 * 
	 * @return true if the train can move (or contains no carriages), false otherwise
	 */
	public boolean canMove() {
		int totalWeight = 0;
		
		if (train.isEmpty()) { // Return true - no Carriages
			return true;
		}
		
		// Find totalWeight of train
		for (int i = 0; i < train.size(); i++) { // Loop through train
			totalWeight += train.elementAt(i).getGrossWeight();
		}
		
		// Locomotive power VS. total train weight
		if (train.firstElement() instanceof Locomotive) {
			if (((Locomotive)train.firstElement()).power() > totalWeight) {
				return true;
			} else {
				return false;
			}
		} else {
			// First object not Locomotive. Train incorrectly configured?
			return true;
		}
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
		if (numberOnBoard() > 0) {
			throw new TrainException("Cannot perform action with " +
					"passengers on board.");
		} else {
			train.addElement(newCarriage); // Push newCarriage to back of train
		}
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
		if (numberOnBoard() > 0) {
			throw new TrainException("Cannot perform action with " +
				"passengers on board.");
		} else if (train.isEmpty()){
			throw new TrainException("No Carriages to remove.");
		} else {
			train.remove(train.lastElement());
		}
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
		// Iterate through train vector, calling ToString() for each.
		// toString override
		return "ERROR";
	}

	/**
	 * Does DepartingTrain need a main method?
	 * 
	 * @param args
	 */
	/* public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Test comment, for merging with live repo
	} */

}
