package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author 
 */
public class Locomotive extends RollingStock {
	private int locoPower;
	private String locoEngine;
	
	public Locomotive(int grossWeight, String classification)
     throws TrainException {
		super(grossWeight);
		
		if (this.getGrossWeight() < 90) {
			throw new TrainException("Locomotive may not have a gross " +
					"weight that is less than 90 tonnes.");
		}
		
	}
	
	public int power() {
		int pullingPower = (locoPower * 100) - this.getGrossWeight();
		return pullingPower;
	}

	@Override
	public String toString() {
		return locoPower + locoEngine;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}



}