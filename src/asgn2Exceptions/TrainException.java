package asgn2Exceptions;

/**
 * @author Grant O'Meara
 */
public class TrainException extends Exception {
	/*
	 * Added this because compiler complained without one.
	 * Don't think it serves any useful purpose though.
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * constructor for train exception message
	 * @param an informative message describing the cause of the problem
	 */
	public TrainException(String message) {
	}
	
}
