package My_Games;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;

import realTime_Traffic_Simulator.AudioClass;

import java.awt.Image;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BouncingBall {

	private JFrame frame, frame2,frame3;
	private JPanel panel, firstPanel, sPanel;
	private JLabel loaderLabel;
	private JLabel label;
	private ImageIcon balA;
	private JButton exec;
	private Thread play, begin;
	private ImageIcon imgIcons[] = new ImageIcon[10];
	private Image img[] = new Image[10];
	private Random rand = new Random();
	private JProgressBar loader;
	private int imgArg;
	private Color bd = null;
	private int pick = 0, num = 50;
	private int x_Cor = 20, y_Cor = 20;
	private String loads[] = { "Loading discrete Components....", "Loading Graphics Objectes....",
			"Optimising For Standard view....", "Objects Loaded Successfully!", "Finalising....", "All Done! :-D" };

	public BouncingBall() {

		imageLoader();
		 BackGroundFrame();

		label = new JLabel(balA);
		label.setBounds(20, 20, 48, 48);

		exec = new buttonClass();
		exec.setBorder(BorderFactory.createEmptyBorder());
		exec.setBounds(210, 400, 150, 40);
		exec.addActionListener((ActionEvent) -> {

			exec.setVisible(false);

			play = new bounceThread();
			play.start();

			imgArg = rand.nextInt(8);
			panel.repaint();
		});
		panel = new panelClass();
		panel.setBounds(5, 5, 575, 455);
		panel.setLayout(null);
		panel.add(label);
		panel.add(exec);

		frame = new JFrame();
		frame.setTitle("A Ball Bouncing Off A Vitual Wall");
		frame.setVisible(false);
		frame.setAlwaysOnTop(true);
		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setLayout(null);

		frame.add(panel);
		intitialRuntime();
	}

	public static void main(String[] args) {

		new BouncingBall();

	}

	protected void imageLoader() {

		balA = new ImageIcon(getClass().getResource("BallImg/ballA.png"));

		for (int i = 0; i < 8; i++) {

			imgIcons[i] = new ImageIcon(getClass().getResource("BounceBackground/D" + i + ".png"));
			img[i] = imgIcons[i].getImage();

		}
	}

	private class bounceThread extends Thread {
		@Override
		public void run() {

			try {
				initialBallNav();

				minusPlusY(); // 1
				minusMinusX(); // 2
				plusMinusY(); // 3
				plusPlusX(); // 4

				minusPlusY(); // 1
				minusMinusX(); // 2
				plusMinusY(); // 3

				plusPlusY(); // 5
				plusMinusX(); // 6
				minusMinusY(); // 7
				minusPlusX(); // 8

				plusPlusY(); // 5
				plusMinusX(); // 6
				minusMinusY(); // 7

				minusPlusY(); // 1
				minusMinusX(); // 2
				plusMinusY(); // 3
				plusPlusX(); // 4

				minusPlusY(); // 1
				minusMinusX(); // 2
				plusMinusY(); // 3
				plusPlusX(); // 4

				minusPlusY(); // 1
				minusMinusY(); // 7
				minusPlusX(); // 8
				plusPlusY(); // 5

				plusMinusX(); // 6
				minusMinusY(); // 7
				minusPlusX(); // 8
				plusPlusY(); // 5

				plusMinusY(); // 3
				plusPlusX(); // 4
				minusPlusY(); // 1
				minusMinusX(); // 2

				plusMinusY(); // 3
				plusPlusX(); // 4
				minusPlusY(); // 1
				minusMinusX(); // 2

				plusMinusY(); // 3
				plusPlusY(); // 5
				plusMinusX(); // 6
				minusMinusY(); // 7

				driftToTheCenter();

				exec.repaint();
				panel.repaint();
			} catch (InterruptedException ex) {

				System.err.println("Thread Interrupted! Error Unknown, i.e: \n" + ex);
			}
		}
	}

	public void play(int which) {

		switch (which) {

		case 1:

			new AudioClass("A1");
			break;
		case 2:

			new AudioClass("A2");

			break;
		case 3:

			new AudioClass("A9");
			break;
		default:

			new AudioClass("AA");
		}
	}

	public void initialBallNav() throws InterruptedException {

		for (;;) {

			x_Cor++;

			Thread.sleep(1);
			label.setBounds(x_Cor, y_Cor, 48, 48);

			if (x_Cor > 530) {
				play(2);
				panel.repaint();
				break;
			}
		}
	}

	public void plusPlusX() throws InterruptedException {

		x_Cor = label.getX();
		y_Cor = label.getY();
		int bc = 0;

		for (;;) {

			x_Cor++;
			y_Cor++;
			bc++;

			Thread.sleep(1);
			label.setBounds(x_Cor, y_Cor, 48, 48);

			if (x_Cor > 530) {
				play(2);
				break;
			}
			BorderGesture(bc);
		}
	}

	public void plusPlusY() throws InterruptedException {

		x_Cor = label.getX();
		y_Cor = label.getY();
		int bc = 0;

		for (;;) {

			x_Cor++;
			y_Cor++;
			bc++;

			Thread.sleep(1);
			label.setBounds(x_Cor, y_Cor, 48, 48);

			if (y_Cor > 410) {
				play(2);
				break;
			}
			BorderGesture(bc);
		}
	}

	public void minusMinusX() throws InterruptedException {

		x_Cor = label.getX();
		y_Cor = label.getY();
		int bc = 0;

		for (;;) {

			x_Cor--;
			y_Cor--;
			bc++;

			Thread.sleep(1);
			label.setBounds(x_Cor, y_Cor, 48, 48);

			if (x_Cor < -3) {
				play(2);
				break;
			}
			BorderGesture(bc);
		}
	}

	public void minusMinusY() throws InterruptedException {

		x_Cor = label.getX();
		y_Cor = label.getY();
		int bc = 0;

		for (;;) {

			x_Cor--;
			y_Cor--;
			bc++;

			Thread.sleep(1);
			label.setBounds(x_Cor, y_Cor, 48, 48);

			if (y_Cor < -3) {
				play(2);
				break;
			}
			BorderGesture(bc);
		}
	}

	public void plusMinusX() throws InterruptedException {

		x_Cor = label.getX();
		y_Cor = label.getY();
		int bc = 0;

		for (;;) {

			x_Cor++;
			y_Cor--;
			bc++;

			Thread.sleep(1);
			label.setBounds(x_Cor, y_Cor, 48, 48);

			if (x_Cor > 530) {
				play(2);
				break;
			}
			BorderGesture(bc);
		}

	}

	public void plusMinusY() throws InterruptedException {

		x_Cor = label.getX();
		y_Cor = label.getY();
		int bc = 0;

		for (;;) {

			x_Cor++;
			y_Cor--;
			bc++;

			Thread.sleep(1);
			label.setBounds(x_Cor, y_Cor, 48, 48);

			if (y_Cor < -3) {
				play(2);
				break;
			}
			BorderGesture(bc);
		}

	}

	public void minusPlusY() throws InterruptedException {

		x_Cor = label.getX();
		y_Cor = label.getY();
		int bc = 0;

		for (;;) {

			x_Cor--;
			y_Cor++;
			bc++;

			Thread.sleep(1);
			label.setBounds(x_Cor, y_Cor, 48, 48);

			if (y_Cor > 410) {
				play(2);
				break;
			}
			BorderGesture(bc);

		}

	}

	public void minusPlusX() throws InterruptedException {

		x_Cor = label.getX();
		y_Cor = label.getY();
		int bc = 0;

		for (;;) {

			x_Cor--;
			y_Cor++;
			bc++;

			Thread.sleep(1);
			label.setBounds(x_Cor, y_Cor, 48, 48);

			if (x_Cor < -3) {
				play(2);
				break;
			}
			BorderGesture(bc);

		}

	}

	public void driftToTheCenter() throws InterruptedException {

		x_Cor = label.getX();
		y_Cor = label.getY();
		int bc = 0;
		int bb = 50;

		for (;;) {

			for (;;) {

				y_Cor++;

				Thread.sleep(1);
				label.setBounds(x_Cor, y_Cor, 48, 48);

				if (y_Cor > 410) {
					play(2);
					break;
				}
				BorderGesture(bc);
			}
			;

			for (;;) {

				y_Cor--;

				Thread.sleep(1);
				label.setBounds(x_Cor, y_Cor, 48, 48);

				if (y_Cor < bb) {
					break;
				}
			}
			Thread.sleep(20);
			bb += 20;

			if (bb > 500) {
				break;
			}
		}

		play.stop();
		exec.setVisible(true);
	}

	private class panelClass extends JPanel {
		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			g.drawImage(img[imgArg], 0, 0, null);
			panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
					BorderFactory.createMatteBorder(5, 5, 5, 5, bd)));
		}
	}

	private class buttonClass extends JButton {
		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			g.drawImage(img[imgArg], 0, 0, null);
			g.setColor(Color.CYAN);
			g.setFont(new Font("Serif", Font.BOLD, 24));
			g.drawString("START", 41, 27);
		}
	}

	public void BorderGesture(int bc) {

		if (bc < 70) {

			bd = Color.RED;
			panel.repaint();
		} else {

			bd = null;
			panel.repaint();
		}

	}

	private void intitialRuntime() {

		loader = new JProgressBar();
		loader.setBounds(10, 400, 550, 10);
		loader.setBackground(new Color(102, 0, 51));
		loader.setMinimum(0);
		loader.setMaximum(255);

		loaderLabel = new labelClass();
		loaderLabel.setBounds(200, 415, 300, 30);

		firstPanel = new panelClass2();
		firstPanel.setLayout(null);

		TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(),
				"Program Author: Frankline Sable", TitledBorder.RIGHT, TitledBorder.ABOVE_BOTTOM);
		tb.setTitleFont(new Font("Serif", Font.BOLD, 16));
		tb.setTitleColor(Color.BLACK.brighter());
		sPanel = new panelClass3();
		sPanel.setLayout(null);
		sPanel.setBorder(tb);
		sPanel.setBounds(5, 5, 565, 380);

		frame2 = new JFrame();
		frame2.setUndecorated(true);
		frame2.setAlwaysOnTop(true);
		frame2.setVisible(true);
		frame2.setSize(590, 440);
		frame2.setLocationRelativeTo(null);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.getContentPane().setBackground(Color.BLACK);
		frame2.setLayout(new BorderLayout());
		frame2.add(firstPanel, BorderLayout.CENTER);

		firstPanel.add(loader);
		firstPanel.add(sPanel);
		firstPanel.add(loaderLabel);
		begin = new initialThread();
		begin.start();
	}

	private class labelClass extends JLabel {
		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 16));
			g.drawString(loads[pick], 10, 20);
		}
	}

	private class panelClass2 extends JPanel {
		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			g.drawImage(img[0], 0, 0, null);
		}
	}

	private class initialThread extends Thread {
		@Override
		public void run() {

			for (int i = 0; i <= 300; i++) {

				try {

					loader.setValue(i);
					Thread.sleep(20);

					if (i < num) {

						sPanel.repaint();
						if (i <= 255) {

							loader.setForeground(new Color(i, i, i));
						}
					}

					else {
						num += 50;
						pick++;
					}
				} catch (InterruptedException ex) {

					System.out.println("Thread Interrupted, in line: " + ex);
				}
			}
			play(1);
			frame2.setVisible(false);
			frame.setVisible(true);
		}
	}

	private class panelClass3 extends JPanel {
		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			g.drawImage(img[pick], 0, 0, null);
		}
	}
	protected void BackGroundFrame(){
		
		frame3=new JFrame();
		frame3.setUndecorated(true);
		frame3.setAlwaysOnTop(false);
		frame3.setResizable(false);
		frame3.setVisible(true);
		frame3.setSize(1366,768);
		frame3.setLayout(null);
		frame3.getContentPane().setBackground(Color.BLACK);
		
	}
}
