package Splash;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.*;


public class Weather_Panel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private Timer timer = null;
	private String temperature = "Loading...", API_KEY = "03c86e627b8ae03526e2c06db0500ad5";
	private double latitude = 40.87833, longitude = -73.89083, dailyHigh = 0.0, dailyLow = 0.0, apparent_Temp = 0.0;
	private boolean recTrue = false;
	private Font title_font = new Font("Helvetica Bold", Font.BOLD, 18);
	
	/*
	 * creates and starts the timer and it initializes the temperature
	 * the timer repaints graphics g every 10 seconds
	 */
	public Weather_Panel() {
		this.setPreferredSize(new Dimension(300,500));
		JLabel text_Label = new JLabel("<html><font color ='black'>Coordinates: </font></html>");
		JTextField coordinate_Label = new JTextField(10);
		coordinate_Label.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER && !coordinate_Label.getText().equals("")) {
					
					String text = coordinate_Label.getText();
					latitude = Double.parseDouble(text.substring(0,text.indexOf(",")));
					longitude = Double.parseDouble(text.substring(text.indexOf(",")+1));
					coordinate_Label.setText("");
					URL url = null;
					get_Temp(url);
					repaint();
				}
			}
			public void keyReleased(KeyEvent arg0) {				
			}
			public void keyTyped(KeyEvent arg0) {				
			}
		});
		
		timer = new Timer(0, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				URL url = null;
				timer.setDelay(10000); // 10 seconds
				get_Temp(url);
				repaint();
			}	
		});
		timer.start();
		add(text_Label);
		add(coordinate_Label);
		
	}
	/*
	 * connects to the hyperlink and retrieves the weather info from dark sky's JSON file
	 */
	private void get_Temp(URL url) {
		try {
			recTrue = true;
			url = new URL("https://api.darksky.net/forecast/"+API_KEY+"/"+latitude+","+longitude);
			URLConnection con = url.openConnection();
			InputStream is = con.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(is));
			String line = read.readLine();
			temperature = line.substring(line.indexOf("\"temperature\"")+14,line.indexOf("\"apparentTemperature\"")-1)+" °C";
			apparent_Temp = Double.parseDouble(line.substring(line.indexOf("apparentTemperature")+22,line.indexOf("\"dewPoint\"")-1));
			String highlow_text = line.substring(line.indexOf("\"daily\""));
			dailyHigh = Double.parseDouble(highlow_text.substring(highlow_text.indexOf("\"temperatureHigh\"")+18,highlow_text.indexOf("\"temperatureHighTime\"")-1));
			dailyLow = Double.parseDouble(highlow_text.substring(highlow_text.indexOf("\"temperatureLow\"")+17,highlow_text.indexOf("\"temperatureLowTime\"")-1));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 *paints the temperature onto the panel and places a fill_rectangle to cover up previous drawn strings
	 */
	protected void paintComponent(Graphics g) {
		if (recTrue) {
			g.setColor(Color.white);
			g.fillRect(0, 0, 250, 500);
			recTrue = false;
		}
		super.paintComponents(g);
		g.setColor(Color.BLACK);
		g.drawString("Coordinates: "+latitude + ", " + longitude, 20, 100);
		g.drawString("Temperature: "+temperature, 20, 120);
		g.drawString("High: "+dailyHigh, 20, 135);
		g.drawString("Low: "+dailyLow, 20, 150);
		g.drawString("Feels like: "+apparent_Temp, 20, 165);
		g.setFont(title_font);
		g.drawString("Weather", 20, 70);
	}
	/*
	 * places the panel onto a frame to see the result
	 */
	public static void showGUI() {
		JFrame frame = new JFrame();
		frame.add(new Weather_Panel(), BorderLayout.CENTER);
		frame.setSize(250,250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	/*
	 * initializes and calls the static method to show the frame & panel
	 */
	public static void main(String[] args) {
		showGUI();
	}

}
