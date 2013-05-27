package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * 
 * @author Grant O'Meara
 * @author Steven Lomas
 * 
 */
public abstract class RollingStock extends Object {
	
	private int grossWeightPrivate;
	
	/*
	 * Constructs a railway carriage with a specific gross weight 
	 * (i.e., the carriage's weight when fully laden). We assume 
	 * that this weight does not change once shunting operations 
	 * have begun. (Freight carriages are assumed to arrive at the
	 * marshalling yard already loaded, and we consider the weight 
	 * of passengers to be negligible compared to the weight of the
	 * carriage itself.)
	 * 
	 * We require a railway carriage to have at least some weight, but put
	 * no arbitrary upper limit on its weight. (In practice, though, 
	 * locomotives normally weigh around 90 to 180 tonnes, passenger carriages
	 * weigh around 50 to 100 tonnes and laden freight cars weigh around 40 
	 * to 90 tonnes.)
	 */
	public RollingStock(int grossWeight)
            throws TrainException {
		
		if (grossWeight < 0) {
			throw new TrainException("The gross weight for the rolling stock " +
					"must not be less than 0");
		}
		
		this.grossWeightPrivate = grossWeight;
	}
	
	/*
	 * returns the carriage's gross weight in tonnes
	 */
	public int getGrossWeight() {	
		return this.grossWeightPrivate;
	}
	
	/*
	 * Returns a human-readable description of this railway carriage.
	 * In the context of the assignment, this method provides you with a 
	 * textual description of the carriage which you can use to display 
	 * the current train configuration in your user interface.

	 * (Note for Javaphiles: The root Object class already provides a 
	 * default toString method for all classes, so the purpose of this 
	 * abstract method is to override the default one and force us to 
	 * implement a toString method in all subclasses of RollingStock.)
	 */
	@Override
	public abstract String toString();

	/**
	 * @param args
	 */
	/* public static void main(String[] args) {
		// TODO Auto-generated method stub

	} */

}
