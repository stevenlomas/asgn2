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
	
	private Vector<RollingStock> testRollingStock;
	public static final int ZERO = 0;
	public static final int LOCO_WEIGHT = 90;
	public static final int PASSENGER_WEIGHT = 50;
	public static final int FREIGHT_WEIGHT = 40;
	public static final String LOCO_CLASS = "1E";
	public static final int PASSENGER_SEATS = 1;
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
	
	@Test
	public void invalidNumberAlighting() throws TrainException {
		//test
	}
	
	@Test
	public void invalidNumberBoarding() throws TrainException {
		//test
	}
	
	@Test
	public void invalidNumberOfSeats() throws TrainException {
		//test
	}
	
	@Test
	public void locomotivePowerTooLow() throws TrainException {
		//test
	}
	
	@Test
	public void locomotivePrintsCorrectly() {
		//test
	}
	
	@Test
	public void nobodyGetsOff() {
		//test
	}
	
	@Test
	public void nobodyGetsOn() {
		//test
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
	
	@Test
	public void noSeatsAllowed() {
		//test
	}
	
	@Test
	public void numberOfSeatsRemembered() {
		//test
	}
	
	@Test
	public void passengerCarPrintsCorrectly() {
		//test
	}
	
	@Test
	public void passengersAccumulate() {
		//test
	}
	
	@Test
	public void passengersCanAlight() {
		//test
	}
	
	@Test
	public void powerCalculatedCorrectly() {
		//test
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

