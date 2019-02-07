package My_Games;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;

import realTime_Traffic_Simulator.AudioClass;

import java.util.Random;
import java.util.Scanner;

public class SnailMania {

	private final JButton bat1, bat2,bat3;
	private final panelClass panel;
	private final JFrame frame;
	private JSlider slider;
	private int a = 10;
	private int b = 20;
	private firstSnake dd;
	private secondSnail ss;
	private JTextField tf;
	private Color snailsColor = new Color(222, 184, 135);;
	private Boolean flushColors = false;
	private Color colorArray[] = { Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.PINK,
			Color.GRAY, Color.ORANGE, Color.YELLOW, Color.WHITE };
	private Random rand = new Random();
	private String X_Name[] = new String[1000000];
	private String Y_Name[] = new String[1000000];
	private File scanFile;
	private Scanner readFileX,readFileY;
	private PrintStream savex,savey;
	private int latestFile;
	private final JLabel label,label2;
	public SnailMania() {
	
		panel = new panelClass();
		panel.setBackground(new Color(204, 204, 204));
		panel.setBorder(BorderFactory.createLoweredBevelBorder());
		panel.setBounds(10, 20, 620, 300);
		panel.setLayout(null);
		panel.addMouseListener(new mouseHandler());
		
		bat1 = new JButton("C");
		bat1.setToolTipText("Change the snails color..");
		bat1.setBounds(633, 20, 57, 70);
		bat1.setBorder(BorderFactory.createEmptyBorder());
		bat1.setBackground(Color.LIGHT_GRAY);
		bat1.setForeground(Color.RED);
		bat1.setFont(new Font("Serif", Font.BOLD, 30));
		bat1.addActionListener(new buttonHandler());

		bat2 = new JButton("F");
		bat2.setToolTipText("Allow the snail's body to flicker color");
		bat2.setBounds(633, 80, 57, 70);
		bat2.setBorder(BorderFactory.createEmptyBorder());
		bat2.setBackground(Color.LIGHT_GRAY);
		bat2.setForeground(Color.RED);
		bat2.setFont(new Font("Serif", Font.BOLD, 33));
		bat2.addActionListener(new buttonHandler());
		
		bat3 = new JButton("X");
		bat3.setToolTipText("Follow me");
		bat3.setBounds(633, 150, 57, 70);
		bat3.setBorder(BorderFactory.createEmptyBorder());
		bat3.setBackground(Color.LIGHT_GRAY);
		bat3.setForeground(Color.RED);
		bat3.setFont(new Font("Serif", Font.BOLD, 30));
		bat3.addActionListener(new buttonHandler());
		
		label=new JLabel();
		label.setBounds(10,10,70,30);
		label.setIcon(new ImageIcon(getClass().getResource("snailIcon/attack.gif")));
		label.setVisible(false);
		
		label2=new JLabel();
		label2.setBounds(50,50,150,100);
		//label2.setIcon(new ImageIcon(getClass().getResource("snailIcon/greetings.png")));

		tf = new JTextField(10 + " MS");
		tf.setBorder(BorderFactory.createEmptyBorder());
		tf.setEditable(false);
		tf.setFont(new Font("Serif", Font.PLAIN, 28));
		tf.setBackground(Color.LIGHT_GRAY);
		tf.setForeground(Color.GREEN);
		tf.setBounds(520, 327, 100, 40);

		TitledBorder bd = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(),
				"Adjust The Snail's speeds", TitledBorder.CENTER, TitledBorder.BELOW_TOP);
		bd.setTitleFont(new Font("Serif", Font.PLAIN, 15));

		slider = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 10);
		slider.setMajorTickSpacing(10);
		slider.setBackground(new Color(211, 211, 211));
		slider.setPaintTicks(true);
		slider.setBorder(bd);
		slider.setBounds(10, 327, 500, 40);
		slider.addChangeListener((ChangeEvent e) -> {
			tf.setText(slider.getValue() + "MS");
		});

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setLayout(null);
		frame.setTitle(
				"Your Long Life Dream Of Becoming A Snail, \"YUCK!\" .Its Finally Brought To Light, Happy SNAILING!");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(panel);
		frame.add(slider);
		frame.add(tf);
		frame.add(bat1);
		frame.add(bat2);
		frame.add(bat3);
		
		panel.add(label);
		panel.add(label2);
		
	}

	private class mouseHandler extends MouseAdapter {

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			try {
				dd.stop();
				dd = new firstSnake(arg0.getX(), arg0.getY());
				dd.start();

			} catch (Exception e) {

				dd = new firstSnake(arg0.getX(), arg0.getY());
				dd.start();
			}
		}

	}

	private class panelClass extends JPanel {

		public void paintComponent(Graphics g) {

			super.paintComponent(g);

			g.setColor(snailsColor);
			g.fillOval(a, b, 60, 30);

			g.setColor(Color.WHITE);
			g.fillOval(45 + a, b + 5, 5, 5);
			g.fillOval(45 + a, b + 20, 5, 5);
			
			ImageIcon backgroundImage=new ImageIcon(getClass().getResource("background/background.png"));
			 Image img=backgroundImage.getImage();
			 
			g.drawImage(img,0,0,null);
		}
	}

	public static void main(String[] args) {

		new SnailMania();

	}

	private class firstSnake extends Thread {

		private int x, y;

		public firstSnake(int x_cor, int y_cor) {

			x = x_cor;
			y = y_cor;
		}

		@Override
		public void run() {
			
			Graphics g = panel.getGraphics();
			histroyCollector();
			
			for (;;) {
				try {

					if (flushColors == false) {

						if (a < x) {

							g.setColor(Color.LIGHT_GRAY);
							g.fillOval(a, b, 30, 60);
							g.fillOval(a, b, 60, 30);

							g.fillOval(45 + a, b + 5, 5, 5);
							g.fillOval(45 + a, b + 20, 5, 5);
							
							savex.println(a);
							savey.println(b);

							a += 1;
							g.setColor(snailsColor);
							g.fillOval(a, b, 60, 30);

							g.setColor(colorArray[rand.nextInt(colorArray.length)].brighter());
							g.fillOval(45 + a, b + 5, 5, 5);
							g.fillOval(45 + a, b + 20, 5, 5);
							
							savex.println(a);
							savey.println(b);
							
							new AudioClass("A1");

							Thread.sleep(slider.getValue());

						} else if (a > x) {

							g.setColor(Color.LIGHT_GRAY);
							g.fillOval(a, b, 30, 60);
							g.fillOval(a, b, 60, 30);

							g.fillOval(10 + a, b + 5, 5, 5);
							g.fillOval(10 + a, b + 20, 5, 5);
							
							savex.println(a);
							savey.println(b);

							a -= 1;
							g.setColor(snailsColor);
							g.fillOval(a, b, 60, 30);

							g.setColor(colorArray[rand.nextInt(colorArray.length)].brighter());
							g.fillOval(10 + a, b + 5, 5, 5);
							g.fillOval(10 + a, b + 20, 5, 5);
							
							savex.println(a);
							savey.println(b);
							
							new AudioClass("A1");

							Thread.sleep(slider.getValue());

						} else if (b < y) {

							g.setColor(Color.LIGHT_GRAY);
							g.fillOval(a, b, 30, 60);
							g.fillOval(a, b, 60, 30);

							g.fillOval(a + 20, b + 45, 5, 5);
							g.fillOval(a + 5, b + 45, 5, 5);
							
							savex.println(a);
							savey.println(b);
							b += 1;
							g.setColor(snailsColor);
							g.fillOval(a, b, 30, 60);

							g.setColor(colorArray[rand.nextInt(colorArray.length)].brighter());
							g.fillOval(a + 20, b + 45, 5, 5);
							g.fillOval(a + 5, b + 45, 5, 5);
							
							savex.println(a);
							savey.println(b);
							
							new AudioClass("A14");

							Thread.sleep(slider.getValue());

						} else if (b > y) {

							g.setColor(Color.LIGHT_GRAY);
							g.fillOval(a, b, 30, 60);
							g.fillOval(a, b, 60, 30);

							g.fillOval(a + 20, b + 10, 5, 5);
							g.fillOval(a + 5, b + 10, 5, 5);
							
							savex.println(a);
							savey.println(b);

							b -= 1;
							g.setColor(snailsColor);
							g.fillOval(a, b, 30, 60);

							g.setColor(colorArray[rand.nextInt(colorArray.length)].brighter());
							g.fillOval(a + 20, b + 10, 5, 5);
							g.fillOval(a + 5, b + 10, 5, 5);
							
							savex.println(a);
							savey.println(b);
							
							new AudioClass("A14");

							Thread.sleep(slider.getValue());
						}
					} else if (flushColors == true) {

						if (a < x) {

							g.setColor(Color.LIGHT_GRAY);
							g.fillOval(a, b, 30, 60);
							g.fillOval(a, b, 60, 30);

							g.fillOval(45 + a, b + 5, 5, 5);
							g.fillOval(45 + a, b + 20, 5, 5);

							a += 1;
							g.setColor(colorArray[rand.nextInt(colorArray.length)].brighter());
							g.fillOval(a, b, 60, 30);

							g.setColor(Color.WHITE);
							g.fillOval(45 + a, b + 5, 5, 5);
							g.fillOval(45 + a, b + 20, 5, 5);

							Thread.sleep(slider.getValue());

						} else if (a > x) {

							g.setColor(Color.LIGHT_GRAY);
							g.fillOval(a, b, 30, 60);
							g.fillOval(a, b, 60, 30);

							g.fillOval(10 + a, b + 5, 5, 5);
							g.fillOval(10 + a, b + 20, 5, 5);

							a -= 1;
							g.setColor(colorArray[rand.nextInt(colorArray.length)].brighter());
							g.fillOval(a, b, 60, 30);

							g.setColor(Color.WHITE);
							g.fillOval(10 + a, b + 5, 5, 5);
							g.fillOval(10 + a, b + 20, 5, 5);

							Thread.sleep(slider.getValue());

						} else if (b < y) {

							g.setColor(Color.LIGHT_GRAY);
							g.fillOval(a, b, 30, 40);
							g.fillOval(a, b, 60, 30);

							g.fillOval(a + 20, b + 45, 5, 5);
							g.fillOval(a + 5, b + 45, 5, 5);

							b += 1;
							g.setColor(colorArray[rand.nextInt(colorArray.length)].brighter());
							g.fillOval(a, b, 30, 60);

							g.setColor(Color.WHITE);
							g.fillOval(a + 20, b + 45, 5, 5);
							g.fillOval(a + 5, b + 45, 5, 5);

							Thread.sleep(slider.getValue());

						} else if (b > y) {

							g.setColor(Color.LIGHT_GRAY);
							g.fillOval(a, b, 30, 60);
							g.fillOval(a, b, 60, 30);

							g.fillOval(a + 20, b + 10, 5, 5);
							g.fillOval(a + 5, b + 10, 5, 5);

							b -= 1;
							g.setColor(colorArray[rand.nextInt(colorArray.length)].brighter());
							g.fillOval(a, b, 30, 60);

							g.setColor(Color.WHITE);
							g.fillOval(a + 20, b + 10, 5, 5);
							g.fillOval(a + 5, b + 10, 5, 5);
							Thread.sleep(slider.getValue());
						}
					}
					
				} catch (InterruptedException e) {

					System.out.println(e);
				}
			}
			
		}
		
	}

	private class buttonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == bat1) {

				Color n = JColorChooser.showDialog(frame, "Customize Your Snail's Appearance", Color.GRAY);

				if (n == null) {

					snailsColor = new Color(222, 184, 135);
				} else {

					snailsColor = n;
				}

			} else if (e.getSource() == bat2) {

				if (flushColors == false) {

					flushColors = true;
					JOptionPane.showMessageDialog(frame, "Snail Can Flush Colors", "Glowing Effect",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (flushColors == true) {

					flushColors = false;
					JOptionPane.showMessageDialog(frame, "Snail Cannot Flush Colors", "Glowing Effect",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else{
				
				JOptionPane.showMessageDialog(frame,"Following initiated");
				
				try {
					ss.stop();
					ss = new secondSnail();
					ss.start();

				} catch (Exception es) {

					ss = new secondSnail();
					ss.start();
				}
			}
		}
	}
	private class secondSnail extends Thread{
		
		private int X_cor;
		private int Y_cor;
		@Override
		public void run(){
			
			Graphics g = panel.getGraphics();
			label.setVisible(true);
			savex.close();
			savey.close();
			try {
				readFileX=new Scanner(new File(X_Name[latestFile-1]));					//latest file is used hope it wont be a problem
					readFileY=new Scanner(new File(Y_Name[latestFile-1]));
					panel.repaint();
					while(readFileX.hasNext()){
						
						X_cor=readFileX.nextInt();
						Y_cor=readFileY.nextInt();
						
						label.setBounds(X_cor, Y_cor, 70, 30);
						Thread.sleep(5);
					}
			}
			catch(InterruptedException e){
				
				System.out.println("error");
			}
			
			catch(FileNotFoundException e){
				
				System.out.println("error");
			}
		}
	}
	public void histroyCollector(){
		
		try {
			for (latestFile = 1; latestFile< X_Name.length;latestFile++) {

				X_Name[latestFile] = "C:\\Users\\Frankline Sable\\IdeaProjects\\New And Improved Programs Maseno\\src\\SnailManiaNav\\X-Cordinates["
						+ latestFile + "].txt";
				
				Y_Name[latestFile] = "C:\\Users\\Frankline Sable\\IdeaProjects\\New And Improved Programs Maseno\\src\\SnailManiaNav\\Y-Cordinates["
						+ latestFile + "].txt";
				
				scanFile = new File(X_Name[latestFile]);
				scanFile = new File(Y_Name[latestFile]);
				
				if (!scanFile.exists()) {

					savex = new PrintStream(X_Name[latestFile]);
					savey = new PrintStream(Y_Name[latestFile]);
					break;
				}
				
			}

		} catch (FileNotFoundException ex) {

			System.out.println(ex);

		}
	}
}

					
			