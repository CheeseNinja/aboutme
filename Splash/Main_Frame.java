package Splash;

import java.awt.BorderLayout;
import java.awt.Dimension;


import javax.swing.*;

public class Main_Frame extends JFrame{
	
	double latitude = 40.87833, longitude = -73.89083;
	
	public Main_Frame() {
		
		components();
		setVisible(true);
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	public void components() {
		
		JPanel mainPanel = new JPanel(new BorderLayout());
						
		JPanel weather = new Weather_Panel();
		JPanel right = new JPanel(new BorderLayout());
		right.setPreferredSize(new Dimension(250,500));
		right.add(new USDA_Panel(), BorderLayout.CENTER);
		mainPanel.add(weather, BorderLayout.CENTER);
		mainPanel.add(right, BorderLayout.EAST);
		add(mainPanel);
	}
	

	public static void main(String[] args) {
		new Main_Frame();
	}

}
