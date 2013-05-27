package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author Grant O'Meara
 */
public class Locomotive extends RollingStock {
	private Integer locoPower, locoWeight;
	private String locoClass, locoEngine;
	//private char locoEngineType;
	private char[] locoClassChar; //char array to split up the classification string

	public Locomotive(Integer grossWeight, String classification)
     throws TrainException {
		super(grossWeight);

		locoWeight = grossWeight; 
		locoClass = classification;
		locoClassChar = locoClass.toCharArray();
		char engineType = locoClassChar[1];
		locoPower = (Integer.parseInt(String.valueOf(locoClassChar[0])) * 100);

		/* Exception tests below */
		//90 tonnes was chosen as it was mentioned to be the average min 
		//weight of a locomotive in the assignment spec
		if (this.locoWeight < 90) {
			throw new TrainException("Locomotive may not have a gross " +
					"weight that is less than 90 tonnes.");
		}
		//there should only be 2 characters in the classification string
		if (locoClassChar.length != 2) {
			throw new TrainException("Invalid classification. Locomotive must have letter " +
					"(engine type) and integer (power class) within its classification.");
		}
		//if the locopower is less than 100 that means the power classification was less than 1
		//and therefore invalid
		if (locoPower < 100) {
			throw new TrainException("Invalid power classification, must be greater than 1.");
		}
		//checks if the engine classification was one of the valid types
		if (engineType != 'E' && engineType != 'D' && engineType != 'S') {
			throw new TrainException("Invalid engine classification, must be E, D or S.");
		}

		//end exception tests

		switch (engineType) {
	        case 'D': locoEngine = "Diesel";
	        	break;
	        case 'S': locoEngine = "Steam";
         		break;
	        case 'E': locoEngine = "Electric";
	        	break;           
		}	
	}

	public int power() {
		return locoPower;
	}

	@Override
	public String toString() {
		return "Loco(" + this.locoClass + ")";
		/* return "Locmotive weighs " + locoWeight + "tonnes. It has a(n) " +
				locoEngine + " engine that has a power output of  " + locoPower; */
	}
}
