package My_Games;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class MySimpleGame {

	private JFrame frame;
	private final sidePanelClass sPanel;
	private final bodyPanelClass bodyPanel;
	private final headerPanelClass headerPanel;
	private final buttomPanelClass bPanel;
	private JButton confirm;
	private animationClass anime;
	private JButton buttons[] = new JButton[20];
	private JLabel label;
	private Image img[] = new Image[100];
	private ImageIcon headerPanelIcon;
	private ImageIcon iconList[] = new ImageIcon[100];
	private String bG[] = { "Default", "Image A", "Image B", "Image C", "Image D", "Image E", "Image F", "Image G",
			"Image H", "Image I", "Image J", "Image K", "Image L" };
	private String buttonNames[] = { "Colour", "Skin", "Tracks", "Trail ", "Glow", "Renbw", " " };
	private final JComboBox list;
	private int coverPic = 0;
	private int X_cor = 5;
	private int Y_cor = 200;
	private Color snailColor = new Color(222, 184, 135);
	private Color trailColor = Color.LIGHT_GRAY;
	private Boolean EE = true;
	private Boolean WW = false;
	private Boolean NN = false;
	private Boolean SS = false;
	private Boolean tracks = false;
	private Boolean rainBow = false;
	private int vA, vB, vC, vD;
	private final JSlider slider;
	private Color array[] = { Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.MAGENTA, Color.BLUE,
			Color.WHITE, Color.CYAN, Color.PINK, Color.GRAY };
	private Boolean flicker = false;
	private glowThread glow;
	private Random rand = new Random();
	private Random randX = new Random();
	private Random randY = new Random();
	private Random randXX = new Random();
	private Random randYY = new Random();
	private Random randXXX = new Random();
	private Random randYYY = new Random();
	private File checkFile;
	private Scanner readFileX;
	private Scanner readFileY;
	private Scanner readLast;
	private PrintStream writeFileX;
	private PrintStream writeLast;
	private PrintStream writeFileY;
	private String X_Name[] = new String[1000000];
	private String Y_Name[] = new String[1000000];
	private String last_File[] = new String[1000000];
	private int savedLast;
	private int fileNo;
	private JLabel enemy, enemy2, enemy3, level;
	private villainClass en;
	private villainClass2 en2;
	private villainClass3 en3;
	private int f = 0;
	private Boolean end = true;
	private JPanel mainPanel, diaPanel, diaPanel2;
	private finalPanel fPanel;
	private JLabel endLabel;
	private int currentLevel = 1;
	private ImageIcon level1img, level2img, level3img, level4img;
	private JDialog dialog;
	private int villainSpeed = 10;
	private timeClass timer;
	private JTextField scoreText;
	Timer time;

	public MySimpleGame() {

		headerPanelIcon = new ImageIcon(getClass().getResource("Header/header.gif"));

		for (int i = 1; i < bG.length; i++) {

			iconList[i] = new ImageIcon(getClass().getResource("Images/D" + i + ".png"));
			img[i] = iconList[i].getImage();

		}
		memoryFile();

		bPanel = new buttomPanelClass();
		bPanel.setBackground(Color.LIGHT_GRAY);
		bPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		bPanel.setBounds(0, 535, 937, 29);
		bPanel.setLayout(null);

		sPanel = new sidePanelClass();
		sPanel.setBackground(new Color(201, 201, 201));
		sPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		sPanel.setBounds(850, 115, 80, 425);
		sPanel.setLayout(null);

		headerPanel = new headerPanelClass();
		headerPanel.setBackground(Color.CYAN);
		headerPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		headerPanel.setBounds(0, 0, 935, 110);
		headerPanel.setLayout(null);

		endLabel = new JLabel(new ImageIcon(getClass().getResource("GameOver/img1.gif")));
		endLabel.setBounds(-5, -5, 950, 605);

		bodyPanel = new bodyPanelClass(coverPic);
		bodyPanel.setBackground(Color.DARK_GRAY);
		bodyPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		bodyPanel.setBounds(0, 115, 850, 420);
		bodyPanel.setLayout(null);

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.GRAY);
		mainPanel.setBounds(0, 0, 950, 605);
		mainPanel.setLayout(null);

		fPanel = new finalPanel();
		fPanel.setBackground(Color.RED);
		fPanel.setBounds(-5, -5, 950, 605);
		fPanel.setLayout(null);

		enemy = new JLabel(new ImageIcon(getClass().getResource("villain/villain.gif")));
		enemy.setBounds(100, 100, 90, 50);
		enemy.setVisible(false);

		enemy2 = new JLabel(new ImageIcon(getClass().getResource("villain/villain2.gif")));
		enemy2.setBounds(100, 100, 90, 50);
		enemy2.setVisible(false);

		enemy3 = new JLabel(new ImageIcon(getClass().getResource("villain/villain3.gif")));
		enemy3.setBounds(300, 100, 90, 50);
		enemy3.setVisible(false);

		scoreText = new JTextField("Score 0");
		scoreText.setBounds(520, 3, 80, 25);
		scoreText.setFont(new Font("serif", Font.BOLD, 20));
		bPanel.add(scoreText);

		label = new JLabel(headerPanelIcon);
		label.setBounds(headerPanel.getBounds());

		list = new JComboBox(bG);
		list.setSelectedIndex(0);
		list.setBounds(2, 10, 80, 35);
		list.setFont(new Font("Serif", Font.PLAIN, 16));

		slider = new JSlider(SwingConstants.HORIZONTAL, 0, 10, 7);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setBounds(0, 5, 500, 29);

		int bv = 55;
		for (int i = 0; i < 7; i++) {

			buttons[i] = new JButton(buttonNames[i]);
			buttons[i].setBounds(2, bv, 78, 50);
			buttons[i].addActionListener(new buttonHandler());
			buttons[i].setFont(new Font("Serif", Font.PLAIN, 16));
			bv += 50;
		}
		buttons[5].setEnabled(false);
		buttons[6].setIcon(new ImageIcon(getClass().getResource("buttonIcons/nextButton.gif")));
		buttons[6].setBorder(BorderFactory.createEmptyBorder());
		buttons[6].setBackground(sPanel.getBackground());

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(950, 605);
		frame.setTitle("A Simple Program For Simple Thinkers");
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setLayout(null);
		frame.addWindowListener(new windowHandler());

		for (int i = 0; i < buttonNames.length; i++) {

			sPanel.add(buttons[i]);
		}

		headerPanel.add(label);
		sPanel.add(list);
		bPanel.add(slider);
		bodyPanel.add(enemy);
		bodyPanel.add(enemy2);
		bodyPanel.add(enemy3);

		frame.add(mainPanel);
		mainPanel.add(headerPanel);
		mainPanel.add(bodyPanel);
		mainPanel.add(bPanel);
		mainPanel.add(sPanel);

		slider.addChangeListener((ChangeEvent e) -> {

		});

		bodyPanel.addMouseListener(new MouseAdapter() {
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

		list.addItemListener((ItemEvent e) -> { /// ***********************

			coverPic = list.getSelectedIndex();
		});
	}

	private class bodyPanelClass extends JPanel {

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

		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// JFrame.setDefaultLookAndFeelDecorated(true);
		new MySimpleGame();

	}

	private class buttonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == buttons[0]) {

				Color x = JColorChooser.showDialog(frame, "Pick A Background Color", Color.DARK_GRAY);

				if (x == null) {

					bodyPanel.setBackground(Color.DARK_GRAY);

				} else {

					bodyPanel.setBackground(x);
				}
			} else if (e.getSource() == buttons[1]) {

				Color x = JColorChooser.showDialog(frame, "Pick A Color For Your Snail", Color.MAGENTA);

				if (x == null) {

					snailColor = new Color(222, 184, 135);

				} else {

					snailColor = x;
				}
			} else if (e.getSource() == buttons[2]) {

				if (tracks) {

					JOptionPane.showMessageDialog(frame, "Previous Snail Tracks Have Been Hidden",
							"SNAIL TRACKS VISIBLITY", JOptionPane.INFORMATION_MESSAGE);
					tracks = false;
					vA = 0;
					vB = 0;
					vC = 0;
					vD = 0;

				} else {

					JOptionPane.showMessageDialog(frame, "Previous Snail Tracks\nAre Now Visible",
							"SNAIL TRACKS VISIBLITY", JOptionPane.WARNING_MESSAGE);
					tracks = true;
					vA = 1;
					vB = 2;
					vC = 3;
					vD = 4;
				}
				bodyPanel.repaint();
			} else if (e.getSource() == buttons[3]) {

				Color x = JColorChooser.showDialog(frame, "Pick A Color For Your Snail", Color.GREEN);

				if (x == null) {

					trailColor = Color.LIGHT_GRAY;

				} else {

					trailColor = x;
				}
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

				timer = new timeClass(0);
				time = new Timer(1000, timer);
				time.start();
			}
			if (flicker && tracks) {

				buttons[5].setEnabled(true);
			} else {

				buttons[5].setEnabled(false);

			}

		}
	}

	private class buttomPanelClass extends JPanel {

		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
		}
	}

	private class sidePanelClass extends JPanel {

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
			} catch (InterruptedException e) {

				System.out.println(e);
			}

		}
	}

	public void coverUp(int dir) {

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
						// System.out.println(savedLast);
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

					enemy.setVisible(true);
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

					System.out.println(e);
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

					enemy2.setVisible(true);
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

					System.out.println(e);
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

					System.out.println(e);
				}
			}
		}
	}
	public void gameOver2() {

		for (int h = 0; h <= 50; h++) {

			for (int i = 0; i <= 90; i++) {

				if (enemy2.getX() + i == X_cor && enemy2.getY() + h == Y_cor) {

					en2.stop();
					terminate();
					break;
				} else if (enemy3.getX() + i == X_cor && enemy3.getY() + h == Y_cor) {

					en3.stop();
					terminate();
					break;
				} else if (enemy.getX() + i == X_cor && enemy.getY() + h == Y_cor) {

					en.stop();
					terminate();
					break;
				} else {

					for (int z = 0; z <= 40; z++) {

						for (int t = 0; t < 80; t++) {

							if (X_cor + t == enemy.getX() && Y_cor + z == enemy.getY()) {

								en.stop();
								terminate();
								break;
							} else if (X_cor + t == enemy2.getX() && Y_cor + z == enemy2.getY()) {

								en2.stop();
								terminate();
								break;
							} else if (X_cor + t == enemy3.getX() && Y_cor + z == enemy3.getY()) {

								en3.stop();
								terminate();
								break;
							}
						}
					}
					break;
				}

			}

		}

	}

	public void terminate() {

		mainPanel.setVisible(false);

		fPanel.add(endLabel);
		frame.add(fPanel);
	}

	private class finalPanel extends JPanel {
		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);

		}
	}

	protected void levelDialog(int levelNo) {

		level1img = new ImageIcon(getClass().getResource("levels/level1.gif"));
		level2img = new ImageIcon(getClass().getResource("levels/level2.gif"));
		level3img = new ImageIcon(getClass().getResource("levels/level3.gif"));
		level4img = new ImageIcon(getClass().getResource("levels/level4.gif"));

		level = new JLabel();

		confirm = new JButton("Okay");
		confirm.setBounds(100, 192, 150, 45);
		confirm.setBackground(Color.LIGHT_GRAY);
		confirm.setFont(new Font("Serif", Font.BOLD, 25));

		diaPanel = new JPanel();
		diaPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		diaPanel.setBackground(Color.GRAY);
		diaPanel.setBounds(30, 5, 295, 185);
		diaPanel.setLayout(new BorderLayout());

		diaPanel2 = new JPanel();
		diaPanel2.setBorder(BorderFactory.createRaisedBevelBorder());
		diaPanel2.setBackground(Color.CYAN);
		diaPanel2.setLayout(null);

		dialog = new JDialog();
		dialog.setSize(370, 280);
		dialog.setVisible(true);
		dialog.setAlwaysOnTop(true);
		dialog.setLocationRelativeTo(null);
		dialog.setTitle("Level Changed");
		dialog.setLayout(new BorderLayout());

		diaPanel.add(level, BorderLayout.CENTER);
		diaPanel2.add(diaPanel);
		diaPanel2.add(confirm);
		dialog.add(diaPanel2, BorderLayout.CENTER);

		confirm.addActionListener((ActionEvent) -> {

			dialog.setVisible(false);

		});

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

			level.setIcon(level2img);
			currentLevel = 3;
		} else if (currentLevel == 3) {

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
			scoreText.setText("Score " + score);

		}
	}
}
