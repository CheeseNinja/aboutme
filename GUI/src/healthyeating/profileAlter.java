package healthyeating;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

//make a pop up to set the area of the pic to put on the label
//auto resize pic if too big (no need for small pics), background will be black

public class profileAlter extends JFrame{
 private JPanel mainPanel, profileBox;
 private String picLocation = "", sub = "";
 private JFileChooser fc;
 private JButton addpic;
 private JLabel pic;
 private JFrame editor;
 private Preferences prefs = Preferences.userRoot().node(this.getClass().getName());


 public profileAlter() {
  components();
  
  setSize(800,600);
  setVisible(true);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
 
 public void components() {

  mainPanel = new JPanel(new BorderLayout());
  profileBox = new JPanel();
  profileBox.setPreferredSize(new Dimension(250,600));
   JPanel in = new JPanel();
   in.setPreferredSize(new Dimension(225,270));
   in.setBackground(Color.BLACK);
   pic = new JLabel();
   pic.setIcon(new ImageIcon(prefs.get(sub, "")));
   in.add(pic);
    
   
   
   addpic = new JButton("add image");
   fc = new JFileChooser();
   FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & GIF Images", "jpg", "gif");
    fc.setFileFilter(filter);
   
   addpic.addActionListener(new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent arg0) {
     if(fc.showOpenDialog(addpic) == JFileChooser.APPROVE_OPTION) {
    	 picLocation = fc.getSelectedFile().getAbsolutePath();
    	 ImageIcon picture = new ImageIcon(picLocation);
    	 File fileImage = new File(picLocation);
    	 prefs.put(sub, System.getProperty("user.home") + "/Desktop/croppedimages/cropped.jpg");
    	 editorPane ep = new editorPane(picture,fileImage);
     }
    }
    
   });

  JButton refresh = new JButton("refresh");
  refresh.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		 pic.setIcon(new ImageIcon(System.getProperty("user.home") + "/Desktop/croppedimages/cropped2.jpg"));
	}
	  
  });
  profileBox.add(in);
  profileBox.add(addpic);
  profileBox.add(refresh);
  profileBox.setBackground(Color.BLACK);
  
  
  mainPanel.add("West", profileBox);
  add(mainPanel);
 }
 public static void hi() {
	 System.out.println("hi");
 }
public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			profileAlter al = new profileAlter();
	}	 
 });
}
}
