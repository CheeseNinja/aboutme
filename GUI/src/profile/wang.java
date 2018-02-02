package profile;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/*
 * Wow, I am in disbelief. 
 * However, we are going to recreate it but BETTER.
 * This is not the end. I can do this!
 * 
 * @Author: _Darren_Wang
 * @Date:_January_22_2018
 * Draw with graphics g inside JPanel to create image
 */
public class wang extends JFrame{
	public wang() {
		components();
		//Other properties
		setVisible(true);
		setSize(800,600);	
		setDefaultCloseOperation(JFrame .EXIT_ON_CLOSE);
	}
	public void components() {
		//Layered pane
		lp = new JLayeredPane();
		setContentPane(lp);
		//Adding & Creating Layered JPanel
		mainPanel= new JPanel(new BorderLayout());
		glassPanel = new JPanel();
		lp.add(mainPanel, 0); 		//Default layer
		mainPanel.setBounds(0,0,800,600);
		lp.add(glassPanel,400);  	//Drag later
			glassPanel.setBounds(0,0,800,600);
		
		//Properties of panels
		//mainPanel
			center = new JPanel();
				center.setBackground(Color.BLACK);
				
			east = new JPanel();
				east.setPreferredSize(new Dimension(200,600));
				east.setBackground(Color.blue);
					
			west = new JPanel();
				
				
			mainPanel.add(center, BorderLayout.CENTER);
			mainPanel.add(east, BorderLayout.EAST);
			mainPanel.add(west, BorderLayout.WEST);
		//glassPanel
		glassPanel.setOpaque(false);
		
	}
private Image getScaledImage(Image srcImg, int w, int h){ //found from the Internet
    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB); //TYPE_INT+ARGB is like pixel stuff
    Graphics2D g2 = resizedImg.createGraphics();

    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(srcImg, 0, 0, w, h, null);
    g2.dispose();

    return resizedImg;
}
public JLabel getImage(File f, int height, int width) {
	JLabel label = new JLabel();
try {
	 BufferedImage image = ImageIO.read(f);
	 ImageIcon icon = new ImageIcon(image);	
	 Image image2 = icon.getImage();
	 ImageIcon resizedProf = new ImageIcon(getScaledImage(image2, icon.getIconHeight()/height, icon.getIconWidth()/width));
	 label = new JLabel(resizedProf);
	}
	catch (Exception e) {
	}
return label;
}
private void addingOnFile(File f, String str) {
	try{
		FileWriter fileW = new FileWriter(f,true);
		BufferedWriter buffW = new BufferedWriter(fileW);
		buffW.write(str);
		buffW.newLine();
		buffW.close();
	}
	catch(Exception e){
		e.printStackTrace();
	}		
}
public String fileReader (File f){
	String text = "";
	try {
	FileReader file = new FileReader(f);
	BufferedReader reader = new BufferedReader(file);
	
	String line;
	line = reader.readLine();
	while (line!=null){
		text += line + "\n";
		line = reader.readLine();
	}
	reader.close();
	}
	catch (IOException e) {
	}
	return text;
}
public static void main(String[] args) {
	wang main = new wang();
}
	
	private JLayeredPane lp;
	private JPanel mainPanel;
		private JPanel center;
		private JPanel east;
		private JPanel west;
	private JPanel glassPanel;
}
