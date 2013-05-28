package asgn2GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import asgn2Train.DepartingTrain;
//import asgn2RollingStock.*;

/**
 * @author Steven Lomas
 *
 */
public class TrainGUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -7031008862559936404L;
	private static final String TITLE = "Departing Train - INB370 asgn2";
	
	private JPanel centerPanel;
	private JPanel northPanel;
	private JPanel southPanel;
	private JPanel eastPanel;
	private JPanel westPanel;
	
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
		
		//Panel related code will go here
		centerPanel = createPanel(Color.WHITE);
		northPanel = createPanel(Color.LIGHT_GRAY);
		southPanel = createPanel(Color.LIGHT_GRAY);
		eastPanel = createPanel(Color.LIGHT_GRAY);
		westPanel = createPanel(Color.LIGHT_GRAY);
		
		this.getContentPane().add(centerPanel,BorderLayout.CENTER);
		this.getContentPane().add(northPanel,BorderLayout.NORTH);
		this.getContentPane().add(southPanel,BorderLayout.SOUTH);
		this.getContentPane().add(eastPanel,BorderLayout.EAST);
		this.getContentPane().add(westPanel,BorderLayout.WEST);
		
		repaint();
	}
	
	private JPanel createPanel(Color c) {
		//Create a JPanel object and store it in a local var
		JPanel newPanel = new JPanel();
		
		//set the background colour to that passed in c
		newPanel.setBackground(c);
		
		//Return the JPanel object
		return newPanel;
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
		DepartingTrain Train = new DepartingTrain(); // Departing Train
		
		// Show GUI
		GUI.setResizable(false);
		GUI.setVisible(true);
	}

}