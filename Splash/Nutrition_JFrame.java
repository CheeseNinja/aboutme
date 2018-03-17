package Splash;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.swing.*;

public class Nutrition_JFrame extends JFrame{
	
	private JPanel nutr_panel;
	private Font title_font = new Font("Helvetica Bold", Font.BOLD, 25);
	private String name = "No data", water="No data", calories="No data", protein="No data", sodium="No data", fat="No data", API_KEY="6l5HlpQdoRU0FzsijrozdHm4h0mOAa8pfzPYFlYV", num="0", text="";

		public Nutrition_JFrame(String num_) {
			num = num_;
			setVisible(true);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(250,500);
			components();
		}
		
		public void components() {
			getInfo();
			nutr_panel = new JPanel() {
				public void paintComponent(Graphics g) {
					super.paintComponents(g);
					g.drawString("Water: "+water, 10, 90);
					g.drawString("Calories: "+calories, 10, 105);
					g.drawString("Protein: "+protein, 10, 120);
					g.drawString("Sodium: "+sodium, 10, 135);
					g.drawString("Total lipid (fat): "+fat, 10, 150);
					g.setFont(title_font);
					g.drawString("Nutrition Facts", 10, 70);
					repaint();
				}
			};
			File file = new File("C:/Users/Wendy/Desktop/eclipse/AtomHacks2018/src/Splash/files/food.txt");
			JButton add = new JButton("Add");
			add.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						FileWriter fileW = new FileWriter(file, true);
						BufferedWriter buffW = new BufferedWriter(fileW);
						buffW.write(name+ " " + calories);
						buffW.newLine();
						buffW.newLine();
						buffW.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			});
			nutr_panel.setPreferredSize(new Dimension(250,500));
			nutr_panel.setBackground(Color.white);
			nutr_panel.add(add);

			add(nutr_panel);
		}
		
		public void getInfo() {
			URL url;
			try {
				url = new URL("https://api.nal.usda.gov/ndb/V2/reports?ndbno="+num+"&type=f&format=json&api_key="+API_KEY);
				URLConnection con = url.openConnection();
				InputStream is = con.getInputStream();
				BufferedReader read = new BufferedReader(new InputStreamReader(is));
				text = read.readLine();
				water = get_nutr(text,"Water");
				calories = get_nutr(text,"Energy");
				protein = get_nutr(text,"Protein");
				sodium = get_nutr(text,"Sodium");
				fat = get_nutr(text,"Total lipid (fat)");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public String get_nutr(String text, String look) {
			String unit="g", value="g", total = "No data";
			name = text.substring(text.indexOf("name")+7,text.indexOf("sd")-3);
			if (text.indexOf(look)!=-1) {
				text = text.substring(text.indexOf(look));
				unit = text.substring(text.indexOf("unit")+7,text.indexOf("value")-3);
				value = text.substring(text.indexOf("value")+7,text.indexOf("derivation")-2);
				total = value+unit;
			}			
			return total;
		}
		
}
