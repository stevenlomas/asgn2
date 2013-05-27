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
	
	/**
	 * Adds the given number of new passengers to the number on board
	 * the carriage. If there are too many new passengers for the number
	 * of spare seats the left over people are not boarded
	 * 
	 * @param newPassengers - the number of people who wish to board
	 * the carriage
	 * @return the number of people who were unable to board the carriage
	 * because they couldn't get a seat
	 * @throws TrainException - if the number of new passengers is negative
	 */
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
		return "Passenger(" + this.numberOnBoard() + "/" +
				this.numberOfSeats() + ")";
	}
	
	/**
	 * @param args
	 */
	/* public static void main(String[] args) {
		// TODO Auto-generated method stub

	} */
}
