package Splash;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.*;

public class SplashGUI extends JFrame {
	private JPanel mainPan, north, south, east, west, center;
	private Font title = new Font("Lucida Blackletter", Font.BOLD | Font.PLAIN, 50);
	private int alpha_Color = 50, title_YPos = 75;

	public SplashGUI() {
		components();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setSize(830, 580);

	}

	private void components() {
		// Background image
		ImageIcon flow_Background;
		try {
			flow_Background = new ImageIcon(ImageIO.read(SplashGUI.class.getResource("/imgs/splash.jpeg")));
			setContentPane(new JLabel(flow_Background));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setLayout(new BorderLayout());

		mainPan = new JPanel(new BorderLayout());
		mainPan.setPreferredSize(new Dimension(830, 580));
		mainPan.setOpaque(false);

		north = new JPanel() {
			public void paintComponent(Graphics g) {
				g.setFont(title);
				g.setColor(new Color(255, 255, 255, alpha_Color));
				g.drawString("splash", 335, title_YPos);
				if (alpha_Color < 250) {
					alpha_Color+=5;

				}
				if (title_YPos > 50) {
					title_YPos--;
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				repaint();
			}
		};
		JButton open = new JButton("Continue");
		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Main_Frame();
			}
			
		});
		north.setPreferredSize(new Dimension(830, 580));
		north.setOpaque(false);

		mainPan.add(north, BorderLayout.NORTH);
		add(mainPan);
	}
}