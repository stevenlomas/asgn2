package asgn2GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import asgn2Train.DepartingTrain;
//import asgn2RollingStock.*;
import asgn2Exceptions.TrainException;

/**
 * @author Steven Lomas
 *
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
	private JMenuItem removePassengersChoice;
	
	public static final int WIDTH = 720;
	public static final int HEIGHT = 240;
	

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
		westPanel.setPreferredSize(new Dimension(150, 100));
		
		/* Labels */
		informationTitle = createLabel(INFORMATION_TITLE);
		currentLoad = createLabel("");
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
		textDisplay.setFont(new Font("Arial",Font.BOLD,24));
		
		/* Setup Panels */
		this.getContentPane().add(centerPanel,BorderLayout.CENTER);
		this.getContentPane().add(northPanel,BorderLayout.NORTH);
		this.getContentPane().add(southPanel,BorderLayout.SOUTH);
		this.getContentPane().add(eastPanel,BorderLayout.EAST);
		this.getContentPane().add(westPanel,BorderLayout.WEST);
		
		centerPanel.add(textDisplay);
		
		/* Setup West Panel */
		westPanel.add(informationTitle);
		informationTitle.setFont(new Font("Arial",Font.ITALIC,16));
		westPanel.add(currentLoad);
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
		UpdateInformation();
	}
	
	private JPanel createPanel(Color c) {
		// Create a JPanel object
		JPanel newPanel = new JPanel();
		
		// Set the background colour
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
	
	public void UpdateInformation() {
		boolean moveable = Train.canMove();
		int passengers = Train.numberOnBoard();
		int seats = Train.numberOfSeats();
		int freeSeats = seats - passengers;
		
		/* westPanel Labels */
		if (moveable) { // Train can move
			currentLoad.setText(" Weight: Under Limit");
		} else { // Train cannot move
			currentLoad.setText(" Weight: Overloaded");
		}
		
		currentPassengers.setText(" Passengers: " + String.valueOf(passengers));
		remainingCapacity.setText(" Empty Seats: " + String.valueOf(freeSeats));
		totalSeats.setText(" Total Seats: " + String.valueOf(seats));
		if (freeSeats > 0) { // Seats available
			capacity.setText(" Capacity: Not full");
		} else { // No seats available
			capacity.setText(" Capacity: Full");
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
		
		public void actionPerformed(ActionEvent e) {
			Component source = (Component)e.getSource();
			if (source == addLocomotiveChoice) {
				//
			} else if (source == removeCarriageChoice) { // Remove Locomotive
				try {
					Train.removeCarriage();
				} catch (TrainException e1) {
					errorHandler(e1);
				}
			} else if (source == removePassengersChoice) { // Remove passengers
				// Does nothing yet
			}
			
			UpdateInformation();
		}
		
		private void errorHandler(TrainException e) {
			JOptionPane.showMessageDialog(null, e, "Error: Train Exception",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
