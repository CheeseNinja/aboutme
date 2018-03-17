package Splash;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.swing.*;

public class USDA_Panel extends JPanel{
	
	private String food, food_ground = "", API_KEY="6l5HlpQdoRU0FzsijrozdHm4h0mOAa8pfzPYFlYV", totalstring = "";
;
	private JPanel search_panel;
	private ArrayList<FoodItem> food_list, food_list2;
	private int array_size=0, food_index;
	private JComboBox<String> group_box; 
	private String[] group_array = {"All","American Indian/Alaska Native Foods","Baby Foods","Baked Products","Beef Products","Beverages","Breakfast Cereals","Cereal Grains and Pasta","Dairy and Egg Products","Fast Foods","Fats and Oils","Finfish and Shellfish Products","Fruits and Fruit Juices","Lamb, Veal, and Game Products","Legumes and Legume Products","Meals, Entrees, and Side Dishes","Nut and Seed Products","Pork Products","Poultry Products","Restaurant Foods","Sausages and Luncheon Meats","Snacks","Soups, Sauces, and Gravies","Spices and Herbs","Sweets","Vegetables and Vegetable Products"};
	private String[] id = {"","3500","0300","1800","1300","1400","0800","2000","0100","2100","0400","1500","0900","1700","1600","2200","1200","1000","0500","3600","0700","2500","0600","0200","1900","1100"};
	
	public USDA_Panel() {
		setPreferredSize(new Dimension(250,500));
		add(searchPanel());
	}
	
	public JPanel searchPanel() {
		search_panel = new JPanel();
		search_panel.setPreferredSize(new Dimension(250,500));
		search_panel.setBackground(new Color(59, 89, 152));
		
		JLabel search_label = new JLabel("<html><font color='white'>Food: </font></html>");
		JTextField food_field = new JTextField(15);
		
		group_box = new JComboBox<>(group_array);
		JTextArea food_area = new JTextArea(12,18);
		food_area.setEditable(false);
		JScrollPane scroll = new JScrollPane(food_area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		food_field.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER && !food_field.getText().equals("")) {
					food = food_field.getText();
					food_field.setText("");
					food_ground = "&fg="+id[group_box.getSelectedIndex()];
					getArray();
					String text ="";
					int index = 1;
					for (int i=0; i<(array_size+1)/7; i++) {
						text += index + ") " + food_list2.get(i).getname() +"\n";
						index++;
					}
					food_area.setText(text);
				}
			}
			public void keyReleased(KeyEvent arg0) {				
			}
			@Override
			public void keyTyped(KeyEvent arg0) {				
			}
		});
		JTextField index_field = new JTextField(10);
		index_field.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER && !index_field.getText().equals("")) {
					try {
						food_index = Integer.parseInt(index_field.getText());
						new Nutrition_JFrame(food_list2.get(food_index-1).getndbno());
					}
					catch(Exception e) {
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			@Override
			public void keyTyped(KeyEvent arg0) {				
			}
		});
		JTextArea list_area = new JTextArea(7,18);
		food_area.setEditable(false);

		list_area.setEditable(false);
		JTextArea food_area2 = new JTextArea(7,18);
		food_area2.setEditable(false);
		JScrollPane scroll2 = new JScrollPane(food_area2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JButton refresh = new JButton("Refresh");
		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileReader file;
				try {
					file = new FileReader("C:/Users/Wendy/Desktop/eclipse/AtomHacks2018/src/Splash/files/food.txt");
					BufferedReader reader = new BufferedReader(file);
					String line = reader.readLine();
					while (line!=null){
						totalstring += line + "\n";
						line = reader.readLine();
					}
					reader.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				catch (IOException e) {
				}
				food_area2.setText(totalstring);
			}
			
		});
		

		search_panel.add(search_label);
		search_panel.add(food_field);
		search_panel.add(group_box);
		search_panel.add(new JLabel("<html><font color = 'white'>Food Results</font></html>"));
		search_panel.add(scroll);
		search_panel.add(new JLabel("Enter index: "));
		search_panel.add(index_field);
		search_panel.add(refresh);
		search_panel.add(scroll2);
		return search_panel;
	}
	
	private ArrayList getArray() {
		food_list = new ArrayList<FoodItem>();	
		String text = "", name ="", ndbno = "";
		URL url = null;
		File file;
		
		try {
			file = new File("C:/Users/Wendy/Desktop/eclipse/AtomHacks2018/src/Splash/files/USDA_Info.txt");
			url = new URL("https://api.nal.usda.gov/ndb/search/?format=json&q="+food+food_ground+"&sort=n&max=25&offset=0&api_key="+API_KEY);
			URLConnection con = url.openConnection();
			InputStream is = con.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(is));
            
			while ((text = read.readLine()) != null) {
            	String temp = text;
            	
	            if (text.indexOf("name")!=-1) {
	            	if (text.indexOf("UPC")!=-1) {
	            		temp = text.substring(text.indexOf("name")+8,text.length()-1);
	            		name = temp.substring(0,temp.indexOf("UPC")-2);
	            	}
	            	else if (text.indexOf("UPC")==-1){
	            		name = text.substring(text.indexOf("name")+8,text.length()-2);
	            	}
	            }
	            else if (text.indexOf("ndbno")!=-1){
	            	ndbno = text.substring(text.indexOf("ndbno")+9,text.length()-2);
	            }
	            if (!name.equals("") && !ndbno.equals("")) {
	            	food_list.add(new FoodItem(name,ndbno));
	            }
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		array_size = food_list.size();
		food_list2 = new ArrayList<FoodItem>();
		int j = 0;
		for (int i=0; i<array_size; i++) {
			if (i%7==0) {
				food_list2.add(food_list.get(i));
				j++;
			}
		}
		return food_list2;
	}
	
	public static void showGUI() {
		JFrame frame = new JFrame();
		frame.add(new USDA_Panel(), BorderLayout.CENTER);
		frame.setSize(250,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		showGUI();
	}
}
