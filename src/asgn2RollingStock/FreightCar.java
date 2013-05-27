package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author Steven Lomas
 */
public class FreightCar extends RollingStock {
	
	private String goodsType;
	
	/**
	 * Constructor for a freight car object.
	 * @param grossWeight - the freight car's gross weight (fully-laden), in tonnes
	 * @param goodsType - the type of goods the car is designed to carry (either "G", "R" or "D")
	 * @throws TrainException - if the gross weight is not positive or if the goods' type is invalid
	 */
	public FreightCar(int grossWeight, String goodsType)
     throws TrainException {
		super(grossWeight); // Implicit super constructor
		
		if (goodsType != "G" ||
			goodsType != "R" ||
			goodsType != "D") {
			throw new TrainException("Invalid goodsType.");
		}
		
		this.goodsType = goodsType; // ELSE?
	}
	
	/**
	 * Returns the type of goods this carriage was designed to carry. 
	 * (Simulates someone checking the label on the freight car to 
	 * determine what's inside.)
	 * @return - the goodsType (G", "R" or "D")
	 */
	public String goodsType() {
		return goodsType;
	}
	
	/**
	 * Returns a human-readable description of the freight car. This has 
	 * the form "Freight(x)" where x is a character ("G", "R" or "D") 
	 * indicating the type of goods the car is designed to carry.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
		{
		return "Freight(" + goodsType() + ")";
	}
	
}
