package marnie.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class CinematicPanel extends JPanel {

	int w, h;

	public JButton play;

	private Timer t;

	private JLabel BG = new JLabel(new ImageIcon(getClass().getResource("StartImage.png")));
	private int x, y, timerCount;
	
	public JTextField tfNome;
	
	public JLabel messageDialog = new JLabel(new ImageIcon(getClass().getResource("MessageDialog.png")));
	public JButton yes;
	public JButton no;
	
	
	public CinematicPanel(int w, int h) {

		this.w = w;
		this.h = h;
		setLayout(null);
		setPreferredSize(new Dimension(w, h));

		init();
	}

	public void init() {
		
		yes = new JButton();
		yes.setSize(62,21);
		yes.setLocation(105,135);
		yes.setContentAreaFilled(false);
		
		no = new JButton();
		no.setSize(62,21);
		no.setLocation(32,135);
		no.setContentAreaFilled(false);
		
		tfNome = new JTextField();
		tfNome.setSize(273,30);
		tfNome.setLocation(441,413);
		tfNome.setBackground(new Color(254,215,136));
		tfNome.setFont(new Font("Courier new", Font.BOLD, 20));
		tfNome.setForeground(new Color(80, 29, 17));
		tfNome.setBorder(null);
		
		play = new JButton();

		BG.setSize(1152, 1154);
		animationBG();
		play.setSize(205, 110);
		play.setLocation((w / 2) - (play.getWidth() / 2), h - 180);
		play.setContentAreaFilled(false);
		
		messageDialog.setSize(200,200);
		
		add(tfNome);
		add(play);
		tfNome.setVisible(false);
		play.setVisible(false);
		
		add(yes);
		add(no);
		yes.setVisible(false);
		no.setVisible(false);
		
		add(messageDialog);
		messageDialog.setVisible(false);
		
		add(BG);
		
		
		
	}

	public void animationBG() {
		x = 0;
		y = 0;
		timerCount = 0;

		t = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timerCount++;
				if (timerCount >= 10) {
					y -= 10;
					BG.setLocation(x, y);
					repaint();
					if (y == -500) {
						t.stop();
						tfNome.setVisible(true);
						play.setVisible(true);
						repaint();
					}

				}

			}
		});
		
		t.start();
	}

	public void animation_transition() {

		play.setVisible(false);

		t = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				y -= 2;
				BG.setLocation(x, y);
				repaint();
				if (y == -1500) {
					t.stop();
					
				}

			}
		});

		t.start();
	}

}
