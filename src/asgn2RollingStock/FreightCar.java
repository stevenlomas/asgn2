package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * 
 * @author 
 *
 */
public class FreightCar extends RollingStock {
	/*
	 * Constructor for a freight car object.
	 * @param grossWeight
	 * @param goodsType
	 * @throws TrainException
	 */
	public FreightCar(int grossWeight, String goodsType)
     throws TrainException {
		//implicit super constructor
		super(grossWeight);
	}
	
	/*
	 * Returns the type of goods this carriage was designed to carry. 
	 * (Simulates someone checking the label on the freight car to 
	 * determine what's inside.)
	 * @return - the goodsType (G", "R" or "D")
	 */
	public String goodsType() {
		
		return "returned string goes here";
	}
	
	/*Returns a human-readable description of the freight car. This has 
	 * the form "Freight(x)" where x is a character ("G", "R" or "D") 
	 * indicating the type of goods the car is designed to carry.
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return "";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
