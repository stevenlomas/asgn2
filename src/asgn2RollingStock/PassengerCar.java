package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author Steven Lomas
 */
public class PassengerCar extends RollingStock {
	
	private int currentPassengers;
	private int seats;

	/**
	 * Constructs a passenger car with a known weight and a fixed number
	 * of seats
	 * (We allow a passenger car to have zero seats, although
	 * it would not be very useful)
	 * 
	 * @param grossWeight - the carriage's gross weight in tonnes
	 * (ignoring the weight of passengers, which we treat as negligible)
	 * @param numberOfSeats - how many seats are available in the carriage
	 * @throws TrainException if the gross weight is not positive or
	 * if the number of seats is negative
	 */
	public PassengerCar(int grossWeight, int numberOfSeats)
			throws TrainException {
		super(grossWeight);
		this.seats = numberOfSeats; // Specified seating capacity
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
	 * @throws TrainException if the number of new passengers is negative
	 */
	public int board(int newPassengers)
			throws TrainException {
		if (newPassengers < 0) {
			throw new TrainException("Cannot board negative passengers - PassengerCar");
		}
		
		// Find free number of seats
		int freeSeats = seats - currentPassengers;
		
		if (freeSeats >= newPassengers) { // Can fit ALL passengers, return none
			currentPassengers += newPassengers;
			return 0;
		} else { // Cannot all fit, greater than freeSeats
			currentPassengers += freeSeats; // Fill free seats
			return newPassengers - freeSeats; // Return passengers minus seated
		}		
	}
	
	/**
	 * Removes the given number of passengers from this carriage.
	 * Attempting to remove more passengers than are on board is not allowed
	 * 
	 * @param departingPassengers - the number of passengers alighting
	 * from the carriage
	 * @throws TrainException if the number of departing passengers is
	 * negative or if the number of departing passengers exceeds the number
	 * on board
	 */
	public void alight(int departingPassengers)
            throws TrainException {
		if (departingPassengers > currentPassengers) {
		throw new TrainException("May not remove more passengers" +
			" than are currently boarded.");
		} else if (departingPassengers < 0) {
			throw new TrainException("May not remove negative passengers.");
		} else {
		currentPassengers -= departingPassengers;
		}
	}
	
	/**
	 * Returns the number of passengers currently on board this carriage
	 * 
	 * @return the number of passengers on board
	 */
	public int numberOnBoard() {
		return currentPassengers;
	}
	
	/**
	 * Returns the number of seats installed on this carriage
	 * 
	 * @return the number of seats on this carriage
	 */
	public int numberOfSeats() {
		return seats;
	}

	/**
	 * Returns a human-readable description of the passenger car.
	 * This has the form "Passenger(x/y)" where x is the number of
	 * passengers currently on board and y is the number of seats
	 * in the carriage
	 * 
	 * @return a human-readable description of the passenger car
	 */
	@Override
	public String toString() {
		return "Passenger(" + this.numberOnBoard() + "/" +
				this.numberOfSeats() + ")";
	}
	
}
