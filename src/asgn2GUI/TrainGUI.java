package asgn2GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import asgn2Train.DepartingTrain;
import asgn2RollingStock.*;
import asgn2Exceptions.TrainException;

/**
 * @author Steven Lomas
 */
public class TrainGUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -7031008862559936404L;
	private static final String TITLE = "Departing Train - INB370 asgn2";
	private static final String INFORMATION_TITLE = "Train information:";
	
	private DepartingTrain Train = new DepartingTrain();
	
	private JPanel centerPanel;
	private JPanel northPanel;
	private JPanel southPanel;
	private JPanel eastPanel;
	private JPanel westPanel;
	
	private JTextArea textDisplay;
	
	private JLabel informationTitle;
	private JLabel currentLoad;
	private JLabel maxLoad;
	private JLabel loadCapacity;
	private JLabel currentPassengers;
	private JLabel totalSeats;
	private JLabel remainingCapacity;
	private JLabel capacity;
	
	// Add Menu
	private JMenuItem addLocomotiveChoice;
	private JMenuItem addPassengerCarChoice;
	private JMenuItem addFreightCarChoice;
	private JMenuItem addPassengersChoice;
	
	// Remove Menu
	private JMenuItem removeCarriageChoice;
	// private JMenuItem removePassengersChoice;
	
	public static final int WIDTH = 720;
	public static final int HEIGHT = 200;
	

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public TrainGUI(String arg0) throws HeadlessException {
		super(arg0);
		createGUI();
	}
	
	private void createGUI() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		/* Panels */
		centerPanel = createPanel(Color.WHITE);
		northPanel = createPanel(Color.LIGHT_GRAY);
		southPanel = createPanel(Color.LIGHT_GRAY);
		eastPanel = createPanel(Color.LIGHT_GRAY);
		
		westPanel = createPanel(Color.LIGHT_GRAY);
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		westPanel.setPreferredSize(new Dimension(150, HEIGHT - 20));
		
		/* Labels */
		informationTitle = createLabel(INFORMATION_TITLE);
		currentLoad = createLabel("");
		maxLoad = createLabel("");
		loadCapacity = createLabel("");
		currentPassengers = createLabel("");
		totalSeats = createLabel("");
		remainingCapacity = createLabel("");
		capacity = createLabel("");
		
		/* Add Menu */
		JMenu addMenu = new JMenu("Add");
		addMenu.setToolTipText("Add Carriages or Passengers to this train.");
		
		/* Remove Menu */
		JMenu removeMenu = new JMenu("Remove");
		removeMenu.setToolTipText("Remove Carriages from this train.");
		
		/* Menu Items */
		addLocomotiveChoice = createMenuItem("Locomotive");
		addPassengerCarChoice = createMenuItem("Passenger Carriage");
		addFreightCarChoice = createMenuItem("Freight Carriage");
		addPassengersChoice = createMenuItem("Passengers");
		removeCarriageChoice = createMenuItem("Carriage");
		
		/* Text Area */
		textDisplay = new JTextArea();
		textDisplay.setEditable(false);
		textDisplay.setLineWrap(true);
		textDisplay.setFont(new Font("Arial",Font.BOLD,20));
		textDisplay.setPreferredSize(new Dimension(540, 130));
		
		/* Setup Panels */
		this.getContentPane().add(centerPanel,BorderLayout.CENTER);
		this.getContentPane().add(northPanel,BorderLayout.NORTH);
		this.getContentPane().add(southPanel,BorderLayout.SOUTH);
		this.getContentPane().add(eastPanel,BorderLayout.EAST);
		this.getContentPane().add(westPanel,BorderLayout.WEST);
		
		centerPanel.add(textDisplay);
		// 550 x 130
		
		/* Setup West Panel */
		westPanel.add(informationTitle);
		informationTitle.setFont(new Font("Arial",Font.ITALIC,16));
		westPanel.add(currentLoad);
		westPanel.add(maxLoad);
		westPanel.add(loadCapacity);
		westPanel.add(currentPassengers);
		westPanel.add(totalSeats);
		westPanel.add(remainingCapacity);
		westPanel.add(capacity);
		
		/* Setup Menus */
		addMenu.add(addLocomotiveChoice);
		addMenu.add(addPassengerCarChoice);
		addMenu.add(addFreightCarChoice);
		addMenu.add(addPassengersChoice);
		removeMenu.add(removeCarriageChoice);
		
		JMenuBar bar = new JMenuBar();
		bar.add(addMenu);
		bar.add(removeMenu);
		setJMenuBar(bar);
		
		repaint();
		updateInformation();
	}
	
	private JPanel createPanel(Color c) {
		// Create a JPanel object
		JPanel newPanel = new JPanel();
		
		// Set the background color
		newPanel.setBackground(c);
		
		// Return the JPanel object
		return newPanel;
	}
	
	private JLabel createLabel(String text) {
		// Create a JLabel object
		JLabel newLabel = new JLabel();
		
		// Set the text
		newLabel.setText(text);
		newLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		// Return the JLabel object
		return newLabel;
	}
	
	private JMenuItem createMenuItem(String name) {
		// Create a JMenuItem object
		JMenuItem newMenuItem = new JMenuItem(name);
		
		// Add the Action Listener to catch events
		newMenuItem.addActionListener(new MenuListener());
		
		// Return the JMenuItem object
		return newMenuItem;
	}
	
	private int getTotalWeight() {
		// Loop through train in DepartingTrain
		// Stop when null is returned
		RollingStock tempCar = Train.firstCarriage();
		int totalWeight = 0;
		
		while (tempCar != null) {
			totalWeight += tempCar.getGrossWeight();
			tempCar = Train.nextCarriage();
		}
		
		return totalWeight;
	}
	
	public void updateInformation() {
		RollingStock tempCar = Train.firstCarriage();
		boolean moveable = Train.trainCanMove();
		int passengers = Train.numberOnBoard();
		int seats = Train.numberOfSeats();
		int freeSeats = seats - passengers;
		
		/* westPanel Labels */
		if (tempCar == null) {
			maxLoad.setText("Power: -");
		} else {
			maxLoad.setText("Power: " + String.valueOf(((Locomotive)tempCar).power()));
		}
		currentLoad.setText("Current Load: " + String.valueOf(getTotalWeight()));
		
		if (moveable) { // Train can move
			loadCapacity.setText("Weight: Under Limit");
		} else { // Train cannot move
			loadCapacity.setText("Weight: Overloaded");
		}
		
		currentPassengers.setText("Passengers: " + String.valueOf(passengers));
		remainingCapacity.setText("Empty Seats: " + String.valueOf(freeSeats));
		totalSeats.setText("Total Seats: " + String.valueOf(seats));
		if (freeSeats > 0) { // Seats available
			capacity.setText("Capacity: Not full");
		} else { // No seats available
			capacity.setText("Capacity: Full");
		}
		
		/* centerPanel text */
		textDisplay.setText("Train: " + Train.toString());
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Set up components
		TrainGUI GUI = new TrainGUI(TITLE); // GUI
		// DepartingTrain Train = new DepartingTrain(); // Departing Train
		
		// Show GUI
		GUI.setResizable(false);
		GUI.setVisible(true);
	}
	
	private class MenuListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			Component source = (Component)event.getSource();
			
			if (source == addLocomotiveChoice) { // Add Locomotive
				addLocomotive();
			} else if (source == addPassengerCarChoice) { // Add Passenger Car
				addPassengerCarriage();
			} else if (source == addFreightCarChoice) { // Add Freight
				addFreight();
			} else if (source == addPassengersChoice) { // Board Passengers
				int overflow = addPassengers();
				if (overflow > 0) { // Cannot fit all passengers
					JOptionPane.showMessageDialog(null, overflow +
							" passengers were unable to find a seat.",
							"No Free Seats Left", JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (source == removeCarriageChoice) { // Remove Locomotive
				try {
					Train.removeCarriage();
				} catch (TrainException e) {
					errorHandler(e);
				}
			}
			
			updateInformation();
		}
		
		private void addLocomotive() {
			boolean valid = false;
			String input = "";
			int weight = 90;
			Object[] enginePower = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
			Object[] engineType = { "Diesel", "Steam", "Eletric" };
			
			do {
				input = (String)JOptionPane.showInputDialog(null,
						"Specify Carriage Weight (tonnes)\n " +
						"Between 90 and 300", "Carriage Weight",
						JOptionPane.INFORMATION_MESSAGE, null, null, 90);
				try { 
			        weight = Integer.parseInt(input);
			        if (weight >= 90 && weight <= 300) {
			        	valid = true;
			        }
			    } catch(NumberFormatException e) { 
			        valid = false;
			    }
			} while (!valid);
			
			String power = (String)JOptionPane.showInputDialog(null,
					"Choose the engine power.", "Engine Power",
					JOptionPane.INFORMATION_MESSAGE, null,
					enginePower, enginePower[0]);
			
			String engine = (String)JOptionPane.showInputDialog(null,
					"Choose the type of engine.", "Engine Type",
					JOptionPane.INFORMATION_MESSAGE, null,
					engineType, engineType[0]);
			
			switch (engine) {
	        case "Diesel"	: engine = "D";
	        	break;
	        case "Steam"	: engine = "S";
         		break;
	        case "Eletric"	: engine = "E";
	        	break;           
			}
			
			try {
				Train.addCarriage(new Locomotive(weight, power + engine));
			} catch (TrainException e) {
				errorHandler(e);
			}
		}
		
		private void addPassengerCarriage() {
			boolean valid = false;
			String input = "";
			int weight = 50;
			int seats = 50;
			
			do {
				input = (String)JOptionPane.showInputDialog(null,
						"Specify Carriage Weight (tonnes)\n " +
						"Between 50 and 200", "Carriage Weight",
						JOptionPane.INFORMATION_MESSAGE, null, null, 50);
				try { 
			        weight = Integer.parseInt(input);
			        if (weight >= 50 && weight <= 200) {
			        	valid = true;
			        }
			    } catch(NumberFormatException e) { 
			        valid = false;
			    }
			} while (!valid);
			
			do {
				input = (String)JOptionPane.showInputDialog(null,
						"Specify number of seats in carriage\n " +
						"Between 0 and 200", "Carriage Seats",
						JOptionPane.INFORMATION_MESSAGE, null, null, 0);
				try { 
			        seats = Integer.parseInt(input);
			        if (seats >= 40 && seats <= 200) {
			        	valid = true;
			        }
			    } catch(NumberFormatException e) { 
			        valid = false;
			    }
			} while (!valid);
			
			try {
				Train.addCarriage(new PassengerCar(weight, seats));
			} catch (TrainException e) {
				errorHandler(e);
			}
		}
		
		private void addFreight() {
			boolean valid = false;
			String input = "";
			int weight = 40;
			Object[] goodsType = { "General Goods", "Refrigerated Goods",
					"Dangerous Materials" };
			
			do {
				input = (String)JOptionPane.showInputDialog(null,
						"Specify Carriage Weight (tonnes)\n " +
						"Between 40 and 200", "Carriage Weight",
						JOptionPane.INFORMATION_MESSAGE, null, null, 40);
				try { 
			        weight = Integer.parseInt(input);
			        if (weight >= 40 && weight <= 200) {
			        	valid = true;
			        }
			    } catch(NumberFormatException e) { 
			        valid = false;
			    }
			} while (!valid);
			
			String goods = (String)JOptionPane.showInputDialog(null,
					"Choose the type of goods this\n Freight Carriage will carry.",
					"Goods Type", JOptionPane.INFORMATION_MESSAGE, null,
					goodsType, goodsType[0]);
			
			switch (goods) {
	        case "General Goods"		: goods = "G";
	        	break;
	        case "Refrigerated Goods"	: goods = "R";
         		break;
	        case "Dangerous Materials"	: goods = "D";
	        	break;           
			}
			
			try {
				Train.addCarriage(new FreightCar(weight,  goods));
			} catch (TrainException e) {
				errorHandler(e);
			}
		}
		
		private int addPassengers() {
			boolean valid = false;
			String input = "";
			int passengers = 0;
			int overflow = 0;
						
			do {
				input = (String)JOptionPane.showInputDialog(null,
						"Specify number of passengers to board\n " +
						"Between 0 and 1000", "Carriage Weight",
						JOptionPane.INFORMATION_MESSAGE, null, null, 0);
				try { 
					passengers = Integer.parseInt(input);
			        if (passengers >= 0 && passengers <= 1000) {
			        	valid = true;
			        }
			    } catch(NumberFormatException e) { 
			        valid = false;
			    }
			} while (!valid);
			
			try {
				overflow = Train.board(passengers);
			} catch (TrainException e1) {
				errorHandler(e1);
			}
			
			return overflow;
		}
		
		private void errorHandler(TrainException e) {
			JOptionPane.showMessageDialog(null, e, "Error: Train Exception",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
