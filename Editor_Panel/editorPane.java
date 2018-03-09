import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;
import javax.swing.*;
// can't crop close refresh twice
public class editorPane extends JFrame{
	private int height=0, width=0;
	private boolean drawing = false, isThere = false;
	private Rectangle2D rect;
	private int dragX=0, dragY=0, sizeX=100, sizeY=100;
	private BufferedImage bi;
    String location2 ="";
    String desktop;
	public editorPane(ImageIcon icon, File f) {
		//JFrame properties 
	   setVisible(true);
	   setSize(600,500);
	   setResizable(false);
	   setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	   
	   //Getting image width and height
	   Image im = icon.getImage();
	   	try {
	   		bi = ImageIO.read(f);
	        height = bi.getHeight();
	        width = bi.getWidth();
	   	} catch (IOException e) {
	   		e.printStackTrace();
	   	}
	   
	   	//Drawing image and crop rectangle
	   rect = new Rectangle2D.Double(0, 0, 100, 100);
	   JPanel edit = new JPanel(new BorderLayout()){
           @Override
           protected void paintComponent(Graphics g) {
               super.paintComponent(g);
               g.drawImage(im, 0, 0, null);
               Graphics2D g2 = (Graphics2D)g;
               if (drawing==true) {
            	   rect.setFrame(dragX,dragY, sizeX, sizeY);
            	   g2.setColor(new Color(25,116,153,125));
            	   
                   g2.fill(rect);
                   isThere = true;
               }
           }
       };
       edit.addMouseMotionListener(new MouseMotionListener() {

		@Override
		public void mouseDragged(MouseEvent arg0) {
			
				dragX = arg0.getX()-(sizeX)/2;
				dragY = arg0.getY()-(sizeY)/2;
			
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			
		}
    	   
       });
       //Inserting scroll pane
   	   edit.setPreferredSize(new Dimension(width,height));
       JScrollPane sp = new JScrollPane(edit);
       //Tool panel
       JPanel tools = new JPanel();
       tools.setPreferredSize(new Dimension(120,500));
       add(tools, BorderLayout.EAST);
       JButton addRec = new JButton("Add Rec");
       addRec.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		   drawing = true;
     	   repaint();	

		}
    	   
       });
       
       
       
       
       JButton cancel = new JButton(" Close  ");
       
       cancel.addActionListener(new ActionListener() {
    	   
    	   public void actionPerformed(ActionEvent e) {
    		isThere = false;
    		drawing = false;
   			dispose();
   		}
       });
       JButton crop = new JButton("    Crop   ");
       crop.addActionListener(new ActionListener() {
    	   
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(drawing) {
			try {
		   		bi = ImageIO.read(f);
		   		bi = cropImage(bi,rect);
		   		desktop = System.getProperty("user.home") + "/Desktop/croppedimages";
		   		File folder = new File(desktop);
		   		folder.mkdir();
		   		location2 = desktop+"/cropped.jpg";
		   		String location3 = desktop+"/cropped2.jpg";
		   		System.out.println(location2);
		   		ImageIO.write(bi, "jpg", new File(location2));
		   		ImageIO.write(bi, "jpg", new File(location3));

		   		
			} catch (IOException e) {
		   		e.printStackTrace();
		   	}
		  }
		}
    	   
       });
       JTextField insertX = new JTextField(5);
       JTextField insertY = new JTextField(5);
       insertX.addKeyListener(new KeyListener() {

		@Override
		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
				int x = Integer.parseInt(insertX.getText());
				if(x>225) {
					sizeX = 225;
					insertX.setText("225");
				}
				else {
					sizeX = x;
				}
				repaint();
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    	

       });
       insertY.addKeyListener(new KeyListener() {

   		@Override
   		public void keyPressed(KeyEvent arg0) {
   			if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
   				int y = Integer.parseInt(insertY.getText());
				if(y>270) {
					sizeY = 270;
					insertY.setText("270");
				}
				else {
					sizeY = y;
				}
				repaint();
   			}
   		}

   		@Override
   		public void keyReleased(KeyEvent arg0) {
   			// TODO Auto-generated method stub
   			
   		}

   		@Override
   		public void keyTyped(KeyEvent arg0) {
   			// TODO Auto-generated method stub
   			
   		}
       });
       setLayout(new BorderLayout());
       add(sp, BorderLayout.CENTER);
       add(tools, BorderLayout.EAST);
       tools.add(crop);
       tools.add(addRec);
       tools.add(cancel);
       tools.add(new JLabel("Width: "));
       tools.add(insertX);
       tools.add(new JLabel("Height: "));
       tools.add(insertY);
	}
	private BufferedImage cropImage(BufferedImage src, Rectangle2D rect) {
	      BufferedImage cropped = src.getSubimage(dragX, dragY, sizeX, sizeY);
	      return cropped; 
	   }
	public String getLocation(String str) {
		return location2;
	}

}
