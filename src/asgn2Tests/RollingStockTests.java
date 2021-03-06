package asgn2Tests;

import org.junit.Test;
import static org.junit.Assert.*;
import asgn2RollingStock.*;
import asgn2Exceptions.TrainException;

/**
 * 
 * @author Grant O'Meara
 *
 */
public class RollingStockTests {
	
	private RollingStock testRollingStock;
	public static final int ZERO = 0;
	public static final int LOCO_WEIGHT = 90;
	public static final int PASSENGER_WEIGHT = 50;
	public static final int FREIGHT_WEIGHT = 40;
	public static final String LOCO_CLASS = "5E";
	public static final int PASSENGER_SEATS = 5;
	public static final String FREIGHT_TYPE = "G";
	
	
	@Test
	public void testConstructorWithValidWeight() throws TrainException{
		testRollingStock = new Locomotive(LOCO_WEIGHT, LOCO_CLASS);
		assertTrue(LOCO_WEIGHT == testRollingStock.getGrossWeight());
	}
	/**
	 * A constant of value zero is used to test that an invalid gross
	 * weight of a locomotive throws an exception, as zero is not a valid
	 * weight
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testLocoConstructorWithInvalidWeight() throws TrainException {
		testRollingStock = new Locomotive(ZERO, LOCO_CLASS);
	}
	
	/**
	 * Exceptional test - A constant of value zero is used to test that 
	 * an invalid gross weight of a passenger car throws an exception, as
	 * zero is not a valid weight
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testPassengerConstructorWithInvalidWeight() throws TrainException {	
		testRollingStock = new PassengerCar(ZERO, PASSENGER_SEATS);
	}
	
	/**
	 * Exceptional test -A constant of value zero is used to test that an invalid gross
	 * weight of a freight car throws an exception, as zero is not a valid weight
	 * 
	 * @throws TrainException
	 */	
	@Test (expected = TrainException.class)
	public void testFreightConstructorWithInvalidWeight() throws TrainException {	
		testRollingStock = new FreightCar(ZERO, FREIGHT_TYPE);		
	}
	
	/**
	 * Exceptional test - tests that an exception is thrown when
	 * an invalid char is put as the power in the classification string.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testInvalidLocomotivePower() throws TrainException {
		String invalidPower = "0E";
		testRollingStock = new Locomotive(LOCO_WEIGHT, invalidPower);
	}
	
	/**
	 * Exceptional test - tests that an exception is thrown when
	 * an invalid char is put as the engine type in the classification
	 * string.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testInvalidLocomotiveType() throws TrainException {
		String invalidType = "1A";
		testRollingStock = new Locomotive(LOCO_WEIGHT, invalidType);
	}
	
	/**
	 * Exceptional test - tests that an exception is thrown when a
	 * negative number of passengers are alighted.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testInvalidNumberAlightingNegative() throws TrainException {
		int numAlighting = -1, passengers = 5;
		testRollingStock = new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS);
		((PassengerCar)testRollingStock).board(passengers);
		((PassengerCar)testRollingStock).alight(numAlighting);
	}
	
	/**
	 * Exceptional test - tests than an exception is thrown when the
	 * number of passengers to be alighed is greater than the number
	 * of passengers on the train.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testInvalidNumberAlightingTooMany() throws TrainException {
		int numAlighting = 6, passengers = 5;
		testRollingStock = new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS);
		((PassengerCar)testRollingStock).board(passengers);
		((PassengerCar)testRollingStock).alight(numAlighting);
	}
	
	/**
	 * Boundary case - tests for more people boarding than there
	 * are seats, which does not throw an exception but instead
	 * only boards as many people as there are free seats.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testTooManyBoarding() throws TrainException {
		int passengers = 50;
		testRollingStock = new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS);
		((PassengerCar)testRollingStock).board(passengers);
	}
	
	/**
	 * Exceptional case - tests for a negative number of passengers 
	 * attempting to board the carriage
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testInvalidNumberBoarding() throws TrainException {
		int passengers = -1;
		testRollingStock = new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS);
		((PassengerCar)testRollingStock).board(passengers);
	}
	
	/**
	 * Exceptional case - tests for a negative number of seats that have
	 * been assigned in the carriage.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testInvalidNumberOfSeats() throws TrainException {
		int numSeats = -1;
		testRollingStock = new PassengerCar(PASSENGER_WEIGHT, numSeats);
	}
	
	/*@Test
	public void locomotivePowerTooLow() throws TrainException {
		int locoWeight = 250;
		String locoClass = "2D";
		testRollingStock = new Locomotive(locoWeight, locoClass);	
	}*/
	
	/**
	 * Normal case - tests that the toString() method in the locomotive
	 * class returns the string as intended.
	 * 
	 * @throws TrainException
	 */
	@Test 
	public void testLocomotivePrintsCorrectly() throws TrainException{
		String locoClass = "5D";
		testRollingStock = new Locomotive(LOCO_WEIGHT, locoClass);
		assertEquals(((Locomotive)testRollingStock).toString(), "Loco(5D)");		
	}
	
	/**
	 * Normal case - tests that the toString method for the passengerCar
	 * returns correctly. This is done by running the code correctly and
	 * predicting the results.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCarPrintsCorrectly() throws TrainException {
		int numAlighting = 2, passengers = 5, expectedPassengers = 3;
		testRollingStock = new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS);
		((PassengerCar)testRollingStock).board(passengers);
		((PassengerCar)testRollingStock).alight(numAlighting);
		String carriageString = ((PassengerCar)testRollingStock).toString();
		assertEquals(carriageString, "Passenger(" + 
				expectedPassengers + "/" + PASSENGER_SEATS + ")");
	}
	
	/**
	 * Normal case - tests that the toString method for the passengerCar
	 * returns correctly. This is done by running the code correctly and
	 * predicting the results.
	 * 
	 * 
	 * @throws TrainException
	 **/
	@Test
	public void testFreightCarPrintsCorrectly() throws TrainException {
		//int numAlighting = 2, passengers = 5, expectedPassengers = 3;
		testRollingStock = new FreightCar(FREIGHT_WEIGHT, FREIGHT_TYPE);
		String freightString = ((FreightCar)testRollingStock).toString();
		assertEquals(freightString, "Freight(" + FREIGHT_TYPE+ ")");
	}
	
	/**
	 * Normal case (boundary case?) - tests that the code can handle nobody
	 * being removed from the train.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testNobodyGetsOff() throws TrainException {
		int numAlighting = 0, passengers = 5, onBoard;
		testRollingStock = new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS);
		((PassengerCar)testRollingStock).board(passengers);
		((PassengerCar)testRollingStock).alight(numAlighting);
		onBoard = ((PassengerCar)testRollingStock).numberOnBoard();
		assertEquals(onBoard, passengers);
	}
	
	/**
	 * Normal case (boundary case?) - tests that the code can handle nobody
	 * boarding the train.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testNobodyGetsOn() throws TrainException {
		//onBoard is set to 1 to prove that the code performs as expected
		int passengers = 0;
		testRollingStock = new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS);
		((PassengerCar)testRollingStock).board(passengers);
		int onBoard = ((PassengerCar)testRollingStock).numberOnBoard();
		assertEquals(onBoard, passengers);
	}
	
	/**
	 * Normal case - tests that the code allows zero seats to be
	 * allocated.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testNoSeatsAllowed() throws TrainException {
		int numSeats = 0, expected = 0;
		testRollingStock = new PassengerCar(PASSENGER_WEIGHT, numSeats);
		assertEquals(((PassengerCar)testRollingStock).numberOfSeats(), expected);
		
	}
	
	/**
	 * Normal case - tests that the number of seats is remembered after assignment
	 * by comparing the expected amount the the integer returned from numberOfSeats().
	 * @throws TrainException
	 */
	@Test
	public void testNumberOfSeatsRemembered() throws TrainException {
		int expectedSeats = 5;
		testRollingStock = new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS);
		int numSeats = ((PassengerCar)testRollingStock).numberOfSeats();
		assertEquals(numSeats, expectedSeats);
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
	public void testPassengersAccumulate() throws TrainException {
		int passengers = 2, expectedPassengers = 4;
		testRollingStock = new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS);
		//board two lots of 2 passengers
		((PassengerCar)testRollingStock).board(passengers);
		((PassengerCar)testRollingStock).board(passengers);
		int onBoard = ((PassengerCar)testRollingStock).numberOnBoard();
		assertEquals(onBoard, expectedPassengers);
	}
	
	/**
	 * Normal case - tests that passengers can aligt by comparing the
	 * actual number on board (with numberOnBoar()) against the expected 
	 * number of passengers on board (expected passengers).
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testPassengersCanAlight() throws TrainException {
		int numAlighting = 2, passengers = 5, expectedPassengers = 3;
		testRollingStock = new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS);
		((PassengerCar)testRollingStock).board(passengers);
		((PassengerCar)testRollingStock).alight(numAlighting);
		int onBoard = ((PassengerCar)testRollingStock).numberOnBoard();
		assertEquals(onBoard, expectedPassengers);
	}
	
	/**
	 * Normal case - compares the expected pulling power of the
	 * locomotive (calculated prior) with the actual pulling power
	 * that was calculated in the Locomotive class.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testPowerCalculatedCorrectly() throws TrainException {
		//for simplicity I assigned the locomotive power to a variable
		int locoWeight = 120, locoPower = 7;
		int expectedPower = ((locoPower * 100));
		String locoClass = "7S";
		testRollingStock = new Locomotive(locoWeight, locoClass);
		int pullingPower = ((Locomotive)testRollingStock).power();
		assertEquals(pullingPower, expectedPower);
		
	}
	
	/**
	 * Exceptional case - tests that an exception is thrown when
	 * the program attempts to alight more passengers that are 
	 * currently on the train.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testTooManyAlighting() throws TrainException {
		int numAlighting = 5, passengers = 4;
		testRollingStock = new PassengerCar(PASSENGER_WEIGHT, PASSENGER_SEATS);
		((PassengerCar)testRollingStock).board(passengers);
		((PassengerCar)testRollingStock).alight(numAlighting);
	}

	/**
	 * Normal case - tests that valid freight car types are assigned without
	 * errors
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testTalidFreightCarTypes() throws TrainException {
		String freightType = "G";
		testRollingStock = new FreightCar(FREIGHT_WEIGHT, freightType);
		String actualType = ((FreightCar)testRollingStock).toString();
		assertEquals(actualType, "Freight(G)");
	}
	
	/**
	 * Normal case - tests that valid loco types are assigned without 
	 * errors
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testTalidLocomotiveCodes() throws TrainException {
		String locoClass = "5D";
		testRollingStock = new Locomotive(LOCO_WEIGHT, locoClass);
		String actualClass = ((Locomotive)testRollingStock).toString();
		assertEquals(actualClass, "Loco(5D)");
	}
}