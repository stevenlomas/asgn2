package asgn2Exceptions;

/**
 * @author Grant O'Meara
 * @author Steven Lomas
 */
public class TrainException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Empty exception
	 */
	public TrainException() {
		super();
	}
	/**
	 * Constructs a new TrainException object
	 * 
	 * @param message - an informative message describing the
	 * cause of the problem
	 */
	public TrainException(String message) {
		super("Train Exception: " + message);
	}
	
}
