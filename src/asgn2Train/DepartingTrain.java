package asgn2Train;

import asgn2RollingStock.*;
import asgn2Exceptions.TrainException;
import java.util.Vector;

/**
 * @author Steven Lomas
 */

public class DepartingTrain extends Object {
	
	private Vector<RollingStock> train = new Vector<RollingStock>();
	private int iterator;

	/**
	 * Constructs a (potential) train object containing no carriages (yet)
	 */
	public DepartingTrain() {
		iterator = 0;
		
		// OLD - this.nextCar = new PassengerCar(50, 24);
		// OLD - this.firstCar = new Locomotive(100, "6D");
	}

	/**
	 * Returns the first carriage on the train (which must be a locomotive).
	 * Special value null is returned if there are no carriages on the train at all.
	 * 
	 * @return the first carriage in the train, or null if there are no carriages
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
	 */
	public RollingStock nextCarriage() {
		if (train.isEmpty()) { // Return null - no Carriages
			return null;
		} else if (iterator + 1 > train.size() - 1) { // Out of range - no carriage
			iterator = 0; // Reset counter
			return null; // Return null
		} else {
			iterator += 1;
			return train.elementAt(iterator);
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
	 * @throws TrainException if the number of new passengers is negative
	 */
	public int board(int newPassengers)
		throws TrainException {
		if (newPassengers < 0) {
			throw new TrainException("Cannot board negative passengers - DepartingTrain");
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
	public boolean trainCanMove() {
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
		} else { // First Carriage not Locomotive. Train incorrectly configured!
			return false;
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
		
		if (newCarriage instanceof Locomotive) { // Try to shunt Locomotive
			if (firstCarriage() instanceof Locomotive) { // First car is locomotive
				throw new TrainException("Cannot shunt Locomotive - "+
						"Locomotive already exists.");
			} else if (train.isEmpty()) { // No carriages, can shunt Locomotive
				train.addElement(newCarriage); // Push newCarriage to end of train
			} else { // Non-Locomotive is first carriage...
				throw new TrainException("Cannot shunt Locomotive - " +
						"train incorrectly configured.");
			}
		} else if (newCarriage instanceof PassengerCar) { // Try to shunt PassengerCar
			if (firstCarriage() instanceof Locomotive) { // First car is a locomotive
				if (train.lastElement() instanceof FreightCar) { // FreightCar
					throw new TrainException("Cannot shunt Passenger "+
							"Carriage - train incorrectly configured.");
				} else if (numberOnBoard() > 0) { // Passengers on board
					throw new TrainException("Cannot shunt with passengers " +
							"on board - addCarriage(PassengerCar)");
				} else { // Has Locomotive (at front) and no Freight Carriages (at rear) or Passengers
					train.addElement(newCarriage); // Push newCarriage to end of train
				}
			} else { // First Car is NOT a Locomotive
				throw new TrainException("Cannot shunt Passenger Carriage - "+
						"train incorrectly configured.");
			}
		} else if (newCarriage instanceof FreightCar) { // Try to shunt FreightCar
			if (firstCarriage() instanceof Locomotive) { // First car is a locomotive
				if (numberOnBoard() > 0) { // Passengers on board
					throw new TrainException("Cannot shunt with passengers " +
							"on board - addCarriage(FreightCar)");
				} else { // Has Locomotive (at front) and no Passengers
					train.addElement(newCarriage); // Push newCarriage to end of train
				}
			} else { // First Car is NOT a Locomotive
				throw new TrainException("Cannot shunt Freight Carriage - "+
						"train incorrectly configured.");
			}
		} else { // Invalid RollingStock type
			throw new TrainException("Unknown Carriage type.");
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
		if (numberOnBoard() > 0) { // Check for boarded Passengers
			throw new TrainException("Cannot perform action with " +
				"passengers on board - removeCarriage");
		} else if (train.isEmpty()){ // Check for empty train
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
	 * description may be:
	 * "Loco(6D)-Passenger(13/24)-Passenger(16/16)-Freight(G)".
	 * 
	 * In the degenerate case of a "train" with no carriages,
	 * the empty string is returned.
	 * 
	 * @overrides toString in class Object
	 * @returns a human-readable description of the entire train
	 */
	public String toString() {
		String output = "";
		
		for (int i = 0; i < train.size(); i++) { // Loop through train
			output += train.elementAt(i).toString();
			if (i + 1 < train.size()) { // Add break between carriages
				output += "-";
			}
		}
		
		return output;
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
