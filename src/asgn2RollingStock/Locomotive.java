package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author Grant O'Meara
 * @author Steven Lomas
 */
public class Locomotive extends RollingStock {
	
	private Integer locoPower;
	private String locoClass;
	//private char locoEngineType;
	private char[] locoClassChar; //char array to split up the classification string

	/**
	 * Constructs a new locomotive object with a fixed gross weight
	 * and classification code
	 * 
	 * @param grossWeight - the locomotive's (fully-laden) weight in tonnes
	 * @param classification - the locomotive's two-character classification code
	 * @throws TrainException if the locomotive's weight is not strictly positive
	 * or if its classification code is invalid
	 */
	public Locomotive(Integer grossWeight, String classification)
     throws TrainException {
		super(grossWeight);
		
		//90 tonnes was chosen as it was mentioned to be the average min 
		//weight of a locomotive in the assignment spec
		if (grossWeight < 90) {
			throw new TrainException("Min weight of Locomotive is 90t");
		}

		locoClass = classification;
		locoClassChar = locoClass.toCharArray();
		char engineType = locoClassChar[1];
		locoPower = (Integer.parseInt(String.valueOf(locoClassChar[0])) * 100);

		/* More exception tests below */
		//there should only be 2 characters in the classification string
		if (locoClassChar.length != 2) {
			throw new TrainException("Invalid classification. Locomotive must have letter " +
					"(engine type) and integer (power class) within its classification.");
		}

		if (locoPower < 100) { // Locopower less than 100 (classification 1)
			throw new TrainException("Invalid power classification, must be greater than 1.");
		}
		
		if (engineType != 'E' &&
			engineType != 'D' &&
			engineType != 'S') { // Not Valid engine classification
			throw new TrainException("Invalid engine classification, must be E, D or S.");
		}

		//end exception tests

		/* switch (engineType) {
	        case 'D': locoEngine = "Diesel";
	        	break;
	        case 'S': locoEngine = "Steam";
         		break;
	        case 'E': locoEngine = "Electric";
	        	break;           
		} */
	}

	/**
	 * Returns how much total weight the locomotive can pull (including itself)
	 * 
	 * @return the locomotive's "pulling power" in tonnes
	 */
	public int power() {
		return locoPower;
	}

	/**
	 * Returns a human-readable description of the locomotive. This has the form
	 * "Loco(x)" where x is the locomotive's two-character classification code
	 * 
	 * @return a human-readable description of the locomotive
	 */
	@Override
	public String toString() {
		return "Loco(" + this.locoClass + ")";
		/* return "Locmotive weighs " + locoWeight + "tonnes. It has a(n) " +
				locoEngine + " engine that has a power output of  " + locoPower; */
	}
	
}
