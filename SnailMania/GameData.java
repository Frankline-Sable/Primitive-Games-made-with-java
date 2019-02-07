/**
 *
 */
package SnailMania;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

import javax.swing.event.ChangeEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

/**
 * @author Frankline Sable
 *
 */
public class GameData {

	private final JFrame mainFrame;
	private final JPanel sidePanel, bodyPanel, headerPanel, buttomPanel, componentPanel;
	private Thread anime, glow, en, en2, en3, it;
	private JButton buttons[] = new JButton[20];
	private ImageIcon iconList[] = new ImageIcon[100], villain1, villain2, villain3;
	private String X_Name[] = new String[1000000];
	private String Y_Name[] = new String[1000000];
	private String last_File[] = new String[1000000];
	private Image img[] = new Image[100];
	private JLabel label, enemy, enemy2, enemy3, level, endLabel;
	private ImageIcon headerPanelIcon, level1img, level2img, level3img, level4img, nextIcon, endImage;
	private File checkFile;
	private Scanner readFileX, readFileY, readLast;
	private PrintStream writeFileX, writeLast, writeFileY;
	private JPanel mainPanel, diaPanel, diaPanel2;
	private JFrame dialog;
	private timeClass timer;
	private JTextField scoreText, speedText;
	private Timer time;
	private final JSlider slider;
	private final JComboBox<?> list;
	private Boolean EE = true, WW = false, NN = false, SS = false, tracks = false, rainBow = false, flicker = false;
	private int vA, vB, vC, vD, currentLevel = 1, savedLast, fileNo, coverPic = 0, X_cor = 5, Y_cor = 200,
			villainSpeed = 10;;
	private String bG[] = { "Default", "Image A", "Image B", "Image C", "Image D", "Image E", "Image F", "Image G",
			"Image H", "Image I", "Image J", "Image K", "Image L" };
	private String buttonNames[] = { "Background", "Skin Color", "Trail Color", "Tracks", "Flicker", "Rainbow", " " };
	private Color trailColor = Color.LIGHT_GRAY, snailColor = new Color(222, 184, 135);
	private Color array[] = { Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.MAGENTA, Color.BLUE,
			Color.WHITE, Color.CYAN, Color.PINK, Color.GRAY };
	private Random rand = new Random(), randX = new Random(), randY = new Random(), randXX = new Random(),
			randYY = new Random(), randXXX = new Random(), randYYY = new Random();

	public GameData() {

		programImages();
		memoryFile();

		buttomPanel = new buttomPanelClass();
		buttomPanel.setBackground(Color.LIGHT_GRAY);
		buttomPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		buttomPanel.setBounds(0, 535, 850, 35);
		buttomPanel.setLayout(null);

		sidePanel = new sidePanelClass();
		sidePanel.setBackground(Color.RED);
		sidePanel.setBorder(BorderFactory.createLoweredBevelBorder());
		sidePanel.setBounds(850, 115, 98, 454);
		sidePanel.setLayout(null);

		headerPanel = new headerPanelClass();
		headerPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setBounds(0, 0, 951, 113);
		headerPanel.setLayout(new BorderLayout());

		bodyPanel = new bodyPanelClass(coverPic);
		bodyPanel.setBackground(Color.DARK_GRAY);
		bodyPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		bodyPanel.setBounds(0, 115, 850, 420);
		bodyPanel.setLayout(null);

		componentPanel = new finalPanel();
		componentPanel.setBackground(Color.BLACK);
		componentPanel.setBounds(0, 0, 966, 605);
		componentPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		componentPanel.setLayout(null);

		mainPanel = new mainPanelClass();
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		mainPanel.setLayout(null);

		endLabel = new JLabel(endImage);
		endLabel.setBounds(0, 0, 970, 572);

		enemy = new JLabel(villain1);
		enemy.setBounds(-100, 100, 90, 50);

		enemy2 = new JLabel(villain2);
		enemy2.setBounds(-200, 100, 90, 50);

		enemy3 = new JLabel(villain3);
		enemy3.setBounds(-300, 100, 90, 50);

		scoreText = new JTextField("Score 0");
		scoreText.setBounds(2, 405, 97, 50);
		scoreText.setForeground(Color.BLACK);
		scoreText.setBackground(buttomPanel.getBackground());
		scoreText.setBorder(BorderFactory.createMatteBorder(5, 5, 1, 1, Color.BLUE.darker()));
		scoreText.setFont(new Font("serif", Font.PLAIN, 25));

		speedText = new JTextField("�Snail's Speed 7MS");
		speedText.setBounds(640, 3, 185, 30);
		speedText.setForeground(Color.DARK_GRAY);
		speedText.setBackground(buttomPanel.getBackground());
		speedText.setBorder(BorderFactory.createMatteBorder(5, 5, 1, 1, Color.GRAY));
		speedText.setFont(new Font("serif", Font.ITALIC, 21));

		label = new JLabel(headerPanelIcon);
		label.setBounds(headerPanel.getBounds());

		list = new JComboBox<Object>(bG);
		TitledBorder listBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE),
				"Actions", TitledBorder.CENTER, TitledBorder.ABOVE_TOP);
		listBorder.setTitleFont(new Font("Serif", Font.PLAIN, 20));
		listBorder.setTitleColor(Color.CYAN.brighter());
		list.setBorder(listBorder);
		list.setBackground(Color.GRAY);
		list.setForeground(Color.ORANGE.brighter());
		list.setSelectedIndex(0);
		list.setBounds(2, 0, 95, 55);
		list.setFont(new Font("Serif", Font.PLAIN, 16));

		slider = new JSlider(SwingConstants.HORIZONTAL, 0, 10, 7);
		slider.setMajorTickSpacing(1);
		TitledBorder bd = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(),
				"Drag to change the speed", TitledBorder.CENTER, TitledBorder.BELOW_TOP);
		bd.setTitleFont(new Font("Serif", Font.PLAIN, 14));
		bd.setTitleColor(Color.BLACK);
		slider.setBorder(bd);
		slider.setPaintTicks(true);
		slider.setBounds(5, 5, 500, 29);

		int bv = 55;

		for (int i = 0; i < 7; i++) {

			buttons[i] = new JButton(buttonNames[i]);
			buttons[i].setBounds(2, bv, 95, 50);
			buttons[i].addActionListener(new buttonHandler());
			buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLUE.darker()));
			buttons[i].setBackground(Color.gray);
			buttons[i].setForeground(Color.BLACK.brighter());
			buttons[i].setFont(new Font("Serif", Font.PLAIN, 16));
			bv += 50;
		}
		buttons[5].setEnabled(false);
		buttons[6].setIcon(nextIcon);

		sidePanel.add(scoreText);
		buttomPanel.add(speedText);
		headerPanel.add(label, BorderLayout.CENTER);
		sidePanel.add(list);
		buttomPanel.add(slider);
		bodyPanel.add(enemy);
		bodyPanel.add(enemy2);
		bodyPanel.add(enemy3);
		componentPanel.add(headerPanel);
		componentPanel.add(bodyPanel);
		componentPanel.add(buttomPanel);
		componentPanel.add(sidePanel);
		mainPanel.add(componentPanel);

		for (int i = 0; i < buttonNames.length; i++) {

			sidePanel.add(buttons[i]);
		}
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.setSize(966, 613);
		mainFrame.setTitle("A Simple Program For Simple Thinkers");
		mainFrame.setLocationRelativeTo(null);
		mainFrame.getContentPane().setBackground(Color.GRAY);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setMinimumSize(new Dimension(966, 613));
		mainFrame.addWindowListener(new windowHandler());

		mainFrame.add(mainPanel, BorderLayout.CENTER);

		slider.addChangeListener((ChangeEvent e) -> {

			speedText.setText("�Snail's Speed " + slider.getValue() + "MS");
		});

		bodyPanel.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseReleased(MouseEvent e) {

				try {
					anime.stop();
					anime = new animationClass(e.getX(), e.getY());
					anime.start();

				} catch (Exception ex) {

					anime = new animationClass(e.getX(), e.getY());
					anime.start();
				}
			}
		});

		list.addItemListener((ItemEvent e) -> {

			coverPic = list.getSelectedIndex();
		});
	}

	private class bodyPanelClass extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public bodyPanelClass(int pickedPic) {

			coverPic = pickedPic;

		}

		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);

			if (coverPic == 0) {

			} else {

				g.drawImage(img[coverPic], 0, 0, null);

			}
			if (EE) {

				g.setColor(snailColor);
				g.fillOval(X_cor, Y_cor, 80, 40);

				g.setColor(Color.BLACK);
				g.fillOval(X_cor + 60, Y_cor + 5, 5, 5);
				g.fillOval(X_cor + 60, Y_cor + 30, 5, 5);
				g.drawLine(X_cor + 75, Y_cor + 15, X_cor + 75, Y_cor + 25);
			} else if (WW) {

				g.setColor(snailColor);
				g.fillOval(X_cor, Y_cor, 80, 40);
				g.setColor(Color.BLACK);
				g.fillOval(X_cor + 20, Y_cor + 5, 5, 5);
				g.fillOval(X_cor + 20, Y_cor + 30, 5, 5);
				g.drawLine(X_cor + 10, Y_cor + 15, X_cor + 10, Y_cor + 25);
			} else if (SS) {

				g.setColor(snailColor);
				g.fillOval(X_cor, Y_cor, 40, 80);
				g.setColor(Color.BLACK);
				g.fillOval(X_cor + 20, Y_cor + 5, 5, 5);
				g.fillOval(X_cor + 20, Y_cor + 30, 5, 5);
				g.drawLine(X_cor + 10, Y_cor + 25, X_cor + 10, Y_cor + 40);
			} else if (NN) {

				g.setColor(snailColor);
				g.fillOval(X_cor, Y_cor, 40, 80);
				g.setColor(Color.BLACK);
				g.fillOval(X_cor + 20, Y_cor + 55, 5, 5);
				g.fillOval(X_cor + 20, Y_cor + 70, 5, 5);
				g.drawLine(X_cor + 10, Y_cor + 15, X_cor + 10, Y_cor + 35);
			}
			if (tracks == true) {

				list.setEnabled(false);

			} else {

				list.setEnabled(true);
				repaint();
			}

		}
	}

	private class headerPanelClass extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);

		}
	}

	private class buttonHandler implements ActionListener {

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == buttons[0]) {

				Color x = JColorChooser.showDialog(mainFrame, "Pick A Background Color", Color.DARK_GRAY);

				if (x == null) {

					bodyPanel.setBackground(Color.DARK_GRAY);

				} else {

					bodyPanel.setBackground(x);
				}
			} else if (e.getSource() == buttons[1]) {

				Color x = JColorChooser.showDialog(mainFrame, "Pick A Color For Your Snail", Color.MAGENTA);

				if (x == null) {

					snailColor = new Color(222, 184, 135);

				} else {

					snailColor = x;
				}
			} else if (e.getSource() == buttons[2]) {

				Color x = JColorChooser.showDialog(mainFrame, "Pick A Color For Your Snail Trail", Color.GREEN);

				if (x == null) {

					trailColor = Color.LIGHT_GRAY;

				} else {

					trailColor = x;
				}

			} else if (e.getSource() == buttons[3]) {

				if (tracks) {

					JOptionPane.showMessageDialog(mainFrame, "Snail Tracks Will Not Be Shown", "SNAIL TRACKS INVISIBLE",
							JOptionPane.WARNING_MESSAGE);
					tracks = false;
					vA = 0;
					vB = 0;
					vC = 0;
					vD = 0;

				} else {

					JOptionPane.showMessageDialog(mainFrame, "Snail Tracks Will Be Shown", "SNAIL TRACKS VISIBLE",
							JOptionPane.INFORMATION_MESSAGE);
					tracks = true;
					vA = 1;
					vB = 2;
					vC = 3;
					vD = 4;
				}
				bodyPanel.repaint();

			} else if (e.getSource() == buttons[4]) {

				if (flicker) {

					glow.stop();
					flicker = false;

				} else {

					try {
						glow.stop();
						glow = new glowThread();
						glow.start();
					} catch (Exception ex) {

						glow = new glowThread();
						glow.start();
					}
					flicker = true;
				}
			} else if (e.getSource() == buttons[5]) {

				if (rainBow) {

					rainBow = false;

				} else {

					rainBow = true;
				}
			} else {

				levelDialog(currentLevel);

			}
			if (flicker && tracks) {

				buttons[5].setEnabled(true);
			} else {

				buttons[5].setEnabled(false);

			}

		}
	}

	private class buttomPanelClass extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
		}
	}

	private class mainPanelClass extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
		}
	}

	private class sidePanelClass extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
		}
	}

	private class animationClass extends Thread {

		private int X_mov, Y_mov;

		public animationClass(int x, int y) {

			X_mov = x;
			Y_mov = y;

			saved();
			writeFileX.println(X_mov);
			writeFileY.println(Y_mov);
		}

		@Override
		public void run() {

			Graphics g = bodyPanel.getGraphics();

			try {
				for (;;) {

					if (X_cor < X_mov) {

						EE = true;
						WW = false;
						NN = false;
						SS = false;

						coverUp(vA);
						X_cor++;

						writeFileX.println(X_mov);
						writeFileY.println(Y_mov);

						g.setColor(snailColor);
						g.fillOval(X_cor, Y_cor, 80, 40);

						g.setColor(Color.BLACK);
						g.fillOval(X_cor + 60, Y_cor + 5, 5, 5);
						g.fillOval(X_cor + 60, Y_cor + 30, 5, 5);
						g.drawLine(X_cor + 75, Y_cor + 15, X_cor + 75, Y_cor + 25);

						Thread.sleep(slider.getValue());

					} else if (X_cor > X_mov) {

						WW = true;
						EE = false;
						NN = false;
						SS = false;

						coverUp(vB);

						X_cor--;

						writeFileX.println(X_mov);
						writeFileY.println(Y_mov);

						g.setColor(snailColor);
						g.fillOval(X_cor, Y_cor, 80, 40);
						g.setColor(Color.BLACK);
						g.fillOval(X_cor + 20, Y_cor + 5, 5, 5);
						g.fillOval(X_cor + 20, Y_cor + 30, 5, 5);
						g.drawLine(X_cor + 10, Y_cor + 15, X_cor + 10, Y_cor + 25);

						Thread.sleep(slider.getValue());

					} else if (Y_cor > Y_mov) {

						SS = true;
						WW = false;
						NN = false;
						EE = false;

						coverUp(vC);

						Y_cor--;

						writeFileX.println(X_mov);
						writeFileY.println(Y_mov);

						g.setColor(snailColor);
						g.fillOval(X_cor, Y_cor, 40, 80);
						g.setColor(Color.BLACK);
						g.fillOval(X_cor + 20, Y_cor + 5, 5, 5);
						g.fillOval(X_cor + 20, Y_cor + 30, 5, 5);
						g.drawLine(X_cor + 10, Y_cor + 25, X_cor + 10, Y_cor + 40);
						Thread.sleep(slider.getValue());

					} else if (Y_cor < Y_mov) {

						NN = true;
						WW = false;
						SS = false;
						EE = false;

						coverUp(vD);

						Y_cor++;

						writeFileX.println(X_mov);
						writeFileY.println(Y_mov);

						g.setColor(snailColor);
						g.fillOval(X_cor, Y_cor, 40, 80);
						g.setColor(Color.BLACK);
						g.fillOval(X_cor + 20, Y_cor + 55, 5, 5);
						g.fillOval(X_cor + 20, Y_cor + 70, 5, 5);
						g.drawLine(X_cor + 10, Y_cor + 15, X_cor + 10, Y_cor + 35);

						Thread.sleep(slider.getValue());

					}
				}
			} catch (Exception e) {

			}

		}
	}

	public void coverUp(int dir) {

		try {
			Graphics g = bodyPanel.getGraphics();

			if (tracks && rainBow) {

				g.setColor(rainbow());
			} else {

				g.setColor(trailColor);
			}

			switch (dir) {

			case 1:

				g.fillOval(X_cor, Y_cor, 80, 40);
				g.fillOval(X_cor, Y_cor, 40, 80);
				g.fillOval(X_cor + 60, Y_cor + 5, 5, 5);
				g.fillOval(X_cor + 60, Y_cor + 30, 5, 5);
				g.drawLine(X_cor + 75, Y_cor + 15, X_cor + 75, Y_cor + 25);

			case 2:

				g.fillOval(X_cor, Y_cor, 80, 40);
				g.fillOval(X_cor, Y_cor, 40, 80);
				g.fillOval(X_cor + 20, Y_cor + 5, 5, 5);
				g.fillOval(X_cor + 20, Y_cor + 30, 5, 5);
				g.drawLine(X_cor + 10, Y_cor + 15, X_cor + 10, Y_cor + 25);

			case 3:

				g.fillOval(X_cor, Y_cor, 80, 40);
				g.fillOval(X_cor, Y_cor, 40, 80);
				g.fillOval(X_cor + 20, Y_cor + 5, 5, 5);
				g.fillOval(X_cor + 20, Y_cor + 30, 5, 5);
				g.drawLine(X_cor + 10, Y_cor + 25, X_cor + 10, Y_cor + 40);

			case 4:

				g.fillOval(X_cor, Y_cor, 80, 40);
				g.fillOval(X_cor, Y_cor, 40, 80);
				g.fillOval(X_cor + 20, Y_cor + 55, 5, 5);
				g.fillOval(X_cor + 20, Y_cor + 70, 5, 5);
				g.drawLine(X_cor + 10, Y_cor + 15, X_cor + 10, Y_cor + 35);

			default:

			}
		} catch (Exception e) {

			// exception ignored
		}
	}

	private class glowThread extends Thread {

		@Override
		public void run() {

			Graphics g = bodyPanel.getGraphics();

			try {
				for (;;) {

					snailColor = array[rand.nextInt(array.length)];
					g.setColor(snailColor);
					if (EE || WW) {

						g.fillOval(X_cor, Y_cor, 80, 40);

					} else if (SS || NN) {

						g.fillOval(X_cor, Y_cor, 40, 80);
					}
					Thread.sleep(10);

				}
			} catch (InterruptedException e) {

				System.out.println(e);
			}
		}
	}

	public Color rainbow() {

		return snailColor;

	}

	public void saved() {

		try {
			for (fileNo = 0; fileNo < X_Name.length; fileNo++) {

				X_Name[fileNo] = "C:\\Users\\Frankline Sable\\IdeaProjects\\New And Improved Programs Maseno\\src\\My_Games\\Saved\\X_cor\\X["
						+ fileNo + "].txt";

				Y_Name[fileNo] = "C:\\Users\\Frankline Sable\\IdeaProjects\\New And Improved Programs Maseno\\src\\My_Games\\Saved\\Y_cor\\Y["
						+ fileNo + "].txt";

				checkFile = new File(X_Name[fileNo]);
				checkFile = new File(Y_Name[fileNo]);

				if (!checkFile.exists()) {

					writeFileX = new PrintStream(X_Name[fileNo]);
					writeFileY = new PrintStream(Y_Name[fileNo]);

					break;
				}
			}
		} catch (FileNotFoundException e) {

			System.out.println("Error with the histroy directory!");
		}
	}

	private class windowHandler extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {

			try {
				for (int i = 0; i < X_Name.length; i++) {

					last_File[i] = "C:\\Users\\Frankline Sable\\IdeaProjects\\New And Improved Programs Maseno\\src\\My_Games\\Saved\\lastFile["
							+ i + "].txt";

					checkFile = new File(last_File[i]);

					if (!checkFile.exists()) {

						writeLast = new PrintStream(last_File[i]);

						break;
					}
				}
				writeLast.println(fileNo);
			} catch (FileNotFoundException ex) {

				System.out.println("Error with the histroy directory!");
			}
		}
	}

	public void memoryFile() {

		try {

			for (int i = 0; i < X_Name.length; i++) {

				last_File[i] = "C:\\Users\\Frankline Sable\\IdeaProjects\\New And Improved Programs Maseno\\src\\My_Games\\Saved\\lastFile["
						+ i + "].txt";

				checkFile = new File(last_File[i]);

				if (!checkFile.exists()) {

					readLast = new Scanner(new File(last_File[i - 1]));

					while (readLast.hasNext()) {

						savedLast = readLast.nextInt();
						break;

					}
					readFileX = new Scanner(new File(
							"C:\\Users\\Frankline Sable\\IdeaProjects\\New And Improved Programs Maseno\\src\\My_Games\\Saved\\X_cor\\X["
									+ savedLast + "].txt"));
					readFileY = new Scanner(new File(
							"C:\\Users\\Frankline Sable\\IdeaProjects\\New And Improved Programs Maseno\\src\\My_Games\\Saved\\Y_cor\\Y["
									+ savedLast + "].txt"));

					while (readFileX.hasNext()) {

						X_cor = readFileX.nextInt();
						Y_cor = readFileY.nextInt();
						break;

					}
					break;
				}
			}

		} catch (FileNotFoundException e) {

			System.out.println("error");
		}
	}

	private class villainClass extends Thread {

		@Override
		public void run() {

			int X_start = randX.nextInt(850) + 1;
			int Y_start = randY.nextInt(420) + 1;

			for (;;) {

				try {

					int X_end = randX.nextInt(850) + 1;
					int Y_end = randY.nextInt(420) + 1;

					for (;;) {

						if (X_start < X_end) {

							enemy.setBounds(X_start, Y_start, 90, 50);
							Thread.sleep(villainSpeed);
							gameOver2();
							X_start++;

						} else if (X_start > X_end) {

							enemy.setBounds(X_start, Y_start, 90, 50);
							Thread.sleep(villainSpeed);
							gameOver2();
							X_start--;

						} else if (Y_start < Y_end) {

							enemy.setBounds(X_start, Y_start, 90, 50);
							Thread.sleep(villainSpeed);
							gameOver2();
							Y_start++;
						} else if (Y_start > Y_end) {

							enemy.setBounds(X_start, Y_start, 90, 50);
							Thread.sleep(villainSpeed);
							gameOver2();
							Y_start--;
						} else {

							break;
						}
					}

				} catch (Exception e) {

				}

			}

		}
	}

	private class villainClass2 extends Thread {

		@Override
		public void run() {

			int XX_start = randXX.nextInt(850) + 1;
			int YY_start = randYY.nextInt(420) + 1;

			for (;;) {

				try {

					int XX_end = randXX.nextInt(850) + 1;
					int YY_end = randYY.nextInt(420) + 1;

					for (;;) {

						if (XX_start < XX_end) {

							enemy2.setBounds(XX_start, YY_start, 90, 50);
							gameOver2();

							Thread.sleep(villainSpeed);
							XX_start++;

						} else if (XX_start > XX_end) {

							enemy2.setBounds(XX_start, YY_start, 90, 50);
							gameOver2();

							Thread.sleep(villainSpeed);
							XX_start--;

						} else if (YY_start < YY_end) {

							enemy2.setBounds(XX_start, YY_start, 90, 50);
							gameOver2();

							Thread.sleep(villainSpeed);
							YY_start++;
						} else if (YY_start > YY_end) {

							enemy2.setBounds(XX_start, YY_start, 90, 50);
							gameOver2();

							Thread.sleep(villainSpeed);
							YY_start--;
						} else {

							break;
						}
					}

				} catch (Exception e) {

				}

			}

		}
	}

	private class villainClass3 extends Thread {

		@Override
		public void run() {

			int XXX_start = randXXX.nextInt(850) + 1;
			int YYY_start = randYYY.nextInt(420) + 1;

			for (;;) {

				try {

					int XXX_end = randXXX.nextInt(850) + 1;
					int YYY_end = randYYY.nextInt(420) + 1;

					enemy3.setVisible(true);
					for (;;) {

						if (XXX_start < XXX_end) {

							enemy3.setBounds(XXX_start, YYY_start, 90, 50);
							Thread.sleep(villainSpeed);
							gameOver2();
							XXX_start++;

						} else if (XXX_start > XXX_end) {

							enemy3.setBounds(XXX_start, YYY_start, 90, 50);
							Thread.sleep(villainSpeed);
							gameOver2();
							XXX_start--;

						} else if (YYY_start < YYY_end) {

							enemy3.setBounds(XXX_start, YYY_start, 90, 50);
							Thread.sleep(villainSpeed);
							gameOver2();
							YYY_start++;
						} else if (YYY_start > YYY_end) {

							enemy3.setBounds(XXX_start, YYY_start, 90, 50);
							Thread.sleep(villainSpeed);
							gameOver2();
							YYY_start--;
						} else {

							break;
						}
					}

				} catch (Exception e) {

				}
			}
		}
	}

	@SuppressWarnings({ "deprecation", "unused" })
	public void gameOver2() {

		for (int h = 0; h <= 50; h++) {

			for (int i = 0; i <= 90; i++) {

				if (enemy2.getX() + i == X_cor && enemy2.getY() + h == Y_cor) {

					en2.stop();
					terminate(true);
					break;
				} else if (enemy3.getX() + i == X_cor && enemy3.getY() + h == Y_cor) {

					en3.stop();
					terminate(true);
					break;
				} else if (enemy.getX() + i == X_cor && enemy.getY() + h == Y_cor) {

					en.stop();
					terminate(true);
					break;
				} else {

					for (int z = 0; z <= 40; z++) {

						for (int t = 0; t < 80; t++) {

							if (X_cor + t == enemy.getX() && Y_cor + z == enemy.getY()) {

								terminate(true);
								en.stop();
								break;
							} else if (X_cor + t == enemy2.getX() && Y_cor + z == enemy2.getY()) {

								terminate(true);
								en2.stop();
								break;
							} else if (X_cor + t == enemy3.getX() && Y_cor + z == enemy3.getY()) {

								terminate(true);
								en3.stop();
								break;
							}
						}
					}
					break;
				}

			}

		}

	}

	private class finalPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);

		}
	}

	@SuppressWarnings("deprecation")
	private void levelDialog(int levelNo) {

		levelM();

		level1img = new ImageIcon(getClass().getResource("levels/level1.gif"));
		level2img = new ImageIcon(getClass().getResource("levels/level2.gif"));
		level3img = new ImageIcon(getClass().getResource("levels/level3.gif"));
		level4img = new ImageIcon(getClass().getResource("levels/level4.gif"));

		level = new JLabel();
		level.setBounds(-20, -10, 480, 210);

		diaPanel = new JPanel();
		diaPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		diaPanel.setBackground(Color.BLACK);
		diaPanel.setBounds(0, 0, 455, 210);
		diaPanel.setLayout(null);

		diaPanel2 = new JPanel();
		diaPanel2.setBorder(BorderFactory.createRaisedBevelBorder());
		diaPanel2.setBackground(Color.BLACK);
		diaPanel2.setLayout(null);

		dialog = new JFrame();
		dialog.setSize(465, 230);
		dialog.setVisible(true);
		dialog.setAlwaysOnTop(true);
		dialog.setLocationRelativeTo(null);
		dialog.setTitle("Level Changed");
		dialog.setLayout(new BorderLayout());

		diaPanel.add(level, BorderLayout.CENTER);
		diaPanel2.add(diaPanel);
		dialog.add(diaPanel2);

		try {

			it.stop();
			it = new invisiThread();
			it.start();

		} catch (Exception ex) {

			invisiThread it = new invisiThread();
			it.start();
		}

		if (currentLevel == 1) {

			try {

				en2.stop();
				en3.stop();

				en = new villainClass();
				en.start();

				villainSpeed = 10;
				level.setIcon(level1img);
				currentLevel = 2;

			} catch (Exception ex) {

				en = new villainClass();
				en.start();

				villainSpeed = 10;
				level.setIcon(level1img);
				currentLevel = 2;
			}
		} else if (currentLevel == 2) {

			en2 = new villainClass2();
			en2.start();

			timer = new timeClass(0);
			time = new Timer(1000, timer);
			time.start();

			level.setIcon(level2img);
			currentLevel = 3;
		} else if (currentLevel == 3) {

			time.stop();
			timer = new timeClass(0);
			time = new Timer(1000, timer);
			time.start();

			en3 = new villainClass3();
			en3.start();

			villainSpeed = 5;

			level.setIcon(level3img);
			currentLevel = 4;
		} else {
			en = new villainClass();
			en.start();
			en2 = new villainClass2();
			en2.start();
			en3 = new villainClass3();
			en3.start();

			level.setIcon(level4img);
			currentLevel = 1;
		}

	}

	private class timeClass implements ActionListener {

		private int score;

		public timeClass(int timeGot) {

			score = timeGot;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			score++;
			scoreText.setText("�Score " + score);

		}
	}

	public void terminate(Boolean termin) {

		if (termin) {

			try {

				mainPanel.remove(componentPanel);
				mainPanel.add(endLabel);
				mainPanel.repaint();

			} catch (Exception ex) {

				mainPanel.remove(componentPanel);
				mainPanel.add(endLabel);
				mainPanel.repaint();
			}
			termin = false;
			GameOver();
		} else {
			mainPanel.repaint();
			// System.out.println("d");
		}
	}

	public void GameOver() {

		Graphics g = mainPanel.getGraphics();
		g.drawImage(endImage.getImage(), 0, 0, null);
		mainPanel.repaint();
	}

	protected void programImages() {

		headerPanelIcon = new ImageIcon(getClass().getResource("Header/header.gif"));
		villain1 = new ImageIcon(getClass().getResource("villain/villain.gif"));
		villain2 = new ImageIcon(getClass().getResource("villain/villain2.gif"));
		villain3 = new ImageIcon(getClass().getResource("villain/villain3.gif"));
		nextIcon = new ImageIcon(getClass().getResource("buttonIcons/nextButton.gif"));
		endImage = new ImageIcon(getClass().getResource("GameOver/end.gif"));

		for (int i = 1; i < bG.length; i++) {

			iconList[i] = new ImageIcon(getClass().getResource("Images/D" + i + ".png"));
			img[i] = iconList[i].getImage();

		}
	}

	public static void main(String[] args) {
		
		new GameData();

	}

	public class invisiThread extends Thread {
		@Override
		public void run() {

			try {
				for (float i = (float) 1.00; i >= 0.0; i -= 0.01) {

					dialog.setOpacity(i);
					Thread.sleep(50);
					buttons[6].setEnabled(false);
				}
				dialog.setVisible(false);
				buttons[6].setEnabled(true);

			} catch (Exception ex) {

				System.out.print(ex);
			}

		}
	}

	public static void levelM() {

		JFrame.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				new fram();

			}
		});
	}

}
