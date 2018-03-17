package Splash;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class Food_List_And_Recipe extends JPanel{
	
	
	
	public Food_List_And_Recipe() {
		this.setPreferredSize(new Dimension(250,500));
		this.setBackground(Color.LIGHT_GRAY);
		components();
	}
	
	public void components() {
		JLabel eat_label= new JLabel("- - - -To eat list- - - -");
		
		JTextArea food_area = new JTextArea(8,18);
		food_area.setEditable(false);
		JScrollPane scroll = new JScrollPane(food_area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		add(eat_label);
		add(scroll);
	}
	public static void showGUI() {
		JFrame frame = new JFrame();
		frame.add(new Food_List_And_Recipe(), BorderLayout.CENTER);
		frame.setSize(250,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		showGUI();
	}
}
