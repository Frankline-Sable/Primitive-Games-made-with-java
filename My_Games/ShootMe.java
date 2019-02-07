package My_Games;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import realTime_Traffic_Simulator.AudioClass;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class ShootMe {

    private JButton bat1;
    private panelClass panel;
    private JFrame frame;
    private initialPaint ip1;
    private JSlider slider;

    public ShootMe() {

        panel = new panelClass();
        panel.setBackground(new Color(204, 204, 204));
        panel.setBorder(BorderFactory.createLoweredBevelBorder());
        panel.setBounds(20, 20, 650, 280);
        panel.setLayout(null);

        bat1 = new JButton("Fire");
        bat1.setBounds(570, 310, 100, 40);
        bat1.setBorder(BorderFactory.createRaisedBevelBorder());
        bat1.setBackground(Color.GRAY);
        bat1.setForeground(Color.RED);
        bat1.setFont(new Font("Serif", Font.BOLD, 20));
        bat1.addActionListener(new buttonHandler());
        bat1.addMouseListener(new mouseHandler());

        TitledBorder bd = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(),
                "Adjust The Shooting Velocity", TitledBorder.CENTER, TitledBorder.BELOW_TOP);
        bd.setTitleFont(new Font("Serif", Font.PLAIN, 18));

        slider = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 10);
        slider.setMajorTickSpacing(10);
        slider.setBackground(new Color(211, 211, 211));
        slider.setPaintTicks(true);
        slider.setBorder(bd);
        slider.setBounds(0, 225, 500, 50);

        panel.add(slider);

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setLayout(null);
        frame.setTitle("Aim & Shoot");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(bat1);
        frame.add(panel);

    }

    public class panelClass extends JPanel {

        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            g.setColor(Color.BLACK);
            g.fill3DRect(10, 50, 150, 40, true);
            g.fill3DRect(15, 50, 20, 100, true);
            g.fillArc(25, 70, 50, 50, 100, 100);

        }
    }

    private class initialPaint extends Thread {

        @Override
        public void run() {

            Graphics g = panel.getGraphics();
            try {
                int a = 160;

                for (int i = 0; i < 15; i++) {

                    g.setColor(new Color(0, 0, 0));

                    g.drawLine(165, 50, 190, 40);
                    g.drawLine(165, 55, 190, 45);
                    g.drawLine(165, 60, 190, 50);
                    g.drawLine(170, 65, 190, 60);
                    g.drawLine(165, 70, 190, 70);
                    g.drawLine(170, 73, 190, 80);
                    g.drawLine(165, 75, 190, 85);
                    g.drawLine(165, 80, 190, 90);
                    g.drawLine(165, 85, 190, 95);
                    g.drawLine(165, 90, 190, 100);

                    g.fillOval(a, 55, 30, 30);

                    Thread.sleep(slider.getValue());

                    g.setColor(panel.getBackground());
                    g.fillRect(160, 40, 450, 60);

                    a += 30;
                }
            } catch (InterruptedException ex) {

                System.out.println(ex);
            }

        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new ShootMe();

    }

    private class buttonHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            ip1 = new initialPaint();
            ip1.start();
            new AudioClass("A16");

        }

    }

    private class mouseHandler extends MouseAdapter {

        @Override
        public void mouseEntered(MouseEvent me) {

            Graphics g = panel.getGraphics();
            g.setColor(Color.RED);
            g.drawLine(620, 65, 620, 85);
            g.drawLine(610, 75, 630, 75);
        }

        @Override
        public void mouseExited(MouseEvent me) {

            Graphics g = panel.getGraphics();
            g.setColor(Color.LIGHT_GRAY);
            g.drawLine(620, 65, 620, 85);
            g.drawLine(610, 75, 630, 75);
        }
    }

}
