package asgn2Tests;

import java.util.Vector;

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
	
	//private Vector<RollingStock> train = new Vector<RollingStock>();
	private DepartingTrain testTrain = new DepartingTrain();
	
	@Test
	public void assembleValidTrain() throws TrainException {
		RollingStock loco = new Locomotive(90, "5D");
		RollingStock passenger = new	PassengerCar(50, 10);
		RollingStock freight = new FreightCar(40, "G");
		testTrain.addCarriage(loco);
		testTrain.addCarriage(passenger);
		testTrain.addCarriage(freight);
		assertEquals(testTrain.firstCarriage(), loco);
		assertEquals(testTrain.nextCarriage(), passenger);
		assertEquals(testTrain.nextCarriage(), freight);
	}
	
	@Test
	public void cantIterateThroughCarriages() {
		//test
	}
	
	@Test
	public void freightBeforePassengers() {
		//test
	}
	
	@Test
	public void negativePassengerBoarding() {
		//test
	}
	
	@Test
	public void noCarriagesInitially() {
		//test
	}
	
	
	@Test
	public void noLocomotiveFirst() {
		//test
	}
	
	@Test
	public void noPassengersIntitially() {
		//test
	}
	
	@Test
	public void noSeatsInitially() {
		//test
	}
	
	@Test
	public void noShuntingWithPassengers() {
		//test
	}
	
	@Test
	public void nullTrainCanMove() {
		//test
	}
	
	@Test
	public void nullTrainPrintsCorrectly() {
		//test
	}
	
	@Test
	public void numberOfSeatsCountedCorrectly() {
		//test
	}
	
	@Test
	public void passengersCanBoard() {
		//test
	}
	
	@Test
	public void removeCarriages() {
		//test
	}
	
	@Test
	public void removeTooMany() {
		//test
	}
	
	@Test
	public void tooManyPassengers() {
		//test
	}
	
	@Test
	public void trainCanBarelyMove() {
		//test
	}
	
	@Test
	public void trainExactlyFull() {
		//test
	}
	
	@Test
	public void trainTooHeavy() {
		//test
	}
	
	@Test
	public void twoLocomotives() {
		//test
	}
}
