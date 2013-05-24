package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * 
 * @author 
 *
 */
public class PassengerCar extends RollingStock {

	public PassengerCar(int grossWeight, int numberOfSeats)
			throws TrainException {
		super(grossWeight);
	}
	
	public int board(int newPassengers)
			throws TrainException {	
		return 0;		
	}
	
	public void alight(int departingPassengers)
            throws TrainException {
	}
	
	public int numberOnBoard() {
		return 0;
	}
	
	public int numberOfSeats() {
		return 0;
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
