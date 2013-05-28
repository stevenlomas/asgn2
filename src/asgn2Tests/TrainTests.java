package asgn2Tests;

import org.junit.Test;
import static org.junit.Assert.*;
import asgn2Train.DepartingTrain;
import asgn2RollingStock.*;
import asgn2Exceptions.TrainException;

/**
 * 
 * @author Grant O'Meara
 *
 */
public class TrainTests {
	
	public static final int LOCO_WEIGHT = 90;
	public static final int PASSENGER_WEIGHT = 50;
	public static final int FREIGHT_WEIGHT = 40;
	public static final String LOCO_CLASS = "5D";
	public static final int SEATS = 10;
	public static final String FREIGHT_TYPE = "G";
	private DepartingTrain testTrain = new DepartingTrain();
	
	/**
	 * Normal case - test case that creates a valid carriages (and train)
	 * without any errors.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void assembleValidTrain() throws TrainException {
		RollingStock loco = new Locomotive(LOCO_WEIGHT, LOCO_CLASS);
		RollingStock passenger = new PassengerCar(PASSENGER_WEIGHT, SEATS);
		RollingStock freight = new FreightCar(FREIGHT_WEIGHT, FREIGHT_TYPE);
		testTrain.addCarriage(loco);
		testTrain.addCarriage(passenger);
		testTrain.addCarriage(freight);
	}
	
	/**
	 * Normal case - test case that creates 2 different valid carriages 
	 * (and trains) without any errors, as well as testing that they 
	 * do not have the same carriages.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void assembleTwoValidTrains() throws TrainException {
		DepartingTrain testTrain2 = new DepartingTrain();
		RollingStock loco = new Locomotive(LOCO_WEIGHT, LOCO_CLASS);
		RollingStock passenger = new PassengerCar(PASSENGER_WEIGHT, SEATS);
		RollingStock freight = new FreightCar(FREIGHT_WEIGHT, FREIGHT_TYPE);
		testTrain.addCarriage(loco);
		testTrain2.addCarriage(loco);
		testTrain.addCarriage(passenger);
		testTrain2.addCarriage(passenger);
		testTrain.addCarriage(freight);
		testTrain2.addCarriage(passenger);
		assertTrue(testTrain != testTrain2);
	}

	
	/**
	 * Boundary case - tests that null is returned if nextCarriage() is 
	 * called when there are no more carriages to be iterated.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testIteratingThroughTooManyCarriages() throws TrainException {
		RollingStock loco = new Locomotive(LOCO_WEIGHT, LOCO_CLASS);
		RollingStock passenger1 = new PassengerCar(PASSENGER_WEIGHT, SEATS);
		RollingStock freight = new FreightCar(FREIGHT_WEIGHT, FREIGHT_TYPE);
		testTrain.addCarriage(loco);
		testTrain.addCarriage(passenger1);
		testTrain.addCarriage(freight);
		testTrain.firstCarriage();
		testTrain.nextCarriage();
		testTrain.nextCarriage();
		assertEquals(testTrain.nextCarriage(), null);
	}
	
	/**
	 * Exceptional case - tests that adding a freight car before
	 * a passenger car throws an exception.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void freightBeforePassengers() throws TrainException {
		RollingStock loco = new Locomotive(LOCO_WEIGHT, LOCO_CLASS);
		RollingStock passenger = new PassengerCar(PASSENGER_WEIGHT, SEATS);
		RollingStock freight = new FreightCar(FREIGHT_WEIGHT, FREIGHT_TYPE);
		testTrain.addCarriage(loco);
		testTrain.addCarriage(freight);
		testTrain.addCarriage(passenger);
	}
	
	/**
	 * Normal case - tests that adding passengers when there is no
	 * passenger car adds 0 passengers to the train
	 * 
	 * @throws TrainException
	 */
	@Test 
	public void testBoardWithoutPassengerCar() throws TrainException {
		int expectedPassengers = 0;
		RollingStock loco = new Locomotive(LOCO_WEIGHT, LOCO_CLASS);
		testTrain.addCarriage(loco);
		testTrain.board(3);
		assertEquals(testTrain.numberOnBoard(), expectedPassengers);
	}
	
	/**
	 * Exceptional case - tests that adding a negative amount
	 * of passengers throws an exception.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void negativePassengerBoarding() throws TrainException {
		RollingStock loco = new Locomotive(LOCO_WEIGHT, LOCO_CLASS);
		RollingStock passenger = new PassengerCar(PASSENGER_WEIGHT, SEATS);
		RollingStock freight = new FreightCar(FREIGHT_WEIGHT, FREIGHT_TYPE);
		testTrain.addCarriage(loco);
		testTrain.addCarriage(passenger);
		testTrain.addCarriage(freight);
		testTrain.board(-1);
	}
	
	/**
	 * Exceptional case - tests that adding anything other than a locomotive
	 * as the first car in a train throws an exception.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void noLocomotiveFirst() throws TrainException {
		RollingStock passenger = new PassengerCar(PASSENGER_WEIGHT, SEATS);
		RollingStock freight = new FreightCar(FREIGHT_WEIGHT, FREIGHT_TYPE);
		testTrain.addCarriage(passenger);
		testTrain.addCarriage(freight);
	}
	
	/**
	 * Exceptional case - tests that adding another cart after passengers
	 * have boarded the train throws an exception.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testShuntingWithPassengers() throws TrainException {
		int numPassengers = 5;
		RollingStock loco = new Locomotive(LOCO_WEIGHT, LOCO_CLASS);
		RollingStock passenger = new PassengerCar(PASSENGER_WEIGHT, SEATS);
		testTrain.addCarriage(loco);
		testTrain.addCarriage(passenger);
		testTrain.board(numPassengers);
		testTrain.addCarriage(passenger);
	}
	
	/**
	 * Exceptional case - tests that trying to remove a carriage
	 * with people still on board will throw an exception.
	 */
	@Test (expected = TrainException.class)
	public void testRemoveCarriageWithPassengers() throws TrainException {
		int numPassengers = 5;
		RollingStock loco = new Locomotive(LOCO_WEIGHT, LOCO_CLASS);
		RollingStock passenger = new PassengerCar(PASSENGER_WEIGHT, SEATS);
		testTrain.addCarriage(loco);
		testTrain.addCarriage(passenger);
		testTrain.board(numPassengers);
		testTrain.removeCarriage();
	}
	
	/**
	 * Exceptional case - tests than an exception is thrown when
	 * the user tries to remove a carriage when there are none left.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void testRemovingTooManyCarriages() throws TrainException {
		RollingStock loco = new Locomotive(LOCO_WEIGHT, LOCO_CLASS);
		RollingStock passenger = new PassengerCar(PASSENGER_WEIGHT, SEATS);
		RollingStock freight = new FreightCar(FREIGHT_WEIGHT, FREIGHT_TYPE);
		testTrain.addCarriage(loco);
		testTrain.addCarriage(passenger);
		testTrain.addCarriage(freight);
		testTrain.removeCarriage();
		testTrain.removeCarriage();
		testTrain.removeCarriage();
		testTrain.removeCarriage();
	}
	
	/**
	 * Normal case - tests that the number of seats are correctly
	 * returned from the numberOfSeats() method 
	 * @throws TrainException
	 */
	@Test
	public void numberOfSeatsCountedCorrectly() throws TrainException {
		RollingStock loco = new Locomotive(LOCO_WEIGHT, LOCO_CLASS);
		RollingStock passenger = new PassengerCar(PASSENGER_WEIGHT, SEATS);
		testTrain.addCarriage(loco);
		testTrain.addCarriage(passenger);
		assertEquals(testTrain.numberOfSeats(), SEATS);
	}
	
	/**
	 * Normal case - tests that the number of seats are correctly
	 * returned from the numberOfSeats() method when there are 
	 * multiple passenger carriages.
	 * @throws TrainException
	 */
	public void numberOfSeatsCountedCorrectlyMultiCarriage() throws TrainException {
		int expectedSeats = 20;
		RollingStock loco = new Locomotive(LOCO_WEIGHT, LOCO_CLASS);
		RollingStock passenger = new PassengerCar(PASSENGER_WEIGHT, SEATS);
		testTrain.addCarriage(loco);
		testTrain.addCarriage(passenger);
		testTrain.addCarriage(passenger);
		assertEquals(testTrain.numberOfSeats(), expectedSeats);
	}
	
	/**
	 * Normal case - tests that calling board() will add passengers
	 * to the train seats.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void passengersCanBoard() throws TrainException {
		int numPassengers = 5;
		RollingStock loco = new Locomotive(LOCO_WEIGHT, LOCO_CLASS);
		RollingStock passenger = new PassengerCar(PASSENGER_WEIGHT, SEATS);
		testTrain.addCarriage(loco);
		testTrain.addCarriage(passenger);
		testTrain.board(numPassengers);
		assertEquals(testTrain.numberOnBoard(), numPassengers);
	}
	
	/**
	 * Normal case - tests that calling removeCarriage() removes
	 * a carriage from the train.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void removeCarriages() throws TrainException {
		RollingStock loco = new Locomotive(LOCO_WEIGHT, LOCO_CLASS);
		RollingStock passenger = new PassengerCar(PASSENGER_WEIGHT, SEATS);
		RollingStock freight = new FreightCar(FREIGHT_WEIGHT, FREIGHT_TYPE);
		testTrain.addCarriage(loco);
		testTrain.addCarriage(passenger);
		testTrain.addCarriage(passenger);
		testTrain.addCarriage(freight);
		testTrain.removeCarriage();
		testTrain.firstCarriage();
		testTrain.nextCarriage();
		testTrain.nextCarriage();
		assertFalse(testTrain.nextCarriage() == freight);
	}
	

	
	/**
	 * Boundary case - This test tries to add 50 passengers into a 10 seat
	 * carriage. To test that the code prevents adding too many passengers we
	 * assert equals on the number of passengers with the number of seats to 
	 * ensure that there are only as many passengers as there are seats.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void tooManyPassengers() throws TrainException {
		int numPassengers = 50;
		RollingStock loco = new Locomotive(LOCO_WEIGHT, LOCO_CLASS);
		RollingStock passenger = new PassengerCar(PASSENGER_WEIGHT, SEATS);
		testTrain.addCarriage(loco);
		testTrain.addCarriage(passenger);
		testTrain.board(numPassengers);
		assertEquals(testTrain.numberOnBoard(), SEATS);
	}
	
	/**
	 * Boundary case - tests that trainCanMove returns true even if 
	 * the gross weight of a train is only 1 less than its pulling power.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void trainCanBarelyMove() throws TrainException {
		int locoWeight = 200, passengerWeight = 299;
		RollingStock loco = new Locomotive(locoWeight, LOCO_CLASS);
		RollingStock passenger = new PassengerCar(passengerWeight, SEATS);
		testTrain.addCarriage(loco);
		testTrain.addCarriage(passenger);
		assertTrue(testTrain.trainCanMove());
	}
	
	/**
	 * Boundary case(?) - tests that a grossWeight of a train that 
	 * exceeds the pulling power if the locomotive returns true when trainCanMove()
	 * returns false.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void trainTooHeavy() throws TrainException {
		int locoWeight = 200, passengerWeight = 500;
		RollingStock loco = new Locomotive(locoWeight, LOCO_CLASS);
		RollingStock passenger = new PassengerCar(passengerWeight, SEATS);
		testTrain.addCarriage(loco);
		testTrain.addCarriage(passenger);
		assertTrue(!testTrain.trainCanMove());
	}
	
	/**
	 * Exceptional test - tests that an exception is thrown when
	 * a second locomotive is added.
	 * 
	 * @throws TrainException
	 */
	@Test (expected = TrainException.class)
	public void twoLocomotives() throws TrainException {
		RollingStock loco = new Locomotive(LOCO_WEIGHT, LOCO_CLASS);
		RollingStock passenger = new PassengerCar(PASSENGER_WEIGHT, SEATS);
		testTrain.addCarriage(loco);
		testTrain.addCarriage(passenger);
		testTrain.addCarriage(loco);	
	}
}
