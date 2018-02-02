package profile;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;

public class main extends JFrame{
	//Fonts
	Font title = new Font("Lucida Blackletter", Font.BOLD | Font.PLAIN, 48);
	public main() {
		components();
		setVisible(true);
		setSize(350,700);
		setResizable(false);
		addMouseListener(new DrawingStars());	
		addMouseMotionListener(new DrawingStars());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void components() {
		Image starbackground = new ImageIcon(this.getClass().getResource("/jadespacedust.jpg")).getImage();
		setContentPane(new JLabel(new ImageIcon(starbackground)));
		setLayout(new BorderLayout());
		mainPan = new JPanel(new BorderLayout()) {
					@Override
		            public void paintComponent(Graphics g) {
		                super.paintComponent(g);
		                g.setColor(Color.WHITE);
		                for (int i=0; i<points.size(); i++) {
		                    g.fillOval(points.get(i).x-5, points.get(i).y-25, 10, 10);
		                }
		                if(points.size()>1) {
		                		for (int i=1; i<points.size(); i++) {
					               g.drawLine(points.get(i-1).x-3, points.get(i-1).y-21,points.get(i).x-3, points.get(i).y-21);
			                }
	                    }
		                
		                repaint();
		            }};
		mainPan.setPreferredSize(new Dimension(350,700));
		mainPan.setOpaque(false);
		add(mainPan);
		
		loginPanel = new JPanel();
		loginPanel.setLayout(new GridBagLayout());
		loginPanel.setOpaque(false);
		
		GridBagConstraints c = new GridBagConstraints();	
		c.gridx=0;
		c.gridy=0;
		c.weighty=.2;
		c.anchor = GridBagConstraints.PAGE_START;
			loginPanel.setFont(title);
			JLabel welcome = new JLabel("<html><font color = 'white'>Welcome</font></html>");
			welcome.setFont(title);
			loginPanel.add(welcome, c);
		c.weighty=60;
		c.gridy++;
			loginPanel.add(userpass(),c);
		c.gridy++;
		JPanel connect = new JPanel();
		connect.setPreferredSize(new Dimension(180,80));
		connect.setOpaque(false);
			JLabel fb = new JLabel();
			
			connect.add(linking(getImageIcon("/facebookicon.png"),"https://www.facebook.com/"));
			connect.add(filler(10,10));
			
			connect.add(linking(getImageIcon("/twittericon.png"),"https://www.twitter.com/"));
			connect.add(filler(10,10));
			
			connect.add(linking(getImageIcon("/instagramicon.png"),"https://www.instagram.com/"));
			connect.add(new JLabel("<html><font color ='white'>CONNECT WITH US</font></html>"));
		loginPanel.add(connect,c);
		
		header = new JPanel();
		header.setPreferredSize(new Dimension(800,200));
		header.setOpaque(false);
		
		mainPan.add(loginPanel, BorderLayout.CENTER);
		mainPan.add(header, BorderLayout.NORTH);
	}
	private JPanel userpass() {
		String userFinal = "cheeseninja", passFinal = "123";
		subLogin = new JPanel();
		subLogin.setPreferredSize(new Dimension(300,115));
		subLogin.setOpaque(false);
		JPanel fillerPanel = new JPanel();
		fillerPanel.setPreferredSize(new Dimension(300,7));
		fillerPanel.setOpaque(false);
		subLogin.add(fillerPanel);
		JLabel username = new JLabel("<html><font color = 'white'>Username: </font></html>");
		username.setFont(new Font("Lucida Blackletter", Font.PLAIN, 15));
		subLogin.add(username);
		JTextField userInp = new JTextField(16);
		userInp.setBorder(null);
		subLogin.add(userInp);
		JLabel password = new JLabel("<html><font color = 'white'>Password: </font></html>");
		password.setFont(new Font("Lucida Blackletter", Font.PLAIN, 16));
		subLogin.add(password);
		JPasswordField passInp = new JPasswordField(16);
		passInp.setBorder(null);
		subLogin.add(passInp);
		JButton enter = new JButton("Enter");
		JLabel warning = new JLabel("");
			enter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (userInp.getText().equals(userFinal)&&userInp.getText().equals(userFinal)&&passInp.getText().equals(passFinal)) {
						new wang();
						dispose();
					}
					else {
						passInp.setText("");
						warning.setText("<html><font color = 'white'>Incorrect username/password</font></html>");
					}
				}
			});
			userInp.addKeyListener(new KeyListener() {

				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode()==KeyEvent.VK_ENTER&&userInp.getText().equals(userFinal)&&passInp.getText().equals(passFinal)) {
						new wang();
						dispose();
					}
					else if (arg0.getKeyCode()==KeyEvent.VK_ENTER && userInp.getText().equals(userFinal)==false){
						passInp.setText("");
						warning.setText("<html><font color = 'white'>Incorrect username/password</font></html>");
					}
					else if (arg0.getKeyCode()==KeyEvent.VK_ENTER && passInp.getText().equals(passFinal)==false) {
						passInp.setText("");
						warning.setText("<html><font color = 'white'>Incorrect username/password</font></html>");
					}
				}

				@Override
				public void keyReleased(KeyEvent arg0) {					
				}

				@Override
				public void keyTyped(KeyEvent arg0) {					
				}
				
			});
			passInp.addKeyListener(new KeyListener() {

				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode()==KeyEvent.VK_ENTER&&userInp.getText().equals(userFinal)&&passInp.getText().equals(passFinal)) {
						new wang();
						dispose();
					}
					else if (arg0.getKeyCode()==KeyEvent.VK_ENTER && userInp.getText().equals(userFinal)==false){
						passInp.setText("");
						warning.setText("<html><font color = 'white'>Incorrect username/password</font></html>");
					}
					else if (arg0.getKeyCode()==KeyEvent.VK_ENTER && passInp.getText().equals(passFinal)==false) {
						passInp.setText("");
						warning.setText("<html><font color = 'white'>Incorrect username/password</font></html>");
					}
				}
				@Override
				public void keyReleased(KeyEvent arg0) {					
				}
				@Override
				public void keyTyped(KeyEvent arg0) {					
				}
				
			});
		JPanel fillerPanel2 = new JPanel();
		fillerPanel2.setPreferredSize(new Dimension(210,5));
		fillerPanel2.setOpaque(false);
		subLogin.add(fillerPanel2);
		subLogin.add(enter);
		subLogin.add(warning);
		return subLogin;
	}
	private class DrawingStars implements MouseMotionListener,MouseListener{
		public void mouseClicked(MouseEvent e) {
			points.add(new Point(e.getX(), e.getY()));
		}
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private JLabel linking(ImageIcon imageIcon, String link){
		JLabel icon= new JLabel();
		icon.setIcon(imageIcon);
		icon.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Desktop.getDesktop().browse(new URL(link).toURI());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		return icon;	
	}
	private JPanel filler(int x, int y) {
		JPanel filler= new JPanel();
		filler.setPreferredSize(new Dimension(x,y));
		filler.setOpaque(false);
		return filler;
	}
	private ImageIcon getImageIcon(String image) {
		Image gotImage = new ImageIcon(this.getClass().getResource(image)).getImage();
		ImageIcon gotIcon = new ImageIcon(gotImage);
		return gotIcon;
	}
	public static void main(String[] args) {
		main m = new main();
	}
	private JPanel mainPan;
	private JPanel loginPanel;
		private JPanel subLogin;
	private JPanel header;
	private ArrayList<Point> points = new ArrayList<Point>();
;
	
}
