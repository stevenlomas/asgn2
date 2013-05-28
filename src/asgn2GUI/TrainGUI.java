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
	
	private DepartingTrain Train = new DepartingTrain();
	
	private JPanel centerPanel;
	private JPanel northPanel;
	private JPanel southPanel;
	private JPanel eastPanel;
	private JPanel westPanel;
	
	private JTextArea textDisplay;
	
	private JLabel currentLoad;
	private JLabel currentPassengers;
	private JLabel totalSeats;
	private JLabel capacity;
	
	// Add Menu
	private JMenuItem addLocomotiveChoice;
	private JMenuItem addPassengerCarChoice;
	private JMenuItem addFreightCarChoice;
	private JMenuItem addPassengersChoice;
	
	// Remove Menu
	private JMenuItem removeLocomotiveChoice;
	private JMenuItem removePassengersChoice;
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	

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
		westPanel.setSize(WIDTH - 500, HEIGHT - 50);
		
		/* Labels */
		currentLoad = createLabel("");
		currentPassengers = createLabel("");
		totalSeats = createLabel("");
		capacity = createLabel("");
		
		/* Add Menu */
		JMenu addMenu = new JMenu("Add");
		
		addLocomotiveChoice = new JMenuItem("Locomotive");
		addLocomotiveChoice.addActionListener(new MenuListener());
		addMenu.add(addLocomotiveChoice);
		
		addPassengerCarChoice = new JMenuItem("Passenger Carriage");
		addPassengerCarChoice.addActionListener(new MenuListener());
		addMenu.add(addPassengerCarChoice);
		
		addFreightCarChoice = new JMenuItem("Freight Carriage");
		addFreightCarChoice.addActionListener(new MenuListener());
		addMenu.add(addFreightCarChoice);
		
		addPassengersChoice = new JMenuItem("Passengers");
		addPassengersChoice.addActionListener(new MenuListener());
		addMenu.add(addPassengersChoice);
		
		/* Remove Menu */
		JMenu removeMenu = new JMenu("Remove");
		
		removeLocomotiveChoice = new JMenuItem("Carriage");
		removeLocomotiveChoice.addActionListener(new MenuListener());
		removeMenu.add(removeLocomotiveChoice);
		
		removePassengersChoice = new JMenuItem("Passengers");
		removePassengersChoice.addActionListener(new MenuListener());
		removeMenu.add(removePassengersChoice);
		
		/* Text Area */
		textDisplay = new JTextArea();
		textDisplay.setEditable(false);
		textDisplay.setLineWrap(true);
		textDisplay.setFont(new Font("Arial",Font.BOLD,24));
		textDisplay.setBorder(BorderFactory.createEtchedBorder());
		// textDisplay.setSize(100, 200);
		
		/* Setup Panels */
		this.getContentPane().add(centerPanel,BorderLayout.CENTER);
		this.getContentPane().add(northPanel,BorderLayout.NORTH);
		this.getContentPane().add(southPanel,BorderLayout.SOUTH);
		this.getContentPane().add(eastPanel,BorderLayout.EAST);
		this.getContentPane().add(westPanel,BorderLayout.WEST);
		
		centerPanel.add(textDisplay);
		
		/* West Panel */
		westPanel.add(currentLoad);
		westPanel.add(currentPassengers);
		westPanel.add(totalSeats);
		westPanel.add(capacity);
		
		JMenuBar bar = new JMenuBar();
		bar.add(addMenu);
		bar.add(removeMenu);
		setJMenuBar(bar);
		
		repaint();
	}
	
	private JPanel createPanel(Color c) {
		// Create a JPanel object and store it in a local var
		JPanel newPanel = new JPanel();
		
		// Set the background colour to that passed in c
		newPanel.setBackground(c);
		
		// Return the JPanel object
		return newPanel;
	}
	
	private JLabel createLabel(String text) {
		// Create a JLabel object and store it in a local var
		JLabel newLabel = new JLabel();
		
		// Set the text
		newLabel.setText(text);
		
		// Return the JLabel object
		return newLabel;
	}
	
	public void UpdateInformation() {
		boolean moveable = Train.canMove();
		int passengers = Train.numberOnBoard();
		int seats = Train.numberOfSeats();
		int freeSeats = seats - passengers;
		
		if (moveable) { // Train can move
			currentLoad.setText("Underloaded");
		} else { // Train cannot move
			currentLoad.setText("Overloaded");
		}
		
		if (freeSeats > 0) { // Seats available
			capacity.setText("Train is not full");
		} else {
			capacity.setText("Train is full");
		}
		
		currentPassengers.setText(String.valueOf(passengers));
		totalSeats.setText(String.valueOf(seats));
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
           Component source = (Component) e.getSource();
           if (source == removeLocomotiveChoice) { // Remove Locomotive
        	   try {
				Train.removeCarriage();
				} catch (TrainException e1) {
					errorHandler(e1);
				}
           }
           
           UpdateInformation();
		}
		
		private void errorHandler(TrainException e) {
			JOptionPane.showMessageDialog(null, e, "Error: Train Exception",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
