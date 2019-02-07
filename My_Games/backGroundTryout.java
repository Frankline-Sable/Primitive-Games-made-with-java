/**
 * 
 */
package My_Games;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Image;

/**
 * @author Frankline Sable
 *
 */
public class backGroundTryout {

	/**
	 * @param args
	 */
	private PanelClass backgroundPanel;
	private static JFrame frame;
	private Image img;
	private ImageIcon backgroundImage;
	private JLabel label;
	private JButton bat;
	
	public backGroundTryout(){
		
		backgroundPanel=new PanelClass();
		backgroundPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		backgroundPanel.setLayout(null);
		backgroundPanel.setBounds(50,30,600,400);
		backgroundPanel.setBackground(Color.BLACK);
		
		backgroundImage=new ImageIcon(getClass().getResource("background/background.png"));
		
	
		bat=new JButton("Fail");
		bat.setBounds(200,100,100,30);
		
		backgroundPanel.add(bat);

		
		frame.add(backgroundPanel);
		
		img=backgroundImage.getImage();
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				
				frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(700, 500);
				frame.setVisible(true);
				frame.getContentPane().setBackground(Color.LIGHT_GRAY);
				frame.setLayout(null);
				frame.setTitle("Chanding  The BK");
				frame.setLocationRelativeTo(null);
				frame.setOpacity(0.70f);
				new  backGroundTryout();
				
				
			}
		});

	}
	private class PanelClass extends JPanel{
		
		public void paintComponent(Graphics g){
			
			super.paintComponent(g);
			g.drawImage(img,0,0,null);
		}
	}
	
	

}
