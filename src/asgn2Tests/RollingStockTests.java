package asgn2Tests;

import java.util.Vector;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import asgn2RollingStock.RollingStock;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.PassengerCar;
import asgn2Exceptions.TrainException;

/**
 * 
 * @author Grant O'Meara
 *
 */
public class RollingStockTests {
	
	private Vector<RollingStock> testRollingStock = new Vector<RollingStock>();
	public static final int ZERO = 0;
	public static final int LOCO_WEIGHT = 90;
	public static final int PASSENGER_WEIGHT = 50;
	public static final int FREIGHT_WEIGHT = 40;
	public static final String LOCO_CLASS = "5E";
	public static final int PASSENGER_SEATS = 5;
	public static final String FREIGHT_TYPE = "G";
	
	//public static final String LOCO_CLASS = "1E";
	//public static final String INVALID_LOCO_POWER = "0E";
	//public static final String INVALID_LOCO_TYPE = "1F";
	
	/* 
	 * may not need this, depends on the implementation
	 */
	/*@Before
	public void setUp() {
		testRollingStock = new RollingStock(0);
	}*/
	
	/**
	 * A constant of value zero is used to test that an invalid gross
	 * weight of a locomotive throws an exception, as zero is not a valid
	 * weight
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void invalidGrossWeightLoco() throws TrainException {
		testRollingStock.addElement(new Locomotive(ZERO, LOCO_CLASS));
	}
	
	/**
	 * A constant of value zero is used to test that an invalid gross
	 * weight of a passenger car throws an exception, as zero is not a 
	 * valid weight
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void invalidGrossWeightPassenger() throws TrainException {	
		testRollingStock.addElement(new PassengerCar(ZERO, PASSENGER_SEATS));
	}
	
	/**
	 * A constant of value zero is used to test that an invalid gross
	 * weight of a freight car throws an exception, as zero is not a valid weight
	 * 
	 * @throws TrainException
	 */	
	@Test (expected = TrainException.class)
	public void invalidGrossWeightFreight() throws TrainException {	
		testRollingStock.addElement(new FreightCar(ZERO, FREIGHT_TYPE));		
	}
	
	@Test (expected = TrainException.class)
	public void invalidLocomotivePower() throws TrainException {
		String invalidPower = "0E";
		testRollingStock.addElement(new Locomotive(LOCO_WEIGHT, invalidPower));
	}
	
	@Test (expected = TrainException.class)
	public void invalidLocomotiveType() throws TrainException {
		String invalidType = "1A";
		testRollingStock.addElement(new Locomotive(LOCO_WEIGHT, invalidType));
	}
	
	@Test (expected = TrainException.class)
	public void invalidNumberAlightingNegative() throws TrainException {
		int numAlighting = -1, passengers = 5;
		testRollingStock.addElement(new Locomotive(LOCO_WEIGHT, LOCO_CLASS));
		testRollingStock.addElement(new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS));
		((PassengerCar)testRollingStock.elementAt(1)).board(passengers);
		((PassengerCar)testRollingStock.elementAt(1)).alight(numAlighting);
	}
	
	@Test (expected = TrainException.class)
	public void invalidNumberAlightingTooMany() throws TrainException {
		int numAlighting = 6, passengers = 5;
		testRollingStock.addElement(new Locomotive(LOCO_WEIGHT, LOCO_CLASS));
		testRollingStock.addElement(new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS));
		((PassengerCar)testRollingStock.elementAt(1)).board(passengers);
		((PassengerCar)testRollingStock.elementAt(1)).alight(numAlighting);
	}
	
	/**
	 * Exceptional case - tests for a negative number of passengers 
	 * attempting to board the carriage
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void invalidNumberBoarding() throws TrainException {
		int passengers = -1;
		testRollingStock.addElement(new Locomotive(LOCO_WEIGHT, LOCO_CLASS));
		testRollingStock.addElement(new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS));
		((PassengerCar)testRollingStock.elementAt(1)).board(passengers);
	}
	
	/**
	 * Exceptional case - tests for a negative number of seats that have
	 * been assigned in the carriage.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void invalidNumberOfSeats() throws TrainException {
		int numSeats = -1;
		testRollingStock.addElement(new PassengerCar(PASSENGER_WEIGHT, numSeats));
	}
	
	@Test
	public void locomotivePowerTooLow() throws TrainException {
		//test
	}
	
	/**
	 * Normal case - tests that the toString() method in the locomotive
	 * class returns the string as intended.
	 * 
	 * @throws TrainException
	 */
	@Test 
	public void locomotivePrintsCorrectly() throws TrainException{
		String locoClass = "5D";
		testRollingStock.addElement(new Locomotive(LOCO_WEIGHT, locoClass));
		assertEquals(testRollingStock.elementAt(0).toString(), "Loco(5D)");		
	}
	
	/**
	 * Normal case (boundary case?) - tests that the code can handle nobody
	 * being removed from the train.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void nobodyGetsOff() throws TrainException {
		int numAlighting = 0, passengers = 5, onBoard;
		testRollingStock.addElement(new Locomotive(LOCO_WEIGHT, LOCO_CLASS));
		testRollingStock.addElement(new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS));
		((PassengerCar)testRollingStock.elementAt(1)).board(passengers);
		((PassengerCar)testRollingStock.elementAt(1)).alight(numAlighting);
		onBoard = ((PassengerCar)testRollingStock.elementAt(1)).numberOnBoard();
		assertEquals(onBoard, passengers);
	}
	
	/**
	 * Normal case (boundary case?) - tests that the code can handle nobody
	 * boarding the train.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void nobodyGetsOn() throws TrainException {
		//onBoard is set to 1 to prove that the code performs as expected
		int passengers = 0;
		testRollingStock.addElement(new Locomotive(LOCO_WEIGHT, LOCO_CLASS));
		testRollingStock.addElement(new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS));
		((PassengerCar)testRollingStock.elementAt(1)).board(passengers);
		int onBoard = ((PassengerCar)testRollingStock.elementAt(1)).numberOnBoard();
		assertEquals(onBoard, passengers);
	}
	
	@Test
	public void noExtraRollingStockPublicConstructor() {
		//test
	}
	
	@Test
	public void noExtraRollingStockPublicFields() {
		//test
	}
	
	@Test
	public void noExtraRollingStockPublicMethods() {
		//test
	}
	
	@Test
	public void noOneOnBoardInitially() {
		//test
	}
	
	/**
	 * Normal case - tests that the code allows zero seats to be
	 * allocated.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void noSeatsAllowed() throws TrainException {
		int numSeats = 0;
		testRollingStock.addElement(new PassengerCar(PASSENGER_WEIGHT, numSeats));
	}
	
	@Test
	public void numberOfSeatsRemembered() {
		//test
	}
	
	/**
	 * Normal case - tests that the toString method for the passengerCar
	 * returns correctly. This is done by running the code correctly and
	 * predicting the results.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void passengerCarPrintsCorrectly() throws TrainException {
		int numAlighting = 2, passengers = 5, expectedPassengers = 3;
		testRollingStock.addElement(new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS));
		((PassengerCar)testRollingStock.elementAt(0)).board(passengers);
		((PassengerCar)testRollingStock.elementAt(0)).alight(numAlighting);
		String carriageString = ((PassengerCar)testRollingStock.elementAt(0)).toString();
		assertEquals(carriageString, "Passenger(" + 
				expectedPassengers + "/" + PASSENGER_SEATS + ")");
	}
	
	/**
	 * Normal case - tests that the number of passengers can accumulate
	 * over multiple instances of boarding by comparing a pre-set variable
	 * of the number of passengers that should be on the train, against the 
	 * actual number of passengers found with the numberOnBoard() method.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void passengersAccumulate() throws TrainException {
		int passengers = 2, expectedPassengers = 4;
		testRollingStock.addElement(new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS));
		//board two lots of 2 passengers
		((PassengerCar)testRollingStock.elementAt(0)).board(passengers);
		((PassengerCar)testRollingStock.elementAt(0)).board(passengers);
		int onBoard = ((PassengerCar)testRollingStock.elementAt(0)).numberOnBoard();
		assertEquals(onBoard, expectedPassengers);
	}
	
	/**
	 * Normal case - tests that passengers can aligt by comparing the
	 * actual number on board (with numberOnBoar()) against the expected 
	 * number of passengers on board (expected passengers).
	 * @throws TrainException
	 */
	@Test
	public void passengersCanAlight() throws TrainException {
		int numAlighting = 2, passengers = 5, expectedPassengers = 3;
		testRollingStock.addElement(new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS));
		((PassengerCar)testRollingStock.elementAt(0)).board(passengers);
		((PassengerCar)testRollingStock.elementAt(0)).alight(numAlighting);
		int onBoard = ((PassengerCar)testRollingStock.elementAt(0)).numberOnBoard();
		assertEquals(onBoard, expectedPassengers);
	}
	
	@Test
	public void powerCalculatedCorrectly() throws TrainException {
		//int locoWeight = 120;
		//String locoClass = "7S";
		testRollingStock.addElement(new Locomotive(LOCO_WEIGHT, LOCO_CLASS));
		
	}
	
	@Test
	public void tooManyAlighting() {
		//test
	}
	
	@Test
	public void validFreightCarTypes() {
		//test
	}
	
	@Test
	public void validLocomotiveCodes() {
		//test
	}
}

