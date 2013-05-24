package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * 
 * @author 
 *
 */
public class PassengerCar extends RollingStock {
	
	private int currentPassengers;
	private int seats;

	public PassengerCar(int grossWeight, int numberOfSeats)
			throws TrainException {
		super(grossWeight);
		seats = numberOfSeats; // Specified seating capacity
		currentPassengers = 0; // No passengers on new carriage
	}
	
	public int board(int newPassengers)
			throws TrainException {	
		return 0;		
	}
	
	/* Removes the given number of passengers from this carriage.
	 * Attempting to remove more passengers than are on board is not allowed. */
	public void alight(int departingPassengers)
            throws TrainException {
		/*if (departingPassengers > currentPassengers) {
		throw new TrainException("May not remove more passengers" +
			" than are currently boarded.");
		} else {
		currentPassengers -= departingPassengers
		}*/
	}
	
	public int numberOnBoard() {
		return currentPassengers;
	}
	
	public int numberOfSeats() {
		return seats;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
